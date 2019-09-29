public class Person {
    //静态 属性
    String name;//null
    int age;//0

    //动态 方法
    //吃饭
    public void eat() {
        String name = "xx局部变量xxx";
        System.out.println(name + "吃饭。。。" + age);
    }

    //睡觉
    public void sleep() {
        System.out.println("睡觉。。。");
    }

}
