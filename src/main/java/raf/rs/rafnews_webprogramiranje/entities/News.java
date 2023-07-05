package raf.rs.rafnews_webprogramiranje.entities;

import java.util.Date;

public class News {
    private int id;
    private String title;
    private String text;
    private Date publishDate;
    private int visits;
    private int category; //as ID
    private int author; //as ID

    public News(int id, String title, String text, Date publishDate) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.publishDate = publishDate;
    }

    public News(){}

    public News(int id, String title, String text, Date publishDate, int category, int author, int visits) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.publishDate = publishDate;
        this.category = category;
        this.author = author;
        this.visits = visits;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}
    public String getText() {return text;}
    public void setText(String text) {this.text = text;}
    public Date getPublishDate() {return publishDate;}
    public void setPublishDate(Date publishDate) {this.publishDate = publishDate;}
    public int getVisits() {return visits;}
    public void setVisits(int visits) {this.visits = visits;}

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }
}
