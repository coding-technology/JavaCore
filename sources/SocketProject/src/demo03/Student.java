package demo03;

import java.io.Serializable;

/*
 * Created by 颜群
 */
public class Student implements Serializable {//将要传递的对象序列化
    private int sid ;
    private String sname ;
    private int age ;

    public Student(){
    }

    public Student(int sid, String sname, int age) {
        this.sid = sid;
        this.sname = sname;
        this.age = age;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sid=" + sid +
                ", sname='" + sname + '\'' +
                ", age=" + age +
                '}';
    }
}