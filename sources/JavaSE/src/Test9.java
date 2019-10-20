public class Test9 {
    public static void main(String[] args) {
        int[] nums = {2, 12, 4, 20, 1};
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

        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);

        }

    }
}
