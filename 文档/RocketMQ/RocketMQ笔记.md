# 消息队列（Message Queue）



## 作用

- 削峰填谷

  ​	秒杀。 可以将 峰值的流量 转移到 其他地方（谷）

- 解耦合

  ​	生产者 和 消费者 可以通过 mq解耦合

  生产者-mq-消费者

- 异步消息

  生产者-mq-消费者



## 主流MQ产品

​	协议：AMQP （Advanced Message QUeuing Protocol,高级消息队列协议）  、XMPP、  SMTP、 STMP

- ActiveMQ： apache ,多种协议（AMQP ，MQTT,OpenWire,Stomp ） , java ，将数据直接持久化到数据库中，可靠性较低

- RabbitMQ: 基于AMQP ， 运行Erlang语言，性能高，高并发，可靠性高
- Kafaka: linkedin开源MQ，完全分布式架构，高吞吐量（单机10w/s） ，设计之初 是为了 处理 日志 ， 大量数据的数据收集业务
- RocketMQ：阿里巴巴->Apache ,  双十一 ，Java/C++/go语言，支持多协议(AMQP,XMPP,STMP,STOP）,分布式 易扩展 ， 亿级消息堆积能力（单机1万以上 持久化队列）
- 其他MQ: Redis , ZeroMQ



## RocketMQ

## 角色

- Producer:生产者
- Consumer：消费者
  - Push Consumer: Broker将消息 推 给 消费者
  - Pull Consumer: 消费者 请求 Broker将 消息发送给我（拉）
- Broker:MQ消息服务
- Producer Group: 生产者集合
- Consumer Group：消费者集合



## 搭建RoketMQ环境

centos7 :  192.168.2.128   root/root   

下载

http://rocketmq.apache.org/release_notes/release-notes-4.4.0/  :binary

上传到centos7中

解压

tar -zxvf    XXX.tar.gz

unzip Xxx.zip



配置：--配置master开始--

​	nameserver: 协调多个rocketmq

​	master : rocketmq主节点

域名映射：

vi /etc/hosts

192.168.2.128 mqnameserver1
192.168.2.128 mqmaster1



存储路径 ：mkdir mqstore

mkdir mqstore/commitlog

mkdir mqstore/consumequeue

mkdir mqstore/index



配置消息队列: broker

路径：/usr/rocketmq/conf

2m-2s-async  ：  2m   两个master , 2s 两个slaver  ,async：异步



配置单机版： broker-a.properties :

brokerId：   0表示master              >0 表示slaver

全部内容

```
brokerClusterName=DefaultCluster
brokerName=broker-a
brokerId=0
deleteWhen=04
fileReservedTime=48
brokerRole=ASYNC_MASTER
flushDiskType=ASYNC_FLUSH
namesrvAddr=mqnameserver1:9876
defaultTopicQueueNums=4
autoCreateTopicEnable=true
listenPort=10911
deleteWhen=04
fileReservedTime=48
storePathRootDir=/usr/rocketmq/mqstore
storePathCommitLog=/usr/rocketmq/mqstore/commitlog
storePathConsuQueue=/usr/rocketmq/mqstore/consumequeue
storePathIndex=/usr/rocketmq/mqstore/index
maxMessageSize=65536
brokerRole=ASYNC_MASTER
flushDiskType=ASYNC_FLUSH
```



配置日志：

一次性的将 所有xml中的 ${user.home} 替换为/usr/rocketmq

sed -i 's#${user.home}#/usr/rocketmq#g'  *.xml



启动参数：

​	将bin/runbroker.sh      runserver.sh ：

JAVA_OPT="${JAVA_OPT} -server -Xms1g -Xmx1g -Xmn1g 



启动Namesrv：

​	bin中： nohup sh mqnamesrv &

查看进程：

NamesrvStartup 说明启动Namesrv成功



启动BrokerServer:

nohup sh mqbroker -c /usr/rocketmq/conf/2m-2s-async/broker-a.properties &

查看进程：

BrokerStartup  说明broker启动成功



## 控制台（Web界面）

下载

https://github.com/apache/rocketmq-externals

解压缩

导入工程（修改maven,改为自己本地配置的maven）



maven加速：

阿里云加速

在本地maven的配置文件settings.xml中 加入以下：

```
    <mirror>  
      <id>alimaven</id>  
      <name>aliyun maven</name>  
      <url>http://maven.aliyun.com/nexus/content/groups/public/</url>  
      <mirrorOf>central</mirrorOf>          
    </mirror>
```

在application.properties中配置 mq服务地址

```
rocketmq.config.namesrvAddr=192.168.2.128:9876
```



也可以将控制台工程打成jar，然后运行Jar即可：

打成jar:

mvn clean package -Dmaven.test.skip=true

执行：

java -jar   jar包名字.jar





## 创建mq工程

maven工程 (修改maven配置，同上一步)

pom.xml

```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.1.3.RELEASE</version>
    </parent>

    <groupId>com.yanqun</groupId>
    <artifactId>MyRocketMQ</artifactId>
    <version>1.0-SNAPSHOT</version>
    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <java.version>1.8</java.version>
        <guava.version>16.0.1</guava.version>
        <commons-digester.version>2.1</commons-digester.version>
        <commons-lang.version>2.6</commons-lang.version>
        <commons-io.version>2.4</commons-io.version>
        <commons-cli.version>1.2</commons-cli.version>
        <rocketmq.version>4.4.0</rocketmq.version>
        <surefire.version>2.19.1</surefire.version>
        <aspectj.version>1.6.11</aspectj.version>
        <main.basedir>${basedir}/../..</main.basedir>
        <docker.image.prefix>styletang</docker.image.prefix>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>commons-collections</groupId>
            <artifactId>commons-collections</artifactId>
            <version>3.2.2</version>
        </dependency>
        <dependency>
            <groupId>org.apache.rocketmq</groupId>
            <artifactId>rocketmq-client</artifactId>
            <version>${rocketmq.version}</version>
        </dependency>
    </dependencies>


</project>
```



如果在控制台查询消息时 topic无反应（或者报错desc208） ，原因是rocketmq内部问题（ac认证问题）







## 第一个MQ程序



```
public class CONST {
    public static final String NAMESERVER_ADDR = "192.168.2.128:9876" ;
}

```





生产者：

```
package com.yanqun.producer;

import com.yanqun.api.CONST;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

public class MyProducer {

    public static void main(String[] args) {
        //创建生产者
        DefaultMQProducer producer = new DefaultMQProducer("myProducer");
        producer.setNamesrvAddr(CONST.NAMESERVER_ADDR);
        try {
            producer.start(); //启动生产者
        } catch (MQClientException e) {
            e.printStackTrace();
        }
        //生产消息（生产主题和数据）
        for(int i=0;i<10;i++){
            /*
                    topic:主题（一级目录）
                    tags:标签（二级目录）
                    keys + body :  以key-value的形式 存放内容
             */
            Message message = new Message("mytopic1","mytag11", "key"+i,("mymq"+i) .getBytes());
            //生产者发送消息
            try {
                SendResult result = producer.send(message);
                System.out.println("发送成功："+ result);

            } catch (MQClientException e) {
                e.printStackTrace();
            } catch (RemotingException e) {
                e.printStackTrace();
            } catch (MQBrokerException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        producer.shutdown();


    }
}

```



消费者

```
package com.yanqun.comsumer;

import com.yanqun.api.CONST;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class MyConsumer {
    public static void main(String[] args) {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("myConsumer");
        consumer.setNamesrvAddr(CONST.NAMESERVER_ADDR);

        consumer.setConsumeFromWhere( ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET );
        try {
            consumer.subscribe("mytopic1","*");
            //设置监听器：当生产者生产数据时，将数据推送给 消费者
            consumer.registerMessageListener(new MessageListenerConcurrently() {
                @Override
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                    MessageExt messageExt =   list.get(0) ;
                    String topic = messageExt.getTopic();
                    String tags = messageExt.getTags();
                    String keys = messageExt.getKeys();
                    try {
                        String body =   new String(  messageExt.getBody(), RemotingHelper.DEFAULT_CHARSET);
                        System.out.println("消费："+topic+ "，\t"+tags+ "，\t"+keys+ "，\t"+body);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }


                    return  ConsumeConcurrentlyStatus.CONSUME_SUCCESS;//此条消息消费成功，继续下一个...
                }
            });

            consumer.start();


        } catch (MQClientException e) {
            e.printStackTrace();
        }

    }
}

```



批量删除 maven下载失败的jar：for /r %i in (*.lastUpdated) do del %i





## mq集群：主从同步

​		

将master关闭：

[root@bigdata01 bin]# ./mqshutdown broker

[root@bigdata01 bin]# ./mqshutdown namesrv



之前已经存在了master，现在配置slaver:

域名映射：

vi /etc/hosts

192.168.2.128 mqnameserver1
192.168.2.128 mqmaster1

192.168.2.129 mqnameserver2
192.168.2.129 mqmaster1slaver1

将master节点上的 mq远程复制到slaver节点上  :scp -r rocketmq/ root@192.168.2.129:/usr/



/usr/rocketmq/conf/2m-2s-async/ broker-a.properties :

需要修改内容

```

brokerId=0
rokerRole=ASYNC_MASTER
namesrvAddr=mqnameserver1:9876;mqnameserver2:9876

```

broker-a-s.properties :

```
brokerClusterName=DefaultCluster
brokerName=broker-a
brokerId=1
deleteWhen=04
fileReservedTime=48
brokerRole=SLAVE
flushDiskType=ASYNC_FLUSH
namesrvAddr=mqnameserver1:9876;mqnameserver2:9876
defaultTopicQueueNums=4
autoCreateTopicEnable=true
listenPort=10911
deleteWhen=04
fileReservedTime=48
storePathRootDir=/usr/rocketmq/mqstore
storePathCommitLog=/usr/rocketmq/mqstore/commitlog
storePathConsuQueue=/usr/rocketmq/mqstore/consumequeue
storePathIndex=/usr/rocketmq/mqstore/index
maxMessageSize=65536
flushDiskType=ASYNC_FLUSH
```



问题：

master: 主配置文件、从配置文件

slaver:主配置文件、从配置文件



启动master-slaver:

先启动master（192.168.2.128中启动）

启动Namesrv：

​	bin中： nohup sh mqnamesrv &

启动BrokerServer:

nohup sh mqbroker -c /usr/rocketmq/conf/2m-2s-async/broker-a.properties &



再启动slaver（192.168.2.129中启动）

bin中： nohup sh mqnamesrv &

nohup sh mqbroker -c /usr/rocketmq/conf/2m-2s-async/broker-a-s.properties  &



修改项目的namersrv地址

```
rocketmq.config.namesrvAddr=192.168.2.128:9876;192.168.2.129:9876
```

启动客户端



验证主从：

挂掉master, 查看能否从slaver中取数据

一般： 向Master中写书，从slaver中读数据



挂掉kill -9 进程号

可以发现，当mater挂掉后，仍然可以从slaver中消费数据





## 发送消息的类型

异步

```java
          //2发送异步消息
                /*
                发送异步消息之后：有2个线程：a.Main线程，发送完毕 立刻执行以后的程序 ；
                                b.处理消息的线程 ，并在处理完毕后 触发回调函数onSuccess（）\onException()

                producer.send(message, new SendCallback() {
                    @Override
                    public void onSuccess(SendResult sendResult) {
                        System.out.println("发送成功："+sendResult);
                    }
                    @Override
                    public void onException(Throwable throwable) {
                        System.out.println("发送失败，异常："+ throwable.getMessage());
                    }
                }); */
```



同步

```
 SendResult result = producer.send(message);
//System.out.println("发送成功："+ result);
```



单向发送

```
 producer.sendOneway(message);//只发送，不接收返回值，不可靠的消息 ；不重要的数据，日志
```



## 消费模式

默认集群模式：

```
   consumer.setMessageModel(MessageModel.CLUSTERING);
```

搭建消费者集群：只需要将groupName设置相同即可



messageDelayLevel = 1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h

























​		