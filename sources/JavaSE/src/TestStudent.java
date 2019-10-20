public class TestStudent {

    //{stu1,stu2,stu3}
    public static void compareStudent(Student[] students) {
        //冒泡排序 1 5 9 10  25
        for (int i = 0; i < students.length - 1; i++) {
            for (int j = 0; j < students.length - 1 - i; j++) {
                if (students[j].getSum() < students[j + 1].getSum()) {
                    //交换 int
                    Student temp = students[j];
                    students[j] = students[j + 1];
                    students[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {

        Student stu1 = new Student();
        stu1.name = "zs";
        stu1.javaScore = 99;
        stu1.sqlScore = 98;

        Student stu2 = new Student();
        stu2.name = "ls";
        stu2.javaScore = 98;
        stu2.sqlScore = 98;

        Student stu3 = new Student();
        stu3.name = "ww";
        stu3.javaScore = 100;
        stu3.sqlScore = 98;


        double stu1Avg = stu1.getAvg();

        double stu2Avg = stu2.getAvg();
        double stu3Avg = stu3.getAvg();
        System.out.println(stu1.name + "\t" + stu1.getSum() + "\t" + stu1.getAvg());
        System.out.println(stu2.name + "\t" + stu2.getSum() + "\t" + stu2.getAvg());
        System.out.println(stu3.name + "\t" + stu3.getSum() + "\t" + stu3.getAvg());

        Student[] students = new Student[]{stu1, stu2, stu3};

        for (Student student : students) {
            System.out.println(student.name + "\t" + student.getAvg() + "\t" + student.getSum());
        }
        compareStudent(students);

        //

        System.out.println("---");
        for (int i = 0; i < students.length; i++) {
            System.out.println(students[i].name + "\t" + students[i].getAvg() + "\t" + students[i].getSum());
        }
    }
}
