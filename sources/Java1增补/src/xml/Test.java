package xml;


import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.InputStream;
import java.util.List;

/*
 * Created by 颜群
 */
public class Test {
    public static void main(String[] args) throws Exception{
        SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
        InputStream in = Test.class.getClassLoader().getResourceAsStream("xml/student.xml") ;

        SAXParseXML saxParseXML =   new SAXParseXML() ;
        parser.parse(  in,saxParseXML);

        List<Student> students = saxParseXML.getStudents();
        for(Student stu :students)
        {
            System.out.println(stu);
        }


    }
}
