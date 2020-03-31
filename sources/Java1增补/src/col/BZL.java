package col;

/*
 * Created by 颜群
 */
public class BZL {
    public static void main(String[] args) {
        int i = 10 ;

        Integer j = new Integer(20) ;
        i = j ;//Integer->int  自动拆箱 底层用的是intValue()
        j = i ;//int ->Integer 自动装箱 底层用的是valueOf() ,该方法中有个缓冲区 [-128,127],如果要装箱的数字 在缓冲区范围以内，则直接从缓冲区中取；否则，new Integer()一个新对象

        Integer i1 = 100 ;
        Integer i2 = 100 ;

        Integer i3 = 1000 ;
        Integer i4 = 1000 ;
        System.out.println(i1 == i2);
        System.out.println(i3 == i4);
    }
}
