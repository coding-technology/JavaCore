import java.util.Scanner;

public class Test7 {
    public static void main(String[] args) {

////        students[0] = 88;
////        students[1] = 97 ;
////        students[2] = 93 ;
////
////        System.out.println( students[2]  );
//
//
//        int students[] = new int[300] ;
        int sum = 0;
        int[] students = new int[3];
        System.out.println("数组的长度" + students.length);
        Scanner input = new Scanner(System.in);
        for (int i = 0; i < students.length; i++) {
            System.out.println("请输入第" + (i + 1) + "个学生的成绩：");// +:字符串拼接  数学加
            students[i] = input.nextInt();
            sum += students[i];
        }
        System.out.println(sum);

        //1:只声明数组的元素个数，但不赋值（默认值）
        int[] students0;
        students0 = new int[3];
//        System.out.println(students0[1]);


        int num;
        num = 1;
        //2:只赋值，但不声明元素个数
//        int[] students ;
//        students = new int[]{97,98,99} ;


//        System.out.println(students[1]);

        //3: 理解为2的简化形式 .此种形式 不能拆开编写
//        int[] students ;
//        students = {97,98,99} ;
//        System.out.println(students[1]);


    }
}
