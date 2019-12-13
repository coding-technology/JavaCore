import java.io.*;

public class FileCopy {
    //abc.txt->内存->xyz.txt
    public static void main(String[] args) {
        InputStream in = null ;
        OutputStream out = null ;
        try {
            //abc.txt->内存
             in = new FileInputStream("d:/IMG_6285.JPG") ;

             out = new FileOutputStream("e:/颜群微信.jpg") ;
            //开辟10字节的内存
            byte []buf = new byte[10] ;
            int len = -1 ;

        while(   (len= in.read(buf)) != -1){//in ->buf
            out.write(buf,0,len);//buf->out
        }
            System.out.println("成功");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(out !=null)out.close();
                if(in !=null) in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }
}
