public class Person2 {
    //属性。。。
    //方法
    public static void eatFruit() {
        System.out.println("吃水果...");
    }

    public static void main(String[] args) {
        Person2 p = new Person2();
        p.eatFood();
    }

    public void eatFood() {
        System.out.println("吃主食...");
        eatFruit();
    }

    public void sleep() {

    }
}
