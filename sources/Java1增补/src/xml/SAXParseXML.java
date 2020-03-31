package xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/*
 * Created by 颜群
 */
public class SAXParseXML extends DefaultHandler {
    private List<Student> students ;

    private String tagName ;//null -> student
    private Student student  ;


    public List<Student> getStudents() {
        return students;
    }



    //开始解析xml文件 （执行一次）
    @Override
    public void startDocument() throws SAXException {
        students = new ArrayList<>() ;
    }
    //解析xml文件 结束（执行一次）
    @Override
    public void endDocument() throws SAXException {
        System.out.println("SAX解析结束...");
    }


    //开始解析xml元素（执行多次）
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if(qName.equals("student")){
            student =  new Student() ;
              int id = Integer.parseInt(  attributes.getValue( 0 )) ;
              student.setId( id );
        }
         this.tagName = qName ;//

    }
    //结束 解析xml元素（执行多次）
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
          if( qName.equals("student")){
                students.add(student) ;
          }
          this.tagName = null ;
    }

    //在startElement、endElement 之间调用多次
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

//        student
        if(this.tagName != null ){//student

            String data = new String(ch,start,length);//ch[] -> String

            if(this.tagName .equals("name")) {
                    student.setName( data );

            }

            if(this.tagName .equals("age")) {
                student.setAge( Integer.parseInt(data)    );
            }

            if(this.tagName .equals("birthday")) {
                try{
                    SimpleDateFormat sdf = new SimpleDateFormat(  "yyyy-mm-dd") ;
                     student.setBirthday( sdf.parse(   data)  );
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

        }

    }
}
