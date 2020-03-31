package com.yanquan;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.util.Iterator;

/*
 * Created by 颜群
 */
public class JsoupDemo01 {
    public static void main(String[] args) throws Exception{
       //将html文件的内容读取到内存    commons-io
        String lanqiaoHtml = FileUtils.readFileToString(new File("d:/lanqiao.txt"), "UTF-8");
        //解析  (lanqiao.txt -> String ->Document)
        Document document = Jsoup.parse(lanqiaoHtml);
        //jquery ,xml
        //标签选择器
        Element title = document.getElementsByTag("title").first();
        //id选择器
        Element dasaiId = document.getElementById("dasaiId");
        // 属性选择器
        Element hello1 = document.getElementsByAttribute("hello").first();


        System.out.println(hello1);
        System.out.println(hello1.text());
        //attr()可以拿到一切属性
        System.out.println(  hello1.attr("hello"));  ;
        System.out.println(  hello1.attr("id"));  ;
        // hello1.xxx()//只能获取一些常见的html内置属性

        System.out.println(  hello1.id() );

        System.out.println("-------");
        Attributes attributes = hello1.attributes();//获取当前元素的全部属性
        Iterator<Attribute> iterator = attributes.iterator();
        while(iterator.hasNext()){
            Attribute attribute = iterator.next();
            System.out.println( attribute.getKey());
            System.out.println(attribute.getValue());
        }


        System.out.println("-------");
        
        
        


        Element hello2 = document.getElementsByAttributeValue("hello", "word2").first();


        //类选择器class
//        news-msg
        Element last = document.getElementsByClass("news-msg").last();


        System.out.println(   title.text()        );
        System.out.println(   dasaiId.text()        );
        System.out.println(   last.text()        );
        System.out.println(   hello1.text()        );
        System.out.println(   hello2.text()        );

        System.out.println("-----jsoup选择器,select--------");
        Elements metas = document.select("meta");//标签
        for(Element element: metas){
            System.out.println(element.attr("name"));
        }

        //#id
        Elements selectId = document.select("#dasaiId");
        System.out.println(selectId.text());

        //.class
        Elements selectClasses = document.select(".news-msg");
        for(Element element:selectClasses){
            System.out.println(element.text());
        }
        System.out.println("****");
        //属性  []
        Elements selectAttrs = document.select("[myname]");
        for(Element element:selectAttrs){
            System.out.println(element.text());
        }

        Elements selectAttrs2 = document.select("[myname=mynews2]");
        for(Element element:selectAttrs2){
            System.out.println(element.text());
        }

        //复合选择器
        //交集选择器：直接拼起来写
        Elements selectJJ = document.select(".news-msg[myname=mynews2]");
        System.out.println(selectJJ.toString());
        System.out.println("***---");
        //并集选择器: , 。特殊：个别选择器 和jquery用法不一致，例如jsoup中不支持，并集
        Elements selectBJ = document.select("[myname=mynews],news-msg2");
        System.out.println(selectBJ+"特殊情况：jsoup中不能像jq那样使用,实现并集");

        System.out.println("////");
        //后代:空格
        Elements elementsHD = document.select("#myulID li");
        System.out.println(elementsHD.toString());
        System.out.println("////2222");

        Elements elementsHD2 = document.select("#myulID a");
        System.out.println(elementsHD2.toString());

        //子代:>
        System.out.println("////333");
        Elements elementsZD = document.select("#myulID>li");
        System.out.println(elementsZD.toString());
        System.out.println("////444");

        Elements elementsZD2 = document.select("#myulID>a");//a是#myulID的孙子代，不是直接子代，因此无法通过子代选择器选中
        System.out.println(elementsZD2.toString());

        System.out.println("////555");
        //全选
        Elements selectQX = document.select("#login_normal>*");
        System.out.println(selectQX);
    }
}
