package reflect;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * Created by 颜群
 *
 *  通过反射操作动态数组：Array
 *  String[] str = new String[3] ;
 *
 */
public class Demo01 {

    //通过反射操作动态一唯数组
    public static void array1() throws  Exception{

        Scanner input= new Scanner(System.in);
        System.out.println("请输入数组的类型：");
        String type = input.nextLine() ;//java.lang.String
        System.out.println("请输入数组的个数：");
        int num = input.nextInt() ;

        Class<?> c = Class.forName(type);
        Object arr = Array.newInstance(c, num);

        //给数组的一个元素复制
        Array.set(arr, 0,"zs"   );
        Array.set(arr, 1,"ls"   );
        Array.set(arr, 2,"ww"   );
        System.out.println(     Array.get(arr,0)              );
        System.out.println(     Array.get(arr,1)              );
        System.out.println(     Array.get(arr,2)              );

    }
    //通过反射操作动态二唯数组
    public static void array2() throws  Exception{
        Class c =  Integer.TYPE ;//数组类型 为 int
        //数组的长度  3,3
        int dim[] = {3,3} ;
        Object arr = Array.newInstance(c, dim);

        //从二维数组中 获取 一行（第2行）
       Object arr2 =   Array.get(arr,2) ;

        Array.set( arr2   ,   1, 369         );

        System.out.println(   Array.get(arr2,1)          );

    }
    public static void main(String[] args) throws  Exception{
        array2();
    }
}
