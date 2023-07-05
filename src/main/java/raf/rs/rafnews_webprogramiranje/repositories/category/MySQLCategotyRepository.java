package raf.rs.rafnews_webprogramiranje.repositories.category;

import raf.rs.rafnews_webprogramiranje.entities.Category;
import raf.rs.rafnews_webprogramiranje.entities.News;
import raf.rs.rafnews_webprogramiranje.repositories.MySQLAbstractRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLCategotyRepository extends MySQLAbstractRepository implements CategoryRepository {
    @Override
    public Category addCategory(Category category) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;


        ArrayList<Category> allCategories = (ArrayList<Category>) this.allCategories();
        for(Category cat : allCategories){ //provera da li postoji vec kategorija sa datim imenom
            if(category.getName().equals(cat.getName()))
                return null; //TODO
        }
        System.out.println(category.getName()+category.getDescription());

        connection = this.newConnection();

        String[] generatedColumns = {"id"};

        statement = connection.prepareStatement("INSERT INTO categories (title, desctription) VALUES (?, ?)");
        statement.setString(1, category.getName());
        statement.setString(2, category.getDescription());

        resultSet = statement.getGeneratedKeys();
        resultSet = statement.getGeneratedKeys();

        if(resultSet.next()){
            category.setId(resultSet.getInt(1));
        }
        statement.executeUpdate();

        this.closeStatement(statement);
        this.closeConnection(connection);

        return category;
    }

    @Override
    public List<Category> allCategories() throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<Category> allCategories = new ArrayList<>();

        connection = this.newConnection();
        statement = connection.createStatement();

        String query = "SELECT * from categories";
        resultSet = statement.executeQuery(query);

        while(resultSet.next()){

            allCategories.add(new Category(
                    resultSet.getString("title"),
                    resultSet.getString("desctription"),
                    resultSet.getInt("id")
            ));
        }

        this.closeStatement(statement);
        this.closeResultSet(resultSet);
        this.closeConnection(connection);

        return allCategories;
    }

    @Override
    public Category findCategory(String id) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Category cat = null;

        String query = "SELECT * from categories WHERE name like "+  '"' + id + '"';

        connection = this.newConnection();
        statement = connection.createStatement();

        resultSet = statement.executeQuery(query);

        if(resultSet.next()) {
            cat = new Category(
                    resultSet.getString("title"),
                    resultSet.getString("desctription"),
                    resultSet.getInt("id")
            );
        }
        this.closeStatement(statement);
        this.closeResultSet(resultSet);
        this.closeConnection(connection);

        return cat;

    }

    @Override
    public List<News> findNewsByCategory(String id) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        ArrayList<News> newsByCategory = new ArrayList<>();

        connection = this.newConnection();

        statement = connection.createStatement();
        String query = "SELECT * from news n WHERE n.category like " +  '"' + id + '"';
        resultSet = statement.executeQuery(query);

        while(resultSet.next()){
            newsByCategory.add(new News(
                    resultSet.getInt("id"),
                    resultSet.getString("title"),
                    resultSet.getString("content"),
                    resultSet.getDate("timeCreated"),
                    resultSet.getInt("category"),
                    resultSet.getInt("author"),
                    resultSet.getInt("noVisits")
            ));
        }

        this.closeStatement(statement);
        this.closeResultSet(resultSet);
        this.closeConnection(connection);

        return newsByCategory;
    }

    @Override
    public void deleteCategory(String title) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        ResultSet resultSet = null;
        Statement st = null;
        ArrayList<News> allNewsByCategory = new ArrayList<>();

        connection = this.newConnection();

        //---------------------------------provera da li ima novosti sa datom kategorijom
        st = connection.createStatement();
        String query = "SELECT * from news n WHERE n.category like " +  '"' + title + '"';
        while(resultSet.next()){
            allNewsByCategory.add(new News(
                    resultSet.getInt("id"),
                    resultSet.getString("title"),
                    resultSet.getString("content"),
                    resultSet.getDate("timeCreated"),
                    resultSet.getInt("category"),
                    resultSet.getInt("author"),
                    resultSet.getInt("noVisits")
            ));
        }

        if(!allNewsByCategory.isEmpty())
            return; //TODO: mozda da se vrati error poruka?
        //-------------------------------------------------------------------------------
        resultSet = st.executeQuery(query);


        statement = connection.prepareStatement("DELETE FROM categories where title like " + '"' + title + '"'); //brisanje bez provere!!
        statement.executeUpdate();

        this.closeStatement(statement);
        this.closeConnection(connection);

    }

    @Override
    public Category updateCategory(String id, Category editedCategory) {
        //TODO
        return editedCategory;
    }
}
