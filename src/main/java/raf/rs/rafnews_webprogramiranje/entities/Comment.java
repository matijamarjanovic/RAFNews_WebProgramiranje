package raf.rs.rafnews_webprogramiranje.entities;

import java.util.Calendar;
import java.util.Date;

public class Comment {
    private int id;
    private String commenter;
    private int authorId;
    private String text;
    private Date dateOfCreation;
    private int newsId;

    public Comment(int id, String commenter, int authorId, String text, int newsId) {
        this.id = id;
        this.commenter = commenter;
        this.authorId = authorId;
        this.text = text;
        this.dateOfCreation = Calendar.getInstance().getTime();
        this.newsId = newsId;
    }

    public Comment(int id, String commenter, int authorId, String text, Date dateOfCreation, int newsId) {
        this.id = id;
        this.commenter = commenter;
        this.authorId = authorId;
        this.text = text;
        this.dateOfCreation = dateOfCreation;
        this.newsId = newsId;
    }

    public Comment(){

    }

    public int getId() { return id; }
    public void setId(int id) {this.id = id;}
    public String getCommenter() {return commenter;}
    public void setCommenter(String commenter) {this.commenter = commenter;}
    public String getText() {return text;}
    public void setText(String text) {this.text = text;}
    public Date getDateOfCreation() {return dateOfCreation;}
    public void setDateOfCreation(Date dateOfCreation) {this.dateOfCreation = dateOfCreation;}
    public int getNewsId() {return newsId;}
    public void setNewsId(int newsId) {this.newsId = newsId;}
}
