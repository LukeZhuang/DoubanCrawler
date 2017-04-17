package com.luke.doubancrawler.processor;

import com.luke.doubancrawler.utils.Constant;
import com.luke.doubancrawler.utils.FileUtil;
import org.apache.log4j.Logger;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * Created by qinan on 2017/4/10.
 */
public class DoubanProcessor implements PageProcessor {

    private Logger log = Logger.getLogger(DoubanProcessor.class);

    private final static String INDEX="https://movie\\.douban\\.com/tag/$";
    private final static String TYPE_INDEX="https://movie\\.douban\\.com/tag/.+?(start=\\d+&type=T)?$";
    private final static String MOVIE_PAGE="https://movie\\.douban\\.com/subject/\\d+/$";
    private final static String REVIEW_PAGE="https://movie\\.douban\\.com/subject/\\d+/comments\\?(start=\\d+&limit=20&sort=new_score&)?status=(P|F)$";

    private Site site;

    public void process(Page page) {
        if(page.getUrl().regex(INDEX).match()){
            Processor.processIndexPage(page,log);
        }else if(page.getUrl().regex(TYPE_INDEX).match()){
            Processor.processTypePage(page,log);
        }else if(page.getUrl().regex(MOVIE_PAGE).match()){
            Processor.processMoviePage(page,log);
        }else if(page.getUrl().regex(REVIEW_PAGE).match()){
            Processor.processReviewPage(page,log);
        }
    }

    public Site getSite() {
        if(Constant.PROXY_USE==true) {
            site = Site.me()
                    .setDomain("https://movie.douban.com/")
                    .setUserAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.133 Safari/537.36")
                    .setHttpProxyPool(FileUtil.readProxyList(),false)
                    .setCycleRetryTimes(10)
                    .setSleepTime(700);
        }else{
            site = Site.me()
                    .setDomain("https://movie.douban.com/")
                    .setUserAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.133 Safari/537.36")
                    .setRetryTimes(10)
                    .setCycleRetryTimes(5)
                    .setSleepTime(700);
        }
        return site;
    }
}
