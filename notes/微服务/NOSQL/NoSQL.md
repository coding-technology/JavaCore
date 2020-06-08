# NoSQL数据库

NoSQL: not only sql

Redis:分布式数据库

Hbase: 大数据领域的数据库(hadoop)

neo4j:图形化数据库

mongodb:分布式文档存储的数据库

# 1.Redis

全称：remote dictionary server，基于key-value结构 。将数据存储在内存 （硬盘，rdb/aof）,写速度10w/s。

（1）缓存

​	java -redis - db

（2）存储

### redis 环境搭建

部署环境:linux（centos7）

在centos7中执行：wget http://download.redis.io/releases/redis-6.0.1.tar.gz

解压tar -zxvf redis-6.0.1.tar.gz

重命名mv redis-6.0.1 redis

编译:进入redis目录：执行make

> 说明：如果执行make报错：/bin/sh: cc: command not found
>
> 解决： yum install gcc-c++
>
> 如果安装cc后仍然报错：fatal error: jemalloc/jemalloc.h: No such file or directory
>
> 解决： make  MALLOC=libc
>
> 如果报错： error: â€˜struct redisServerâ€™ has no member named â€˜supervised_modeâ€
>
> 解决：可能是gcc版本太低，升级gcc:
>
> yum -y install centos-release-scl yum -y install devtoolset-9-gcc devtoolset-9-gcc-c++ devtoolset-9-binutils scl enable devtoolset-9 bash
>
> 再试： make  MALLOC=libc

配置：redis.conf：  daemonize  yes  (理解为：开启redis后，可以同时去操作其他事情)

测试：

服务端：redis默认端口6379

客户端

