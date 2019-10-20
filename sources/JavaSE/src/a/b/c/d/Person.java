package a.b.c.d;

//private  + get/set
public class Person {
    private String name;
    private int age;

    public void showInfo() {
        System.out.println(this.name + "--" + this.age);
    }

    public String getName() {
        return name;
    }

//    public void setName(String n) {
//        name = n;
//    }

    /*
    name:方法的参数name
    this.name:当前类的属性name
 */
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

//    public void setAge(int a){//23
//        if(a>=0 && a<=120){
//            age = a ;
//        }else {
//            age = -1;//-1:标识写错
//            System.out.println("年龄有误！");
//        }
//    }


}
