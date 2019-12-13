import java.io.*;

public class FileCopyCharactor {
    public static void main(String[] args) {
        //文件->内存（Reader）
        Reader reader = null ;
        Writer writer = null ;
        try {
             reader = new FileReader("d:/个人介绍.txt");
             writer = new FileWriter("d:/个人完整介绍.txt") ;

            char[] buf = new char[4];
            StringBuffer sb = new StringBuffer() ;

            int len = -1 ;
            while((len =reader.read(  buf)) != -1 ){
                // str += buf ;
                sb.append(buf,0,len) ;//将每次读取到的 4个字符 拼接起来
            }

            System.out.println(sb);

            //在内存中 替换占位符
            String content = sb.toString() ;
            content= content.replace("{name}","颜群")
                    .replace("{enterprise}","蓝桥学院")
                    .replace("{weixin}","157468995");

            //将替换后的内容 输出到文件   ，内存 ->文件（Writer）
            writer.write(content);
            System.out.println("成功...");

//            writer.flush(); 将管道中的数据 刷出到 文件中

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
               if(writer!=null) writer.close();
                if(reader!=null) reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }







    }
}
