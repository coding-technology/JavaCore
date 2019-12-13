import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class InputStreamDemo {
    public static void main(String[] args) {
        InputStream in = null ;
        try {
            in =   new FileInputStream(new File("d:/abc.txt"));
//            InputStream in = new FileInputStream("d://abc.txt");
            System.out.println(in.available());//file.length()
            byte[] buf = new byte[in.available()] ;
            in.read(buf);//将文件abc.txt内容读取到buf中
            //buf :byte[] ->String
            System.out.println(new String(buf));



        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
