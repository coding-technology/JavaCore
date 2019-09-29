public class Student {
    String name;
    int javaScore;
    int sqlScore;

    public double getAvg() {
        return getSum() / 2.0;
    }

    public int getSum() {
        return javaScore + sqlScore;
    }
}
