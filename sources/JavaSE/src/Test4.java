public class Test4 {
    public static void main(String[] args) {
        /*xm98,  如果zs考试成绩 比xm高，则奖励一个Mp3
        int xm = 98 ;
        int zs = 100 ;
        if(zs > xm) {//如果没有{}，则if/for/while 都只对 下面第一条语句生效
            System.out.println("奖励mp3");//如果条件为真 则执行
        }else{
            System.out.println("抄10遍...");
        }
        //如果zs的java成绩>90 且 英语程序>95 则奖励 ；或者zs的java成绩>95 且 英语成绩>80 也奖励
        int zsJavaScore = 98 ;
        int zsEnglishScore = 99 ;
        if( (zsJavaScore>90 && zsEnglishScore>95) || (zsJavaScore>95 && zsEnglishScore>80)  ){
            System.out.println("奖励》。。");
        }*/
        /*
        String str = "" ;
        int a = 100 ;
        int b = 20 ;
        if(a>b){ //java语言的 if(表达式) ，表达式必须是boolean类型
            str = "hello" ;
            System.out.println("xxx");
        }else{
            str = "world" ;
        }
       System.out.println(str);
        //如何简化？  简单的if..else赋值语句，可以等价为 三目运算符
//        String str =     100>20 ? "hello"  :"world" ;

        System.out.println(str);
        */
        /*
        double ran = Math.random() ;  //[0,1)
        System.out.println(ran);

        //产生四位随机整数   123   589   666   111
        // 0-1    * 9000  --> [0 ,9000) -> int   --> [0,8999]  +1000 -->[1000 ,9999  ]
        System.out.println( (int)(Math.random()*9000) +1000);

        //三位随机整数
        // 0-1    * 三位数中最大的整百数  --> [0 ,9000) -> int   --> [0,8999]  +1000 -->[1000 ,9999  ]
        int ran2 = (int)(Math.random()*900) +100 ;
        System.out.println(ran2);
        //六位随机整数
        int ran3 = (int)(Math.random()*900000) +100000 ;
        System.out.println(ran3);
        //n位随机数  ：  (int)(Math.random()* (最大值+1-最小值)    ) +  n位数的最小值 ;

        //会员卡号必须是三位；   抽象规则：如果 卡号的十位 等于随机数，则中奖。
        int vip = (int)(Math.random()*900) +100 ; //123
        int shiWei = vip/10%10 ;
        //0-1 *10   0-9
        int ran = (int)(Math.random()*10) ; //0-9

        if(shiWei == ran){
            System.out.println("中奖");
        }else{
            System.out.println("没中奖");
        }

        System.out.println("具体如下：卡号："+vip+ ",幸运数字："+ran);

        //三位随机数
//      int vip = (int)(Math.random()*900) +100 ;
        int ran = (int)(Math.random()*900)  +100 ;//注意括号()
        System.out.println(ran);
        */

        /*
            if(3>2) {
                b = 1;
                b++;
            }
             else
                b = -1 ;

        int b = 0 ;
        if(3>2) {
            b = 1;
        }
        else {
            b = -1;
        }
        b++;
        System.out.println(b); */
//             输出b ;

        // >=90 优秀  >=80  良好  >=60  及格 ，否则不及格
    /*    int score = 88 ;
        if(score >= 90){
            System.out.println("优秀");
        }else if(score >= 80){
            System.out.println("良好");
        }else if(score >= 60){
            System.out.println("及格");
        }else{
            System.out.println("不及格");
        }

        int score = 88 ;
        if(score >= 60){
            System.out.println("及格");
        }else if(score >= 80){//空集
            System.out.println("良好");
        }else if(score >= 90){
            System.out.println("优秀");
        }else{
            System.out.println("不及格");
        }


        //测试人员 对项目进行测试,  bug<3  A；bug<5  B  <10 ; >=10  D
        int bug = 5 ;
        if(bug < 3 ){
            System.out.println("A");
        }else if(bug <5){
            System.out.println("B");
        }else if(bug < 10){
            System.out.println("C");
        }else{
            System.out.println("D");
        }

    //10秒以内 进入角色； 男，男子组决赛；女，女子组决赛
        double sec = 9.8  ;
        char sex = '女' ;
        if(sec<=10){
            if(sex == '男'){
                System.out.println("进入男子组决赛..");
            }else{
                System.out.println("进入女子组决赛..");
            }
        }else{
            System.out.println("淘汰。。");
        }
        */

        //1.夏令营  ；2 笔记本电脑  ；3  U盘   ，否则不奖励
        // int rank = 1 ;
       /* if(rank ==1){
            System.out.println("夏令营");
        }else if(rank ==2){
            System.out.println("笔记本电脑");
        }else if(rank ==3){
            System.out.println("U盘");
        }else
        {
              System.out.println("不奖励..");
        }
        */
        int rank = 20;
//       int num1 = 1 ;
//       int num2 = 2 ;
//       int num3 = 3 ;
//       switch(rank){
//           case 1:
//               System.out.println("笔记本电脑");
//               break ;
//           case 2:
//               System.out.println("U盘");
//               break ;//break：表示整个switch全部结束
//           case 3:
//               System.out.println("夏令营");
//               break ;
//           default://else...
//               System.out.println("不奖励...");
//               break ;
//       }

//        System.out.println("请输入月份：");
//        Scanner input = new Scanner(System.in);
//
////        if(是否是数字){
//        if (input.hasNextInt()) {
//            int month = input.nextInt();
//            switch (month) {
//                case 1:
//                case 3:
//                case 5:
//                case 7:
//                case 8:
//                case 10:
//                case 12:
//                    System.out.println("是大月..");
//                    break;
//            }
//
//        } else {
//            System.out.println("请输入数字！");
//        }

//        int num =22 ;
        //注意 ==和=
//        boolean flag = 3<2 ;//false
//
//        ///
//        if(flag == true){//flag : true
//            System.out.println("a");
//        }else{
//            System.out.println("b");
//        }

        String name = "zs";
        String pwd = "123";
        if (name.equals("zs") && pwd.equals("123")) {  //常量池
            System.out.println("成功！");
        } else {
            System.out.println("shibai ..");
        }

//       接口 A          实现类B  实现类C
        //A a = new B();
        //A a = new C() ;
        //  if(a instanceof B){}


    }
}
