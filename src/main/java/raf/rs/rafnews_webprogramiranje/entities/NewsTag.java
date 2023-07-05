package raf.rs.rafnews_webprogramiranje.entities;

public class NewsTag {
    private int id;
    private int tagId;
    private int newsId;

    public NewsTag(int id, int tag, int newsId) {
        this.id = id;
        this.tagId = tag;
        this.newsId = newsId;
    }

    public NewsTag(){}

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public int getNewsId() {return newsId;}
    public void setNewsId(int newsId) {this.newsId = newsId;}
}
