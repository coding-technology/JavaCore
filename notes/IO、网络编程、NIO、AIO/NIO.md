# NIO

IO存在一些问题：

- 服务端接收客户端的accept()方法是一个阻塞式方法，该方法会一直阻塞，直到有客户端发来连接。因此，服务端在等待期间（阻塞）会什么也不做，造成资源浪费。
- 如果在高并发环境下，服务端需要通过开辟线程来分别处理各个客户端请求。但是每开辟一个线程 大约会消耗1mb内存，因此在高并发环境下对内存等资源的损耗也是非常大的。

以上可以通过NIO进行优化。

NIO: new IO、non blocking io  (jdk1.4之后提供的)  ,高效率实现IO技术

IO：面向流的操作、阻塞式

NIO：面向通道、非阻塞式



NIO三个组成： 缓冲区buffer、通道Channel、选择器Selector

![1576723176902](NIO.assets/1576723176902.png)

## 缓冲区  

java.nio.Buffer ：数组

实现类（7个）：ByteBuffer、ShortBuffer、IntBuffer、LongBuffer、FloatBuffer、DoubleBuffer、CharBuffer（没有BooleanBuffer、StringBuffer）

## ByteBuffer

    private int mark = -1;
    private int position = 0;
    private int limit;
    private int capacity;
    long address;
position：下一个读或者写的元素的位置

![1576723936481](NIO.assets/1576723936481.png)



![1576723960621](NIO.assets/1576723960621.png)



