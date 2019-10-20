import java.util.StringTokenizer;

public class StringDemo02 {
    //split :特殊符号不适应：.  |  \ $ + * ，如果要用 加\\
    public static void test01() {
        String str = "hello|world";
        String[] split = str.split("\\|");
        for (int i = 0; i < split.length; i++) {
            System.out.println(split[i]);
        }
    }

    public static void test02() {
        String str = "helloaworld";
        StringTokenizer token = new StringTokenizer(str, "a");
        while (token.hasMoreElements()) {
            System.out.println(token.nextElement());
        }


    }

    //输入一个字符串 ，统计该字符串中某个单字符串出现的次数
    /*
        helloworld  ：
        helloworld -> {"h","e","l","l","o",...}

     */

    //input:helloworld
    public static int test03(String input, String word) {//l
//     helloworld -> {"h","e","l","l","o",...}
        int count = 0;
        String[] strs = new String[input.length()];
        for (int i = 0; i < input.length(); i++) {
            strs[i] = input.substring(i, i + 1);
            if (strs[i].equals(word)) {
                count++;
            }
        }
        return count;
    }

    //input:  helloworld      "low"  ，判断一个字符串中 ， 某个子字符串出现的次数
    public static int test04(String input, String llo) {
        /*
            input:  hellowollodxxxllloxxxxxxxx      ,llo
         */
//       int position = input.indexOf(llo) ;//==-1
//        System.out.println(position);
        int count = 0;
        while (input.indexOf(llo) != -1) {
            count++;
            input = input.substring(input.indexOf(llo) + 1);

        }
        return count;
    }

    public static void test05() {
        //indexOf("子字符串"):  子字符串在源字符串中的 位置

        //位置->字符

        String str = "hexxxoworxxd";
        int position = str.indexOf("llo");
        System.out.println(position);

        //位置->字符
        char c = str.charAt(2);
        System.out.println(c);

        //replace
        System.out.println(str);
//        str = str.replace('x','y');
//        System.out.println(str);
        str = str.replace("xow", "AAA");
        System.out.println(str);


    }
//helloworld :llo


    public static void test06() {

        StringBuffer sb = new StringBuffer("abc");//String str = "abc";
        //str += "aaa"
        //abcaaa
        sb.append("aaa");//追加
        sb.insert(2, "bbb");//插入
        System.out.println(sb);
        sb.reverse();//逆序
        System.out.println(sb);


        //String - StringBuffer各自独立，  可以相互转换
        //String ->StringBuffer

        String a = "hello";
        StringBuffer sb2 = new StringBuffer(a);

        //StringBuffer ->String
//       String b =  sb2.toString() ;
//      System.out.println(b);
        String c = sb2 + "";//任何类型遇到字符串 都会转为字符串
        System.out.println(c);
    }

    //"12345678"  ->  12,345,678
    public static void test07(String digital) {
        StringBuffer sb = new StringBuffer(digital);
        for (int i = digital.length() - 3; i > 0; i = i - 3) {
            sb.insert(i, ",");
        }

        System.out.println(sb.toString());

    }


    public static void main(String[] args) {
//    test01();
//    test02();
//    System.out.println( test03("ooohelloworldoo","o"));
//      System.out.println( test04( "hlloelloworllolllod" ,"o"));;
//        test05();
//        test06();
        test07("12345678");

    }


}
