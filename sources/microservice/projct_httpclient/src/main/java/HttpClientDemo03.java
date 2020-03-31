import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import java.io.IOException;

/*
 * Created by 颜群
 */
public class HttpClientDemo03 {
    public static void main(String[] args){
        //每次都是new一个新的httpClient对象
//        CloseableHttpClient httpClient = HttpClients.createDefault();

        //创建连接池
        PoolingHttpClientConnectionManager pool = new PoolingHttpClientConnectionManager();
        pool.setMaxTotal(100);//设置连接池中 最多可以存放100个httpClient连接
        pool.setDefaultMaxPerRoute(20);//最多有20个httpclient对象可以访问同一个网站

        //从连接池中获取httpClient对象
        CloseableHttpClient httpClient =  HttpClients.custom().setConnectionManager( pool).build() ;

        HttpGet httpGet = new HttpGet("http://dasai.lanqiao.cn/pages/dasai/news.html?id=19");
        HttpEntity entity = null ;
        CloseableHttpResponse response = null ;

        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(3000) //创建连接
                .setConnectionRequestTimeout(5000)//连接服务器时间
                .setSocketTimeout(10000).build();//数据传输时间

        httpGet.setConfig( requestConfig  );

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
