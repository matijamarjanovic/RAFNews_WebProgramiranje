package raf.rs.rafnews_webprogramiranje.repositories.news;

import raf.rs.rafnews_webprogramiranje.entities.News;

import java.sql.SQLException;
import java.util.List;

public interface NewsRepository {

    public News addNews(News news) throws SQLException;
    public News updateNews(Integer id) throws SQLException;
    public void incrementVisits(Integer id) throws SQLException;
    public List<News> allNews() throws SQLException;
    public List<News> mostReadNews() throws SQLException;
    public News findNews(Integer id) throws SQLException;
    public List<News> findNewsByTag(String tag_id) throws SQLException;
    public void deleteNews(Integer id) throws SQLException;
}
