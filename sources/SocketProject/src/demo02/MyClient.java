package demo02;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

/*
 * Created by 颜群
 */
public class MyClient {
    public static void main(String[] args) {
        Socket socket = null ;
        OutputStream out = null ;
        ObjectOutputStream oos = null ;
        try {
             socket = new Socket("localhost",8888) ;

            Student student = new Student(1001,"zs",23);

             out = socket.getOutputStream();
            //将OutputStream转为对象流
             oos = new ObjectOutputStream(out) ;
            oos.writeObject( student );//发送对象
            socket.shutdownOutput();



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
