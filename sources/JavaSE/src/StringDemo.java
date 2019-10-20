public class StringDemo {
    static String s3;//有默认值  null

    //字符串基本操作
    public static void test1() {
        //定义字符串方式一：
        String s = "abcd";
        System.out.println(s);
        //基本类型  int num = 10 ;
        //对象类型： Person per = new Person();
        //定义字符串方式二：
        String s1 = "helloworld";
        System.out.println(s1);

        //方式三
        String s2 = ""; //方式三 默认生成的s2是 ""
        System.out.println(s2.equals(""));//“”
        System.out.println(s2 == null);//null


        System.out.println("s3" + s3);

        //常见的String方法
        String str = "  hello   world                                      ";
        boolean flag = str.equals("helloWorlD");
        //忽略大小写 比较
        boolean flag2 = str.equalsIgnoreCase("helloWorlD");
        System.out.println(flag);
        System.out.println(flag2);


        int len = str.length();//字符串长度
        System.out.println(len);


        //转为大写
        str = str.toUpperCase();
        System.out.println(str);

        //转为小写
        str = str.toLowerCase();
        System.out.println(str);

        //helloworld
        //判断 字符串A 是否存在于 另一个字符串B中，如果存在 ，则返回位置；如果不存在，返回-1
        //int position =  str.indexOf('o') ; //char ->int

        int position = str.lastIndexOf('o');
        System.out.println(position);
        //str
        System.out.println(str);


        System.out.println("实际长度" + str.length());
        str = str.trim();//去掉首尾空格，不会去掉中间的
        System.out.println("去掉首尾空格之后的 长度" + str.length());

    }

    //验证 邮箱是否 合法 157468995@qq.com       15768995.com
    public static boolean isValidateEmail(String email) {
        //合法情况
        return email.indexOf("@") != -1 && email.indexOf(".") != -1 &&
                email.indexOf("@") < email.indexOf(".");
    }

    //校验 电话是否合法
    public static void testSubstring() {
        //029-88888888
//        String phone = "18055555555"  ;
        //座机要求： 区号是 3或4位 ，右侧 8位
        String phone = "029-123456789";
        if (phone.indexOf("-") != -1) {
            System.out.println("座机号码");
            //phone.substring(start,end) :   [start,end)
            //phone.substring(start) :   从start到最后

//           phone =  phone.substring(2,6); //>=2 <6
//            System.out.println(phone);
//            区号是 3或4位 ，右侧 8位
            // 截取区号
            int start = 0;
            int end = phone.indexOf("-");
//            "029"   "0931"
            String zone = phone.substring(start, end);
            if (zone.length() == 3 || zone.length() == 4) {
                System.out.println("区号正确");
            } else {
                System.out.println("区号有误！");
            }
            //座机的右侧："029-12345678" ;
            int startRight = end + 1;
            String numberStr = phone.substring(startRight);
            if (numberStr.length() == 8) {
                System.out.println("号码正确");
            } else {
                System.out.println("号码有误！");
            }

        } else {
            System.out.println("手机号码");
        }
    }

    public static void testSubstring2(String phone) {
        //029-88888888
//        String phone = "18055555555"  ;
        //座机要求： 区号是 3或4位 ，右侧 8位
//        String phone = "029-12345678" ;
        if (phone.indexOf("-") != -1) {
            System.out.println("座机号码");
            int start = 0;
            int end = phone.indexOf("-");
//            "029"   "0931"
            String zone = phone.substring(start, end);
            //座机的右侧："029-12345678" ;
            int startRight = end + 1;
            String numberStr = phone.substring(startRight);

            //正确：  区号正确 且 号码正确
            if ((zone.length() == 3 || zone.length() == 4) && (numberStr.length() == 8)) {
                System.out.println("座机正确");
            } else {
                System.out.println("座机有误！");
            }


        } else {
            //18888888888
            System.out.println("手机号码");
            String first = phone.substring(0, 1);
            if (first.equals("1") && phone.length() == 11) {
                System.out.println("正确的手机号");
            } else {
                System.out.println("错误的手机号");
            }
        }
    }


    public static void testSplit() {
        String phone = "029-12345-678";
        String[] ps = phone.split("-");
        for (String p : ps) {
            System.out.println(p);
        }
//        System.out.println(ps[0]);
//        System.out.println(ps[1]);
//        System.out.println(ps[2]);

    }

    //staitc->static
    public static void main(String[] args) {
//        boolean result = isValidateEmail("157468995@qq.com");
//        System.out.println(result==true ? "合法":"不合法"          );
//        testSubstring();
//        testSubstring2("029-54548787");
        testSplit();
    }

}
