## 内部类

匿名内部类：隐藏的继承了一个类（可以是普通类、抽象类，不能是被final修饰的类），或者实现了一个接口





## 泛型

List<Object> objs = new ArrayList<Object> (); 对

List<Object> objs = new ArrayList<String> ();错

List<?> objs = new ArrayList<String> ();对

List<?  extends Object> objs = new ArrayList<String> ();对

List<?  extends String> objs = new ArrayList<String> ();对,在考虑泛型时，不用考虑 final









## 包装类



int  Integer

byte Byte

short Short

long Long

float Float

double Double

boolean Boolean

char Character

自动装箱和拆箱：

​	Integer i = 10 ; //自动装箱  ：  包装类  = 基本类型

   int j = i ;//自动拆箱 ：   基本类型 = 包装类 





```
(Integer + int)  -> int类型
当包装类 遇到基本类型时，会自动转为基本类型
```



自动装箱：

```
  public static Integer valueOf(int i) {
        if (i >= IntegerCache.low && i <= IntegerCache.high)
            return IntegerCache.cache[i + (-IntegerCache.low)];
        return new Integer(i);
    }
```



默认范围:以Integer为例，在自动装箱时，如果判断 整数值的范围在[-128,127]之间，则直接使用 整形常量池中的值；

如果不在此范围，则会new 一个新的Integer();

201909











