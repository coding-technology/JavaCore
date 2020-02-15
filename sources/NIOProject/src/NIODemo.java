import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

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


    public static void test5(){
/*      FileChannel.open()  ->Channel
        FileInputStream/FileOutputStream/RandomAccessFile:getChannel() ;
        Socket/ServerSocket/DatagramSocket:getChannel()
*/
    }

    //   尝试使用 buffer和channel进行 文件复制
//    使用非直接缓冲区
    public static void test6(){
        long start = System.currentTimeMillis();
        FileInputStream input = null ;
        FileOutputStream out = null ;
        FileChannel inChannel = null ;
        FileChannel outChannel = null ;
        try{
        ByteBuffer buffer = ByteBuffer.allocate(1024);//非直接缓冲区

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

    //   尝试使用 buffer和channel进行 文件复制
//    使用直接缓冲区
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
    public static void test10(){
        long start = System.currentTimeMillis();
        try{
            FileChannel inChannel = FileChannel.open(Paths.get("d:/mvnrep.rar"));
            FileChannel outChannel = FileChannel.open( Paths.get("d:/mvnrep2.rar"),StandardOpenOption.WRITE,StandardOpenOption.READ,StandardOpenOption.CREATE );

            //零拷贝
            inChannel.transferTo( 0,inChannel.size(),outChannel ) ;
//            outChannel.transferFrom( 0,inChannel.size(),inChannel ) ;
            inChannel.close();
            outChannel.close();
            long end = System.currentTimeMillis();
            System.out.println("零拷贝时间："+  (end-start));

        }catch (Exception e){
            e.printStackTrace();
        }
    }

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


    public static void main(String[] args)  throws Exception {
//        test1();
//        test2();
//        test3();
//        test4() ;
//        test6() ; //使用非直接缓冲区 复制
//        test7() ;//使用直接缓冲区 复制
//        test8() ;//使用直接缓冲区 复制（物理映射文件）
//        test9() ;
//        test10() ;
//        server();//非直接 (由于服务端，无法直接感知 客户端发送的文件大小，因此无法精准的开辟一块合适的 直接缓冲区大小)
//        client();//非直接
//        client2();//直接



    }

}
