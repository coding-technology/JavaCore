# JVM： java virtua Machine

jdk中包含了jvm和“屏蔽操作系统差异的组件”

- jvm各个操作系统之上是一致的
- “屏蔽操作系统差异的组件：在各个PC上各不相同（联想下载jdk，不同系统 需要下载不同版本的jdk）

![1568186168727](jvm.assets/1568186168727.png)



## 类的生命周期

生命周期： 类的加载->连接->初始化->使用->卸载

-  类的加载

  查找并加载类的二进制数据（class文件）

  硬盘上的class文件 加载到jvm内存中

- 连接 ：确定类与类之间的关系  ； student.setAddress( address ); 

  - 验证

    .class 正确性校验

  - 准备

    static静态变量分配内存，并赋初始化默认值值

    static int num =  10 ;  在准备阶段，会把num=0，之后（初始化阶段）再将0修改为10

    在准备阶段，JVM中只有类，没有对象。

    初始化顺序： static ->非static ->构造方法

    public class Student{

    ​	static int age ; //在准备阶段，将age = 0 ;

    ​	String name ; 

    }

    

  - 解析:把类中符号引用，转为直接引用

    前期阶段，还不知道类的具体内存地址，只能使用“com.yanqun.pojo.Student ”来替代Student类，“com.yanqun.pojo.Student ”就称为符号引用；

    在解析阶段，JVM就可以将 “com.yanqun.pojo.Student ”映射成实际的内存地址，会后就用 内存地址来代替Student，这种使用 内存地址来使用 类的方法 称为直接引用。

 - 初始化：给static变量 赋予正确的值

static int num =  10 ;  在连接的准备阶段，会把num=0，之后（初始化阶段）再将0修改为10

 - 使用： 对象的初始化、对象的垃圾回收、对象的销毁
 - 卸载



jvm结束生命周期的时机：

- 正常结束
- 异常结束/错误  
- System.exit()
- 操作系统异常

