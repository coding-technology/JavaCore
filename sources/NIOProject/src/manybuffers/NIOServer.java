package manybuffers;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/*
 * Created by 颜群
 */
public class NIOServer {
    public static void main(String[] args) throws  Exception{
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind( new InetSocketAddress(8888)) ;
        //定义两个缓冲区，接受客户端的数据
        ByteBuffer[] buffers = new ByteBuffer[2] ;
        buffers[0] = ByteBuffer.allocate(4) ;
        buffers[1] = ByteBuffer.allocate(8) ;
        int bufferSize = 4 + 8 ;//总容量

        SocketChannel socketChannel = serverSocketChannel.accept();
        System.out.println("服务端：接收到客户端连接。。。");
        while(true){//接收数据
            int totalRead = 0 ;//每一次实际使用
            //如果buffer没满，继续读
            while(totalRead < bufferSize){
                long read = socketChannel.read(buffers);
                totalRead += read ;
                System.out.println("【每一次】，实际读取到的数据大小："+read);
//                System.out.println("实际读取到的【总数据】大小："+totalRead);
            }
            //如果buffer已满， flip() ;
            for(ByteBuffer buffer:buffers){
                buffer.flip() ;
            }
        }

    }
}
