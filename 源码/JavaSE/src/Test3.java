public class Test3 {

    public static void main(String[] args) {
        // 46天，包含了几周零几天
        int weeks = 46 / 7;
        int days = 46 % 7;
        System.out.println(weeks + "\t" + days);
        System.out.println("---");

        //商场活动100当做120花；。 420元，付多少钱？
        int money = (420 / 120) * 100 + 420 % 120;
        System.out.println(money);
        System.out.println("---");
        /*输入张三成绩，与李四（80） 。输出 张三是否比李四高
        Scanner input = new Scanner(System.in) ;
        System.out.println("请输入张三成绩：");
        int zsScore = input.nextInt();

        System.out.println( zsScore>80   );//80
        */
        //int num ;   num*3  num+num1
        System.out.println((2 + (43 % 6)) > ((10 / 3) * 2));
        System.out.println(3 >= 3);

        System.out.println("---------");
        //输入五位数，求各个位数之和
        int num = 12345;


        int geWei = num / 1 % 10;//12345->5
        int shiWei = num / 10 % 10;//12345->1234->4
        int baiWei = num / 100 % 10; //12345 ->123 ->3
        int qianWei = num / 1000 % 10; //12345->12 ->2
        int wanWei = num / 10000 % 10; //12345 ->1 ->1
        System.out.println(geWei + shiWei + baiWei + qianWei + wanWei);
        //通项：num任一一位 ，num /所在位数的最小值  % 10 ；

        System.out.println();
        //两个数交换
        int num1 = 10;//30
        int num2 = 20;

        /*通过 中间变量实现
        int temp =num1;
        num1     =num2;
        num2  = temp ;
//      num1 = num2 ;
//      num2 = num1 ;

    */
        /*数学加减（如果数字很大，可能溢出）
        num1 = num1 + num2 ;//30
        num2 = num1 - num2 ;//10
        num1 = num1 - num2 ;//20
        */

        //位运算(效率最高)
        num1 = num1 ^ num2;
        num2 = num1 ^ num2;
        num1 = num1 ^ num2;

        System.out.println(num1);
        System.out.println(num2);

        System.out.println("------------");
        int max = Integer.MAX_VALUE;//int最大值
        int min = Integer.MIN_VALUE;//int最小值
        System.out.println("最大值：" + max);
        System.out.println("最大值+1：" + (max + 1));
        System.out.println("最小值：" + min);
        System.out.println("最小值-1：" + (min - 1));

        System.out.println("--------");
        System.out.println(10 % -3);
        System.out.println(-10 % -3);
        System.out.println(10 % 3);
        System.out.println(-10 % 3);

        System.out.println("---");
        System.out.println(3 != 2);
        System.out.println("---");

        //
        System.out.println(2 < 1 && 1 / 0 == 0);//假  且。。。-> 假
        System.out.println(2 > 1 || 1 / 0 == 0);//真 或 ... ->真

        /*
        System.out.println(2<1 & 1/0==0);
        System.out.println(2>1 | 1/0==0);
        */
        int myNum = 2;
        //myNum = myNum + 10  ;
        myNum += 10;
        System.out.println(myNum);

        int sum = 100;
        // sum = sum + myNum ;
        sum += myNum;
        System.out.println(sum);

        System.out.println("--------");

//        int i = 10 ;
//        i = i +1 ;
//        i += 1 ;
//        i++ ;   //i--;
//        ++i ;   //--i ;
//        System.out.println(i);

//        i++: 先用 后加
//        int i = 10 ;
//        System.out.println(i++);//11
//        System.out.println(i++);//12
//        System.out.println(i++);//13
//        System.out.println(i);//13
//
//        --i：先减后用
        int j = 10;
        System.out.println(--j); //9
        System.out.println(--j);//8
        System.out.println(--j);//7
        System.out.println(j);//

        System.out.println("----");
//        byte b1 = 10 ;// int
//        float f = 123.4f ;//double
        //  整数 =  +=内置转换器

        byte b1 = 100;
        byte b2 = 100;
//         b2 = b1 + b2 ; //特例  byte int 的整数情况，  byte +byte ,short +short ->int
        //byte = byte + byte ;
        //-128 -- 127  = -128 -- 127   +  -128 -- 127
        // -128 -- 127  = -256 -- 254
        b2 += b1;//b2 = b2 + b1 ;

        System.out.println("--------");
        String str1 = "abc";
        String str2 = "abc";
        //System.out.println( str1 == str2); //类和对象，引用问题
        //System.out.println( str1.equals(str2));

        System.out.println("--------");
        String result = 10 > 20 ? "hello" : "world";
        System.out.println(result);
    }
}
