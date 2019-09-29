public class MethodArgs {

    //语法
    public static void myMethod(String a, int b, double c) {//double c = 12
        System.out.println(a + "," + b + "c" + c);
    }

    public static void main(String[] args) {
        myMethod("hello world", 10, 12.3);
    }


}
