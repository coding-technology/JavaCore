package demo01;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * Created by 颜群
 */
public class MyServer {

    public static void main(String[] args) {
//        1.准备服务.ip:默认本机127.0.0.1,端口8888
        ServerSocket serverSocket = null ;
        Socket socket = null ;
        InputStream in = null ;
        BufferedReader reader = null ;
        OutputStream out = null ;
        try {
             serverSocket = new ServerSocket(8888) ;
            System.out.println("服务器启动");
            //准备完毕，可以监听客户端请求
             socket = serverSocket.accept();//一直阻塞，直到有客户端连接
            System.out.println("服务端检测到客户端连接成功！");
            

            //  2.通过socket生成inputstream/outputstream（准备发送数据）
             in = socket.getInputStream();
            //带缓冲区的字符流（字节流-转换流-字符流）
             reader = new BufferedReader(new InputStreamReader(in));
            String info = null ;
            while(  (info=reader.readLine()) != null       ){
                System.out.println("I am server,接受到客户端信息是：" + info);
            }

            socket.shutdownInput();



            //服务端做出反馈
             out = socket.getOutputStream();
            out.write("welcome  client....".getBytes());

        socket.shutdownOutput();




        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
               if(reader !=null) reader.close();
                if(out !=null) out.close();
                if(in !=null) in.close();
                if(socket !=null) socket.close();
                if(serverSocket !=null) serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//

//
//        3.使用inputstream/outputstream进行发送、接受数据
//
//        4.关闭inputstream/outputstream、socket


    }
}
