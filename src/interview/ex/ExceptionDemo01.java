package interview.ex;

public class ExceptionDemo01 {
    void test() throws NullPointerException{
        System.out.println("A...");
    }
    public static void main(String[] args) {
        new ExceptionDemo01().test();
    }
}
