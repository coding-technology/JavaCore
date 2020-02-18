package chat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/*
 * Created by 颜群
 */
public class ChatClient {
    public static void main(String[] args) {
        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false) ;
            Selector selector = Selector.open();
            //注册“连接事件”
            socketChannel.register(  selector  , SelectionKey.OP_CONNECT) ;

            int[] ports = {7777,8888,9999} ;
            int port = ports[ (int)(Math.random()*3) ] ;
            socketChannel.connect(new InetSocketAddress("127.0.0.1",port)) ;

            while(true){
                selector.select() ;
                //selectionKeys：包含了所有的事件
                Set<SelectionKey> selectionKeys = selector.selectedKeys();

                Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
                while(keyIterator.hasNext()  ){

                    SelectionKey selectionKey = keyIterator.next();//每个事件
                    //真实的发生“连接事件”
                    if(selectionKey.isConnectable()){ //连接完毕？接收（读）、发送（写）
                        //buffer  + channel
                        ByteBuffer sendBuffer = ByteBuffer.allocate(1024);
                        SocketChannel clientChannel =  (SocketChannel)selectionKey.channel();


                        if(clientChannel.isConnectionPending()){//正在连接

                            if(clientChannel.finishConnect()){
                                System.out.println("连接服务端成功,连接的端口是："+port);
                                //向服务端 发送一条测试数据
                                sendBuffer.put("connecting".getBytes()) ;
                                sendBuffer.flip() ;
                                clientChannel.write(sendBuffer) ;
                            }
                        }
                        //在客户端看来，“写操作”不需要注册到通道中，再去使用?

                        //客户端，每次写操作，创建一个线程
                        new Thread( ()->{
                            try {
                                sendBuffer.clear();
                                //写数据： 接收用户从控制台输入的内容
                                InputStreamReader reader = new InputStreamReader(System.in);
                                BufferedReader bReader = new BufferedReader(reader);
                                String message = bReader.readLine();

                                sendBuffer.put(message.getBytes()) ;
                                sendBuffer.flip() ;
                                clientChannel.write( sendBuffer) ;

                                //发送数据
                            }catch (Exception e){
                                e.printStackTrace();
                            }

                        }    ) .start();


                        //发送数据（写）
                        clientChannel.register(  selector,SelectionKey.OP_READ) ;
                    }else if(selectionKey.isReadable()  ){//读
                        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                        SocketChannel clientChannel =  (SocketChannel)selectionKey.channel();
                        int len = clientChannel.read(readBuffer);//读
                        if(len>0){
                            String receive = new String(readBuffer.array(), 0, len);
                            System.out.println(receive);
                        }
                    }
                }

                selectionKeys.clear();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