![img](file://D:/github/JavaCore/notes/%E5%BE%AE%E6%9C%8D%E5%8A%A1/NOSQL/nosql.assets/1588556842280.png?lastModify=1588990738)



### redis常见操作

注意事项： 

1.不区分大小写 hello HELLO

2.单位: k != kb  

k:1000  kb:1024  

3.默认提供了16个数据库（编号0-15） select 0

### String(默认)

http://www.redis.cn/

```
set key value  [EX 秒][px 毫秒]  [NX不存在][XX存在] 
```

案例：

```
127.0.0.1:6379> select 0
OK
127.0.0.1:6379> set name zs
OK
127.0.0.1:6379> get name
"zs"
127.0.0.1:6379> strlen name
(integer) 2
127.0.0.1:6379> getrange name 0 1
"zs"
127.0.0.1:6379> set name zhangsan
OK
127.0.0.1:6379> getrange name 0 1
"zh"
127.0.0.1:6379> mset k1 v1 k2 v2 
OK
127.0.0.1:6379> get k1
"v1"
127.0.0.1:6379> mget k1 k2 k3
1) "v1"
2) "v2"
3) (nil)
127.0.0.1:6379> setex address 10 xa
OK
127.0.0.1:6379> get address
(nil)
127.0.0.1:6379> setex address 10 xa
OK
127.0.0.1:6379> get address
"xa"
127.0.0.1:6379> get address
"xa"
127.0.0.1:6379> get address
"xa"
127.0.0.1:6379> get address
"xa"
127.0.0.1:6379> get address
"xa"
127.0.0.1:6379> get address
"xa"
127.0.0.1:6379> get address
"xa"
127.0.0.1:6379> get address
"xa"
127.0.0.1:6379> get address
"xa"
127.0.0.1:6379> get address
"xa"
127.0.0.1:6379> get address
(nil)
127.0.0.1:6379> setex address 10 xa
OK
127.0.0.1:6379> ttl address
(integer) 4
127.0.0.1:6379> ttl address
(integer) 2
127.0.0.1:6379> ttl address
(integer) 1
127.0.0.1:6379> ttl address
(integer) -2
127.0.0.1:6379> ttl address
(integer) -2
127.0.0.1:6379> ttl address
(integer) -2
127.0.0.1:6379> setnx age 23
(integer) 1
127.0.0.1:6379> setnx age 23
(integer) 0
127.0.0.1:6379> setnx age 23
(integer) 0
127.0.0.1:6379> set num 10
OK
127.0.0.1:6379> incr num
(integer) 11
127.0.0.1:6379> incr num
(integer) 12
127.0.0.1:6379> decr num
(integer) 11
127.0.0.1:6379> decr num
(integer) 10
127.0.0.1:6379> incrby num 100
(integer) 110
127.0.0.1:6379> decrby num 50
(integer) 60
127.0.0.1:6379> set name lisi ex 10 nx
(nil)
127.0.0.1:6379> get name
"zhangsan"
127.0.0.1:6379> set name lisi ex 10 xx
OK
127.0.0.1:6379> set name wangwu 
OK
```



### List操作



```
127.0.0.1:6379> lpush names zs ls ww
(integer) 3
127.0.0.1:6379> rpush names zhangsan lisi wangwu
(integer) 6
127.0.0.1:6379> lrange names 0 -1
1) "ww"
2) "ls"
3) "zs"
4) "zhangsan"
5) "lisi"
6) "wangwu"
127.0.0.1:6379> clear
127.0.0.1:6379> lrange names 0 -1
1) "ww"
2) "ls"
3) "zs"
4) "zhangsan"
5) "lisi"
6) "wangwu"
127.0.0.1:6379> lrange names 0 3
1) "ww"
2) "ls"
3) "zs"
4) "zhangsan"
127.0.0.1:6379> ltrim names 0 2
OK
127.0.0.1:6379> lrange names 0 -1
1) "ww"
2) "ls"
3) "zs"
127.0.0.1:6379> lset names 1 QQ
OK
127.0.0.1:6379> lrange names 0 -1
1) "ww"
2) "QQ"
3) "zs"
127.0.0.1:6379> linsert names before QQ WW
(integer) 4
127.0.0.1:6379> lrange names
(error) ERR wrong number of arguments for 'lrange' command
127.0.0.1:6379> lrange names 0 -1
1) "ww"
2) "WW"
3) "QQ"
4) "zs"
127.0.0.1:6379> linsert names after  QQ PP
(integer) 5
127.0.0.1:6379> lrange names 0 -1
1) "ww"
2) "WW"
3) "QQ"
4) "PP"
5) "zs"
```





### Set

list元素可以重复，set不能重复;set是无序的,list是有序的。

sadd set h1 h2 h3



srandmember set 1 :随机获取1个元素（不删除）



spop set 1 :随机获取1个元素（删除）

交集：

​	sinter set set2

差集：

 sdiff set set2

并集：

​	 sunion set set2

### SortedSet

解决set因无序 而无法遍历的问题：sortedset增加了一个score字段。



```
127.0.0.1:6379> zadd persons  8 zs 9 ls 10 ww
(integer) 3
127.0.0.1:6379> zrange persons 1 2
1) "ls"
2) "ww"
127.0.0.1:6379> zrange persons 1 2 withscores
1) "ls"
2) "9"
3) "ww"
4) "10"
127.0.0.1:6379> zrerange persons 0 2
(error) ERR unknown command `zrerange`, with args beginning with: `persons`, `0`, `2`, 
127.0.0.1:6379> zrevrange persons 0 2
1) "ww"
2) "ls"
3) "zs"
127.0.0.1:6379> zrevrange persons 0 2 withscores
1) "ww"
2) "10"
3) "ls"
4) "9"
5) "zs"
6) "8"
127.0.0.1:6379> zrangebyscore persons 8 9
1) "zs"
2) "ls"
127.0.0.1:6379> zrangebyscore persons (8  (10
1) "ls"
127.0.0.1:6379> zrangebyscore persons 8 10 withscores limit 1 1 
1) "ls"
2) "9"
127.0.0.1:6379> zrangebyscore persons 8 10 withscores limit 1 2
1) "ls"
2) "9"
3) "ww"
4) "10"
127.0.0.1:6379> zrem person ls
(integer) 0
127.0.0.1:6379> zrem persons ls
(integer) 1
127.0.0.1:6379> zcount person  8 10
(integer) 0
127.0.0.1:6379> zcount persons  8 10
(integer) 2
127.0.0.1:6379> zrank persons zs
(integer) 0
127.0.0.1:6379> zrank persons ww
(integer) 1
127.0.0.1:6379> zscore persons ww
"10"
127.0.0.1:6379> zscore persons zs
```



### Hash

person(name,age)

person.setName("zs")  :  

hset  p name zs

hmset p name ls age 23



```
127.0.0.1:6379> hset  p name zs
(integer) 1
127.0.0.1:6379> hmset p name ls age 23
OK
127.0.0.1:6379> hget p name
"ls"
127.0.0.1:6379> hmget p name age
1) "ls"
2) "23"
127.0.0.1:6379> hkeys p
1) "name"
2) "age"
127.0.0.1:6379> hvals p
1) "ls"
2) "23"
127.0.0.1:6379> hgetall p
1) "name"
2) "ls"
3) "age"
4) "23"
127.0.0.1:6379> hdel p name age
(integer) 2
127.0.0.1:6379> hincrby p age 1.5
(error) ERR value is not an integer or out of range
127.0.0.1:6379> hincrby p age 1
(integer) 1
127.0.0.1:6379> hgetall p
1) "age"
2) "1"
127.0.0.1:6379> hincrby p age 10
(integer) 11
127.0.0.1:6379> hget all p
(nil)
127.0.0.1:6379> hgetall p
1) "age"
2) "11"
127.0.0.1:6379> hincrybyfloat p age 1.5
(error) ERR unknown command `hincrybyfloat`, with args beginning with: `p`, `age`, `1.5`, 
127.0.0.1:6379> hincrbyfloat p age 1.5
"12.5"
127.0.0.1:6379> hdecrbyfloat p age 1.5
(error) ERR unknown command `hdecrbyfloat`, with args beginning with: `p`, `age`, `1.5`, 
127.0.0.1:6379> hincrbyfloat p age -1.5
"11"
```



### key及其他操作

expire k1

pexpire k1

keys *

keys k?

type k1

flushdb:清空当前数据库  select 1

flushall：清空全部数据(慎用)



## Java 操作Redis：  Jedis

java - Jedis - Redis

配置步骤：

1.设置redis服务器地址

redis.conf中配置服务器的地址：  bind 192.168.2.130  127.0.0.1  (注意，在第二个bind中配置)

2.引入jedis依赖

（1）直接引入jedis-2.9.1.jar

  (2)通过maven引入

3.使用

```
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * Created by 颜群
 */
public class JedisDemo {
    public static void main(String[] args) throws  Exception {
    //java - redis
        Jedis jedis = new Jedis("192.168.2.130",6379) ;


        System.out.println(  jedis.ping( ) );
        //String
        jedis.select(0) ;
        jedis.flushDB() ;
        jedis.set("kj1","vj1234567");
        jedis.setnx("kj2","vj2");
        String r = jedis.getrange("kj1",2,4) ;
        String v = jedis.get("kj1");
        System.out.println(v);
        System.out.println(r);

        //数字
        jedis.set("n","1") ;
        jedis.incr("n")     ;
        jedis.incr("n")     ;
        jedis.incr("n")     ;
        jedis.decr("n") ;
        jedis.incrBy("n",10) ;
        jedis.decrBy("n",3) ;
        System.out.println(  jedis.get("n")   );

        //key
        Boolean f = jedis.exists("n");
        System.out.println(f);
        String type = jedis.type("n");
        System.out.println(f);
        System.out.println(type);
        jedis.expire("n",100) ;

//        Thread.sleep(3000);
        System.out.println(  jedis.ttl("n") );

        jedis.del("n","kj1") ;

        //list
        jedis.lpush("list1","a","b","c") ;
//        jedis.lpush("list1","hello","world") ;
        System.out.println("----");
        jedis.lpush("list2","listv1","listv2","list2v3") ;
        jedis.lset("list1",1,"QQ") ;//"v1","QQ","v3"


//        jedis.lrem("list1",2,"v1") ;
//        jedis.ltrim( "list2", 0,1  );//只保留第0、第1个元素，其他全部删除

        List<String> list1 = jedis.lrange("list1", 0, -1);
        for(String e :list1){
            System.out.println(e);
        }

        System.out.println("----");
        List<String> list2 = jedis.lrange("list2", 0, 1);
        for(String e :list2){
            System.out.println(e);
        }

        //hash
        jedis.hset("person","name","zs");
        jedis.hset("person","age","23");

        Map<String, String> person = jedis.hgetAll("person");
        System.out.println(person);

        Map<String,String> map = new HashMap<>() ;
        map.put("id","1");
        map.put("name","lisi");

        jedis.hset("people" ,map) ;
        Map<String, String> result = jedis.hgetAll("people");
        System.out.println(result);

        Set<String> keys = jedis.hkeys("people");
        List<String> values = jedis.hvals("people") ;
        System.out.println("keys:"+keys);
        System.out.println("values:"+values);

        //set
        jedis.sadd("skey","sv1","sv2") ;
        Long count = jedis.scard("skey");
        System.out.println(count);
        System.out.println(  jedis.sismember("skey","sv1"));

       //sortedset
        jedis.zadd("zset" , 10,"zv1") ;
        jedis.zadd("zset" , 9,"zv2") ;

        Map<String,Double> map2 = new HashMap<>() ;
        map2.put("zv3",8.8) ;
        map2.put("zv4",9.9) ;
        jedis.zadd("zset",map2) ;
        Set<String> zset = jedis.zrange("zset", 0, -1);
        System.out.println(zset);

        Set<Tuple> zset1 = jedis.zrangeByScoreWithScores("zset", 9, 10);
        System.out.println(zset1);


    }
}

```





## 爬虫获取海量数据 、 Redis基本操作

redis（key-value） - 关系型数据库 （二维表）

redis和关系型数据的 数据互通方式

![img](file://D:/github/JavaCore/notes/%E5%BE%AE%E6%9C%8D%E5%8A%A1/NOSQL/nosql.assets/1588642791959.png?lastModify=1588990738)



准备工作：

引入json相关Jar

jackson-core-2.9.6.jar

jackson-databind-2.9.6.jar

jackson-annotations-2.9.6.jar



网站数据->下载->解析->存储到nosql（redis）

引入依赖

```

        <!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-core -->
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-core</artifactId>
            <version>2.9.6</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind -->
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.9.6</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-annotations -->
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-annotations</artifactId>
            <version>2.9.6</version>
        </dependency>


        <dependency>
            <groupId>redis.clients</groupId>
            <artifactId>jedis</artifactId>
            <version>3.1.0</version>
        </dependency>

```



json工具类



```
package com.yanqun.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yanqun.entity.Course;

/*
 * Created by 颜群
 */
public class JsonUtils {
    static ObjectMapper objectMapper = new ObjectMapper();

    //json对象 - 对象(一行记录)

    //json对象(字符串形式) -> 对象
    public static<T> T json2Object(String json ,Class<T> valueType) throws Exception{
        return objectMapper.readValue(json, valueType);
    }


    // 对象 -> json对象(字符串形式)
    public static String  object2json(Object value) throws Exception{
        return objectMapper.writeValueAsString( value ) ;
    }

    public static void main(String[] args)throws Exception {
        //演示json对象(字符串形式) -> 对象
         String json = "{\"name\":\"java\",\"num\":\"30\",\"imgPath\":\"sxxx\"  }" ;
        Course course = JsonUtils.json2Object(json, Course.class);
        System.out.println(course);

    }

}

```





```
    public static void writeRedis( List<Course> courses){
        try {
            for (Course course : courses) {
                //使用uuid模拟课程id
                jedis.hset("course", String.valueOf(UUID.randomUUID()  ), JsonUtils.object2json(course));
            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        //获取数据
        String html = getData() ;
		// System.out.println(html);

        //解析数据
        List<Course> courses = parseData(html);
        //写入redis
        writeRedis(courses);



        System.out.println(courses);

        //存储数据
        //使用Jdbc、框架技术 进行存储
    }

    public static void testReadRedis(){
        String course = jedis.hget("course", "3ab99b0e-1c4b-488b-9ac7-65eda44136a0");
        System.out.println(course);
    }
```

## MongoDB 

分布式的、面向文档的NoSQL数据库。

| mongodb             |        |
| ------------------- | ------ |
| 文档(document)      | 行     |
| 集合(collections)   | 表     |
| 数据库（databases） | 数据库 |

安装配置

下载（windows）mongodb-win32-x86_64-2008plus-ssl-v3.4-latest-signed.exe

将 D:\MongoDB\Server\3.4\bin配置到path中

启动服务：mongod --dbpath=已存在的路径

默认端口号27017

登录mongo

bson ,类似json

创建数据库:  use mg

插入数据：

db.集合.insert(bson格式数据)



db.person.insert( {id:"1",name:"zhangsan",age: NumberInt(23)}     )

db.person.insert( {id:"2",name:"zs",age: NumberInt(24)}     )

db.person.insert( {id:"3",name:"zs",age: NumberInt(25)}     )

查询数据

db.person.find() 

db.person.find( {   id:"2"})     --可以任何条数

db.person.findOne( {   id:"2"})     --仅仅有一条数据

 

db.person.find() .limit(2)



修改：

db.集合.update(条件,修改后的数据)

方式一：会舍去其他字段

db.person.update( {id:"2"}, {name:"wangwu"}   )

方式二：保留其他字段

db.person.update( {id:"3"},        {$set:   {name:"LISI"} }                            )



删除：

db.集合.remove(条件)

删除全部：db.集合.remove({})

根据条件删除：db.person.remove({name:"wangwu"})



统计：

db.person.count() --查询全部的数量

db.person.count({name:"zs"}) --查询name=zs的数量

模糊查询：正则表达式

db.person.find(   { name : /^z/ })



条件查询：

```
>: $gt
>=:  $gte
<:  &lt
<= :&lte
!= :  &ne
```

db.person.find({age: {$gte:24}})

$in  / $nin 

db.person.find(    {age :  {  $in :[ 23,24]  }})



where  ..  and .... or ...

$and /  $or  

  $or:  [ {},{}  ]

find({})

db.person.find( {  $or: [    {age:{&gte:25}},  {age:{&lte:23}}   ]    } ) 



示例

db.person.find({ 	$or: [  		 {age:{$gte:25}},  {age:{$lte:23}} 	     ]

})



### java mongodb API

依赖

mongodb-driver



```
<!-- https://mvnrepository.com/artifact/org.mongodb/mongodb-driver -->
<dependency>
    <groupId>org.mongodb</groupId>
    <artifactId>mongodb-driver</artifactId>
    <version>3.8.2</version>
</dependency>
```



爬虫数据->mongodb

将爬虫数据写入mongodb



```

```











```
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
```



从mongodb中读取数据

```
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
```

## Hbase

基于列式存储 的nosql数据库。

基于google bigtable论文

本质上 只有插入操作 （修改、删除 是用  增加+时间戳 模拟的）。

基于hdfs的分布式存储 

hbase只有字符串类型

很容易扩展（可以简单的通过增加硬件，实现水平扩容）

![img](file://D:/github/JavaCore/notes/%E5%BE%AE%E6%9C%8D%E5%8A%A1/NOSQL/nosql.assets/1588724463799.png?lastModify=1588990738)

hbase的底层架构

![img](file://D:/github/JavaCore/notes/%E5%BE%AE%E6%9C%8D%E5%8A%A1/NOSQL/nosql.assets/1588724948687.png?lastModify=1588990738)

### 列式存储

表：不存储null

行键：主键，唯一标识符

![img](file://D:/github/JavaCore/notes/%E5%BE%AE%E6%9C%8D%E5%8A%A1/NOSQL/nosql.assets/1588725699029.png?lastModify=1588990738)

![img](file://D:/github/JavaCore/notes/%E5%BE%AE%E6%9C%8D%E5%8A%A1/NOSQL/nosql.assets/1588725920110.png?lastModify=1588990738)



hbase可以很容易的扩充列：

![img](file://D:/github/JavaCore/notes/%E5%BE%AE%E6%9C%8D%E5%8A%A1/NOSQL/nosql.assets/1588753297337.png?lastModify=1588990738)

### 搭建hbase开发环境（完全分布式）

下载hbase-1.3.1-bin.tar.gz

解压、重命名

配置：conf中  hbase-site.xml、hbase-env.sh、regionservers

hbase-site.xml里面需要配置zookeeper  



配置外置zookeeper：

1.下载zookeeper-3.4.6.tar.gz，解压

2.重命名配置文件 mv zoo_sample.cfg  zoo.cfg

3.修改配置文件

zoo.cfg

```
先创建data和log两个目录
dataDir=/app/zookeeper-3.4.6/data
dataLogDir=/app/zookeeper-3.4.6/log
server.1=bigdata01:2888:3888
server.2=bigdata02:2888:3888
server.3=bigdata03:2888:3888
```

将这台计算机上的zookeeper复制到其他两台上

scp -r /app/zookeeper-3.4.6 root@bigdata02:/app

scp -r /app/zookeeper-3.4.6 root@bigdata03:/app

在每个计算机的zookeeper/data/myid文件中（新创建），写上内容： 1  /2  /3

分别启动三个zookeeper：

bin/zkServer.sh start

查看zk状态

bin/zkServer.sh status



配置hbase的三个hbase-site.xml、hbase-env.sh、regionservers：

hbase-site.xml



```

```











```
    <property>
        <name>hbase.rootdir</name>
        <value>hdfs://bigdata01:9000/hbase</value>
    </property>


    <property>
        <name>hbase.cluster.distributed</name>
        <value>true</value>
    </property>

    <property>
        <name>hbase.master.port</name>
        <value>16000</value>
    </property>

    <property>
        <name>hbase.zookeeper.quorum</name>
        <value>bigdata01,bigdata02,bigdata03</value>  
    </property>
    
    
    <property>
        <name>hbase.zookeeper.property.dataDir</name>
        <value>/app/zookeeper-3.4.6/data</value>  
    </property>
    
    
```





hbase-env.sh

```
export HBASE_MANAGES_ZK=false
export JAVA_HOME=/usr/java/jdk1.8.0_161
export HADOOP_HOME=/app/hadoop
export HBASE_HOME=/app/hbase-1.3.1
```



regionservers

```
bigdata02
bigdata03
```

至此一台hbase配置完毕，需要将这个配置好的habase复制到其他两台计算机上

scp -r /app/hbase-1.3.1 root@bigdata02:/app

scp -r /app/hbase-1.3.1 root@bigdata03:/app



启动hbase：

启动之前一定要先启动  ：zookeeper（每个计算机都需要启动）、hdfs（只需要在主节点启动一次）

启动hdfs：sbin/start-dfs.sh



启动hbase：  bin/start-hbase.sh



使用hbase

### hbase shell

登录客户端：  bin/hbase shell

创建表

create  表名,列1,列2

create  'scores','grade','course'

查看所有表

list

查看表结构

desc 'scores'

describe 'scores'

增加数据

put 表,行键,列键，值

put 'scores', 'zs','grade:',"1"

put 'scores', 'zs','course:Chinese',"100"

put 'scores', 'zs','course:Math',"95"



put 'scores', 'ls','grade:',"1"

put 'scores', 'ls','course:Chinese',"80"

put 'scores', 'ls','course:Math',"75"





查看数据：

扫描表： 

scan 表名

scan 'scores'

scan 'scores', {COLUMN=>'course:Math'   ,LIMIT=>1}





获取数据（某一行）：

get '表名',行键

查询行键为zs的所有数据

get  'scores' , 'zs'

查询行键为zs，列键为course:Math的数据

get 'scores','zs',{COLUMN=>'course:Math'}



删除数据

delete 表名，行键，列键

delete 'scores','zs','course:Math'



deleteall 表名，行键

deleteall  'scores','zs'



删除表

drop '表名'

drop 'scores'

删除表以前，必须将表disabled

disable  'scores'

drop 'scores'

### java hbase api

引入hbase依赖

```
<!-- https://mvnrepository.com/artifact/org.apache.hbase/hbase-server -->
<dependency>
    <groupId>org.apache.hbase</groupId>
    <artifactId>hbase-server</artifactId>
    <version>1.3.1</version>
</dependency>

<!-- https://mvnrepository.com/artifact/org.apache.hbase/hbase-client -->
<dependency>
    <groupId>org.apache.hbase</groupId>
    <artifactId>hbase-client</artifactId>
    <version>1.3.1</version>
</dependency>
```

将 hbase-site.xml复制到项目的classpath下

```
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

/*
 * Created by 颜群
 */
public class HbaseDemo {

    //读取配置文件
    static Configuration cfg = HBaseConfiguration.create() ;
    //查看所有表 list
    public static void list() throws Exception {
        Connection connection = ConnectionFactory.createConnection(cfg);
        Admin admin = connection.getAdmin();
        TableName[] tableNames = admin.listTableNames();
        for(TableName name: tableNames){
            System.out.println(name);
        }
        connection.close();
    }

    //create  tableName , a,b,c,...
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

//    //修改表结构：增加列族
//    public static void addColumnFamily(String tableName,String ...familyNames) throws Exception {
//        Connection connection = ConnectionFactory.createConnection(cfg);
//        Admin admin = connection.getAdmin();
//        TableName tn = TableName.valueOf(tableName);
//        HTableDescriptor htd = new HTableDescriptor(tn);
//        for(String family:familyNames){
//            htd.addFamily( new HColumnDescriptor( family)    )  ;
//        }
////        admin.createTable(htd);
//        admin.modifyTable( tn,htd );
//        connection.close() ;
//    }
    //修改表结构：增加列族
    public static void addColumnFamily(String tableName,String ...familyNames) throws Exception {
        Connection connection = ConnectionFactory.createConnection(cfg);
        Admin admin = connection.getAdmin();
        TableName tn = TableName.valueOf(tableName);

        HTableDescriptor htd = admin.getTableDescriptor(tn);


        for(String family:familyNames){
            htd.addFamily( new HColumnDescriptor( family)    )  ;
        }
//        admin.createTable(htd);
        admin.modifyTable( tn,htd );
        connection.close() ;
    }
    //修改表结构：删除出列族
    public static void removeColumnFamily(String tableName,String ...familyNames) throws Exception {
        Connection connection = ConnectionFactory.createConnection(cfg);
        Admin admin = connection.getAdmin();
        TableName tn = TableName.valueOf(tableName);

        HTableDescriptor htd =admin.getTableDescriptor(tn);

        for(String family:familyNames){
//            htd.addFamily( new HColumnDescriptor( family)    )  ;
            htd.removeFamily(Bytes.toBytes(family )    ) ;
        }
//        admin.createTable(htd);
        admin.modifyTable( tn,htd );
        connection.close() ;
    }



    //查看表结构
    public static void desc(String tableName) throws Exception {
        Connection connection = ConnectionFactory.createConnection(cfg);
        Admin admin = connection.getAdmin();
        TableName tn = TableName.valueOf(tableName);
        HTableDescriptor htd = admin.getTableDescriptor(tn);
        System.out.println("表结构");
        for(HColumnDescriptor hcd:    htd.getColumnFamilies()){
            System.out.println(   hcd.getNameAsString()  );

        }
        connection.close();

    }


        //增加数据

    //查看表结构
    public static void put(String tableName) throws Exception {
        Connection connection = ConnectionFactory.createConnection(cfg);
        Table table = connection.getTable(TableName.valueOf(tableName));
        Put p = new Put(Bytes.toBytes( "zs" )  );

        //grade:         2
        p.addColumn(  Bytes.toBytes(  "grade")  ,Bytes.toBytes("")  , Bytes.toBytes("2") ) ;
        //course:math   100
        p.addColumn(Bytes.toBytes("course"),Bytes.toBytes("math"),Bytes.toBytes("88")) ;

        table.put(p);
        connection.close() ;
    }

    //查询一行数据
    public static void getRow(String tableName,String rowKey) throws Exception {
        Connection connection = ConnectionFactory.createConnection(cfg); ;

        Table tb = connection.getTable(TableName.valueOf(tableName)) ;
        Get g = new Get(  Bytes.toBytes(rowKey) ) ;

        Result result = tb.get(g);
//        System.out.println(   Bytes.toString( result.value() )       );
        System.out.println(String.format("result.value=%s ,result.toString():%s", Bytes.toString(result.value()),result));
        connection.close();
    }

    //scan
    public static void scan(String tableName) throws Exception{
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


        public static void main(String[] args) throws Exception {
//        create("scores","grade","course") ;
//        addColumnFamily("scores","x","y");
//        removeColumnFamily("scores","a");
//        list() ;
//            desc("scores") ;
//            put("scores");
//            getRow("scores","zs");
            scan("scores");
    }

}

```



### 使用hbase存储爬虫数据

1.引入hbase依赖

```
<!-- https://mvnrepository.com/artifact/org.apache.hbase/hbase-server -->
<dependency>
    <groupId>org.apache.hbase</groupId>
    <artifactId>hbase-server</artifactId>
    <version>1.3.1</version>
</dependency>

<!-- https://mvnrepository.com/artifact/org.apache.hbase/hbase-client -->
<dependency>
    <groupId>org.apache.hbase</groupId>
    <artifactId>hbase-client</artifactId>
    <version>1.3.1</version>
</dependency>
```



2.编写代码



```
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
        //获取数据
        String html = getData() ;
//        System.out.println(html);

        //解析数据
        List<Course> courses = parseData(html);
        //写入redis
//        writeRedis(courses);
        //写入mongodb
//        writeMongodb(courses) ;
        //创建hbase表
        create("course", "name", "num","imgPath");

        //写入hbase
        putHbase("course",courses) ;


        System.out.println(courses);
        //从redis中读取数据
//        testReadRedis();

        //从mongodb中读取数据
//        testReadMongodb() ;

        //从hbase中读取数据
        testScanHabase("course") ;

        //存储数据
        //使用Jdbc、框架技术 进行存储
    }
```



# 4.Neo4J

基于网状结构的、图形化NoSQL数据；

基于JVM，支持事务的ACID特性。



neo4j非常适合用于实现一些复杂的关系。这些复杂关系 如果用关系型数据库来实现，就需要使用“多对多”，而多个“多对多”会形成非常复杂的逻辑结构，不易分析和理解。

关系型数据库：

![1589787908498](NoSQL.assets/1589787908498.png)

Neo4j:

![1589787947546](NoSQL.assets/1589787947546.png)

### 环境搭建

下载

https://neo4j.com/download/neo4j-desktop/?edition=desktop&flavour=winstall64&release=1.2.8&offline=true

输入开发者个人信息，下载。neo4j-desktop-offline-1.2.4-setup.exe

安装

Neo4j 3.5.14

访问：

http://localhost:7474/browser/

用户名：neo4j   ，密码



节点、关系、路径

![1589790011058](NoSQL.assets/1589790011058.png)



创建节点

CREATE (TheMatrix:Movie {title:'The Matrix', released:1999, tagline:'Welcome to the Real World'})

CREATE (标签:标签 {属性名1:'属性值1', 属性名2:'属性值2',属性名3:'属性值3'})

![1589790532935](NoSQL.assets/1589790532935.png)

建立关系

CREATE
(Keanu)-[:ACTED_IN {roles:['Neo']}]->(TheMatrix),
(Carrie)-[:ACTED_IN {roles:['Trinity']}]->(TheMatrix),
(Laurence)-[:ACTED_IN {roles:['Morpheus']}]->(TheMatrix),
(Hugo)-[:ACTED_IN {roles:['Agent Smith']}]->(TheMatrix),
(LillyW)-[:DIRECTED]->(TheMatrix),
(LanaW)-[:DIRECTED]->(TheMatrix)

CREATE (JoelS)-[:PRODUCED]->(TheMatrix)

删除全部的节点和关系：

match (n)
optional match (n)-[r]-()
delete n,r



创建很多节点 和各个节点之间的关系

```sql
CREATE (TheMatrix:Movie {title:'The Matrix', released:1999, tagline:'Welcome to the Real World'})
CREATE (Keanu:Person {name:'Keanu Reeves', born:1964})
CREATE (Carrie:Person {name:'Carrie-Anne Moss', born:1967})
CREATE (Laurence:Person {name:'Laurence Fishburne', born:1961})
CREATE (Hugo:Person {name:'Hugo Weaving', born:1960})
CREATE (LillyW:Person {name:'Lilly Wachowski', born:1967})
CREATE (LanaW:Person {name:'Lana Wachowski', born:1965})
CREATE (JoelS:Person {name:'Joel Silver', born:1952})
CREATE
(Keanu)-[:ACTED_IN {roles:['Neo']}]->(TheMatrix),
(Carrie)-[:ACTED_IN {roles:['Trinity']}]->(TheMatrix),
(Laurence)-[:ACTED_IN {roles:['Morpheus']}]->(TheMatrix),
(Hugo)-[:ACTED_IN {roles:['Agent Smith']}]->(TheMatrix),
(LillyW)-[:DIRECTED]->(TheMatrix),
(LanaW)-[:DIRECTED]->(TheMatrix),
(JoelS)-[:PRODUCED]->(TheMatrix)

CREATE (Emil:Person {name:"Emil Eifrem", born:1978})
CREATE (Emil)-[:ACTED_IN {roles:["Emil"]}]->(TheMatrix)

CREATE (TheMatrixReloaded:Movie {title:'The Matrix Reloaded', released:2003, tagline:'Free your mind'})
CREATE
(Keanu)-[:ACTED_IN {roles:['Neo']}]->(TheMatrixReloaded),
(Carrie)-[:ACTED_IN {roles:['Trinity']}]->(TheMatrixReloaded),
(Laurence)-[:ACTED_IN {roles:['Morpheus']}]->(TheMatrixReloaded),
(Hugo)-[:ACTED_IN {roles:['Agent Smith']}]->(TheMatrixReloaded),
(LillyW)-[:DIRECTED]->(TheMatrixReloaded),
(LanaW)-[:DIRECTED]->(TheMatrixReloaded),
(JoelS)-[:PRODUCED]->(TheMatrixReloaded)

CREATE (TheMatrixRevolutions:Movie {title:'The Matrix Revolutions', released:2003, tagline:'Everything that has a beginning has an end'})
CREATE
(Keanu)-[:ACTED_IN {roles:['Neo']}]->(TheMatrixRevolutions),
(Carrie)-[:ACTED_IN {roles:['Trinity']}]->(TheMatrixRevolutions),
(Laurence)-[:ACTED_IN {roles:['Morpheus']}]->(TheMatrixRevolutions),
(Hugo)-[:ACTED_IN {roles:['Agent Smith']}]->(TheMatrixRevolutions),
(LillyW)-[:DIRECTED]->(TheMatrixRevolutions),
(LanaW)-[:DIRECTED]->(TheMatrixRevolutions),
(JoelS)-[:PRODUCED]->(TheMatrixRevolutions)

CREATE (TheDevilsAdvocate:Movie {title:"The Devil's Advocate", released:1997, tagline:'Evil has its winning ways'})
CREATE (Charlize:Person {name:'Charlize Theron', born:1975})
CREATE (Al:Person {name:'Al Pacino', born:1940})
CREATE (Taylor:Person {name:'Taylor Hackford', born:1944})
CREATE
(Keanu)-[:ACTED_IN {roles:['Kevin Lomax']}]->(TheDevilsAdvocate),
(Charlize)-[:ACTED_IN {roles:['Mary Ann Lomax']}]->(TheDevilsAdvocate),
(Al)-[:ACTED_IN {roles:['John Milton']}]->(TheDevilsAdvocate),
(Taylor)-[:DIRECTED]->(TheDevilsAdvocate)

CREATE (AFewGoodMen:Movie {title:"A Few Good Men", released:1992, tagline:"In the heart of the nation's capital, in a courthouse of the U.S. government, one man will stop at nothing to keep his honor, and one will stop at nothing to find the truth."})
CREATE (TomC:Person {name:'Tom Cruise', born:1962})
CREATE (JackN:Person {name:'Jack Nicholson', born:1937})
CREATE (DemiM:Person {name:'Demi Moore', born:1962})
CREATE (KevinB:Person {name:'Kevin Bacon', born:1958})
CREATE (KieferS:Person {name:'Kiefer Sutherland', born:1966})
CREATE (NoahW:Person {name:'Noah Wyle', born:1971})
CREATE (CubaG:Person {name:'Cuba Gooding Jr.', born:1968})
CREATE (KevinP:Person {name:'Kevin Pollak', born:1957})
CREATE (JTW:Person {name:'J.T. Walsh', born:1943})
CREATE (JamesM:Person {name:'James Marshall', born:1967})
CREATE (ChristopherG:Person {name:'Christopher Guest', born:1948})
CREATE (RobR:Person {name:'Rob Reiner', born:1947})
CREATE (AaronS:Person {name:'Aaron Sorkin', born:1961})
CREATE
(TomC)-[:ACTED_IN {roles:['Lt. Daniel Kaffee']}]->(AFewGoodMen),
(JackN)-[:ACTED_IN {roles:['Col. Nathan R. Jessup']}]->(AFewGoodMen),
(DemiM)-[:ACTED_IN {roles:['Lt. Cdr. JoAnne Galloway']}]->(AFewGoodMen),
(KevinB)-[:ACTED_IN {roles:['Capt. Jack Ross']}]->(AFewGoodMen),
(KieferS)-[:ACTED_IN {roles:['Lt. Jonathan Kendrick']}]->(AFewGoodMen),
(NoahW)-[:ACTED_IN {roles:['Cpl. Jeffrey Barnes']}]->(AFewGoodMen),
(CubaG)-[:ACTED_IN {roles:['Cpl. Carl Hammaker']}]->(AFewGoodMen),
(KevinP)-[:ACTED_IN {roles:['Lt. Sam Weinberg']}]->(AFewGoodMen),
(JTW)-[:ACTED_IN {roles:['Lt. Col. Matthew Andrew Markinson']}]->(AFewGoodMen),
(JamesM)-[:ACTED_IN {roles:['Pfc. Louden Downey']}]->(AFewGoodMen),
(ChristopherG)-[:ACTED_IN {roles:['Dr. Stone']}]->(AFewGoodMen),
(AaronS)-[:ACTED_IN {roles:['Man in Bar']}]->(AFewGoodMen),
(RobR)-[:DIRECTED]->(AFewGoodMen),
(AaronS)-[:WROTE]->(AFewGoodMen)

CREATE (TopGun:Movie {title:"Top Gun", released:1986, tagline:'I feel the need, the need for speed.'})
CREATE (KellyM:Person {name:'Kelly McGillis', born:1957})
CREATE (ValK:Person {name:'Val Kilmer', born:1959})
CREATE (AnthonyE:Person {name:'Anthony Edwards', born:1962})
CREATE (TomS:Person {name:'Tom Skerritt', born:1933})
CREATE (MegR:Person {name:'Meg Ryan', born:1961})
CREATE (TonyS:Person {name:'Tony Scott', born:1944})
CREATE (JimC:Person {name:'Jim Cash', born:1941})
CREATE
(TomC)-[:ACTED_IN {roles:['Maverick']}]->(TopGun),
(KellyM)-[:ACTED_IN {roles:['Charlie']}]->(TopGun),
(ValK)-[:ACTED_IN {roles:['Iceman']}]->(TopGun),
(AnthonyE)-[:ACTED_IN {roles:['Goose']}]->(TopGun),
(TomS)-[:ACTED_IN {roles:['Viper']}]->(TopGun),
(MegR)-[:ACTED_IN {roles:['Carole']}]->(TopGun),
(TonyS)-[:DIRECTED]->(TopGun),
(JimC)-[:WROTE]->(TopGun)

CREATE (JerryMaguire:Movie {title:'Jerry Maguire', released:2000, tagline:'The rest of his life begins now.'})
CREATE (ReneeZ:Person {name:'Renee Zellweger', born:1969})
CREATE (KellyP:Person {name:'Kelly Preston', born:1962})
CREATE (JerryO:Person {name:"Jerry O'Connell", born:1974})
CREATE (JayM:Person {name:'Jay Mohr', born:1970})
CREATE (BonnieH:Person {name:'Bonnie Hunt', born:1961})
CREATE (ReginaK:Person {name:'Regina King', born:1971})
CREATE (JonathanL:Person {name:'Jonathan Lipnicki', born:1996})
CREATE (CameronC:Person {name:'Cameron Crowe', born:1957})
CREATE
(TomC)-[:ACTED_IN {roles:['Jerry Maguire']}]->(JerryMaguire),
(CubaG)-[:ACTED_IN {roles:['Rod Tidwell']}]->(JerryMaguire),
(ReneeZ)-[:ACTED_IN {roles:['Dorothy Boyd']}]->(JerryMaguire),
(KellyP)-[:ACTED_IN {roles:['Avery Bishop']}]->(JerryMaguire),
(JerryO)-[:ACTED_IN {roles:['Frank Cushman']}]->(JerryMaguire),
(JayM)-[:ACTED_IN {roles:['Bob Sugar']}]->(JerryMaguire),
(BonnieH)-[:ACTED_IN {roles:['Laurel Boyd']}]->(JerryMaguire),
(ReginaK)-[:ACTED_IN {roles:['Marcee Tidwell']}]->(JerryMaguire),
(JonathanL)-[:ACTED_IN {roles:['Ray Boyd']}]->(JerryMaguire),
(CameronC)-[:DIRECTED]->(JerryMaguire),
(CameronC)-[:PRODUCED]->(JerryMaguire),
(CameronC)-[:WROTE]->(JerryMaguire)

CREATE (StandByMe:Movie {title:"Stand By Me", released:1986, tagline:"For some, it's the last real taste of innocence, and the first real taste of life. But for everyone, it's the time that memories are made of."})
CREATE (RiverP:Person {name:'River Phoenix', born:1970})
CREATE (CoreyF:Person {name:'Corey Feldman', born:1971})
CREATE (WilW:Person {name:'Wil Wheaton', born:1972})
CREATE (JohnC:Person {name:'John Cusack', born:1966})
CREATE (MarshallB:Person {name:'Marshall Bell', born:1942})
CREATE
(WilW)-[:ACTED_IN {roles:['Gordie Lachance']}]->(StandByMe),
(RiverP)-[:ACTED_IN {roles:['Chris Chambers']}]->(StandByMe),
(JerryO)-[:ACTED_IN {roles:['Vern Tessio']}]->(StandByMe),
(CoreyF)-[:ACTED_IN {roles:['Teddy Duchamp']}]->(StandByMe),
(JohnC)-[:ACTED_IN {roles:['Denny Lachance']}]->(StandByMe),
(KieferS)-[:ACTED_IN {roles:['Ace Merrill']}]->(StandByMe),
(MarshallB)-[:ACTED_IN {roles:['Mr. Lachance']}]->(StandByMe),
(RobR)-[:DIRECTED]->(StandByMe)

CREATE (AsGoodAsItGets:Movie {title:'As Good as It Gets', released:1997, tagline:'A comedy from the heart that goes for the throat.'})
CREATE (HelenH:Person {name:'Helen Hunt', born:1963})
CREATE (GregK:Person {name:'Greg Kinnear', born:1963})
CREATE (JamesB:Person {name:'James L. Brooks', born:1940})
CREATE
(JackN)-[:ACTED_IN {roles:['Melvin Udall']}]->(AsGoodAsItGets),
(HelenH)-[:ACTED_IN {roles:['Carol Connelly']}]->(AsGoodAsItGets),
(GregK)-[:ACTED_IN {roles:['Simon Bishop']}]->(AsGoodAsItGets),
(CubaG)-[:ACTED_IN {roles:['Frank Sachs']}]->(AsGoodAsItGets),
(JamesB)-[:DIRECTED]->(AsGoodAsItGets)

CREATE (WhatDreamsMayCome:Movie {title:'What Dreams May Come', released:1998, tagline:'After life there is more. The end is just the beginning.'})
CREATE (AnnabellaS:Person {name:'Annabella Sciorra', born:1960})
CREATE (MaxS:Person {name:'Max von Sydow', born:1929})
CREATE (WernerH:Person {name:'Werner Herzog', born:1942})
CREATE (Robin:Person {name:'Robin Williams', born:1951})
CREATE (VincentW:Person {name:'Vincent Ward', born:1956})
CREATE
(Robin)-[:ACTED_IN {roles:['Chris Nielsen']}]->(WhatDreamsMayCome),
(CubaG)-[:ACTED_IN {roles:['Albert Lewis']}]->(WhatDreamsMayCome),
(AnnabellaS)-[:ACTED_IN {roles:['Annie Collins-Nielsen']}]->(WhatDreamsMayCome),
(MaxS)-[:ACTED_IN {roles:['The Tracker']}]->(WhatDreamsMayCome),
(WernerH)-[:ACTED_IN {roles:['The Face']}]->(WhatDreamsMayCome),
(VincentW)-[:DIRECTED]->(WhatDreamsMayCome)

CREATE (SnowFallingonCedars:Movie {title:'Snow Falling on Cedars', released:1999, tagline:'First loves last. Forever.'})
CREATE (EthanH:Person {name:'Ethan Hawke', born:1970})
CREATE (RickY:Person {name:'Rick Yune', born:1971})
CREATE (JamesC:Person {name:'James Cromwell', born:1940})
CREATE (ScottH:Person {name:'Scott Hicks', born:1953})
CREATE
(EthanH)-[:ACTED_IN {roles:['Ishmael Chambers']}]->(SnowFallingonCedars),
(RickY)-[:ACTED_IN {roles:['Kazuo Miyamoto']}]->(SnowFallingonCedars),
(MaxS)-[:ACTED_IN {roles:['Nels Gudmundsson']}]->(SnowFallingonCedars),
(JamesC)-[:ACTED_IN {roles:['Judge Fielding']}]->(SnowFallingonCedars),
(ScottH)-[:DIRECTED]->(SnowFallingonCedars)

CREATE (YouveGotMail:Movie {title:"You've Got Mail", released:1998, tagline:'At odds in life... in love on-line.'})
CREATE (ParkerP:Person {name:'Parker Posey', born:1968})
CREATE (DaveC:Person {name:'Dave Chappelle', born:1973})
CREATE (SteveZ:Person {name:'Steve Zahn', born:1967})
CREATE (TomH:Person {name:'Tom Hanks', born:1956})
CREATE (NoraE:Person {name:'Nora Ephron', born:1941})
CREATE
(TomH)-[:ACTED_IN {roles:['Joe Fox']}]->(YouveGotMail),
(MegR)-[:ACTED_IN {roles:['Kathleen Kelly']}]->(YouveGotMail),
(GregK)-[:ACTED_IN {roles:['Frank Navasky']}]->(YouveGotMail),
(ParkerP)-[:ACTED_IN {roles:['Patricia Eden']}]->(YouveGotMail),
(DaveC)-[:ACTED_IN {roles:['Kevin Jackson']}]->(YouveGotMail),
(SteveZ)-[:ACTED_IN {roles:['George Pappas']}]->(YouveGotMail),
(NoraE)-[:DIRECTED]->(YouveGotMail)

CREATE (SleeplessInSeattle:Movie {title:'Sleepless in Seattle', released:1993, tagline:'What if someone you never met, someone you never saw, someone you never knew was the only someone for you?'})
CREATE (RitaW:Person {name:'Rita Wilson', born:1956})
CREATE (BillPull:Person {name:'Bill Pullman', born:1953})
CREATE (VictorG:Person {name:'Victor Garber', born:1949})
CREATE (RosieO:Person {name:"Rosie O'Donnell", born:1962})
CREATE
(TomH)-[:ACTED_IN {roles:['Sam Baldwin']}]->(SleeplessInSeattle),
(MegR)-[:ACTED_IN {roles:['Annie Reed']}]->(SleeplessInSeattle),
(RitaW)-[:ACTED_IN {roles:['Suzy']}]->(SleeplessInSeattle),
(BillPull)-[:ACTED_IN {roles:['Walter']}]->(SleeplessInSeattle),
(VictorG)-[:ACTED_IN {roles:['Greg']}]->(SleeplessInSeattle),
(RosieO)-[:ACTED_IN {roles:['Becky']}]->(SleeplessInSeattle),
(NoraE)-[:DIRECTED]->(SleeplessInSeattle)

CREATE (JoeVersustheVolcano:Movie {title:'Joe Versus the Volcano', released:1990, tagline:'A story of love, lava and burning desire.'})
CREATE (JohnS:Person {name:'John Patrick Stanley', born:1950})
CREATE (Nathan:Person {name:'Nathan Lane', born:1956})
CREATE
(TomH)-[:ACTED_IN {roles:['Joe Banks']}]->(JoeVersustheVolcano),
(MegR)-[:ACTED_IN {roles:['DeDe', 'Angelica Graynamore', 'Patricia Graynamore']}]->(JoeVersustheVolcano),
(Nathan)-[:ACTED_IN {roles:['Baw']}]->(JoeVersustheVolcano),
(JohnS)-[:DIRECTED]->(JoeVersustheVolcano)

CREATE (WhenHarryMetSally:Movie {title:'When Harry Met Sally', released:1998, tagline:'Can two friends sleep together and still love each other in the morning?'})
CREATE (BillyC:Person {name:'Billy Crystal', born:1948})
CREATE (CarrieF:Person {name:'Carrie Fisher', born:1956})
CREATE (BrunoK:Person {name:'Bruno Kirby', born:1949})
CREATE
(BillyC)-[:ACTED_IN {roles:['Harry Burns']}]->(WhenHarryMetSally),
(MegR)-[:ACTED_IN {roles:['Sally Albright']}]->(WhenHarryMetSally),
(CarrieF)-[:ACTED_IN {roles:['Marie']}]->(WhenHarryMetSally),
(BrunoK)-[:ACTED_IN {roles:['Jess']}]->(WhenHarryMetSally),
(RobR)-[:DIRECTED]->(WhenHarryMetSally),
(RobR)-[:PRODUCED]->(WhenHarryMetSally),
(NoraE)-[:PRODUCED]->(WhenHarryMetSally),
(NoraE)-[:WROTE]->(WhenHarryMetSally)

CREATE (ThatThingYouDo:Movie {title:'That Thing You Do', released:1996, tagline:'In every life there comes a time when that thing you dream becomes that thing you do'})
CREATE (LivT:Person {name:'Liv Tyler', born:1977})
CREATE
(TomH)-[:ACTED_IN {roles:['Mr. White']}]->(ThatThingYouDo),
(LivT)-[:ACTED_IN {roles:['Faye Dolan']}]->(ThatThingYouDo),
(Charlize)-[:ACTED_IN {roles:['Tina']}]->(ThatThingYouDo),
(TomH)-[:DIRECTED]->(ThatThingYouDo)

CREATE (TheReplacements:Movie {title:'The Replacements', released:2000, tagline:'Pain heals, Chicks dig scars... Glory lasts forever'})
CREATE (Brooke:Person {name:'Brooke Langton', born:1970})
CREATE (Gene:Person {name:'Gene Hackman', born:1930})
CREATE (Orlando:Person {name:'Orlando Jones', born:1968})
CREATE (Howard:Person {name:'Howard Deutch', born:1950})
CREATE
(Keanu)-[:ACTED_IN {roles:['Shane Falco']}]->(TheReplacements),
(Brooke)-[:ACTED_IN {roles:['Annabelle Farrell']}]->(TheReplacements),
(Gene)-[:ACTED_IN {roles:['Jimmy McGinty']}]->(TheReplacements),
(Orlando)-[:ACTED_IN {roles:['Clifford Franklin']}]->(TheReplacements),
(Howard)-[:DIRECTED]->(TheReplacements)

CREATE (RescueDawn:Movie {title:'RescueDawn', released:2006, tagline:"Based on the extraordinary true story of one man's fight for freedom"})
CREATE (ChristianB:Person {name:'Christian Bale', born:1974})
CREATE (ZachG:Person {name:'Zach Grenier', born:1954})
CREATE
(MarshallB)-[:ACTED_IN {roles:['Admiral']}]->(RescueDawn),
(ChristianB)-[:ACTED_IN {roles:['Dieter Dengler']}]->(RescueDawn),
(ZachG)-[:ACTED_IN {roles:['Squad Leader']}]->(RescueDawn),
(SteveZ)-[:ACTED_IN {roles:['Duane']}]->(RescueDawn),
(WernerH)-[:DIRECTED]->(RescueDawn)

CREATE (TheBirdcage:Movie {title:'The Birdcage', released:1996, tagline:'Come as you are'})
CREATE (MikeN:Person {name:'Mike Nichols', born:1931})
CREATE
(Robin)-[:ACTED_IN {roles:['Armand Goldman']}]->(TheBirdcage),
(Nathan)-[:ACTED_IN {roles:['Albert Goldman']}]->(TheBirdcage),
(Gene)-[:ACTED_IN {roles:['Sen. Kevin Keeley']}]->(TheBirdcage),
(MikeN)-[:DIRECTED]->(TheBirdcage)

CREATE (Unforgiven:Movie {title:'Unforgiven', released:1992, tagline:"It's a hell of a thing, killing a man"})
CREATE (RichardH:Person {name:'Richard Harris', born:1930})
CREATE (ClintE:Person {name:'Clint Eastwood', born:1930})
CREATE
(RichardH)-[:ACTED_IN {roles:['English Bob']}]->(Unforgiven),
(ClintE)-[:ACTED_IN {roles:['Bill Munny']}]->(Unforgiven),
(Gene)-[:ACTED_IN {roles:['Little Bill Daggett']}]->(Unforgiven),
(ClintE)-[:DIRECTED]->(Unforgiven)

CREATE (JohnnyMnemonic:Movie {title:'Johnny Mnemonic', released:1995, tagline:'The hottest data on earth. In the coolest head in town'})
CREATE (Takeshi:Person {name:'Takeshi Kitano', born:1947})
CREATE (Dina:Person {name:'Dina Meyer', born:1968})
CREATE (IceT:Person {name:'Ice-T', born:1958})
CREATE (RobertL:Person {name:'Robert Longo', born:1953})
CREATE
(Keanu)-[:ACTED_IN {roles:['Johnny Mnemonic']}]->(JohnnyMnemonic),
(Takeshi)-[:ACTED_IN {roles:['Takahashi']}]->(JohnnyMnemonic),
(Dina)-[:ACTED_IN {roles:['Jane']}]->(JohnnyMnemonic),
(IceT)-[:ACTED_IN {roles:['J-Bone']}]->(JohnnyMnemonic),
(RobertL)-[:DIRECTED]->(JohnnyMnemonic)

CREATE (CloudAtlas:Movie {title:'Cloud Atlas', released:2012, tagline:'Everything is connected'})
CREATE (HalleB:Person {name:'Halle Berry', born:1966})
CREATE (JimB:Person {name:'Jim Broadbent', born:1949})
CREATE (TomT:Person {name:'Tom Tykwer', born:1965})
CREATE (DavidMitchell:Person {name:'David Mitchell', born:1969})
CREATE (StefanArndt:Person {name:'Stefan Arndt', born:1961})
CREATE
(TomH)-[:ACTED_IN {roles:['Zachry', 'Dr. Henry Goose', 'Isaac Sachs', 'Dermot Hoggins']}]->(CloudAtlas),
(Hugo)-[:ACTED_IN {roles:['Bill Smoke', 'Haskell Moore', 'Tadeusz Kesselring', 'Nurse Noakes', 'Boardman Mephi', 'Old Georgie']}]->(CloudAtlas),
(HalleB)-[:ACTED_IN {roles:['Luisa Rey', 'Jocasta Ayrs', 'Ovid', 'Meronym']}]->(CloudAtlas),
(JimB)-[:ACTED_IN {roles:['Vyvyan Ayrs', 'Captain Molyneux', 'Timothy Cavendish']}]->(CloudAtlas),
(TomT)-[:DIRECTED]->(CloudAtlas),
(LillyW)-[:DIRECTED]->(CloudAtlas),
(LanaW)-[:DIRECTED]->(CloudAtlas),
(DavidMitchell)-[:WROTE]->(CloudAtlas),
(StefanArndt)-[:PRODUCED]->(CloudAtlas)

CREATE (TheDaVinciCode:Movie {title:'The Da Vinci Code', released:2006, tagline:'Break The Codes'})
CREATE (IanM:Person {name:'Ian McKellen', born:1939})
CREATE (AudreyT:Person {name:'Audrey Tautou', born:1976})
CREATE (PaulB:Person {name:'Paul Bettany', born:1971})
CREATE (RonH:Person {name:'Ron Howard', born:1954})
CREATE
(TomH)-[:ACTED_IN {roles:['Dr. Robert Langdon']}]->(TheDaVinciCode),
(IanM)-[:ACTED_IN {roles:['Sir Leight Teabing']}]->(TheDaVinciCode),
(AudreyT)-[:ACTED_IN {roles:['Sophie Neveu']}]->(TheDaVinciCode),
(PaulB)-[:ACTED_IN {roles:['Silas']}]->(TheDaVinciCode),
(RonH)-[:DIRECTED]->(TheDaVinciCode)

CREATE (VforVendetta:Movie {title:'V for Vendetta', released:2006, tagline:'Freedom! Forever!'})
CREATE (NatalieP:Person {name:'Natalie Portman', born:1981})
CREATE (StephenR:Person {name:'Stephen Rea', born:1946})
CREATE (JohnH:Person {name:'John Hurt', born:1940})
CREATE (BenM:Person {name: 'Ben Miles', born:1967})
CREATE
(Hugo)-[:ACTED_IN {roles:['V']}]->(VforVendetta),
(NatalieP)-[:ACTED_IN {roles:['Evey Hammond']}]->(VforVendetta),
(StephenR)-[:ACTED_IN {roles:['Eric Finch']}]->(VforVendetta),
(JohnH)-[:ACTED_IN {roles:['High Chancellor Adam Sutler']}]->(VforVendetta),
(BenM)-[:ACTED_IN {roles:['Dascomb']}]->(VforVendetta),
(JamesM)-[:DIRECTED]->(VforVendetta),
(LillyW)-[:PRODUCED]->(VforVendetta),
(LanaW)-[:PRODUCED]->(VforVendetta),
(JoelS)-[:PRODUCED]->(VforVendetta),
(LillyW)-[:WROTE]->(VforVendetta),
(LanaW)-[:WROTE]->(VforVendetta)

CREATE (SpeedRacer:Movie {title:'Speed Racer', released:2008, tagline:'Speed has no limits'})
CREATE (EmileH:Person {name:'Emile Hirsch', born:1985})
CREATE (JohnG:Person {name:'John Goodman', born:1960})
CREATE (SusanS:Person {name:'Susan Sarandon', born:1946})
CREATE (MatthewF:Person {name:'Matthew Fox', born:1966})
CREATE (ChristinaR:Person {name:'Christina Ricci', born:1980})
CREATE (Rain:Person {name:'Rain', born:1982})
CREATE
(EmileH)-[:ACTED_IN {roles:['Speed Racer']}]->(SpeedRacer),
(JohnG)-[:ACTED_IN {roles:['Pops']}]->(SpeedRacer),
(SusanS)-[:ACTED_IN {roles:['Mom']}]->(SpeedRacer),
(MatthewF)-[:ACTED_IN {roles:['Racer X']}]->(SpeedRacer),
(ChristinaR)-[:ACTED_IN {roles:['Trixie']}]->(SpeedRacer),
(Rain)-[:ACTED_IN {roles:['Taejo Togokahn']}]->(SpeedRacer),
(BenM)-[:ACTED_IN {roles:['Cass Jones']}]->(SpeedRacer),
(LillyW)-[:DIRECTED]->(SpeedRacer),
(LanaW)-[:DIRECTED]->(SpeedRacer),
(LillyW)-[:WROTE]->(SpeedRacer),
(LanaW)-[:WROTE]->(SpeedRacer),
(JoelS)-[:PRODUCED]->(SpeedRacer)

CREATE (NinjaAssassin:Movie {title:'Ninja Assassin', released:2009, tagline:'Prepare to enter a secret world of assassins'})
CREATE (NaomieH:Person {name:'Naomie Harris'})
CREATE
(Rain)-[:ACTED_IN {roles:['Raizo']}]->(NinjaAssassin),
(NaomieH)-[:ACTED_IN {roles:['Mika Coretti']}]->(NinjaAssassin),
(RickY)-[:ACTED_IN {roles:['Takeshi']}]->(NinjaAssassin),
(BenM)-[:ACTED_IN {roles:['Ryan Maslow']}]->(NinjaAssassin),
(JamesM)-[:DIRECTED]->(NinjaAssassin),
(LillyW)-[:PRODUCED]->(NinjaAssassin),
(LanaW)-[:PRODUCED]->(NinjaAssassin),
(JoelS)-[:PRODUCED]->(NinjaAssassin)

CREATE (TheGreenMile:Movie {title:'The Green Mile', released:1999, tagline:"Walk a mile you'll never forget."})
CREATE (MichaelD:Person {name:'Michael Clarke Duncan', born:1957})
CREATE (DavidM:Person {name:'David Morse', born:1953})
CREATE (SamR:Person {name:'Sam Rockwell', born:1968})
CREATE (GaryS:Person {name:'Gary Sinise', born:1955})
CREATE (PatriciaC:Person {name:'Patricia Clarkson', born:1959})
CREATE (FrankD:Person {name:'Frank Darabont', born:1959})
CREATE
(TomH)-[:ACTED_IN {roles:['Paul Edgecomb']}]->(TheGreenMile),
(MichaelD)-[:ACTED_IN {roles:['John Coffey']}]->(TheGreenMile),
(DavidM)-[:ACTED_IN {roles:['Brutus "Brutal" Howell']}]->(TheGreenMile),
(BonnieH)-[:ACTED_IN {roles:['Jan Edgecomb']}]->(TheGreenMile),
(JamesC)-[:ACTED_IN {roles:['Warden Hal Moores']}]->(TheGreenMile),
(SamR)-[:ACTED_IN {roles:['"Wild Bill" Wharton']}]->(TheGreenMile),
(GaryS)-[:ACTED_IN {roles:['Burt Hammersmith']}]->(TheGreenMile),
(PatriciaC)-[:ACTED_IN {roles:['Melinda Moores']}]->(TheGreenMile),
(FrankD)-[:DIRECTED]->(TheGreenMile)

CREATE (FrostNixon:Movie {title:'Frost/Nixon', released:2008, tagline:'400 million people were waiting for the truth.'})
CREATE (FrankL:Person {name:'Frank Langella', born:1938})
CREATE (MichaelS:Person {name:'Michael Sheen', born:1969})
CREATE (OliverP:Person {name:'Oliver Platt', born:1960})
CREATE
(FrankL)-[:ACTED_IN {roles:['Richard Nixon']}]->(FrostNixon),
(MichaelS)-[:ACTED_IN {roles:['David Frost']}]->(FrostNixon),
(KevinB)-[:ACTED_IN {roles:['Jack Brennan']}]->(FrostNixon),
(OliverP)-[:ACTED_IN {roles:['Bob Zelnick']}]->(FrostNixon),
(SamR)-[:ACTED_IN {roles:['James Reston, Jr.']}]->(FrostNixon),
(RonH)-[:DIRECTED]->(FrostNixon)

CREATE (Hoffa:Movie {title:'Hoffa', released:1992, tagline:"He didn't want law. He wanted justice."})
CREATE (DannyD:Person {name:'Danny DeVito', born:1944})
CREATE (JohnR:Person {name:'John C. Reilly', born:1965})
CREATE
(JackN)-[:ACTED_IN {roles:['Hoffa']}]->(Hoffa),
(DannyD)-[:ACTED_IN {roles:['Robert "Bobby" Ciaro']}]->(Hoffa),
(JTW)-[:ACTED_IN {roles:['Frank Fitzsimmons']}]->(Hoffa),
(JohnR)-[:ACTED_IN {roles:['Peter "Pete" Connelly']}]->(Hoffa),
(DannyD)-[:DIRECTED]->(Hoffa)

CREATE (Apollo13:Movie {title:'Apollo 13', released:1995, tagline:'Houston, we have a problem.'})
CREATE (EdH:Person {name:'Ed Harris', born:1950})
CREATE (BillPax:Person {name:'Bill Paxton', born:1955})
CREATE
(TomH)-[:ACTED_IN {roles:['Jim Lovell']}]->(Apollo13),
(KevinB)-[:ACTED_IN {roles:['Jack Swigert']}]->(Apollo13),
(EdH)-[:ACTED_IN {roles:['Gene Kranz']}]->(Apollo13),
(BillPax)-[:ACTED_IN {roles:['Fred Haise']}]->(Apollo13),
(GaryS)-[:ACTED_IN {roles:['Ken Mattingly']}]->(Apollo13),
(RonH)-[:DIRECTED]->(Apollo13)

CREATE (Twister:Movie {title:'Twister', released:1996, tagline:"Don't Breathe. Don't Look Back."})
CREATE (PhilipH:Person {name:'Philip Seymour Hoffman', born:1967})
CREATE (JanB:Person {name:'Jan de Bont', born:1943})
CREATE
(BillPax)-[:ACTED_IN {roles:['Bill Harding']}]->(Twister),
(HelenH)-[:ACTED_IN {roles:['Dr. Jo Harding']}]->(Twister),
(ZachG)-[:ACTED_IN {roles:['Eddie']}]->(Twister),
(PhilipH)-[:ACTED_IN {roles:['Dustin "Dusty" Davis']}]->(Twister),
(JanB)-[:DIRECTED]->(Twister)

CREATE (CastAway:Movie {title:'Cast Away', released:2000, tagline:'At the edge of the world, his journey begins.'})
CREATE (RobertZ:Person {name:'Robert Zemeckis', born:1951})
CREATE
(TomH)-[:ACTED_IN {roles:['Chuck Noland']}]->(CastAway),
(HelenH)-[:ACTED_IN {roles:['Kelly Frears']}]->(CastAway),
(RobertZ)-[:DIRECTED]->(CastAway)

CREATE (OneFlewOvertheCuckoosNest:Movie {title:"One Flew Over the Cuckoo's Nest", released:1975, tagline:"If he's crazy, what does that make you?"})
CREATE (MilosF:Person {name:'Milos Forman', born:1932})
CREATE
(JackN)-[:ACTED_IN {roles:['Randle McMurphy']}]->(OneFlewOvertheCuckoosNest),
(DannyD)-[:ACTED_IN {roles:['Martini']}]->(OneFlewOvertheCuckoosNest),
(MilosF)-[:DIRECTED]->(OneFlewOvertheCuckoosNest)

CREATE (SomethingsGottaGive:Movie {title:"Something's Gotta Give", released:2003})
CREATE (DianeK:Person {name:'Diane Keaton', born:1946})
CREATE (NancyM:Person {name:'Nancy Meyers', born:1949})
CREATE
(JackN)-[:ACTED_IN {roles:['Harry Sanborn']}]->(SomethingsGottaGive),
(DianeK)-[:ACTED_IN {roles:['Erica Barry']}]->(SomethingsGottaGive),
(Keanu)-[:ACTED_IN {roles:['Julian Mercer']}]->(SomethingsGottaGive),
(NancyM)-[:DIRECTED]->(SomethingsGottaGive),
(NancyM)-[:PRODUCED]->(SomethingsGottaGive),
(NancyM)-[:WROTE]->(SomethingsGottaGive)

CREATE (BicentennialMan:Movie {title:'Bicentennial Man', released:1999, tagline:"One robot's 200 year journey to become an ordinary man."})
CREATE (ChrisC:Person {name:'Chris Columbus', born:1958})
CREATE
(Robin)-[:ACTED_IN {roles:['Andrew Marin']}]->(BicentennialMan),
(OliverP)-[:ACTED_IN {roles:['Rupert Burns']}]->(BicentennialMan),
(ChrisC)-[:DIRECTED]->(BicentennialMan)

CREATE (CharlieWilsonsWar:Movie {title:"Charlie Wilson's War", released:2007, tagline:"A stiff drink. A little mascara. A lot of nerve. Who said they couldn't bring down the Soviet empire."})
CREATE (JuliaR:Person {name:'Julia Roberts', born:1967})
CREATE
(TomH)-[:ACTED_IN {roles:['Rep. Charlie Wilson']}]->(CharlieWilsonsWar),
(JuliaR)-[:ACTED_IN {roles:['Joanne Herring']}]->(CharlieWilsonsWar),
(PhilipH)-[:ACTED_IN {roles:['Gust Avrakotos']}]->(CharlieWilsonsWar),
(MikeN)-[:DIRECTED]->(CharlieWilsonsWar)

CREATE (ThePolarExpress:Movie {title:'The Polar Express', released:2004, tagline:'This Holiday Season… Believe'})
CREATE
(TomH)-[:ACTED_IN {roles:['Hero Boy', 'Father', 'Conductor', 'Hobo', 'Scrooge', 'Santa Claus']}]->(ThePolarExpress),
(RobertZ)-[:DIRECTED]->(ThePolarExpress)

CREATE (ALeagueofTheirOwn:Movie {title:'A League of Their Own', released:1992, tagline:'Once in a lifetime you get a chance to do something different.'})
CREATE (Madonna:Person {name:'Madonna', born:1954})
CREATE (GeenaD:Person {name:'Geena Davis', born:1956})
CREATE (LoriP:Person {name:'Lori Petty', born:1963})
CREATE (PennyM:Person {name:'Penny Marshall', born:1943})
CREATE
(TomH)-[:ACTED_IN {roles:['Jimmy Dugan']}]->(ALeagueofTheirOwn),
(GeenaD)-[:ACTED_IN {roles:['Dottie Hinson']}]->(ALeagueofTheirOwn),
(LoriP)-[:ACTED_IN {roles:['Kit Keller']}]->(ALeagueofTheirOwn),
(RosieO)-[:ACTED_IN {roles:['Doris Murphy']}]->(ALeagueofTheirOwn),
(Madonna)-[:ACTED_IN {roles:['"All the Way" Mae Mordabito']}]->(ALeagueofTheirOwn),
(BillPax)-[:ACTED_IN {roles:['Bob Hinson']}]->(ALeagueofTheirOwn),
(PennyM)-[:DIRECTED]->(ALeagueofTheirOwn)

CREATE (PaulBlythe:Person {name:'Paul Blythe'})
CREATE (AngelaScope:Person {name:'Angela Scope'})
CREATE (JessicaThompson:Person {name:'Jessica Thompson'})
CREATE (JamesThompson:Person {name:'James Thompson'})

CREATE
(JamesThompson)-[:FOLLOWS]->(JessicaThompson),
(AngelaScope)-[:FOLLOWS]->(JessicaThompson),
(PaulBlythe)-[:FOLLOWS]->(AngelaScope)

CREATE
(JessicaThompson)-[:REVIEWED {summary:'An amazing journey', rating:95}]->(CloudAtlas),
(JessicaThompson)-[:REVIEWED {summary:'Silly, but fun', rating:65}]->(TheReplacements),
(JamesThompson)-[:REVIEWED {summary:'The coolest football movie ever', rating:100}]->(TheReplacements),
(AngelaScope)-[:REVIEWED {summary:'Pretty funny at times', rating:62}]->(TheReplacements),
(JessicaThompson)-[:REVIEWED {summary:'Dark, but compelling', rating:85}]->(Unforgiven),
(JessicaThompson)-[:REVIEWED {summary:"Slapstick redeemed only by the Robin Williams and Gene Hackman's stellar performances", rating:45}]->(TheBirdcage),
(JessicaThompson)-[:REVIEWED {summary:'A solid romp', rating:68}]->(TheDaVinciCode),
(JamesThompson)-[:REVIEWED {summary:'Fun, but a little far fetched', rating:65}]->(TheDaVinciCode),
(JessicaThompson)-[:REVIEWED {summary:'You had me at Jerry', rating:92}]->(JerryMaguire)

WITH TomH as a
MATCH (a)-[:ACTED_IN]->(m)<-[:DIRECTED]-(d) RETURN a,m,d LIMIT 10;
```

查询：

MATCH (tom {name: "Tom Hanks"}) RETURN tom

官网提供了非常丰富的案例

![1589791732877](NoSQL.assets/1589791732877.png)



查询 名为Tom Hanks的人，参演过哪些电影

MATCH (tom:Person {name: "Tom Hanks"})-[:ACTED_IN]->(tomHanksMovies) RETURN tom,tomHanksMovies

![1589791880305](NoSQL.assets/1589791880305.png)

查询谁导演了  Cloud Atals电影？

MATCH (cloudAtlas {title: "Cloud Atlas"})<-[:DIRECTED]-(directors) RETURN directors.name

其余示例，参见官方文档。



删除

match (p:Person),(m:Movie)

optional match (p)-[r1]-() , (m)-[r2]-()

delete  p,m,r1,r2



### Java Neo4j API

![1589792376971](NoSQL.assets/1589792376971.png)

https://github.com/neo4j/neo4j-documentation/下载和服务器版本一致的 （3.5.14）neo4j的java api案例



简化版：

创建项目

导入依赖

```xml
<!-- https://mvnrepository.com/artifact/org.neo4j/neo4j -->
<dependency>
    <groupId>org.neo4j</groupId>
    <artifactId>neo4j</artifactId>
    <version>3.5.14</version>
</dependency>


```

测试官方示例EmbeddedNeo4j.java （运行前，需要将web关闭）

```java
/*
 * Licensed to Neo4j under one or more contributor
 * license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright
 * ownership. Neo4j licenses this file to you under
 * the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.yanqun.demo;

import org.neo4j.graphdb.*;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.io.fs.FileUtils;

import java.io.File;
import java.io.IOException;

public class EmbeddedNeo4j
{
    //数据库 目录
    //D:\dev\neo4j\db\\
    private static final File databaseDirectory = new File( "D:\\dev\\neo4j\\db\\neo4jDatabases\\database-9768f3fc-3b4a-436f-b31f-749e47ec2376\\installation-3.5.14\\data\\databases" );

    public String greeting;

    // tag::vars[]
    GraphDatabaseService graphDb;
    Node firstNode;
    Node secondNode;
    Relationship relationship;
    // end::vars[]

    // tag::createReltype[]
    private enum RelTypes implements RelationshipType
    {
        //节点之间的关系 （认识，熟悉，喜欢）
        KNOWS
    }
    // end::createReltype[]

    public static void main( final String[] args ) throws IOException
    {
        EmbeddedNeo4j hello = new EmbeddedNeo4j();
        hello.createDb();
        hello.removeData();
        hello.shutDown();
    }

    void createDb() throws IOException
    {
        //删除库
        FileUtils.deleteRecursively( databaseDirectory );

        // tag::startDb[]
        //创建新库
        graphDb = new GraphDatabaseFactory().newEmbeddedDatabase( databaseDirectory );

        registerShutdownHook( graphDb );//jvm回调钩子
        // end::startDb[]

        // tag::transaction[]
        try ( Transaction tx = graphDb.beginTx() )//jdk7
        {
            // Database operations go here
            // end::transaction[]
            // tag::addData[]
            firstNode = graphDb.createNode();
            firstNode.setProperty( "message", "Hello, " );


            secondNode = graphDb.createNode();
            secondNode.setProperty( "message", "World!" );

            relationship = firstNode.createRelationshipTo( secondNode, RelTypes.KNOWS );
            relationship.setProperty( "message", "brave Neo4j " );
            // end::addData[]

            // tag::readData[]
            System.out.print( firstNode.getProperty( "message" ) );
            System.out.print( relationship.getProperty( "message" ) );
            System.out.print( secondNode.getProperty( "message" ) );
            // end::readData[]

            greeting = ( (String) firstNode.getProperty( "message" ) )
                       + ( (String) relationship.getProperty( "message" ) )
                       + ( (String) secondNode.getProperty( "message" ) );

            // tag::transaction[]
            tx.success();
        }
        // end::transaction[]
    }

    void removeData()
    {
        try ( Transaction tx = graphDb.beginTx() )
        {
            // tag::removingData[]
            // let's remove the data
            firstNode.getSingleRelationship( RelTypes.KNOWS, Direction.OUTGOING ).delete();
            firstNode.delete();
            secondNode.delete();
            // end::removingData[]

            tx.success();
        }
    }

    void shutDown()
    {
        System.out.println();
        System.out.println( "Shutting down database ..." );
        // tag::shutdownServer[]
        graphDb.shutdown();
        // end::shutdownServer[]
    }

    // tag::shutdownHook[]
    private static void registerShutdownHook( final GraphDatabaseService graphDb )
    {
        // Registers a shutdown hook for the Neo4j instance so that it
        // shuts down nicely when the VM exits (even if you "Ctrl-C" the
        // running application).
        //通过回调钩子实现：当JVM关闭时，自动关闭neo4j关闭
        Runtime.getRuntime().addShutdownHook( new Thread()
        {
            @Override
            public void run()
            {
                graphDb.shutdown();
            }
        } );
    }
    // end::shutdownHook[]
}

```

更多API介绍：http://neo4j.com/docs/  