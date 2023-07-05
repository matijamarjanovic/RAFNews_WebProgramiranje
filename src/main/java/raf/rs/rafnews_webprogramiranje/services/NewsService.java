package raf.rs.rafnews_webprogramiranje.services;

import raf.rs.rafnews_webprogramiranje.entities.News;
import raf.rs.rafnews_webprogramiranje.repositories.news.NewsRepository;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.List;

public class NewsService {
    @Inject
    private NewsRepository newsRepository;

    public News addNews(News news) throws SQLException { return this.newsRepository.addNews(news); }

    public News updateNews(Integer id) throws SQLException { return this.newsRepository.updateNews(id); }

    public void incrementVisits(Integer id) throws SQLException { this.newsRepository.incrementVisits(id); }

    public List<News> allNews() throws SQLException {
        return this.newsRepository.allNews();
    }

    public List<News> mostReadNews() throws SQLException { return this.newsRepository.mostReadNews(); }

    public News findNews(Integer id) throws SQLException {
        return this.newsRepository.findNews(id);
    }

    public List<News> findNewsByTag(String tag_id) throws SQLException {
        return this.newsRepository.findNewsByTag(tag_id);
    }

    public void deleteNews(Integer id) throws SQLException {
        this.newsRepository.deleteNews(id);
    }
}
