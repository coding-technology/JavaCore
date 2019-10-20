import java.util.Scanner;

public class OOPScore {
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
