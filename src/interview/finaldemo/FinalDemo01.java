package interview.finaldemo;

public class FinalDemo01 {
//    final  int num = 10 ;
    final  int num ;//final修饰的变量，没有默认值
    public FinalDemo01(){
        this.num = 10 ;
    }
    public FinalDemo01(int num ){
        this.num = num ;
    }
    //构造方法的作用？ 1.相当于多个setter  2.实例化对象new（任意一个构造方法都可以实现）
    public static void main(String[] args) {
//        FinalDemo demo = new FinalDemo();
//        System.out.println(demo.num);

        new FinalDemo01(10);
    }
}
