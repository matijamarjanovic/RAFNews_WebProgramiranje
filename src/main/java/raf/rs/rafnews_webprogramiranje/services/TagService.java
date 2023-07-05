package raf.rs.rafnews_webprogramiranje.services;

import raf.rs.rafnews_webprogramiranje.entities.Tag;
import raf.rs.rafnews_webprogramiranje.repositories.tag.TagRepository;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.List;

public class TagService {

    @Inject
    private TagRepository tagRepository;

    public Tag addTag(Tag tag) throws SQLException { return this.tagRepository.addTag(tag); }

    public List<Tag> allTags() throws SQLException {
        return this.tagRepository.allTags();
    }

    public Tag findTag(String name) throws SQLException {
        return this.tagRepository.findTag(name);
    }

    public List<Tag> allTagsForNews(Integer news_id) throws SQLException { return this.tagRepository.allTagsForNews(news_id); }

    public void deleteTag(String id) throws SQLException { this.tagRepository.deleteTag(id); }
}
