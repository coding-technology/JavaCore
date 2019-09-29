public class Test1 {
    public static void main(String[] args) {
        //变量：1.声明数据类型(整数、小数、字符串、....)	2.赋值（赋值符号=，将=右侧 赋给左侧）	  3.使用
        int myNum = 10;
        // int myNum = 10 ; 变量名不能重复
        myNum = myNum + 1;
        System.out.println(myNum);

        byte b = 127;
        System.out.println(b);

        short s = 1000;// short = int  （整数默认是int，但是 = 对于整数自带 整数类型（byte short int long）之间的 转换器）
        System.out.println(s);

        long lon = 2000000;
        System.out.println(lon);

        float f = (float) 123.4; //小数默认是double.  float = double
        float f2 = 123.4f; //小数默认是double.  float = double
        System.out.println(f);

        double d = 123.4;
        System.out.println(d);

        char c = 'x';
        System.out.println(c);

        String str = "a";
        String str2 = "aaaa";
        String str3 = "";
        System.out.println(str);
        System.out.println(str2);
        System.out.println(str3);


        int x1 = 1;
        int X1 = 1;

        int y = 222;
        System.out.println(y);

    }
}
