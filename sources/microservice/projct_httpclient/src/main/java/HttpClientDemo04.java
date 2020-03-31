import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/*
 * Created by 颜群
 */
public class HttpClientDemo04 {
    public static void main(String[] args)throws Exception{
//        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        //http://dasai.lanqiao.cn/pages/dasai/news.html?id=19
        List<NameValuePair> nvps = new ArrayList<>() ;
        nvps.add(new BasicNameValuePair("id","19"));

        URI uri = new URIBuilder().setScheme("http").setHost("dasai.lanqiao.cn/pages/dasai/news.html").setPort(80).setParameters(nvps  ).build();
//        URI url = URI.create("") ;


        HttpGet httpGet = new HttpGet(uri);


        HttpEntity entity = null ;
        CloseableHttpResponse response = null ;
        try {
            //发送http请求 （1.请求类型 get\post    2.网站）
             response = httpClient.execute(httpGet);
            //判断是否得到了 正常的影响
            if (response.getStatusLine().getStatusCode() == 200) {
                //获取响应数据
                 entity = response.getEntity();//响应数据
                //将响应数据以Html源码形式显示
                String html = EntityUtils.toString(entity, "UTF-8");

                System.out.println(entity);
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
