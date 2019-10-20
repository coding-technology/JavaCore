public class Test2 {
    public static void main(String[] args) {
        //8*1 + 9*10 =98
        int num = 98;
        System.out.println(num);


        //0*1 + 1*2
        int num1 = 0b010;
        System.out.println(num1);

        //2*1 + 7*8= 58
        int num2 = 072;
        System.out.println(num2);
        //0-9 A-F
        int num3 = 0xA2;
        //2*1 + 10*16
        System.out.println(num3);

        int myfirstnum = 10;//不建议的方式
        System.out.println(myfirstnum);

        int ￥$_我my2FirstNum = 4;
        System.out.println(￥$_我my2FirstNum);

        int myNum = 4;
        System.out.println(myNum);

        System.out.println("--------");
//        使用以下变量存储手机信息 并打印输出
//        品牌  iphone
//        尺寸  4.0
//        像素	1080px*768px
//        价格	5288
//        String phone = "iphone" ;
//        float size = 4.0f ;
//        String px = "1080px*768px" ;
//        int price = 5288 ;
//        System.out.println(phone);
//        System.out.println(size);
//        System.out.println(px);
//        System.out.println(price);
//
//        // +：拼接
//        System.out.println(phone  + "\t" + size  +"\t"  +  px +"\t" +   price);
//
//        System.out.println("-----------");
//
//        //王浩成绩80，张萌成绩比王浩多10分，输出张萌成绩
//        int whScore = 80  ;
//        int zmScore = whScore + 10 ;
//        System.out.println(zmScore);
//
//        //从控制台输入王浩的三门课程成绩，计算：1. java课程和sql分数之差  ； 2.三门课平均成绩
//        //控制台输入：Scanner (Scanner属于jdk类库，需要手工引入:ctrl+n搜)
//        Scanner input = new Scanner(System.in);//java.util.Scanner
////
//        System.out.println("请输入java成绩：");
//        int javaScore = input.nextInt() ;
////
//        System.out.println("请输入姓名");
//        String name = input.nextLine() ;
//        System.out.println("姓名是：" + name);
//
//
//        System.out.println("sql：");
//        int sqlScore = input.nextInt() ;
//        System.out.println("html：");
//        int htmlScore = input.nextInt() ;
//
//        int between = javaScore - sqlScore ;
//        System.out.println("java比sql多：" + between);
//
//        double avgScore = (javaScore + sqlScore + htmlScore)/3.0 ; //98.333 ->98
//
//        System.out.println("平均分：" + avgScore);
//
//
//
//
//       // ArrayList list = new ArrayList();
//
////        整数：byte<short<int<long 	默认int
//        //flaot = int
//            float myNumVar = 10 ;
//        //float < double
//        double d = 3.14f ;

        //int a = 10 +3.14 ;

        int a = 11;
        String s = "11";
        System.out.println("" + 10 + 1); //101
        System.out.println(10 + "" + 1); //101
        System.out.println(10 + 1 + ""); //11


//        小数：float<double 		默认double

        System.out.println("*********");
        char ch = 'A';
        System.out.println(ch);
        System.out.println(ch + 0);
        System.out.println('颜' + 0);

        System.out.println("=========");
        float f2 = 1234.5f;
        int myNum2 = (int) f2;

        float f3 = (float) 123.4;
        float f4 = 123.4f;

        System.out.println("-------------------");
        //1.已知圆的半径的radius = 1.5,求面积
        double radius = 1.5;
        double pi = 3.14159265354;
        double area = pi * radius * radius;
        System.out.println(area);

        //2.apple电脑市场份额20 ，今年增加了9.8%，求今年的份额 ；
        int fenE = 20;
        double jinNianFenE = fenE * (1 + 9.8 / 100);
        System.out.println(jinNianFenE);


    }


//    public static void a()
//    {
//        System.out.println(myNum);//变量的范围：最近一对大括号{}
//
//    }
}
