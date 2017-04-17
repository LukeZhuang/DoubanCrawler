package com.luke.doubancrawler.processor;

import com.luke.doubancrawler.dal.model.MovieBean;
import com.luke.doubancrawler.dal.model.ReviewBean;
import com.luke.doubancrawler.utils.StringUtil;
import org.apache.log4j.Logger;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by qinan on 2017/4/10.
 */
public class Processor {

    public static void processIndexPage(Page page,Logger log){
        List<String> types=page.getHtml().xpath("//div[@class=article]/table[1]/tbody/tr/td/a/text()").all();
        for(String type:types){
            log.info(type);
        }
        //进入类型列表页面
        page.addTargetRequests(page.getHtml().xpath("//div[@class=article]/table[1]/tbody/tr/td/a/@href").all());
    }

    public static void processTypePage(Page page,Logger log){
        String page_id_str=page.getUrl().regex("start=(\\d+)").toString();
        int page_id=0;
        if(page_id_str!=null)
            page_id=Integer.parseInt(page_id_str);
        List<String> movies=page.getHtml().xpath("//div[@class=article]/div[2]/table/tbody/tr/td[2]/div/a/text()").all();
//        for(String movie:movies){
//            log.info(movie);
//        }
        //进入电影页面
        page.addTargetRequests(page.getHtml().xpath("//div[@class=article]/div[2]/table/tbody/tr/td[2]/div/a/@href").all());
        //TODO:跳页
        if(page_id<=100)
            page.addTargetRequests(page.getHtml().xpath("//div[@class=paginator]/a/@href").all());
    }

    public static void processMoviePage(Page page,Logger log) {
        String movie = page.getHtml().xpath("//div[@id=content]/h1/span[1]/text()").toString();
//        String movie = page.getHtml().xpath("//div[@id=content]/h1/span[1]/text()").toString().split(" ")[0];
        Selectable info = page.getHtml().xpath("//div[@id=info]");
        List<String> types = info.xpath("//span[@property=v:genre]/text()").all();
        String type = "";
        for (String t : types)
            type += t + ",";
        if(type.length()>0)
            type = type.substring(0, type.length() - 1);
        Matcher m = Pattern.compile(" <span class=\"pl\">制片国家/地区:</span> (.*)\\n").matcher(info.toString());
        String country="";
        if (m.find()) {
            country = m.group(1);
            String[] cs = country.split(" / ");
            country = "";
            for (String c : cs)
                country += c + ",";
            country = country.substring(0, country.length() - 1);
        }
        Matcher m2 = Pattern.compile("<span class=\"pl\">语言:</span> (.*)\\n").matcher(info.toString());
        String language="";
        if(m2.find()){
            language = m2.group(1);
            String[] ls = language.split(" / ");
            language = "";
            for (String l : ls)
                language += l + ",";
            language = language.substring(0, language.length() - 1);
        }
        List<String> dates=info.xpath("//span[@property=v:initialReleaseDate]/text()").all();
        String date=null;
        if(dates.size()==0) {
            date="";
        }else {
            for (String d : dates)
                if (d.indexOf("中国大陆") != -1) {
                    date=d.split("\\(")[0];
                }
            if (date == null) {
                date=dates.get(0).split("\\(")[0];
            }
        }
        Selectable rateInfo=page.getHtml().xpath("//div[@class='rating_wrap clearbox']");
        String grade=rateInfo.xpath("//div[@class='rating_self clearfix']/strong/text()").toString();
        if(grade.equals(""))
            grade="-1";
        String people_str=rateInfo.xpath("//div[@class='rating_self clearfix']//div[@class='rating_right']//div[@class='rating_sum']/a/span/text()").toString();
        if(people_str==null)
            people_str="-1";
        int people=Integer.parseInt(people_str);
        Selectable rateStar=rateInfo.xpath("//div[@class=ratings-on-weight]");
        List<String> stars=rateStar.xpath("//div[@class=item]//span[@class=rating_per]/text()").all();
        Selectable summary_div=page.getHtml().xpath("//div[@id='link-report']");
        String summary;
        if(summary_div.xpath("//span[@class='all hidden']").toString()!=null){
            summary=summary_div.xpath("//span[@class='all hidden']/allText()").toString().trim();
        }else{
            summary=summary_div.xpath("//span[@property='v:summary']//allText()").toString().trim();
        }
        MovieBean movieBean=null;
        if(stars.size()>0) {
            String star5 = stars.get(0);
            String star4 = stars.get(1);
            String star3 = stars.get(2);
            String star2 = stars.get(3);
            String star1 = stars.get(4);
            movieBean=new MovieBean(movie,type,Float.parseFloat(grade),country,language,people
                    ,Math.round(people*Float.parseFloat(star5.substring(0,star5.length()-1))/100.0)
                    ,Math.round(people*Float.parseFloat(star4.substring(0,star4.length()-1))/100.0)
                    ,Math.round(people*Float.parseFloat(star3.substring(0,star3.length()-1))/100.0)
                    ,Math.round(people*Float.parseFloat(star2.substring(0,star2.length()-1))/100.0)
                    ,Math.round(people*Float.parseFloat(star1.substring(0,star1.length()-1))/100.0)
                    ,date,summary);
        }else{
            movieBean=new MovieBean(movie,type,Float.parseFloat(grade),country,language,people
                    ,-1,-1,-1,-1,-1,date,summary);
        }
        page.putField("movieBean",movieBean);
        String review_P=page.getUrl().toString()+"comments?start=0&limit=20&sort=new_score&status=P";
        String review_F=page.getUrl().toString()+"comments?start=0&limit=20&sort=new_score&status=F";
        page.addTargetRequest(review_P);
        page.addTargetRequest(review_F);
    }

    public static void processReviewPage(Page page,Logger log){
        int page_id=Integer.parseInt(page.getUrl().regex("start=(\\d+)").toString());
        String url=page.getUrl().toString();
        String status=""+url.charAt(url.length()-1);
        String movie = page.getHtml().xpath("//div[@id='wrapper']/div/h1/text()").toString().split(" 短评")[0];
        List<String> commentList=page.getHtml().xpath("//div[@class='comment-item']//div[@class='comment']").all();
        if(commentList.size()==0)
            return;
        List<ReviewBean> reviewBeans=new ArrayList<>();
        for(String comment_str:commentList){
            Html comment=new Html(comment_str);
            String text=comment.xpath("//p/text()").toString().trim();
            int agree=-1;
            String agree_str=comment.xpath("//span[@class='comment-vote']/span/text()").get();
            if(agree_str!=null)
                agree=Integer.parseInt(agree_str);
            Selectable comment_info=comment.xpath("//span[@class='comment-info']");
//            System.out.println(comment_info);
            int rank=-1;
            String date;
            if(comment_info.xpath("//span[3]").toString()==null){
                date=comment_info.xpath("//span[2]/text()").toString().trim();
            }else{
//                System.out.println(comment_info.xpath("//span[2]/@class"));
                rank=Integer.parseInt(""+comment_info.xpath("//span[2]/@class").toString().charAt(7));
                date=comment_info.xpath("//span[3]/text()").toString().trim();
            }
            text=text.replaceAll("'",""+(char)1);
            ReviewBean reviewBean=new ReviewBean(movie,text,status,rank,agree,date);
//            log.info(reviewBean);
            reviewBeans.add(reviewBean);
        }
        page.putField("reviewList",reviewBeans);
        //TODO:跳页
        if(page_id<200)
            page.addTargetRequest(page.getHtml().xpath("//div[@id='paginator']//a[@class='next']/@href").get());
    }
}
