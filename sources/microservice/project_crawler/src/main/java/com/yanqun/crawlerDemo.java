package com.yanqun;


import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.yanqun.entity.Course;
import com.yanqun.util.JsonUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import redis.clients.jedis.Jedis;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

/*
 * Created by 颜群
 */
public class crawlerDemo {
    private static Jedis jedis ;
    private static MongoClient mongoClient ;
    //读取配置文件
    static Configuration cfg ;
  static{
//        jedis = new Jedis("192.168.2.130",6379) ;
//        //0-15个数据库
//        jedis.select(2) ;

//         mongoClient = new MongoClient("127.0.0.1") ;
      cfg  = HBaseConfiguration.create() ;
  }

    //HttpClient :将https://www.lanqiao.cn/paths/的内容，拉去到本地
    public static String getData() {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //仅有一页数据
        HttpGet httpGet = new HttpGet("https://www.lanqiao.cn/paths/");

        //有多页数据
//        String htmlCommon = "https://www.bilibili.com/video/BV18s411u7EH?p=" ;
//        for(int i=1;i<100;i++){
//            HttpGet httpGet2 = new HttpGet(htmlCommon+i);
//            //解析
//        }

        HttpEntity entity = null;
        CloseableHttpResponse response = null;
        try {
            //发送http请求 （1.请求类型 get\post    2.网站）
            response = httpClient.execute(httpGet);


            //判断是否得到了 正常的影响
            String html = "" ;
            if (response.getStatusLine().getStatusCode() == 200) {
                //获取响应数据
                entity = response.getEntity();//响应数据
                //将响应数据以Html源码形式显示
                 html = EntityUtils.toString(entity, "UTF-8");

                System.out.println("----------蓝桥网站数据-----------");
//                System.out.println(html);
            }
            return html;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (response != null) response.close();
                if (httpClient != null) httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //使用Jsoup解析 httpclient爬取的数据
    public static  List<Course>  parseData(String html){
        List<Course> cs = new ArrayList<>() ;

        //将字符串 转为一个结构化数据 " xxxxxxxxxxxxxxxxxxxx" ->Dont
        //xml  :文件路径->document
        Document document = Jsoup.parse(html);

        //xpath解析
        Elements courses = document.select(".learn-path-container>div");

        for(Element course:courses){
            String courseName = course.select("a>div").first().text() ;
            String courseNum = course.select("a>div").last().text() ;//91门课程
            if(courseNum.indexOf("门") >-1){
                int num =  Integer.parseInt( courseNum.substring(0,courseNum.indexOf("门")));
                String imgPath  = course.select("a>img").attr("src");

                //截取图片后缀
                String suffix = imgPath.substring(  imgPath.lastIndexOf(".")   ) ;

                //下载图片到本地
                downloadFile(imgPath,"d://imgs",courseName+suffix);

                Course c = new Course(courseName,num,imgPath) ;
                cs.add(c) ;
            }
        }
        return cs ;
    }

    //通用下载工具方法
    /*
            urlStr:下载资源网络地址URL
            directory：下载到本地的目录地址
            fileName：下载到本地的文件名

     */
    public static void downloadFile(String urlStr,String diretory,String fileName){
        FileOutputStream out = null ;
        InputStream in = null ;
        try {
            //网络图片 以输入流方式 传入本地
            URL url = new URL(urlStr);
            URLConnection urlConnection = url.openConnection();
             in = urlConnection.getInputStream();
            byte[] buf = new byte[100] ;
            File dir = new File(diretory);
            if(!dir.exists()){
                dir.mkdir() ;
            }
             out = new FileOutputStream(diretory +"\\"+  fileName);
            int len = -1 ;
             while( (len = in.read(buf) ) !=-1 ){
                    out.write(buf,0,len);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally{

            try {
                out.close();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


    public static void writeRedis( List<Course> courses){
        try {
            for (Course course : courses) {
                //使用uuid模拟课程id

                //hset  表名  id  该id对应的数据
                String id = String.valueOf(UUID.randomUUID()  ) ;
                jedis.hset("course", id, JsonUtils.object2json(course));
                System.out.println(id);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    //将爬虫数据 存入mongodb
    //数据库：crawler
    //集合：course
    public static void writeMongodb( List<Course> courses){
        MongoDatabase database = mongoClient.getDatabase("crawler");
        //数据库->集合->文档
        MongoCollection<org.bson.Document> course = database.getCollection("course");


        for(Course c:courses){
            //c ->document
            Map<String,Object> map = new HashMap<>() ;
            map.put( "name", c.getName() );
            map.put("num",c.getNum());
            map.put( "img", c.getImgPath());
            org.bson.Document documet = new org.bson.Document(map) ;
            course.insertOne(documet);
        }
        mongoClient.close();
    }
    //hbase创建表
    public static void create(String tableName,String ...familyNames) throws Exception{
        Connection connection = ConnectionFactory.createConnection(cfg);
        Admin admin = connection.getAdmin();
        TableName tn = TableName.valueOf(tableName);
        if(admin.tableExists( tn )){//如果已经存在，则删除
            admin.disableTable(tn);
            admin.deleteTable(tn);
        }
        //创建表  表：增加1个列族、增加1个列族、增加1个列族
        HTableDescriptor htd = new HTableDescriptor(tn);
        for(String family:familyNames){
            htd.addFamily( new HColumnDescriptor( family)    )  ;
        }
        admin.createTable(htd);
        connection.close() ;
    }

    //增加数据
    public static void putHbase(String tableName,List<Course> courses) throws Exception {
        Connection connection = ConnectionFactory.createConnection(cfg);
        Table table = connection.getTable(TableName.valueOf(tableName));

        for(Course c:courses){
            //行键：课程名
            Put p = new Put(Bytes.toBytes( c.getName())  );
            //课程数量  （num:11）
            p.addColumn(  Bytes.toBytes(  "num")  ,Bytes.toBytes("")  , Bytes.toBytes( c.getNum()+"") ) ;
            //图片路径
            p.addColumn(Bytes.toBytes("imgPath"),Bytes.toBytes(""),Bytes.toBytes(c.getImgPath())) ;
            table.put(p);
        }

        connection.close() ;
    }




    public static void testReadMongodb(){
        MongoDatabase crawler = mongoClient.getDatabase("crawler");
        MongoCollection<org.bson.Document> course = crawler.getCollection("course");

        BasicDBObject bson = new BasicDBObject("num", new BasicDBObject("$gte",20)  ) ;
        FindIterable<org.bson.Document> documents = course.find(bson);
        for(org.bson.Document  d: documents){
            System.out.println("课程名:"+d.getString("name"));
            System.out.println("课程数量:"+d.getInteger("num"));
        }
    }
    public static void testReadRedis(){
        String course = jedis.hget("course", "3ab99b0e-1c4b-488b-9ac7-65eda44136a0");
        System.out.println(course);
    }

    //scan
    public static void testScanHabase(String tableName) throws Exception{
        Connection connection = ConnectionFactory.createConnection(cfg);
        Table tb = connection.getTable(TableName.valueOf(tableName));
        ResultScanner scanner = tb.getScanner(new Scan());
        for( Result row:  scanner){
            System.out.println("每一行数据:"+row);

            for(Cell cell:row.listCells()){
                System.out.println(
                        "rowskey:"+  Bytes.toString(  row.getRow()  )
                                +
                                "family:"  +Bytes.toString(  CellUtil.cloneFamily(cell))
                                +
                                "value:" +Bytes.toString(  CellUtil.cloneValue(cell))

                );
            }
        }
    }
    public static void main(String[] args) throws Exception{

        while(true){
            System.out.println("11");
            Thread.sleep(3000);
        }
        //获取数据
//        String html = getData() ;
//        System.out.println(html);

        //解析数据
//        List<Course> courses = parseData(html);
        //写入redis
//        writeRedis(courses);
        //写入mongodb
//        writeMongodb(courses) ;
        //创建hbase表
//        create("course", "name", "num","imgPath");
        //写入hbase
//        putHbase("course",courses) ;


//        System.out.println(courses);
        //从redis中读取数据
//        testReadRedis();

        //从mongodb中读取数据
//        testReadMongodb() ;

        //从hbase中读取数据
//        testScanHabase("course") ;

        //存储数据
        //使用Jdbc、框架技术 进行存储
    }
}
