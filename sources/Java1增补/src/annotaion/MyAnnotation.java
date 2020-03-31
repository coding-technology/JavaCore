package annotaion;

/*
 * Created by 颜群
 */

import java.lang.annotation.*;

@Documented
@Target(value= {ElementType.FIELD   ,ElementType.METHOD,ElementType.TYPE,
        ElementType.PARAMETER, ElementType.LOCAL_VARIABLE} )
@Retention( RetentionPolicy.RUNTIME )
public @interface MyAnnotation {
    /*
        用定义方法的形式，定义一个属性 value
        方法的名字，就是属性的名字；方法的返回值，就是属性的类型
     */
    String value()  default "张三";//String value = "张三" ；
    int age() default  22 ;
}
