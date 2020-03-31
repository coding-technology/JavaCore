import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 * Created by 颜群
 */
public class HttpClientDemo02 {
    public static void main(String[] args){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://dasai.lanqiao.cn/pages/dasai/news.html");
        HttpEntity result = null ;
        CloseableHttpResponse response = null ;
        try {

            /*
                id:1
                name:zhangsan
                age:23
             */
//            NameValuePair (实现类BasicNameValuePair)
            BasicNameValuePair param1 = new BasicNameValuePair("id","19");
//            BasicNameValuePair param2 = new BasicNameValuePair("name","zhangsan");
//            BasicNameValuePair param3 = new BasicNameValuePair("age","23");
            List<BasicNameValuePair> params = new ArrayList<>() ;
            params.add(param1);
//            params.add(param2);
//            params.add(param3);

            UrlEncodedFormEntity httpEntity = new UrlEncodedFormEntity(params,"UTF-8");
            //设置请求的参数
            httpPost.setEntity(  httpEntity);//参数类型是HttpEntity (具体实现类UrlEncodedFormEntity)



            //发送http请求 （1.请求类型 get\post    2.网站）
             response = httpClient.execute(httpPost);
            //判断是否得到了 正常的影响
            if (response.getStatusLine().getStatusCode() == 200) {
                //获取响应数据

                 result = response.getEntity();//响应数据
                //将响应数据以Html源码形式显示
                String html = EntityUtils.toString(result, "UTF-8");

                System.out.println(result);
                System.out.println(html);

            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {

            try {
               if(response!=null) response.close();
              if(httpClient!=null)  httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
