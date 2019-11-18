public class Test {
    public static void main(String[] args) {
        //获取当前线程
       Thread thread =  Thread.currentThread() ;
       //获取线程名字
        System.out.println(thread.getName());
        //设置线程名字
        thread.setName("hello");

        System.out.println(thread.getName());
    }
}
