package com.yanqun.parents;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

//public class MyException extends Exception{...}
public class MyClassLoaderImpl  extends ClassLoader{
    public  String path ; //null
    //优先使用的类加载器是：getSystemClassLoader()
    public MyClassLoaderImpl(){
        super();
    }

    public MyClassLoaderImpl(ClassLoader parent){//扩展类加载器
        super(parent);
    }
    //com.yq.xx.class
    public Class findClass(String name) {
//            System.out.println("findClass...");
        byte[] b = loadClassData(name);
        return defineClass(name, b, 0, b.length);
    }

    //“com/yq/xxx.class” ->  byte[]
    private byte[] loadClassData(String name)  {
        System.out.println("自定义加载器...");
//              System.out.println("加载loadClassData...");
        if(path != null){//name: com.yanqun.parents.MyDefineCL
//                  System.out.println("去D盘加载;;");
            name = path+ name.substring(  name.lastIndexOf(".")+1  )+".class" ;
        }

        byte[] result = null ;
        FileInputStream inputStream = null ;
        ByteArrayOutputStream output = null ;
        try {
            inputStream = new FileInputStream( new File(  name)  );
            //inputStream -> byte[]
            output = new ByteArrayOutputStream();

            byte[] buf = new byte[2];
            int len = -1;
            while ((len = inputStream.read(buf)) != -1) {
                output.write(buf, 0, len);
            }
            result = output.toByteArray();
        }catch (Exception e){
            e.printStackTrace(); ;
        }finally {
            try {
                if(inputStream != null )inputStream.close();
                if(output != null ) output.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return result ;
    }

    public static void main(String[] args) throws Exception {
//        System.out.println("main...");
        //自定义加载器的对象
//        MyClassLoaderImpl myClassLoader = new MyClassLoaderImpl();//默认在双亲委派时，会根据正规流程：系统——》扩展->根
//        myClassLoader.path = "d:/" ;
//        Class<?> aClass = myClassLoader.loadClass("com.yanqun.parents.MyDefineCL");
//        System.out.println(aClass.hashCode());

        MyClassLoaderImpl myClassLoader2 = new MyClassLoaderImpl();//默认在双亲委派时，会根据正规流程：系统——》扩展->根
        Class<?> aClass2 = myClassLoader2.loadClass("com.yanqun.parents.MyDefineCL");
        System.out.println(aClass2.hashCode());


//        System.out.println(aClass.getClassLoader());
//        MyDefineCL myDefineCL = (MyDefineCL)( aClass.newInstance()) ;
    }

    public static String dotToSplit(String clssName){  return clssName.replace(".","/") ;  }

}


class MyDefineCL{
    public void say(){
        System.out.println("Say...");
    }
}