package xml;

import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * Created by 颜群
 */
public class Student {
    private int id ;
    private String name ;
    private  int age ;
    private Date birthday ;

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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd") ;
       String birth =  sdf.format( birthday ) ;


        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", birthday=" + birth +
                '}';
    }
}
