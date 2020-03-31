package demo03;

import java.io.*;
import java.net.Socket;

/*
 * Created by 颜群
 */
public class ServerThread  extends  Thread{

    Socket socket  ;
    public ServerThread(Socket socket){
      this.socket = socket;
    }

    @Override
    public void run() {
        InputStream in =null ;
        ObjectInputStream ois  = null ;
        OutputStream out = null ;
        try {
            //接受客户端数据（客户端—>服务端）
             in = socket.getInputStream();
             ois  = new ObjectInputStream(in) ;
            Student student =  (Student)ois.readObject() ;
            System.out.println(student);
            socket.shutdownInput();

            //给客户端反馈(服务端->客户端)
             out = socket.getOutputStream();
            out.write("已收到....".getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            try {
                out.close();
                ois.close();
                in.close();
                socket.close(); ;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