limit：buffer能够容纳的最大元素个数(limit及之后的位置 不能使用）

![1576724070118](NIO.assets/1576724070118.png)

capacity：buffer的最大容量（相当于数组的Length），一旦创建后 不能改变。



mark:标记一个位置，当Poistion后移时可以通过reset（）回归到此mark的位置。

![1576724249186](NIO.assets/1576724249186.png)

address: 堆外内存地址。

前四个属性的大小关系：

```
mark <= position <= limit <= capacity
```



## Buffer的方法

flip():将写模式转为读模式

```java
    public final Buffer flip() {
        limit = position;
        position = 0;
        mark = -1;
        return this;//链式写法.  buffer.flip().xx()
    }
```

实际应用：

**（1）写操作->读操作**：flip()

![1577410834061](NIO.assets/1577410834061.png)

写操作put()                   读操作get()

buffer.put() ;

buffer.flip();//写操作->读操作

buffer.get()

（2）/（3）读操作->写操作   /   写操作 -> 写操作 clear()   ,即重新进行写操作 

```java
    public final Buffer clear() {
        position = 0;
        limit = capacity;
        mark = -1;
        return this;
    }

```

注意：clear()只是将标记归位，但元素值并没有删除，而是处于一种被“遗忘”的状态

（4）读->读:rewind() ,注意rewind()不会改变limit的值。因为 本次的操作是“读"到”读"，两次的模式是相同的。

```java
    public final Buffer rewind() {
        position = 0;
        mark = -1;
        return this;
    }
```

（5） mark()/reset()

buffer案例

```java
import java.nio.ByteBuffer;

/*
 * Created by 颜群
 */
public class NIODemo {

    public static  void test1(){
        ByteBuffer buffer = ByteBuffer.allocate(100);// new byte[100]
        System.out.println("position:" + buffer.position());
        System.out.println("limit:" + buffer.limit());
        System.out.println("capacity（一旦定义不可改变）:" + buffer.capacity());


        //put()
        System.out.println("put()...\n");
        buffer.put("hello".getBytes()   ) ;
        System.out.println("position:" + buffer.position());
        System.out.println("limit:" + buffer.limit());

        //切换模式  写->读：flip()
        System.out.println("flip()...\n");
        buffer.flip() ;
        System.out.println("position:" + buffer.position());
        System.out.println("limit:" + buffer.limit());

        //get()
        System.out.println("get()...\n");
        byte[] bs = new byte[ buffer.limit()];
        buffer.get( bs ) ;

        System.out.println("读取到的数据：" +new String( bs));
        System.out.println("position:" + buffer.position());
        System.out.println("limit:" + buffer.limit());
    }

    public static  void test2(){
        ByteBuffer buffer = ByteBuffer.allocate(8);
        for(int i=0;i<buffer.capacity();i++){
            buffer.put( (byte) i ) ;// 0 1 2 ... 7
        }

        buffer.position(2) ;
        buffer.limit(5);

        ByteBuffer sliceBuffer = buffer.slice();//获取原buffer的局部引用（局部范围：position - limit）
        for(int i=0;i<sliceBuffer.capacity();i++)
        {
            byte b = sliceBuffer.get(i);
            b += 100 ;
            sliceBuffer.put(i,b) ;
        }

        buffer.position(0) ;
        buffer.limit(  buffer.capacity())  ;

        while(buffer.hasRemaining()){ //迭代器
            System.out.println( buffer.get()  );
        }


    }

    public static  void test3(){

        ByteBuffer buffer = ByteBuffer.allocate(100);
        buffer.put("helloworld".getBytes()) ;//position:0 ->10
        buffer.mark();//mark: 10
        System.out.println("position:"+buffer.position());
        System.out.println("mark:"+buffer.mark().position());
        buffer.put("123".getBytes()) ; ////position:10 ->13

        System.out.println("position:"+buffer.position());
        buffer.reset() ;//将position恢复到mark的位置
        System.out.println("position:"+buffer.position());

        byte[] bs = new byte[5] ;
        buffer.get(bs ,  2 ,3) ;//三个参数：存放容器，从第几位开始，取几位

        System.out.println(bs[0]);
        System.out.println(bs[1]);
        System.out.println(bs[2]);
        System.out.println(bs[3]);
        System.out.println(bs[4]);
        System.out.println( new String(bs));

    }

    public static void main(String[] args) {
//        test1();
//        test2();
        test3();
    }
}

```

运行结果：

```

position:10
mark:10
position:13
position:10
0
0
49
50
51
123

```

![1578454922916](NIO.assets/1578454922916.png)



```java
    public static void test4(){
        ByteBuffer buffer = ByteBuffer.allocate(100);
        buffer.put("helloworld".getBytes()) ;
        //写->读
        buffer.flip() ;
        byte[] bs = new byte[ buffer.limit()] ;
        buffer.get( bs) ;
        System.out.println(new String(bs)   );

        //读->读
        buffer.rewind() ;
        buffer.get( bs) ;
        System.out.println(new String(bs));

        //clear():并没有真正的删除元素，只是将元素处于一个被 遗忘的状态
        buffer.clear();
        System.out.println(  (char)buffer.get(2) );//unicode (ascii)

    }
```

![1578555644871](NIO.assets/1578555644871.png)

![1578555942886](NIO.assets/1578555942886.png)

## Channel 通道

通道负责 对buffer中数据的传输，通道本身不存储数据，只负责对数据的传输。通道是双向的（不同于IO中是单向）。

![1578556055094](NIO.assets/1578556055094.png)



常见获取channel对象的方法：

```
FileChannel.open()  ->Channel        FileInputStream/FileOutputStream/RandomAccessFile:getChannel() ;        Socket/ServerSocket/DatagramSocket:getChannel()
```

作业：尝试使用 buffer和channel进行 文件复制

方式1：使用非直接缓冲区

```java
    //   尝试使用 buffer和channel进行 文件复制
//    使用非直接缓冲区
    public static void test6(){

        long start = System.currentTimeMillis();
        FileInputStream input = null ;
        FileOutputStream out = null ;
        FileChannel inChannel = null ;
        FileChannel outChannel = null ;
        try{


        ByteBuffer buffer = ByteBuffer.allocate(1024);

         input = new FileInputStream("d:/mvnrep.rar");
         out = new FileOutputStream("d:/mvnrep2.rar");

         inChannel = input.getChannel();
         outChannel = out.getChannel();

        while(inChannel.read(buffer  )  !=-1){//读
            buffer.flip() ;
            outChannel.write(buffer)  ;//写
            buffer.clear() ;
        }
            long end = System.currentTimeMillis();
            System.out.println("复制操作消费时间:" + (end-start));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
            if(outChannel != null) outChannel.close();
            if(inChannel != null) inChannel.close();
            if(out != null) out.close();
            if(input != null) input.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
```

复制操作消费时间:4941



方式2：使用直接缓冲区  （本例代码，不建议使用。因为：让直接缓冲区发挥真正作用 必须借助于：零拷贝、内存映射等手段，而不能用本代码的方法）

```java
//    使用直接缓冲区 (本代码不建议使用)
    public static void test7(){
        long start = System.currentTimeMillis();
        FileInputStream input = null ;
        FileOutputStream out = null ;
        FileChannel inChannel = null ;
        FileChannel outChannel = null ;
        try{
            ByteBuffer buffer = ByteBuffer.allocateDirect(1024);//直接缓冲区

            input = new FileInputStream("d:/mvnrep.rar");
            out = new FileOutputStream("d:/mvnrep3.rar");

            inChannel = input.getChannel();
            outChannel = out.getChannel();

            while(inChannel.read(buffer  )  !=-1){//读
                buffer.flip() ;
                outChannel.write(buffer)  ;//写
                buffer.clear() ;
            }
            long end = System.currentTimeMillis();
            System.out.println("复制操作消费时间:" + (end-start));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(outChannel != null) outChannel.close();
                if(inChannel != null) inChannel.close();
                if(out != null) out.close();
                if(input != null) input.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
```

复制操作消费时间:4710



通过源码可知，DirectByteBuffer就是直接缓冲区（实现类）

![1581317977420](NIO.assets/1581317977420.png)









```
class DirectByteBuffer    extends MappedByteBuffer    implements DirectBuffer
```

MappedByteBuffer:内存映射文件（物理映射文件）

DirectBuffer：直接缓冲区



## 使用非直接缓冲区、直接缓冲区的流程区别

## 1使用非直接缓冲区实现：复制

![1581319314784](NIO.assets/1581319314784.png)







## 2使用非直接缓冲区实现：复制



![1581319736373](NIO.assets/1581319736373.png)

以上，也成为零拷贝

API层面： MappredByteBuffer类  、FileChannel中的 transferFrom()/transferTo()方法来实现零拷贝。



使用 零拷贝（直接缓冲区）实现文件的复制

```java
    //   尝试使用 buffer和channel进行 文件复制
//    使用直接缓冲区(零拷贝)
    public static void test8(){
        long start = System.currentTimeMillis();
        
        //channel buffer
        try {
            FileChannel inChannel = FileChannel.open(Paths.get("D:/mvnrep.rar") );
            FileChannel outChannel = FileChannel.open(Paths.get("D:/mvnrep2.rar") , StandardOpenOption.WRITE, StandardOpenOption.READ,StandardOpenOption.CREATE);

            //物理映射文件
            MappedByteBuffer inMappedBuf = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
            MappedByteBuffer outMappedBuf = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());



            byte[] st = new byte[  inMappedBuf.limit()  ] ;
            inMappedBuf.get( st);
            outMappedBuf.put( st);
            inChannel.close();
            outChannel.close();
            long end = System.currentTimeMillis();
            System.out.println("直接缓冲区（物理映射文件）,花费时间："+ (end-start));

        }catch(Exception e){
                e.printStackTrace();
         }
    }
```

直接缓冲区（物理映射文件）,花费时间：785



物理映射文件：

![1581400883359](NIO.assets/1581400883359.png)



只需要修改MappedXxBuffer，操作系统会自动将此对象中的数据同步到硬盘文件中

```java
   public static void test9(){
        try{
            RandomAccessFile raf = new RandomAccessFile( "d:/a.txt","rw") ;
            FileChannel fileChannel = raf.getChannel();
            //只修改内存中的mappedByteBuffer
            MappedByteBuffer mappedByteBuffer  = fileChannel.map( FileChannel.MapMode.READ_WRITE, 0,raf.length());
            mappedByteBuffer.put(1,(byte)'X') ;
            mappedByteBuffer.put(4,(byte)'Y') ;
            //硬盘中的文件会自动修改（操作系统的功能）
            raf.close();
        }catch (Exception e){
            e.printStackTrace();
        }


    }
```



## 使用FileChannel中的 transferFrom()/transferTo()方法来实现零拷贝

![1581402191363](NIO.assets/1581402191363.png)

```java
    public static void test10(){
        long start = System.currentTimeMillis();
        try{
            FileChannel inChannel = FileChannel.open(Paths.get("d:/mvnrep.rar"));
            FileChannel outChannel = FileChannel.open( Paths.get("d:/mvnrep2.rar"),StandardOpenOption.WRITE,StandardOpenOption.READ,StandardOpenOption.CREATE );

            //零拷贝
            inChannel.transferTo( 0,inChannel.size(),outChannel ) ;
//          outChannel.transferFrom( 0,inChannel.size(),inChannel ) ;
            inChannel.close();
            outChannel.close();
            long end = System.currentTimeMillis();
            System.out.println("零拷贝时间："+  (end-start));

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
```

零拷贝时间：370

提示：零拷贝用到的 transferTo\transferFrom需要依赖于操作系统底层的支持（目前主流的基本都支持）

## NIO实现文件发送

IO发送文件

![1581492574119](NIO.assets/1581492574119.png)

由于直接缓冲区处于JVM以外（在操作系统中内存） ，因此无法被GC及时回收。故，建议只在 那些不经常改变、 持续重用的项目中使用 直接缓冲区



```java

    public static void server() throws Exception {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();//类似于IO技术中的 ServerSocket
        serverSocketChannel.bind(  new InetSocketAddress(8888)) ;
        SocketChannel sChannel = serverSocketChannel.accept();
        System.out.println("客户端连接成功...");
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        long start = System.currentTimeMillis();
        FileChannel outChannel = FileChannel.open(Paths.get("d:/123.rar"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);

        while(sChannel.read(buffer ) != -1){ //3.接收
            buffer.flip();
            outChannel.write(buffer); //4.输出
            buffer.clear();
        }
        sChannel.close();
        outChannel.close();
        serverSocketChannel.close();
        long end = System.currentTimeMillis();
        System.out.println("接收："+(end-start));


    }

    public static void client()  throws Exception {
        long start  = System.currentTimeMillis();
        SocketChannel socketChannel = SocketChannel.open(  new InetSocketAddress( "127.0.0.1" ,8888) );
        FileChannel inFileChannel = FileChannel.open(Paths.get("D:/mvnrep.rar"), StandardOpenOption.READ);
        //buffer+channel
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        while(   inFileChannel.read( buffer )  != -1){//1.读取
            buffer.rewind() ;
            socketChannel.write(  buffer) ;//2.发送
            buffer.clear() ;
        }

        if(inFileChannel!= null) inFileChannel.close();
        if(socketChannel!= null) socketChannel.close();
        long end  = System.currentTimeMillis();
        System.out.println("客户端：" + (end-start));

    }

    public static void client2() throws  Exception{
        long start  = System.currentTimeMillis();
        SocketChannel socketChannel = SocketChannel.open(  new InetSocketAddress( "127.0.0.1" ,8888) );
        FileChannel inFileChannel = FileChannel.open(Paths.get("D:/mvnrep.rar"), StandardOpenOption.READ);
        //使用直接缓冲区（零拷贝）
        inFileChannel.transferTo(0,  inFileChannel.size() ,socketChannel  ) ;  // a   transferFrom/transferTo  b
        long end  = System.currentTimeMillis();
        System.out.println("直接缓冲区方式（客户端）发送文件："+(end-start));

        inFileChannel.close();
        socketChannel.close();
    }
```



## 选择器：

传统IO方式的弊端

![1581579853718](NIO.assets/1581579853718.png)



NIO如何解决以上 上下文切换、开辟线程内存问题？ 通过选择器，思路：多个用户可以用 “通道+缓存”  表示，当有多个用户访问时，选择器 可以根据时间，找那些特定（准备就绪）的用户 进行处理。

选择器：切换通道；谁准备好，指向谁。



案例：使用NIO实现“聊天室功能”

![1581581712414](NIO.assets/1581581712414.png)



















