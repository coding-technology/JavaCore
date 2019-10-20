package a.b.c.d;

public class TestPerson {
    public static void main(String[] args) {
        Person per = new Person();
        per.setName("zs");
        per.setAge(223);
//        per.name ="zs" ;
//        per.age = 11123 ;//数据不安全
        System.out.println(per.getAge());
        System.out.println(per.getName());

//        per.showInfo();

    }
}
