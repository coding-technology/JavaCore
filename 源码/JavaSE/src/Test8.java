public class Test8 {
//8	4	2	 4
// 3 = 数组名.length-1//

    /*

    有一个数列： 8	4	2	1	23	344	12
1.求所有数字的和
2. 随机产生一个 1-3 之间的数，判断数列中是否有此数
3.进行排序，并输出结果

     */

    public static void main(String[] args) {
//        1.求所有数字的和
        int[] nums = {8, 4, 2, 1, 23, 100, 12};
//        nums[0] +  nums[1] + nums[2] + nums[3] +  ... + nums[6]
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];//sum=sum +nums[i] ;
        }
        System.out.println(sum);

        //2. 随机产生一个 1-3 之间的数，判断数列中是否有此数
//      int ran = (int)( Math.random()*10 )%3 + 1;
        //2个可能性：boolean
        boolean flag = false;
        int ran = (int) (Math.random() * 3) + 1;
        System.out.println("随机数：" + ran);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == ran) {
//               System.out.println("存在");
                flag = true;
                break;
            }
        }
        if (flag) {
            System.out.println("存在");
        } else {
            System.out.println("不存在");
        }


//        3.进行排序，并输出结果
//        int[] nums = { 8,4	,2	,1	,23	,100	,12};
        System.out.println("排序前:");
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + "\t");
        }
        //排序    classpath:  .; %JAVA_HOME%\lib
//        Arrays.sort(nums);//自动排序，升序  api开发
        //自己写排序算法   ， 冒泡排序

        for (int i = 0; i < nums.length - 1; i++)//  第一趟、第二趟、第三趟、
        {
            for (int j = 0; j < nums.length - 1 - i; j++)//每一趟内部的两两比较 ，几次？
            {
                if (nums[j] > nums[j + 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
        }


        System.out.println("排序后:");
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + "\t");
        }


        //0-2 ->  1-3

        // 0-9 -> 0 1 2，不合理
        /*
               0    0
               1    1
               2    2
               3    0
               4    1
               5    2
               6    0
               7    1
               8    2
               9    0





         */


    }
}
