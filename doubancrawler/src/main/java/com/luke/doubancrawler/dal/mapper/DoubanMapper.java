package com.luke.doubancrawler.dal.mapper;

import com.luke.doubancrawler.dal.model.MovieBean;
import com.luke.doubancrawler.dal.model.ReviewBean;

/**
 * Created by qinan on 2017/4/10.
 */
public interface DoubanMapper {
    public void insertMovie(MovieBean movieBean);
    public void insertReview(ReviewBean reviewBean);
}
