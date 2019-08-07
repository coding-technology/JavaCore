

# 一个类的声明能否 既是abstract，又是final？

abstract final class A   ->  abstract， 必须使用子类（继承）

{

  ...

}

B extends A{

}

不能。

abstract ：抽象类。怎么使用抽象类？  因为抽象类不能实例化new，因此我们只能使用其子类

B b = new B();



 final class A  {...}：final修饰的类，不能被继承。



# 如何给final修饰成员变量的初始化赋值？

**情况一：没有static**
a.	final  int num = 10 ;

b.构造方法给final赋初值（注意：所有的构造方法 都必须给final变量赋值）

```
public class FinalDemo01 {

    final  int num ;//final修饰的变量，没有默认值
    public FinalDemo(){
        this.num = 10 ;
    }
    public FinalDemo(int num ){
        this.num = num ;
    }
    //构造方法的作用？ 1.相当于多个setter  2.实例化对象new（任意一个构造方法都可以实现）
    public static void main(String[] args) {
//        FinalDemo demo = new FinalDemo();
//        System.out.println(demo.num);

        new FinalDemo(10);
    }
}
```





**情况二：有static，只能通过=初始化值，不能同构方法赋值。 static变量再构造方法之前执行的。**

static final  int num = 10 ;

(1)

static修饰的变量，执行时机：类加载过程中执行，并且会被初始化为 默认值0 null ..
class A{

​	static int a = 10 ; //a ->0 -> 10

}

(2)

final修饰的变量，执行时机：运行时被初始化（直接赋值，也可以通过构造方法赋值）

（3）

static final修饰的变量，执行时机： ：在javac时（编译）生成ConstantValue属性，在类加载的过程中根据ConstantValue属性值为该字段赋值。ConstantValue属性值 没有默认值，必须显示的通过=赋值。

类加载： .java->.class ->jvm

运行时： 执行





# 为什么对于一个public及final修饰的变量，一般建议声明为static？

public class A{

​	 public final static  int num = 10 ;

}

public class B
{

​     void b()

​	{

​	     A.num 

​	}    

void c()

​	{

​       A.num 

​	}

}

加上static可以节约内存（只需要在内存中 拥有该变量的一份存储，直接通过“类名.变量” 使用）。因为final修饰的变量不可改变，因此不存在并发问题。
final static int tickets = 10;



# InterfaceA是接口， InterfaceA  []a = new InterfaceA[2];是否正确？

InterfaceA  []a = new InterfaceA[2];

对象数组： {x,x,x,x,x,x}，对象数组中 实际存放的不是对象本身，而放的是对象引用的地址。







 



 

 