package raf.rs.rafnews_webprogramiranje.services;

import raf.rs.rafnews_webprogramiranje.entities.Category;
import raf.rs.rafnews_webprogramiranje.entities.News;
import raf.rs.rafnews_webprogramiranje.repositories.category.CategoryRepository;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.List;

public class CategoryService {
    @Inject
    private CategoryRepository categoryRepository;

    public Category addCategory(Category category) throws SQLException { return this.categoryRepository.addCategory(category); }

    public List<Category> allCategories() throws SQLException {
        return this.categoryRepository.allCategories();
    }

    public Category findCategory(String id) throws SQLException { return this.categoryRepository.findCategory(id); }

    public List<News> findNewsByCategory(String id) throws SQLException {
        return this.categoryRepository.findNewsByCategory(id);
    }

    public void deleteCategory(String id) throws SQLException {
        this.categoryRepository.deleteCategory(id);
    }

    public Category updateCategory(String id, Category editedCategory){ return this.categoryRepository.updateCategory(id, editedCategory); }
}
