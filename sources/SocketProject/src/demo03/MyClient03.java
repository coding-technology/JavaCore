package demo03;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

/*
 * Created by 颜群
 */
public class MyClient03 {
    public static void main(String[] args) {
        Socket socket = null ;
        OutputStream out = null ;
        ObjectOutputStream oos = null ;
        try {
             socket = new Socket("localhost",8888) ;

            Student student = new Student(1003,"ww",25);

             out = socket.getOutputStream();
            //将OutputStream转为对象流
             oos = new ObjectOutputStream(out) ;
            oos.writeObject( student );//发送对象
            socket.shutdownOutput();

            //接受服务端的反馈
            InputStream in = socket.getInputStream();
            byte[] buf = new byte[100] ;
            in.read(buf) ;
            System.out.println("接收到的服务端反馈:" + new String(buf)      );


        } catch (IOException e) {
            e.printStackTrace();
        }
        finally{
            try {
                if(oos!=null) oos.close();
                if(out!=null)   out.close();
                if(socket!=null)  socket.close();
            }catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
