package com.yanqun.parents;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

//public class MyException extends Exception{...}
public class MyClassLoaderImpl  extends ClassLoader{
        //优先使用的类加载器是：getSystemClassLoader()
        public MyClassLoaderImpl(){
            super();
        }

        public MyClassLoaderImpl(ClassLoader parent){//扩展类加载器
            super(parent);
        }
        //com.yq.xx.class
        public Class findClass(String name) {
            System.out.println(name);
              byte[] b = loadClassData(name);
              return defineClass(name, b, 0, b.length);
          }

          //“com/yq/xxx.class” ->  byte[]
          private byte[] loadClassData(String name)  {

              name =  dotToSplit("out.production.MyJVM."+name)+".class" ;
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
        //自定义加载器的对象
        MyClassLoaderImpl myClassLoader = new MyClassLoaderImpl();//默认在双亲委派时，会根据正规流程：系统——》扩展->根
//        MyClassLoaderImpl myClassLoader = new MyClassLoaderImpl();//直接指定某个 具体的的委派
        Class<?> aClass = myClassLoader.loadClass("com.yanqun.parents.MyDefineCL");
        System.out.println(aClass.getClassLoader());
        MyDefineCL myDefineCL = (MyDefineCL)( aClass.newInstance()) ;
        myDefineCL.say();
    }

    public static String dotToSplit(String clssName){  return clssName.replace(".","/") ;  }

}


class MyDefineCL{
    public void say(){
        System.out.println("Say...");
    }
}