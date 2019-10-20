public class People {

    public static void main(String[] args) {
//        People zs = new People() ;
//        zs.sleep();
//
//        People ls = new People() ;
//        ls.sleep();
        People zs = new People();
        //实参
//        zs.sleep1( "张三","家里","23:00");
//        infos = {name,place,time}
//        String[] infos = {"张三","家里","23:00"};
//        infos[0] = "zs" ;
//            zs.sleep2(infos);
//        zs.sleep3("张三","家里
        MyInfo info = new MyInfo();
        info.name = "zs";
        info.place = "binguan";
        info.time = 22;
        zs.sleep4(info);

        People ls = new People();
//        ls.sleep1("李四","宾馆","22:00");
//        String[] lsInfos = {"李四","宾馆","22:00"};
//        ls.sleep2(lsInfos);
//        ls.sleep3("李四", "宾馆", 22);
        MyInfo lsInfo = new MyInfo();
        lsInfo.name = "李四";
        lsInfo.place = "家";
        lsInfo.time = 23;
        ls.sleep4(lsInfo);

    }

    public void sleep() {
        System.out.println("睡觉...");
    }

    //String name = "张三" ;
    //形参
    public void sleep1(String name, String place, String time) {
        System.out.println(name + "在" + place + "睡觉..." + time);
    }

    //数组infos = {name,place,time}
    //String[] info = {"张三","家里","23:00"}
    //String[] infos = lsInofs ;
    public void sleep2(String[] infos) {
        infos[1] = "JIALI";
        System.out.println(infos[0] + "在" + infos[1] + "睡觉..." + infos[2]);
    }

    //sleep3(String[] infos,int time )
    //sleep3(Xxx xx) ：
    public void sleep3(String name, String place, int time) {
        System.out.println(name + "在" + place + "睡觉..." + time);
    }

    //MyInfo info = new MyInfo();
    //    info.name = "zs";
    //    info.place = "binguan";
    //    info.time = "22";
    public void sleep4(MyInfo info) {//Myinfo info = info
        System.out.println(info.name + "在" + info.place + "睡觉..." + info.time);
    }
}
