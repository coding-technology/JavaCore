package demo05;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/*
 * Created by 颜群
 */
public class URLDemo {
    public static void main(String[] args) {
        InputStream in = null ;
        URLConnection urlConnection = null;
        OutputStream out = null ;
        try {
            URL url = new URL("https://www.163.com/") ;
             urlConnection = url.openConnection();
             in = urlConnection.getInputStream();

             out = new FileOutputStream("d:\\163.txt");
            byte[] buf = new byte[64] ;
            int len = -1 ;
            while(  (len = in.read(buf)) != -1 ){
                out.write(buf,0,len);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
               if(out!=null) out.close();
                if(in!=null)  in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }



        }

    }
}
