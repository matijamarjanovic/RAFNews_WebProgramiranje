package raf.rs.rafnews_webprogramiranje.repositories.category;

import raf.rs.rafnews_webprogramiranje.entities.Category;
import raf.rs.rafnews_webprogramiranje.entities.News;

import java.sql.SQLException;
import java.util.List;

public interface CategoryRepository {

    public Category addCategory(Category category) throws SQLException;
    public List<Category> allCategories() throws SQLException;
    public Category findCategory(String id) throws SQLException;
    public List<News> findNewsByCategory(String id) throws SQLException;
    public void deleteCategory(String id) throws SQLException;
    public Category updateCategory(String id, Category editedCategory);
}
