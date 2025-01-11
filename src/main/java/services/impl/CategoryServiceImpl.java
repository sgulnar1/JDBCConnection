package services.impl;

import configurations.DBConnection;
import domains.Category;
import services.CategoryService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    Connection connection = DBConnection.getConnection();

    @Override
    public Category getCategoryById(int id) {
        try {
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
        } catch (SQLException e) {
            System.out.println("get category by id exception...");
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<Category> getCategoryByName(String name) {
        List<Category> categories = new ArrayList<Category>();
        try {
            Statement statement = connection.createStatement();
            statement.execute("select * from categories where category_name = " + name);
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                Integer category_id = resultSet.getInt(1);
                String category_name = resultSet.getString("category_name");
                String category_description = resultSet.getString(3);
                Category category =
                        new Category(category_id, category_name, category_description);
                categories.add(category);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categories;
    }

    @Override
    public List<Category> getCategoryByNamePrepare(String name) {
        List<Category> categories = new ArrayList<Category>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from categories where category_name = ?");
            preparedStatement.setString(1, name);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                Integer category_id = resultSet.getInt(1);
                String category_name = resultSet.getString("category_name");
                String category_description = resultSet.getString(3);
                Category category =
                        new Category(category_id, category_name, category_description);
                categories.add(category);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categories;
    }

    @Override
    public Category saveCategory(Category category) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into categories (category_id, category_name, description) values (?,?,?)");
            preparedStatement.setInt(1, category.getId());
            preparedStatement.setString(2, category.getName());
            preparedStatement.setString(3, category.getDescription());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return category;
    }

    @Override
    public void updateCategory(Integer categoryId, String columnName, String columnValue) {
        try {
            Statement statement = connection.createStatement();
            statement.execute("update categories set " + columnName + " = '" + columnValue + "' where category_id = " + categoryId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from categories where category_id = ?");
            preparedStatement.setInt(1, categoryId);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveCategories(List<Category> categories) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into categories (category_id, category_name, description) values (?,?,?)");
            for (Category category : categories) {
                preparedStatement.setInt(1, category.getId());
                preparedStatement.setString(2, category.getName());
                preparedStatement.setString(3, category.getDescription());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getCaregoryNameByCategoryId(int categoryId) {
        try {
            CallableStatement callableStatement = connection.prepareCall("{? =  call get_category_by_id(?, ?)}");
            callableStatement.registerOutParameter(1,Types.VARCHAR);
            callableStatement.setInt(2, categoryId);
            callableStatement.setString(3, "");
            callableStatement.execute();
            return callableStatement.getString(1);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getCaregoryNameByCategoryIdProc(int categoryId) {
        try {
            CallableStatement callableStatement = connection.prepareCall(" call get_category_by_id_proc(?, ?)");
            callableStatement.registerOutParameter(2,Types.VARCHAR);
            callableStatement.setInt(1, categoryId);
            callableStatement.execute();
            return callableStatement.getString(2);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
