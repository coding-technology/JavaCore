public class ProvinceCities {
    public static void main(String[] args) {
        System.out.println("  \"  ");
        System.out.println("  \\  ");
        System.out.println("  \\\\  ");
        //陕西  山西  四川 ....
        //西安 咸阳 渭南 ....     成都 都江堰...
        String[] provinces = {"陕西", "山西", "四川"};
        //核心： 一维数组的下标 正好是  二维数组的行号
        //  一维元素值->下标 -> 要打印的二维数组的行号 -> 将二维数组的该行全部打印


        String[][] cities = {
                {"西安", "咸阳", "渭南"},
                {"太原", "运城", "大同"},
                {"成都", "都江堰", "广元"}
        };

//        System.out.println(cities[1][1]);
//        System.out.println(cities[1][2]);
//        一维元素值->下标
        String p = "陕西";
        int position = -1;
        for (int i = 0; i < provinces.length; i++) {
            if (p.equals(provinces[i])) {//{"陕西","山西","四川"} ;
                position = i;//1
                break;
            }
        }
        //要打印的二维数组的行号 1
//              String[][] cities = {
//                               {"西安","咸阳","渭南"}, 0
//                                {"太原","运城","大同"},1   :  cities[1]
//                                {"成都","都江堰","广元"}2
//                           } ;

        if (position == -1) {
            System.out.println("省份输入有误！");
        } else {
            for (int i = 0; i < cities[position].length; i++) {
                System.out.println(cities[position][i]);
            }
        }


    }
}
