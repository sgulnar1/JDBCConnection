package services.impl;

import domains.Category;
import services.CategoryService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    @Override
    public Category getCategoryById(int id) throws SQLException, ClassNotFoundException {
//        Class.forName("org.postgresql.Driver");
        //jdbc:postgresql://localhost:5432/postgres
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "12345");
        Statement statement = connection.createStatement();
        statement.execute("select * from categories where category_id = " + id);
        System.out.println("sorgu ughurlu olundu");
        ResultSet resultSet = statement.getResultSet();
        ResultSetMetaData metaData = resultSet.getMetaData();
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            System.out.println("name: " + metaData.getColumnName(i) +
                    ", type: " + metaData.getColumnTypeName(i));
        }

        while (resultSet.next()) {
            Integer category_id = resultSet.getInt(1);
            String category_name = resultSet.getString("category_name");
            String category_description = resultSet.getString(3);
            return new Category(category_id, category_name, category_description);
        }
        return null;
    }

    @Override
    public List<Category> getCategoryByName(String name) throws SQLException, ClassNotFoundException {
        List<Category> categories = new ArrayList<Category>();
        Connection connection = DriverManager.getConnection
                ("jdbc:postgresql://localhost:5432/postgres",
                        "postgres", "12345");
        Statement statement = connection.createStatement();
        statement.execute("select * from categories where category_name = '" + name + "'");
        ResultSet resultSet = statement.getResultSet();
        while (resultSet.next()) {
            Integer category_id = resultSet.getInt(1);
            String category_name = resultSet.getString("category_name");
            String category_description = resultSet.getString(3);
            Category category =
                    new Category(category_id, category_name, category_description);
            categories.add(category);
        }
        return categories;
    }
}
