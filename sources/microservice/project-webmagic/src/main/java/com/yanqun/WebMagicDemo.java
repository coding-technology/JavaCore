package com.yanqun;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

/*
 * Created by 颜群
 */
public class WebMagicDemo  implements PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000);
    public static void main(String[] args) {
        //webmagic对于爬取https协议的网站 ，存在bug
        Spider.create( new WebMagicDemo() ).addUrl("http://weike.lanqiao.cn/")

        .addPipeline( new ConsolePipeline())
        .addPipeline( new FilePipeline("e:/data"))//文件夹
          . run();

    }

    @Override
    public void process(Page page) {
        System.out.println( page.getHtml().xpath("//div[@class=classList]/div[3]/a/div/img") );//获取网页源码
        //解析  xpath
    }

    @Override
    public Site getSite() {
        return site;
    }

}
