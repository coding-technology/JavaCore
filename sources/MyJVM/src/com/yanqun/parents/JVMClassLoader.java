package com.yanqun.parents;

class JvmClass{}

public class JVMClassLoader {

    public static void main(Math[] args) {
        Object[] objs = new Object[10] ;
        System.out.println(" 数组的类加载器："+  objs.getClass().getClassLoader() );
        Object obj = new Object();
        System.out.println("数组元素的类加载器："+ obj.getClass().getClassLoader());

        JvmClass[] cls = new JvmClass[10] ;
        JvmClass cl = new JvmClass() ;
        System.out.println(cls.getClass().getClassLoader());
        System.out.println(cl.getClass().getClassLoader());

        System.out.println("---");
        int[] nums = new int[10] ;
        System.out.println(nums.getClass().getClassLoader());
        System.out.println( int.class.getClassLoader());


    }
}
