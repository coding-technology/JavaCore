import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/*
 * Created by 颜群
 */
public class HttpClientDemo05 {
    public static void main(String[] args){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://dasai.lanqiao.cn/pages/dasai/news.html?id=19");
        HttpEntity result = null ;
        CloseableHttpResponse response = null ;
        try {
            httpPost.setHeader("Content-Type","text/html;charset=utf-8");



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
