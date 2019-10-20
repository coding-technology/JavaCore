public class Test11 {
    public static void main(String[] args) {
        //给67,87,88,98,99数组中 插入一个元素90 ，要求保持从小到大的顺序。 （不能用排序算法）
        int[] nums = new int[]{67, 87, 88, 98, 99};

        //创建新数组，能够容纳新插入后的 全部元素
        int[] newNums = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            newNums[i] = nums[i];
        }

        int insert = 95;
        //位置
        int postion = newNums.length - 1;//默认插入的位置 就是 最大值的位置
        for (int i = 0; i < newNums.length; i++) {
            if (newNums[i] > insert) {
                postion = i;
                break;
            }
        }
        System.out.println("位置：" + postion);

        //平移
        for (int i = newNums.length - 2; i >= postion; i--) {
            newNums[i + 1] = newNums[i];//newNums[-1]
        }
        //插入
        newNums[postion] = insert;

        System.out.println("查看新数组：");
        for (int i = 0; i < newNums.length; i++) {
            System.out.println(newNums[i]);
        }
    }
}
