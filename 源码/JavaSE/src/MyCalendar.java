import java.util.Scanner;

public class MyCalendar {

    public static void main(String[] args) {
        //已知1900.1.1  是星期1
        //输入年、月（2019,10） ->系统自带打印当月月历
        //第一步？知道 当月1号是星期几！ 推
        //2019.10.1是星期
        /*
           做循环：不怕麻烦...  写几遍
           循环：不会做，不用循环 ，走两步
                       天数（第几天）
            1900.1.1    1    星期1
            19001.2     2    星期2
            1900.1.3    3   星期3
            1900.1.4    4   星期4
            1900.1.5    5   星期5
            1900.1.6    6   星期6
            1900.1.7    7   星期0
            1900.1.8    8  星期1
            1900.1.9    9   星期2
            1900.1.10   10 星期3
            1900.1.11  11   星期4
            1900.1.12  12   星期5
            1900.1.13  13   星期6    13%7 --》6
            1900.1.14  14   星期0
            1900.1.15  15   星期1
            1900.1.16  16   星期2
            ...必然能够推算是 2019.10.1 -> 星期n
            日期 -> 星期几
            a->b
            a(哪一年的哪一天)->c(天数), c->b(星期几)   =>  a-B
               c->b 星期n = 天数% 7 ;


                a->c  ：哪一日(2019.10.1)  是第几天
                1900.1.1    -  2019-10.1
                分为：1900-2018 整个年份包含的天数
                      2019 1  - 2019  9  整个月份  2019.9.30    +1-》10.1




            循环操作？循环条件？ ->规律







         */


//        a->c  ：哪一日(2019.10.1)  是第几天
//        1900.1.1    -  2019-10.1
//        分为：（1）1900-2018 整个年份包含的天数
//              （2） 2019 1  - 2019  9  整个月份  2019.9.30    +1-》10.1
        Scanner input = new Scanner(System.in);
        System.out.println("请输入年份：");
        int year = input.nextInt();
        System.out.println("请输入月份：");
        int month = input.nextInt();

        int days = 0;
        //1900-2018 整个年份包含的天数
        for (int i = 1900; i < year; i++) {//1900 - 2018
            //如果一个年份能够4整除 且不能被100整除 ； 或 一个数字能被400整除
            if ((i % 4 == 0 && i % 100 != 0) || i % 400 == 0) days += 366;
            else days += 365;
        }
        //2018.12.31

        //2019.1.1 ->2019.9.30
        for (int i = 1; i < month; i++) {//每一月
            switch (i) { //if()
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    days += 31;
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    days += 30;
                    break;
                default:
                    if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
                        days += 29;
                    } else {
                        days += 28;
                    }
            }
        }//2019.9.30
        days++;//2019.10.1
//        星期n = 天数% 7 ;
        int xingQi = days % 7;
        System.out.println(year + "年" + month + "月是星期：" + xingQi);

        System.out.println("星期日\t星期一\t星期二\t星期三\t星期四\t星期五\t星期六");
        //根据观察可得： 星期几 前面有几个空格（占位符）
        //空格（占位） ，占位符的个数 就是 星期n
        for (int i = 0; i < xingQi; i++) {
            System.out.print("\t\t");

        }
        //定义当月有多少天
        int daysOfMonth = -1;
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            daysOfMonth = 31;
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            daysOfMonth = 30;
        } else {//2
            if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {//run
                daysOfMonth = 29;
            } else {
                daysOfMonth = 28;
            }
        }

        //数字1-31(日期)
        for (int i = 1; i <= daysOfMonth; i++) {
            System.out.print(i + "\t\t");
            if ((xingQi + i) % 7 == 0) {
                System.out.println();//回车
            }
        }
    }
}
