import java.util.Scanner;

public class MethodTest {
    //3个学生， 平均分 和最高分。不能定义全局变量

    public static void main(String[] args) {
        int[] students = new int[3];//{0,0,0}

        MethodTest test = new MethodTest();
        test.myInput(students);

        double avg = test.calcAvg(students);
        int sum = test.calcSum(students);
        int max = test.calcMax(students);
    }

    //student = {0,0,0}
    public void myInput(int[] students) {
        Scanner input = new Scanner(System.in);
        System.out.println("请输入第一个学生成绩:");
        students[0] = input.nextInt();//99
        System.out.println("请输入第二个学生成绩:");
        students[1] = input.nextInt();//88
        System.out.println("请输入第三个学生成绩:");
        students[2] = input.nextInt();//77

//        students = new int[]{student1,student2,student3} ;
    }

    public double calcAvg(int[] students) {
//        int sum = 0 ;
//       for(int student:students){
//           sum += student ;
//       }
        int sum = calcSum(students);
        return sum * 1.0 / students.length; //299.0/3
    }

    public int calcSum(int[] students) {
        int sum = 0;
        for (int student : students) {
            sum += student;
        }
        return sum;
    }

    public int calcMax(int[] students) {
        int max = students[0];
        for (int i = 1; i < students.length; i++) {
            if (max < students[i]) {
                max = students[i];
            }
        }
        return max;
    }


}
