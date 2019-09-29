public class MethodReturn {
    public static void main(String[] args) {
        MethodReturn method = new MethodReturn();
        method.b();
    }

    public void a() {
        String name = "zs";
        System.out.println(name);
    }

    public String a1() {
        String name = "zs";
//        System.out.println(name);
        return name;
    }

    public void b() {
//        a();
        String name = a1();
        System.out.println("b:" + name);
    }
}
