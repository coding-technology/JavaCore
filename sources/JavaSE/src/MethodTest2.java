public class MethodTest2 {
    //学生姓名组成的数组（5个） ： 指定查找区间 和 查找的名字，返回此人是否存在（如果存在返回位置，否则-1）

    //方法参数如何编写？  按需
    public static int findNameInStudents(String[] names, int start, int end, String name) {
        //{"zs","ls","ww","zl","sq"}
        int position = -1;
        for (int i = start; i <= end; i++) {
            if (names[i].equals(name)) {
                position = i;
            }
        }
        return position;
    }

    public static void main(String[] args) {
        String[] names = {"zs", "ls", "ww", "zl", "sq"};
        int start = 2;
        int end = 3;
        String name = "sq";
        int result = findNameInStudents(names, start, end, name);

        System.out.println(result);
    }


}
