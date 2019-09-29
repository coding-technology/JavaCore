public class Test13 {
    public static void main(String[] args) {
//        System.out.println("*******");
//        System.out.println("*******");
//        System.out.println("*******");
//        for(int j=0;j<3;j++){//行
//            for(int i=0;i<7;i++){//行内的*
//                System.out.print("*");
//            }
//            System.out.println();
//        }

//        System.out.println("*");
//        System.out.println("**");
//        System.out.println("***");
//        System.out.println("****");
//        System.out.println("*****");

        //直角
//        for(int j=0;j<5;j++){
//            for(int i=0;i<j+1 ;i++){//每一行内部的*
//                System.out.print("*");
//            }
//            System.out.println();
//        }

        //逆序
//        System.out.println("daosanjiao...");
//        for(int j=4;j>=0;j--){
//            for(int i=0;i<j+1 ;i++){//每一行内部的*
//                System.out.print("*");
//            }
//            System.out.println();
//        }


//        System.out.println("*****");
//        System.out.println("****");
//        System.out.println("***");
//        System.out.println("**");
//        System.out.println("*");
        //倒直角

//        for(int i=0;i<5;i++){
//            for(int j=0;j<5-i;j++){
//                System.out.print("*");
//            }
//            System.out.println();
//        }
        System.out.println("   *");
        System.out.println("  ***");
        System.out.println(" *****");
        System.out.println("*******");
        System.out.println(" *****");
        System.out.println("  ***");
        System.out.println("   *");
        System.out.println("循环..");

        for (int i = 0; i < 4; i++) {//行
            for (int k = 0; k < 3 - i; k++) {//空格
                System.out.print(" ");
            }
            for (int j = 0; j < 2 * i + 1; j++) {//*
                if (j == 0 || j == 2 * i)
                    System.out.print("*");
                else
                    System.out.print(" ");
            }
            System.out.println();
        }

        for (int i = 2; i >= 0; i--) {//行
            for (int k = 0; k < 3 - i; k++) {//空格
                System.out.print(" ");
            }
            for (int j = 0; j < 2 * i + 1; j++) {//*
                if (j == 0 || j == 2 * i)
                    System.out.print("*");
                else
                    System.out.print(" ");
            }
            System.out.println();
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < i + 1; j++) {                       // 4 * 6 =24
                System.out.print((j + 1) + "*" + (i + 1) + "=" + ((j + 1) * (i + 1)) + "\t\t");
            }
            System.out.println();
        }

        System.out.println("----------------------");
        for (int i = 0; i < 10; i++) {
            System.out.print("外层：" + i);
            for (int j = 0; j < 10; j++) {

                if (j == 3) {
                    // break ;
                    continue;
                }
                System.out.print("\t内层：" + j);
            }
            System.out.println();
        }

    }

}
