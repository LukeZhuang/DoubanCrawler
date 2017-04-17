package com.luke.doubancrawler.pipline;

import com.luke.doubancrawler.dal.mapper.DoubanMapper;
import com.luke.doubancrawler.dal.model.MovieBean;
import com.luke.doubancrawler.dal.model.ReviewBean;
import com.luke.doubancrawler.utils.FileUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.io.FileReader;
import java.util.List;

/**
 * Created by qinan on 2017/4/10.
 */
@Repository
public class DoubanPipeLine implements Pipeline {
    private Logger log = Logger.getLogger(DoubanPipeLine.class);

    @Autowired
    private DoubanMapper doubanMapper;

    public void process(ResultItems resultItems, Task task) {
        MovieBean movieBean=resultItems.get("movieBean");
        if(movieBean!=null) {
            log.info(movieBean);
            try {
                doubanMapper.insertMovie(movieBean);
            }catch (Exception e){
                FileUtil.appendFileByLine("exception_movie.txt",movieBean.toString());
            }
        }
        List<ReviewBean> reviewList=resultItems.get("reviewList");
        if(reviewList!=null){
            for(ReviewBean reviewBean:reviewList){
                log.info(reviewBean);
                try {
                    doubanMapper.insertReview(reviewBean);
                }catch (Exception e){
                    FileUtil.appendFileByLine("exception_review.txt",reviewBean.toString());
                }
            }
        }
    }
}
