package demo04;

import java.io.IOException;
import java.net.*;

/*
 * Created by 颜群
 */
public class Send {
    public static void main(String[] args) {
        DatagramSocket ds = null ;
        //创建一个InetAddress对象
        InetAddress ip = null ;
        try {
             ip = InetAddress.getByName("127.0.0.1");


        String msg = "helloserver.." ;
        DatagramPacket dp = new DatagramPacket(msg.getBytes(),msg.length(),ip,9999) ;
         ds = new DatagramSocket() ;
        ds.send(  dp );
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            ds.close();
        }
    }
}
