package decorator;

/*
 * Created by 颜群
 */
public class Test {
    public static void main(String[] args) {
        ConcretePhone phone = new ConcretePhone();
        //主题（被装饰者）
        phone.call();
        System.out.println("---");

ConcreteSmarPhone1 phone1 = new ConcreteSmarPhone1( phone ) ;
        phone1.call();


        System.out.println("---");

        ConcreteSmarPhone2 phone2 = new ConcreteSmarPhone2( phone ) ;
        phone2.call();


        System.out.println("-------");

       SmartPhone smart =  new ConcreteSmarPhone2(new ConcreteSmarPhone1( phone )) ;
        smart.call();
    }
}
