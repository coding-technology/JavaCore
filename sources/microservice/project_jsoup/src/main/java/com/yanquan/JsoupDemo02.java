package com.yanquan;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;

/*
 * Created by 颜群
 */
public class JsoupDemo02 {
    public static void main(String[] args) throws Exception{

        Document document = Jsoup.parse("<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /><meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" /><title>蓝桥</title><meta name=\"baidu-site-verification\" content=\"oymzMRHnc0\" /><meta name=\"360-site-verification\" content=\"7e9fc70fd0b9dc188a9f5b583b5f577a\" /></head><body><div class=\"header clearfix\"><div class=\"nav fl\"><a href=\"http://www.lanqiao.org/\"><img src=\"http://tpl.lanqiao.org/web/html/img/logo.png\" width=\"177\"></a><a class=\"act\" href=\"##\" target=\"_blank\">首页</a><a href=\"http://dasai.lanqiao.cn/\" target=\"_blank\">蓝桥杯</a><a href=\"http://www.lanqiaohr.com/\" target=\"_blank\">我要找工作</a></div><div class=\"fr\"><span><i class=\"icon-phone\"></i>4006-588-662</span></div></div><!--头部--></div></div></div></div><!--我要学习--><div class=\"box\"></div><!--蓝桥掘金--></div><!--主体内容--></body></html>");
        Element title = document.getElementsByTag("title").first();
        System.out.println(  title.text());

        System.out.println("----");
        Document document2 = Jsoup.parse(new File("D:/lanqiao.txt"), "UTF-8");
        String title2 = document2.title();
        System.out.println(title2);

        Document document3 = Jsoup.parse(new FileInputStream(  new File("D:/lanqiao.txt")), "UTF-8","");
        String title3 = document3.title();
        System.out.println(title3);

        System.out.println("--44--");
        Document document4 = Jsoup.parse(new URL("http://www.lanqiao.cn/"),5000);
        String title4 = document4.title();
        System.out.println(title4);

        System.out.println("--555--");
        Document document5 = Jsoup.connect("http://www.lanqiao.cn/").get();
        System.out.println( document5.title());
    }
}
