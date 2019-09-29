import java.util.Scanner;

public class Test12 {

    public static void main(String[] args) {
        //3个班级，各个班有4个同学。求每个班的平均分。
        for (int j = 0; j < 3; j++) {
            System.out.println("j:" + j + "外-------");
//            System.out.println("请输入第"+(j+1)+"个班的成绩");
            //一个班： 每个人的平均
//            一个班： 每个人的平均
            //一重：4个同学，计算平均分
            Scanner input = new Scanner(System.in);
            int sum = 0;
            int score = 0;
            for (int i = 0; i < 4; i++) {
                System.out.println("i:" + i + "内******");
//                System.out.println("请输入第"+(i+1)+"个学生的成绩");
//                score = input.nextInt() ;
//                sum += score  ;
            }
            System.out.println(sum / 4.0);
        }

    }


}
