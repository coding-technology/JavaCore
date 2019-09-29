import java.util.Scanner;

public class LuckyNum {
    public static void main(String[] args) {
        /*
        抽奖游戏
        1注册 2登录 3抽奖

        注册时，系统会自动生成一个4位随机数作为卡号。

        登录成功后，才能抽奖。

        抽奖时，系统生成6个4位随机数作为幸运数字，
        如果会员卡号是其中之一，则成为本日幸运会员；
        否则不是幸运会员
        */
        System.out.println("注册");
        String registName = "";
        String registPwd = "";
        Scanner input = new Scanner(System.in);
        System.out.println("请输入用户名");
        registName = input.next();
        System.out.println("请输入密码");
        registPwd = input.next();
        System.out.println("注册完毕...");
        int vipNum = (int) (Math.random() * 9000) + 1000;


        System.out.println("\n\n登录");
        String loginName = "";
        String loginPwd = "";
        System.out.println("登录用户名");
        loginName = input.next();
        System.out.println("登录入密码");
        loginPwd = input.next();


        if (loginName.equals(registName) && loginPwd.equals(registPwd)) {
            System.out.println("登录成功");
            System.out.println("抽奖");

            int[] luckyNums = new int[6];
            //产生6个幸运号码
            for (int i = 0; i < 6; i++) {
                luckyNums[i] = (int) (Math.random() * 9000) + 1000;
                System.out.print(luckyNums[i] + "\t");
            }
            System.out.println("你的vipNum:" + vipNum);

            boolean flag = false;
            //判断vipNum 是否存在于luckyNums之中
            for (int i = 0; i < 6; i++) {
                if (vipNum == luckyNums[i]) {
//                   System.out.println("中奖");
                    flag = true;//中奖
                }
            }
            if (flag) {
                System.out.println("中奖");
            } else {
                System.out.println("没中奖");
            }


        } else {
            System.out.println("用户名或密码输入有误！");
        }

    }

    public static class OOPScore {
        //属性（1静态行为 ，全局变量 ）
        int javaScore;
        int cScore;
        int sqlScore;

        public static void main(String[] args) {
            OOPScore score = new OOPScore();
            score.inputScore();
            double avg = score.calcAvg();
            int sum = score.calcSum();

            System.out.println(avg + "--" + sum);
        }

        public void inputScore() {
            Scanner input = new Scanner(System.in);
            System.out.println("请输入java成绩:");
            javaScore = input.nextInt();
            System.out.println("请输入C成绩:");
            cScore = input.nextInt();
            System.out.println("请输入SQL成绩:");
            sqlScore = input.nextInt();
        }

        //计算平均分并返回
        public double calcAvg() {
            double av = (javaScore + cScore + sqlScore) / 3.0;
            return av;
        }

        //计算总分并返回
        public int calcSum() {
            int sum = javaScore + cScore + sqlScore;
            return sum;
        }
    }
}
