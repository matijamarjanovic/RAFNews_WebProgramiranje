package raf.rs.rafnews_webprogramiranje.services;

import raf.rs.rafnews_webprogramiranje.entities.News;
import raf.rs.rafnews_webprogramiranje.entities.NewsTag;
import raf.rs.rafnews_webprogramiranje.repositories.newstag.NewsTagsRepository;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.List;

public class NewsTagService {

    @Inject
    private NewsTagsRepository newsTagRepository;

    public NewsTag addNewsTag(NewsTag newsTag) throws SQLException { return this.newsTagRepository.addNewsTag(newsTag); }

    public List<NewsTag> allNewsTags() throws SQLException {
        return this.newsTagRepository.allNewsTags();
    }

    public List<News> findNewsForTag(int tag_id) throws SQLException {
        return this.newsTagRepository.findNewsForTag(tag_id);
    }

    public void deleteNewsTag(Integer id) throws SQLException {
        this.newsTagRepository.deleteNewsTag(id);
    }

}
