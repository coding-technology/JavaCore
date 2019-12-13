import java.io.*;

public class FileCopyCharactorBuffered {
    public static void main(String[] args) {
        //文件->内存（Reader）
        Reader reader = null ;
        Writer writer = null ;

        BufferedReader br = null ;
                BufferedWriter bw = null ;

        try {
             reader = new FileReader("d:/个人介绍.txt");
             writer = new FileWriter("d:/个人完整介绍2.txt") ;


             br = new BufferedReader( reader) ;
             bw = new BufferedWriter( writer);

            StringBuffer sb = new StringBuffer() ;

            String line = null ;

            while( (line= br.readLine()) != null   ){
                sb.append(line) ;
            }

            System.out.println(sb);

            //在内存中 替换占位符
            String content = sb.toString() ;
            content= content.replace("{name}","颜群")
                    .replace("{enterprise}","蓝桥学院")
                    .replace("{weixin}","157468995");

            //将替换后的内容 输出到文件   ，内存 ->文件（Writer）


            bw.write(content);
            System.out.println("成功...");

//            writer.flush(); 将管道中的数据 刷出到 文件中

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

            /*
            *    1先关出，再关入
            *    2从外往内关  br外 = new BufferedReader( reader内) ;
            * */
            try {
               if(bw != null) bw.close();
              if(br!=null )  br.close();
               if(writer!=null) writer.close();
                if(reader!=null) reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }







    }
}
