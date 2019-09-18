package interview.innerclass;

interface A
{
    void a() ;
}

abstract  class B
{
    void b(){
        System.out.println("abstract b...");
    }
    abstract void b2() ;
}

class C
{

}


public class Demo {
    public static void main(String[] args) {
        //匿名内部类：隐藏的继承了一个类（可以是普通类、抽象类，不能是被final修饰的类），或者实现了一个接口
      A a =  new A(){
          public void a(){
              System.out.println("a....");
          }
      };
     // x x = A的实现类 或子类

        //以下等价于： B b = new BClass() ;其中BClass是B的子类
      B b =   new B(){
          @Override
          void b() {
              System.out.println("inner class b()..");
          }

          @Override
          void b2() {
              System.out.println("inner class b2()..");
          }
      };


      C c =   new C(){};

    }
}
