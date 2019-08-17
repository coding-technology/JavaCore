package interview.generics;

import java.util.ArrayList;
import java.util.List;

public class Demo01 {
    public static void main(String[] args) {

//        List<Object> objs = new ArrayList<Object>();

//        List<Object> objs = new ArrayList<String> ();
        //错： A<y> z = new B<x>();

        List<?> objs = new ArrayList<String> ();
        //正： A<?> z = new B<x>();
        List<? extends Object> objs2 = new ArrayList<String> ();

        //
        List<Object> list = new ArrayList<>();
        List<Object> list2 = new ArrayList<Object>();
      //  List<Object> list3 = new ArrayList<String>();
        List<? extends Object> list4 = new ArrayList<String>();
        List<? extends String> list5 = new ArrayList<String>();//在考虑泛型时，不用考虑 final






    }
}
