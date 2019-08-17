## 异常分类

以下程序，能否成功运行？

```
public class ExceptionDemo01 {
    void test() throws NullPointerException{
        System.out.println("A...");
    }
    public static void main(String[] args) {
        new ExceptionDemo01().test();
    }
}
```

解答：

![1565339819579](C:\Users\YANQUN\AppData\Roaming\Typora\typora-user-images\1565339819579.png)

异常：运行异常和检查异常

运行异常：RuntimeException，运行时才会发现的异常（编译时不会进行检查）。

检查异常：CheckedException（泛指 除了RuntimeException以外的其他所有异常） ：编译时会进行检查。

- 如果是CheckedException,在编写代码时，必须try..catch或者throws二选一。

- 但是RuntimeException在编写时不会进行异常检查，因此本题目中抛出的NullPointerException在编译阶段不会被检查。而在运行时，本题中并不会造成NullPointerException，因此无需任何处理，也不会报错。

  

  简言之，本题目在编译阶段不会进行异常检查（本题是RuntimeException），而在运行时又不会发生异常，因此是正确的，会正常运行。



## ==和equals()的区别？

==比较运算符

equals()最初是在Object中定义的一个方法。

Object中定义的equals()就是== ，只不过 一般来说  其子类都会重写equals()方法 将其重写为 比较“内容”是否相等，例如String.

==比较的对象的引用地址（内存地址），而一般情况下equals()比较的是内容相等。

==还可以比较基本的数据类型。



## 举例说明，如何重写equals()方法？



​	如果person对象的name和age相同，则返回true；否则返回false。

```
public class Person {
    private String name;
    private int age ;
    public Person() {
    }
    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }
    ...
    //约定： 如果name和age相同，则返回true
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        //name  age
        if(obj instanceof  Person){
            Person per = (Person)obj ;
            //用传入的per，和当前对象this比较
            if(this.name.equals(per.getName())  &&  this.age==per.getAge() ){
                return true ;
            }
        }
        return false;
    }
}
```









## 重写equals方法为什么要重写hashcode方法？

​	

两个对象相等（引用地址），则hashcode一定相同

如果两个对象的hashcod相同，这俩对象不一定相同。

两个对象不等（引用地址），hashcode不一定不等

如果两个对象的hashcod不同，这俩对象一定不同。



## 完整的总结hashcode和equals()

判断元素内容是否相等：

1.根据hashcode 快速定位 （提高效率，避免了在大量集合中 由于遍历带来的效率问题）

2.根据equals判断内容是否相同 （判断 正确性）

如果要判断元素的内容 是否相同，就要重写hashcode和equals()



## HashSet和HashMap之间的关系

```
  public HashSet() {
        map = new HashMap<>();
    }
```

可以发现，hashset底层是由hashmap实现的

hashmap.put(key,value );

hashset.add(key,常量值);  所有hashset中增加的Value 都是一个常量值 PRESENT  (new Object())

对于hashset： 在增加元素时，如果集合中不存在，则返回true ；如果已经存在则返回false。

对于hashmap： 在增加元素时，如果key已经存在，则将该key对应的value值覆盖掉





set.add(a)  ->true

set.add(a)  ->false



map集合：a,c

map.put（a,b） 

map.put   (a,c)  



map.put(key,value)返回值：是在本次增加元素之前，key对应的value值、

set.put()返回值：是之前是否已经存在。如果不存在：返回true；如果存在，返回false

二者差异的根本原因，是因为二者的研究对象不一致：map.put 第二次 会覆盖掉一次 相同key对应的“value”值； set.add()第二次 会直接忽略掉，忽略的是key.
















## String 和 StringBuﬀer、StringBuilder 的区别是什么？String 为什么是不可变的

**可变性**

常见错误回答1：

String类是final修饰的，因此String中的字符串不可变。实际上，final修饰的类，只能保证该类不能被继承，与字符串是否改变没有关系；

常见错误回答2：

在String源码中，定义存储字符串的源码是private final char value[]  ， 由于value[]是final修饰的，因此因此String中的字符串不可变。实际上，此时final修饰的是引用类型，只能保证引用的对象地址不能改变，但对象的内容（即字符串的内容）是可以改变的。

正确回答：

String类的不可变性实际在于作者的精心设计。例如，如果让你设计一个getXxx(String name)方法，你既可以设计成以下形式：

```
String getXxx(String name){
	  return name ;
}
```

也可以设计成以下形式：

```
String getXxx(String name){
	 return new Other(name) ;
}
```

​	如果设计成形式一，那么取到的值就是输入值本身；如果设计成形式二，取到的值就是一个新对象。简言之，如何设计一个方法的返回值，归根节点还是“看作者心情”。String不可变的原因也是一样的，是由于编写String的作者精心设计，所以导致了String类的不可变性。

​	如果要刨根问底，研究“作者的心情，究竟是如何的？”，即到底是如何设计成String类的不可变性的，也可以参阅《Effective Java》中 “使可变性最小化” 中对 “设计不可变类” 的解释，具体如下：

​	不可变类只是其实例不能被修改的类。每个实例中包含的所有信息都必须在创建该实例的时候就提供，并且在对象的整个生命周期内固定不变。为了使类不可变，要遵循下面五条规则：

- 不要提供任何会修改对象状态的方法。

- 保证类不会被扩展。 一般的做法是让这个类称为 final 的，防止子类化，破坏该类的不可变行为。

- 使所有的域都是 final 的。

+ 使所有的域都成为私有的。 防止客户端获得访问被域引用的可变对象的权限，并防止客户端直接修改这些对象。

- 确保对于任何可变性组件的互斥访问。 如果类具有指向可变对象的域，则必须确保该类的客户端无法获得指向这些对象的引用。

  在 Java 平台类库中，包含许多不可变类，例如 String , 基本类型的包装类，BigInteger, BigDecimal 等等。综上所述，不可变类具有一些显著的通用特征：类本身是 final 修饰的；所有的域几乎都是私有 final 的；不会对外暴露可以修改对象属性的方法。通过查阅 String 的源码，可以清晰的看到这些特征。

 

​	此外，还要注意以下两点：

1. StringBuilder 与 StringBuﬀer 都继承自 AbstractStringBuilder 类。

2. String覆盖了equals方法和hashCode方法，而StringBuffer/StringBuilder没有覆盖equals方法和hashCode方法，所以，将StringBuffer/StringBuilder对象存储进Java集合类中时会出现问题。



## 说说&和&&的区别 

​	&和&&都可以用作逻辑与的运算符，表示逻辑与（and），当运算符两边的表达式的结果都为true时，整个运算结果才为true，否则，只要有一方为false，则结果为false。

​	&&还具有短路的功能，即如果第一个表达式为false，则不再计算第二个表达式，例如，对于if(str != null && !str.equals(“”))表达式，当str为null时，后面的表达式不会执行，所以不会出现NullPointerException如果将&&改为&，则会抛出NullPointerException异常。If(x==33 & ++y>0) y会增长，If(x==33 && ++y>0)不会增长

​	&还可以用作位运算符，当&操作符两边的表达式不是boolean类型时，&表示按位与操作，我们通常使用0x0f来与一个整数进行&运算，来获取该整数的最低4个bit位，例如，0x31 & 0x0f的结果为0x01。 

​	|与||同理。 



## 试述TCP 三次握手和四次挥手

原因：任何数据传输都无法保证绝对的可靠性

三次：建立连接

四次：关闭连接 （多了一次：服务端在收到客户端的关闭请求后，不能立刻也关闭还需要处理 最后一次请求的数据，请全部数据处理完毕之后，才能再向客户端发出 关闭请求）



