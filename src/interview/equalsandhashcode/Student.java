package interview.equalsandhashcode;

public class Student {
    private String name;
    public Student() {
    }
    public Student(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return   name.hashCode();//"zs"  ->0x0010101   "ls"->0x001001
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj instanceof  Student){
            Student stu =  (Student)obj ;
            if(this.name .equals( stu.getName())){
                return true ;
            }
        }
        return false;
    }
}
