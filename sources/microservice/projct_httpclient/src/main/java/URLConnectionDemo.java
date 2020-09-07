import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Created by 颜群
 */
public class URLConnectionDemo {

    public static String getResource(){
        BufferedReader reader = null ;
        StringBuffer html = new StringBuffer();
        try {
            URL url = new URL("https://www.lanqiao.cn/");//如果某个网站无法爬取，则更换网址，或者终止爬取
            //获取网页的html源码
            URLConnection urlConnection = url.openConnection();
            urlConnection.connect();
            reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));  //字节流 ->转换流-> 字符串(字符流)->带缓冲区的字符流

            String line = null;
            while ((line = reader.readLine()) != null) {
                html.append(line);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            {
                try {
                    if(reader != null)  reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
            return html.toString()  ;
    }

    public static String parseResource(String html){
        //通过正则表达式+字符串解析 从html中，解析出需要的数据：将网站的简介（description）进行提取
        /*
            .: 除了换行符以外的任何字符
            + ： 匹配之前的一次，或多次
            ? ：0次或1次

            html :    aaa<div ccc >xxxxx</div>bbbb
             (.+):贪婪模式  :  <.+>  :  <div ccc >xxxxx</div>
             (.+?) :惰性模式 :   <.+?> :  <div ccc >
         */
        Pattern pattern = Pattern.compile( "meta data-n-head=\"ssr\" data-hid=\"description\" name=\"description\" itemprop=\"description\" content=\"(.+?)\"" ) ;
        //从html中，只提取 符合patter约束的字符串
        Matcher matcher = pattern.matcher(  html) ;

        String result = null ;
        if(matcher.find()){//判断是否存在 符合约束的字符串
            /*
                hellozhangsan
                Pattern.compile("hel(loz)han(gsa)n")
                 group(0):整个字符串
                 group(1):
                 group(2)
             */
            result =   matcher.group(0);  //提取
            result = result.substring(result.indexOf("content=") +  "content=".length());
        }
        return result ;
    }


    public static void main(String[] args) {

        String html = getResource();

        String result = parseResource(html) ;

        System.out.println(result==null? "爬取失败":result);

    }
}
