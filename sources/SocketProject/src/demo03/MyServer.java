package demo03;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * Created by 颜群
 */
public class MyServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8888) ;

            while(true) {
                Socket socket = serverSocket.accept();//阻塞，接受客户端请求
                ServerThread serverThread = new ServerThread(socket) ;
                serverThread.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
