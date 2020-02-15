package decorator;

/*
 * Created by 颜群
 */
public class ConcretePhone implements  Phone {
    @Override
    public void call() {
        System.out.println("打电话....");
    }
}
