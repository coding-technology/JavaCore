package decorator;

/*
 * Created by 颜群
 */
public class ConcreteSmarPhone2 extends  SmartPhone{
    public ConcreteSmarPhone2(Phone phone){
        super(phone) ;
    }

    public void call(){
        super.call();
        //额外的新功能
        changeSize();
    }

    public void changeSize(){
        System.out.println("智能改变尺寸...");
    }



}
