public class Demo {
    public  static void something() {
        int i= 2 ;
        Object[] objs = new Object[3] ;
        try {
            objs[i].hashCode();
        }catch (ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        }finally {
            System.out.println("模拟释放资源...");
        }
        //如果出现了NullPointerException等其他异常，则不会有任何异常提示
    }

    public static void main(String[] args) {
        something();
    }
}
