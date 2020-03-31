package lambda;

/*
 * Created by 颜群
 */

@FunctionalInterface
public interface MyInterface  {//everything is an Object
    public abstract void a() ;//本接口新定义的抽象

    public abstract String toString() ;//和Object中重名(实际会调用Object中的toString())
    public abstract boolean equals(Object obj) ;//和Object中重名

    //重点：toString()和equals()看似是抽象方法，实则是 有方法体的具体方法
}

class MyInterfaceImpl implements  MyInterface{
    @Override
    public void a() {
        System.out.println("a...");
    }
}
class TestMyInterface{
    public static void main(String[] args) {

        MyInterfaceImpl impl = new MyInterfaceImpl();
        impl.a();
        impl.equals("")  ;
        impl.toString();
    }
}
