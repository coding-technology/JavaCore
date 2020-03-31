package demo04;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/*
 * Created by 颜群
 */
public class Receive {

    public static void main(String[] args) {
        DatagramSocket ds = null ;
        byte[] data = new byte[64] ;
        //准备接收数据的对象
        DatagramPacket dp = new DatagramPacket(data , data.length) ;
        //接收数据
        try {
             ds = new DatagramSocket(9999  );
            ds.receive( dp );

            //显示接收到的数据
            String receiveData  = new String(dp.getData(), 0, data.length);
            System.out.println("接收到的数据："+receiveData);
            System.out.println("显示发送方的信息：" +  dp.getAddress().getHostAddress() );




        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            ds.close();
        }

    }
}
