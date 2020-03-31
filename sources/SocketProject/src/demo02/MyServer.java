package demo02;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * Created by 颜群
 */
public class MyServer {
    public static void main(String[] args) {
        ServerSocket serverScoket = null ;
        Socket socket = null ;
        InputStream in = null ;
        ObjectInputStream ois = null ;
        try {
             serverScoket = new ServerSocket( 8888) ;
             socket = serverScoket.accept();

            //接受客户端发来的对象
             in = socket.getInputStream();
            //对象流
             ois = new ObjectInputStream(in);
            try {
                Student student = (Student)ois.readObject();//读取对象
                System.out.println(student);
                socket.shutdownInput();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
               if(ois!=null) ois.close();
                if(in!=null)   in.close();
                if(socket!=null)  socket.close();
                if(serverScoket!=null)  serverScoket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}