

## 一个类的声明能否 既是abstract，又是final？如下所示。
```
abstract final class A  

{

  ...

}
```
不能。原因如下：
abstract ：抽象类。抽象类不能实例化（new），因此我们只能使用其子类，即会使用到“继承”，如下。

B extends A{

}


B b = new B();

而final修饰的类，不能被继承，如下。

 final class A  {...}。
 综上，在能否“继承”方面，abstract和final是矛盾的语义，所以不能同时使用。



## 如何给final修饰成员变量的初始化赋值？
这个问题有两种情况。  
**情况一：没有static**  
只有final，没有static时，既可以通过=直接赋值，也可以通过构造方法赋值，如下。  
a.通过=直接赋值  
final  int num = 10 ;  

b.通过构造方法给final赋初值（注意：所有的构造方法 都必须给final变量赋值）  

```
public class FinalDemo01 {

    final  int num ;//final修饰的变量，没有默认值
    public FinalDemo(){
        this.num = 10 ;
    }
    public FinalDemo(int num ){
        this.num = num ;
    }
    //构造方法的作用？ 实例化对象new（任意一个构造方法都可以实现）
    public static void main(String[] args) {
//        FinalDemo demo = new FinalDemo();
//        System.out.println(demo.num);

        new FinalDemo(10);
    }
}
```



**情况二：有static**
static final  int num = 10 ;  
既有final，又有static时，只能通过=初始化值，不能同构方法赋值。 总体是因为static变量是在构造方法之前执行的，具体的原因见以下三点：  
(1)

static修饰的变量，执行时机：类加载过程中执行，并且会被初始化为 默认值0 null ..
class A{

​	static int a = 10 ; //a ->0 -> 10

}

(2)

final修饰的变量，执行时机：运行时被初始化（直接赋值，也可以通过构造方法赋值）

（3）

static final修饰的变量，执行时机： 在javac时（编译）生成ConstantValue属性，在类加载的过程中根据ConstantValue属性值为该字段赋值。ConstantValue属性值 没有默认值，必须显示的通过=赋值。  


## 为什么对于一个public及final修饰的变量，一般建议声明为static？

```
public class A{
 	 public final static  int num = 10 ;
}

```
如果没有static修饰：假设在10个类中要使用num变量，就必须在这10个类中先实例化A的对象，然后通过“对象.num”来使用num变量，因此至少需要new10次。  
加上static可以节约内存。static是共享变量，因此只会在内存中 拥有该变量的一份存储，之后就可以直接通过“类名.变量” 使用（例如 “A.num”），而不用先new后使用。
由于final修饰的变量不可改变，因此不用考虑并发问题。


## InterfaceA是接口， InterfaceA  []a = new InterfaceA[2];是否正确？

正确。  

考点是对“对象数组”的理解。 {x,x,x,x,x,x}，在对象数组中 实际存放的不是对象本身，而放的是对象引用的地址。  















