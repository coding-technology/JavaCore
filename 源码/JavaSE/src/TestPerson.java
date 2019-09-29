public class TestPerson {
    public static void main(String[] args) {
//        int num = 10 ;
//        类 对象  = new 类() ;
        Person zs;
        zs = new Person();

        //对象.属性
        zs.name = "张三";
        zs.age = 28;

        System.out.println(zs.name);
        System.out.println(zs.age);
        //对象.方法
        zs.eat();
        zs.sleep();

        Person ls = new Person();
        ls.name = "李四";
        ls.age = 33;
        System.out.println(ls.name);
        System.out.println(ls.age);
        ls.eat();
        ls.sleep();


    }
}
