package raf.rs.rafnews_webprogramiranje.repositories.newstag;

import raf.rs.rafnews_webprogramiranje.entities.News;
import raf.rs.rafnews_webprogramiranje.entities.NewsTag;

import java.sql.SQLException;
import java.util.List;

public interface NewsTagsRepository {

    public NewsTag addNewsTag(NewsTag newstag) throws SQLException;
    public List<NewsTag> allNewsTags() throws SQLException;
    public List<News> findNewsForTag(int tagId) throws SQLException;
    public void deleteNewsTag(Integer id) throws SQLException;
}
