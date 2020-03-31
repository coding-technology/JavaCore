package demo05;

import java.net.InetAddress;
import java.net.UnknownHostException;

/*
 * Created by 颜群
 */
public class InetAddressDemo {

    public static void main(String[] args) {
        InetAddress host = null;
        try {
            //本机
            host = InetAddress.getLocalHost();
            System.out.println(host);
            //网络中任意一台
            InetAddress host163 = InetAddress.getByName("www.163.com");
            System.out.println(host163);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }
}
