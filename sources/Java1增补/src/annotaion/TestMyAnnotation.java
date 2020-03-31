package annotaion;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;

/*
 * Created by 颜群
 */

public class TestMyAnnotation {


    @MyAnnotation(value="李四",age=33)
    @Deprecated
    public static void test() throws  Exception{

        Annotation[] annotations = Class.forName("annotaion.TestMyAnnotation").getMethod("test").getAnnotations();
        for(Annotation a :annotations){
            if(a  instanceof  MyAnnotation ){//@MyAnnotation
                System.out.println(    ((MyAnnotation)a)  .value()     );
                System.out.println(    ((MyAnnotation)a)  .age()     );
            }else{//@Deprecated
                System.out.println(        "@Deprecated"     );
            }
        }
    }

    @SuppressWarnings("all")
    public static void main(String[] args) throws  Exception {
        test() ;
    }
}
