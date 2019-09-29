public class Test14 {
    public static void main(String[] args) {
        //while do..while for
//        int[] nums = new int[]{33,2,1,2,455};
//        for(int i=0;i<nums.length;i++){
////            System.out.println(nums[i]);
////        }
        //for(元素类型  变量名 : 数组)  ,变量名 相当于 nums[i]
//        for(int  x : nums){
//            System.out.println(x);
//        }

//        int[] nums = new int[]{33,2,1,2,455};
//        数据类型[]  变量名  = new 数据类型[]{...}
        String[] names = new String[]{"abc", "aaa", "bbb"};
        for (String name : names) {
            System.out.println(name);
        }
        long n = 13; //long = int
        //形式上：  数据类型[]  变量名  = new 数据类型[]{...}
        long[] nums = new long[]{(byte) 33, 2, 1, 2, 455};//兼容   long< float
        // =左右两侧 的类型必须一致，但是 数据值 只要需要兼容（类型本身 或范围比其小的类型）即可。
    }
}
