package col;

/*
 * Created by 颜群
 */
public class Person implements Comparable{
    private int id ;
    private String name ;
    private int age ;
    private String city;
    public Person() {
    }



    public Person(int id, String name, int age, String city) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.city = city;
    }
    public Person( String name, int age, String city) {
        this.name = name;
        this.age = age;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", city='" + city + '\'' +
                '}';
    }

    //重写比较器接口中的方法

    /*
        {1,3,5,7  }升序
        {7,5,3,1  }降序
        //输入源   3     1  7  5
           {3}

        返回值：
            1正数：当前对象大，    降序
            -1负数：传入的对象大 ，升序
            0：一样大
     */
    @Override
    public int compareTo(Object o) {
        Person inputPerson = (Person)o ;
        //根据学号，降序
       int result =  this.id > inputPerson.id ?-1  : (   this.id   ==    inputPerson.id ?0:1 ) ;
       //如果学号相同，再根据姓名升序.  za  zs
        if(result  == 0 ){
            result = this.name .compareTo(      inputPerson.name) ;//调用String已经重写过的compareTo()
        }
        return result;
//        return 1;//当前对象，比传入对象大
    }
}
