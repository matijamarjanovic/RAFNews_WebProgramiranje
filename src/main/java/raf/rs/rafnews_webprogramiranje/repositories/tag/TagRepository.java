package raf.rs.rafnews_webprogramiranje.repositories.tag;

import raf.rs.rafnews_webprogramiranje.entities.Tag;

import java.sql.SQLException;
import java.util.List;

public interface TagRepository {

    public Tag addTag(Tag tag) throws SQLException;
    public List<Tag> allTags() throws SQLException;
    public Tag findTag(String name) throws SQLException;
    public List<Tag> allTagsForNews(Integer news_id) throws SQLException;
    public void deleteTag(String id) throws SQLException;
}
