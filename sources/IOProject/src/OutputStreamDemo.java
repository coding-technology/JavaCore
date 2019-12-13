import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class OutputStreamDemo {

    public static void main(String[] args) {
        OutputStream out = null ;
        try {
             out = new FileOutputStream("d:/xyz.txt") ;
             out.write("helloworld123".getBytes()); //内存->xyz.txt
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
