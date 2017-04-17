package com.luke.doubancrawler.dal.model;

/**
 * Created by qinan on 2017/4/10.
 */
public class MovieBean {
    String movie; //varchar(50) 电影名
    String type; //varchar(50) 类型
    float grade; //float 总评分
    String country; //varchar(20) 制片地区/国家
    String language; //varchar(20) 语言
    long people; //long 总评分人数
    long star5; //long 5星评分人数
    long star4; //long 4星评分人数
    long star3; //long 3星评分人数
    long star2; //long 2星评分人数
    long star1; //long 1星评分人数
    String date; //varchar(10) 上映时间（优先大陆，其他按网站顺序）
    String summary;  //varchar(700) 简介

    public MovieBean() {
    }

    public MovieBean(String movie, String type, float grade, String country, String language, long people, long star5, long star4, long star3, long star2, long star1, String date, String summary) {
        this.movie = movie;
        this.type = type;
        this.grade = grade;
        this.country = country;
        this.language = language;
        this.people = people;
        this.star5 = star5;
        this.star4 = star4;
        this.star3 = star3;
        this.star2 = star2;
        this.star1 = star1;
        this.date = date;
        this.summary = summary;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public long getStar5() {
        return star5;
    }

    public void setStar5(long star5) {
        this.star5 = star5;
    }

    public long getStar4() {
        return star4;
    }

    public void setStar4(long star4) {
        this.star4 = star4;
    }

    public long getStar3() {
        return star3;
    }

    public void setStar3(long star3) {
        this.star3 = star3;
    }

    public long getStar2() {
        return star2;
    }

    public void setStar2(long star2) {
        this.star2 = star2;
    }

    public long getStar1() {
        return star1;
    }

    public void setStar1(long star1) {
        this.star1 = star1;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getPeople() {
        return people;
    }

    public void setPeople(long people) {
        this.people = people;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Override
    public String toString() {
        return "MovieBean{" +
                "movie='" + movie + '\'' +
                ", type='" + type + '\'' +
                ", grade=" + grade +
                ", country='" + country + '\'' +
                ", language='" + language + '\'' +
                ", people=" + people +
                ", star5=" + star5 +
                ", star4=" + star4 +
                ", star3=" + star3 +
                ", star2=" + star2 +
                ", star1=" + star1 +
                ", date='" + date + '\'' +
                '}';
    }
}
