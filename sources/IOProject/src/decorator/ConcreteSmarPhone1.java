package decorator;

/*
 * Created by 颜群
 */
public class ConcreteSmarPhone1  extends  SmartPhone{
    public ConcreteSmarPhone1(Phone phone){
        super(phone) ;
    }

    public void call(){
        super.call();
        //额外的新功能
        changeColor();
    }

    public void changeColor(){
        System.out.println("智能变色...");
    }



}
