import java.util.Scanner;

public class Test6 {
    public static void main(String[] args) {
//        for(int i=0;i<5;i++){
//            if(i==2)
//            {
////                break ;
//                continue ;
//            }
//
//            System.out.println(i);
//    }
        //录入五门课程的成绩，并计算平均分。 如果分数为负数，停止录入，并给出错误提示
        Scanner input = new Scanner(System.in);
//        int sum = 0 ;
//        boolean flag = true ;//true:5个成绩录入正常 ;false：失败！
//        for(int i=1;i<=5;i++){
//            System.out.println("请输入第"+i+"门成绩：");
//            int score = input.nextInt() ;
//            if(score < 0){
//                System.out.println("输入有误！停止！");
//                flag = false ;
//                break ;
//            }
//            sum += score ;
//        }
//        //flag：标记
//        if(flag ==true) {//是，不是 ->boolean(true \false)
//            System.out.println(sum);
//            System.out.println(sum / 5.0);
//        }
//
        //1-20,累加。累加到哪个数字时，刚好大于30 ;
//        int sum = 0;
//        for (int i = 1; i <= 20; i++) {
////            sum += i;
//            sum = sum +i ;// 1  3   6    25  33
//            if(sum > 30){
//                System.out.println(i);
//                break ;
//            }
//        }

//
//
//        boolean flag = false ;//失败
//        //用户登录验证（zs,abc），验证次数最多3次。
//        for(int i=0;i<3;i++){//0  1   2
//            System.out.println("请输入用户名");
//            String name = input.next();
//            System.out.println("请输入密码");
//            String pwd = input.next();
//            if(name.equals("zs") && pwd.equals("abc")){
////                System.out.println("登录成功！");
//                flag = true ;
//                break ;
//            }else{
//                System.out.println("登录失败！用户名、密码有误！");
//            }
//        }
//        if(登录成功){
//            //登录成功
//        }else{
//
//        }
        //if(falg == true)
//        if(flag){
//            System.out.println(  "登录成功！");
//        }else{
//            System.out.println(  "连续输入错误3次");
//        }

        //用户登录验证（zs,abc），验证次数最多3次。
        int i = 0;
        for (; i < 3; i++) {//0  1   2
            System.out.println("请输入用户名");
            String name = input.next();
            System.out.println("请输入密码");
            String pwd = input.next();
            if (name.equals("zs") && pwd.equals("abc")) {
                System.out.println("登录成功！");
                break;
            } else {
                System.out.println("登录失败！用户名、密码有误！");
            }
        }
        //每一轮循环需要经历的步骤：  判断->循环体 ->i++
        if (i == 3) {  //循环退出的可能性：要么成功（0 1 2 ） ；3次全部失败（3）
            System.out.println(" 连续输入错误3次");
        }


    }
}
