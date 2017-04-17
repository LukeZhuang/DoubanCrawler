package com.luke.doubancrawler.dal.model;

/**
 * Created by qinan on 2017/4/10.
 */
public class ReviewBean {
    String movie; //varchar(50) 电影名
    String text; //varchar(150) 影评内容
    String status; //varchar(5) 想看/看过
    int rank; //int 用户评分
    int agree; //int 赞数
    String date; //varchar(10) 评论时间

    public ReviewBean() {
    }

    public ReviewBean(String movie, String text, String status, int rank, int agree, String date) {
        this.movie = movie;
        this.text = text;
        this.status = status;
        this.rank = rank;
        this.agree = agree;
        this.date = date;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getAgree() {
        return agree;
    }

    public void setAgree(int agree) {
        this.agree = agree;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ReviewBean{" +
                "movie='" + movie + '\'' +
                ", text='" + text + '\'' +
                ", status='" + status + '\'' +
                ", rank=" + rank +
                ", agree=" + agree +
                ", date='" + date + '\'' +
                '}';
    }
}