public class Test10 {
    public static void main(String[] args) {
        //求5个学生的最高分
        // {87,98,67,88,99} ->排序
        //{67,87,88,98,99}
        //最小值 :数组名【0】  最大值：数组名【】
        int[] nums = {87, 98, 67, 88, 99};
//        System.out.println(nums[0]);
//        System.out.println(nums[nums.length-1]);
        //打擂台：假设第一个上台的 就是擂主
        int max = nums[0];
       /* if(nums[1] > max){
            max= nums[1];
        }

        if(nums[2]  > max){
            max= nums[2];
        }
        //
        //
        if(nums[nums.length-1]  > max){
            max= nums[nums.length-1];
        }
    */
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
        }
        System.out.println(max);

        int min = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < min) {
                min = nums[i];
            }
        }
        System.out.println(min);


    }
}
