public class Test5 {
    public static void main(String[] args) {
//        System.out.println("Hello World");
//        int i= 0 ; //1
//        while(i<10000)
//        {
//            System.out.println(i+"Hello World");
//            i++ ;//更新变量
//        }


        /*2010上网人数8000万，假设每年按30%增加， 问哪一年人数增长到3亿？
        int year = 2010 ;
        int persons = 8000 ;
//
//        //2011
//        year ++ ;
//        persons =  (int)(persons*(1+0.3)) ;
//
//        //2012
//        year ++ ;
//        persons = (int)(persons*(1+0.3)) ;
//
//        //2013
//        year ++ ;
//        persons = (int)(persons*(1+0.3)) ;

        //通过“多写几步”可以发现 循环操作：
        while(persons<30000){
            year ++ ;
            persons = (int)(persons*(1+0.3)) ;
            System.out.println(year+"---"+persons);
        }
        System.out.println(year);
        */


        //计算100以内的偶数之和  2+4+6+8...+100
        /*
            1-100 ：  1+2+3+4+5+6+...+100

            int  sum = 0 ;
            int i = 1 ;

            sum = sum+1;//0+1
            i++;

            sum = sum+2 ;//0+1+2
            i++ ;

            sum = sum+3 ;//0+1+2+3
            i++ ;

            //...
sum = sum+100 ;//0+1+2+100




         */
        /*
        int  sum = 0 ;//1
        int i = 1 ;//1- 100每个数字
        //1-100之和
        while(i<=100) {
            if(i % 3 !=0) { //1--100 不能被3整除的数之和
                sum = sum + i;  //sum=0+1 ;        sum = 1+2  ;  sum = 1+2 +3  +...100
            }
            i++;

        }
        System.out.println(sum);
        */

//        while(1>2){
//            System.out.println("abc");
//        }

        /*
        do{
            System.out.println("abc");
        }while(1>2);
        */

        /*
        int money = 0 ;
        Scanner input = new Scanner(System.in);
        String isContinue = "" ;
        do{
            System.out.println("请选择：\n1.Tshirt(100)  2.夹克(200) 3.衬衫(300)");
            int choice = input.nextInt();
            if(choice == 1){
                System.out.println("Tshirt\t"+100);
                money = money + 100 ;
            }else if(choice ==2){
                System.out.println("夹克\t"+200);
                money = money + 200 ;
            }else if(choice ==3){
                System.out.println("衬衫\t"+300);
                money = money + 300 ;
            }else{
                System.out.println("输入有误！");
            }

            System.out.println("是否继续？y/n");
            isContinue = input.next();//y/n

        }while(isContinue.equals("y")); //变量的作用域 最近一对{}
        System.out.println(money);
        */

        //用循环实现 登录操作：若用户名、密码错误(zs  abc)  则给出提示，并重新登录
        /*
        Scanner input = new Scanner(System.in);
        String username = "" ;
        String password = "" ;
        do {
            if(!(username.equals("") && password.equals(""))) {
                System.out.println("请重新登录");
            }
            System.out.println("请输入用户名：");
            username = input.next() ;//回车符
            System.out.println("请输入密码：");
            password = input.next() ;
        }while(!(username.equals("zs")  && password.equals("abc")));// == ,equals

        System.out.println("登录成功！");
        */

        /*
        //整数的翻转输出， 12345 ->54321
        int num = 1234567 ;
        int rear = -1 ;//每次打印的数值
        num = num *10 ;
        //5
        num = num /10;
        rear = num% 10 ;
        System.out.println(rear);

        //4
        num = num /10 ; //1234
        rear = num%10 ;
        System.out.println(rear);

        //3
        num = num /10 ;//123
        rear = num%10 ;
        System.out.println(rear);
        //2
//        num = num /10 ;//12
        // num = num /10 ;//1



        //

//        while(num/10>0){
        while(num/10>0){
            num = num /10 ;//123
            rear = num%10 ;
            System.out.println(rear);
        }


        Scanner input = new Scanner(System.in);
        //输入5门课程成绩，计算平均分
        int sum = 0 ;
        for(int i=1;i<=5 ; i++){
            System.out.println("请输入第"+i +"门课成绩：");
            int score = input.nextInt() ;
            sum = sum+ score ;    //sum = sum +i :求和公式
        }
        System.out.println(sum/5.0);

        int sum = 0 ;
        // 求 1-100之间 能被7整除的数之和
        for(int i=1;i<=100 ;i++){
            if(i%7 == 0)
                sum  =  sum +i ;
        }

        System.out.println(sum);

        */
//        水仙花数是指一个三位数其各位数字的立方和等于该数本身
        for (int i = 100; i < 1000; i++) {//123
            int getWei = i % 10;
            int shiWei = i / 10 % 10;
            int baiWei = i / 100;
            if (i == getWei * getWei * getWei + shiWei * shiWei * shiWei + baiWei * baiWei * baiWei) {
                System.out.println(i);
            }

        }
    }
}
