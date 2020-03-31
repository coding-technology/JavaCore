package micro_httpclient.config;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * Created by 颜群
 */
//@ConfigurationProperties(prefix = "http")
@Configuration//配置类，存放了很多实用HttpClient时需要的对象

public class MyHttpClientConfig {
    @Value("${http.maxTotal}")
    private Integer maxTotal ;
    @Value("${http.defaultMaxPerRout}")
    private Integer defaultMaxPerRout ;
    @Value("${http.connectTimeout}")
    private Integer connectTimeout ;
    @Value("${http.connectRequestTimeout}")
    private Integer connectRequestTimeout ;
    @Value("${http.socketTimeout}")
    private Integer socketTimeout ;


    //实例化连接池管理对象
    @Bean(name="httpClientConnectionManager")
    public PoolingHttpClientConnectionManager getHttpClientConnectionManager(){
        PoolingHttpClientConnectionManager manager =  new PoolingHttpClientConnectionManager() ;
        System.out.println( manager);
        System.out.println( maxTotal);

        manager.setMaxTotal( maxTotal );
        manager.setDefaultMaxPerRoute(defaultMaxPerRout);
        return manager ;
    }

    //将HttpClientBuilder对象放入Ioc容器
    @Bean(name="httpClientBuilder")
    public HttpClientBuilder getHttpClientBuilder(@Qualifier("httpClientConnectionManager") PoolingHttpClientConnectionManager manager ){
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        httpClientBuilder.setConnectionManager(manager) ;
        return httpClientBuilder ;
    }

    //manager manager->builder  builder ->HttpClient
    //将HttpClient对象放入Ioc容器
    @Bean
    public CloseableHttpClient getCloseableHttpClient(@Qualifier("httpClientBuilder") HttpClientBuilder builder){
        return builder.build();
    }


    //RequestConfig.Builder
    @Bean(name="builder")
    public RequestConfig.Builder getBuilder(){
        RequestConfig.Builder builder = RequestConfig.custom() ;
        return builder.setConnectTimeout(connectTimeout).setConnectionRequestTimeout(connectRequestTimeout).setSocketTimeout(socketTimeout);
    }

    @Bean(name="requestConfig")
    public RequestConfig getRequestConfig(@Qualifier("builder") RequestConfig.Builder builder ){
       return  builder.build() ;
    }



}
