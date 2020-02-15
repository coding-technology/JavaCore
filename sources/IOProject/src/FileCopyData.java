import java.io.*;
//二进制流
public class FileCopyData {
    //abc.txt->内存->xyz.txt
    public static void main(String[] args) {
        InputStream in = null ;

        OutputStream out = null ;
        InputStream dataInput = null ;
        OutputStream dataOutput = null ;
        try {
            //abc.txt->内存
             in = new FileInputStream("d:/IMG_6285.JPG") ;
              dataInput = new DataInputStream(in) ;//字节流->二进制流
             out = new FileOutputStream("e:/颜群微信3.jpg") ;
             dataOutput = new DataOutputStream(out );//字节流->二进制流
            //开辟10字节的内存
            byte []buf = new byte[10] ;
            int len = -1 ;

        while(   (len= dataInput.read(buf)) != -1){//in ->buf
            dataOutput.write(buf,0,len);//buf->out
        }
            System.out.println("成功");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(dataOutput !=null) dataOutput.close();
                if(dataInput !=null) dataInput.close();
                if(out !=null)out.close();
                if(in !=null) in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }
}
