package services;

import domains.Category;

import java.sql.SQLException;
import java.util.List;

public interface CategoryService {
    Category getCategoryById(int id) throws SQLException, ClassNotFoundException;
    List<Category> getCategoryByName(String Name) throws SQLException, ClassNotFoundException;
}
