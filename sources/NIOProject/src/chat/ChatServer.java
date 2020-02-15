package chat;

import com.sun.org.apache.bcel.internal.generic.Select;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/*
 * Created by 颜群
 */
public class ChatServer {
    private static Map<String,SocketChannel> clientsMap = new HashMap();
    public static void main(String[] args) throws  Exception {

       int[] ports =  new int[]{7777,8888,9999} ;
       //创建一个选择器
        Selector selector = Selector.open();

        for(int port:ports){
           ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
           serverSocketChannel.configureBlocking(false) ;//通道的阻塞模式
           ServerSocket serverSocket = serverSocketChannel.socket();
           serverSocket.bind(  new InetSocketAddress(port));
            // 标识selector感兴趣的事件：接收客户端连接
           serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT ) ;
       }

        while(true ){
            selector.select() ;//一直阻塞，直到选择器上有就绪的事件
            //selectedKeys(): 获取 通道 和选择器之间的事件。 获取 选择器 对通道的哪些事件感兴趣。
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keys.iterator();

            while(iterator.hasNext()){//获取所有的事件
               SocketChannel clientChannel ;//客户端和服务端交互的通道
                SelectionKey selectedKey = iterator.next();//selectedKey:选择器和通道之间的 每一个感兴趣的事件
                if(selectedKey.isAcceptable()) {  //连接准备就绪
                    ServerSocketChannel   channel = (ServerSocketChannel)selectedKey.channel();
                    clientChannel = channel.accept() ;//连接就绪 的channel
                    clientChannel.configureBlocking(false) ;

                    //增加感兴趣的事件：读事件
                    clientChannel.register(selector, SelectionKey.OP_READ   ) ;
                    //给每个用于设置一个 唯一标志符  key(String) -value(SocketChannel)
                    //唯一标志符：是一个 key+四位随机数
                    String key = "key" + (int)(Math.random()*9000 +1000 ) ;
                    clientsMap.put(key, clientChannel ) ;
                }else if(selectedKey .isReadable() ){//读就绪
                    clientChannel =  (SocketChannel) selectedKey.channel() ;
                    ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                    int result =-1 ;
                    String receive = null ;
                    try {
                        result = clientChannel.read(readBuffer);//将客户端发来的数据 存储到readBuffer中
                    }catch (IOException e){//正在读的时候，如果用户退出（断开连接）
                        //谁退出了？key  ,找到 退出的key   map(key, clientChannel )
                        String clientKey = getClientKey(clientChannel  )  ;
                        System.out.println("客户端："+clientKey+"退出聊天室");
                        clientsMap.remove(clientKey) ;
                        clientChannel.close();
                        selectedKey.cancel();
                        continue ;
                    }

                    if(result >0 ){
                        readBuffer.flip() ;
                        Charset charset = Charset.forName("utf-8") ;
                        receive =  String.valueOf(  charset.decode(readBuffer ).array()   ) ;
                        System.out.println(  clientChannel +":"+ receive);

                        if("connecting".equals(receive)){
                            receive = "新客户端上线" ;
                        }

                        //将本次 读通道中的数据，加入到其他通道中
                        selectedKey.attach(receive) ;

                        //感兴趣的事件：写事件
                        selectedKey.interestOps(  SelectionKey.OP_WRITE   )   ;
                    }
                //...
                }else if(selectedKey.isWritable()) {//写数据

                    clientChannel =   (SocketChannel) selectedKey.channel();
                    /*将接收到的消息 广播出去。  例如，张三在聊天室发了一句“hello"，服务端 需要将这个
                    "hello"再广播 所有的聊天室用户  。形式：    张三(key)： hello(SocketChannel)
                    */
                    String key = getClientKey( clientChannel ) ;
                    //广播发送给全部的聊天室用户
                    for(  Map.Entry<String,SocketChannel> entry: clientsMap.entrySet()){
                        SocketChannel eachClient = entry.getValue();//每个用户
                        ByteBuffer broadCastBuffer = ByteBuffer.allocate(1024) ;
                        broadCastBuffer.put((key+":"  + selectedKey.attachment() ).getBytes() ) ;
                        broadCastBuffer.flip() ;
                        eachClient.write( broadCastBuffer ) ;
                    }

                    selectedKey.interestOps(SelectionKey.OP_READ);

                }else{
                    System.out.println("other...");
                }
            }
            keys.clear();
        }
    }


    public static String getClientKey(SocketChannel  clientChannel  ){
        String key = null ;
        //n个人，n个clientChannel
        /*
                key1  :n个clientChannel-1
                key2  :n个clientChannel-2
                key13  :n个clientChannel-3
         */
        Set<Map.Entry<String, SocketChannel>> entries = clientsMap.entrySet();
        for( Map.Entry<String,SocketChannel> entry:entries){
            if(entry.getValue() ==clientChannel ){
                key = entry.getKey() ;
                break ;
            }
        }
        return key;
    }
}
