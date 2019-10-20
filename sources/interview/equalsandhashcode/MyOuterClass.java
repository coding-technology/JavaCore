package interview.equalsandhashcode;

public class MyOuterClass {
    int i = 10 ;
    static class MyInnerClass{
        int i  = 20 ;
        public void myMethod(){
            int i= 30 ;
            System.out.println(i);//30
            System.out.println(this.i);//20
//            System.out.println(MyOuterClass.this.i);//10    外部类.this.成员变量  （仅适用于非static修饰的内部类）
        }
    }

    public static void main(String[] args) {
       MyInnerClass inner =   new MyInnerClass();//必须要求内部类是static修饰的

//        MyOuterClass.MyInnerClass inner =   new MyOuterClass().  new MyInnerClass(); 适合与内部类没有static修饰
        inner.myMethod();
    }
}
