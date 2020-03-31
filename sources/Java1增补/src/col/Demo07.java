package col;

import java.util.Arrays;

/*
 * Created by 颜群
 */
public class Demo07 {
    public static void main(String[] args) {
        int[] arr = new int[]{3,2,14,6,1};
        Arrays.sort(arr);

        for(int i=0;i<arr.length;i++){
            System.out.println(arr[i]);
        }
//
//        Arrays.fill(arr,5);
//        for(int i=0;i<arr.length;i++){
//            System.out.println(arr[i]);
//        }

        Arrays.sort(arr);
        //通过二分法，在arr中查找2的下标（前提是 数组已经排好序）
        int position = Arrays.binarySearch(arr, 2);
        System.out.println(position);


    }
}
