package interview.equalsandhashcode;

public class Person {
    private String name;
    private int age ;
    public Person() {
    }
    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    //约定： 如果name和age相同，则返回true
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        //name  age
        if(obj instanceof  Person){
            Person per = (Person)obj ;
            //用传入的per，和当前对象this比较
            if(this.name.equals(per.getName())  &&  this.age==per.getAge() ){
                return true ;
            }
        }
        return false;
    }

    //equals和hashcode的重写逻辑一致（例如，两个方法 都是根据name和age来计算）
    @Override
    public int hashCode() {//"zs" ,23   ,  "ls" ,24
        return name.hashCode() & age ;
    }
}
