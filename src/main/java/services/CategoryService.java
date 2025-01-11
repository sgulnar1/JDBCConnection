package services;

import domains.Category;

import java.sql.SQLException;
import java.util.List;

public interface CategoryService {
    Category getCategoryById(int id) ;
    List<Category> getCategoryByName(String Name) ;
    List<Category> getCategoryByNamePrepare(String Name);
    Category saveCategory(Category category);
    void updateCategory(Integer categoryId, String columnName, String columnValue);
    void deleteCategory(Integer categoryId);
    void saveCategories(List<Category> categories);
    String getCaregoryNameByCategoryId(int categoryId);
    String getCaregoryNameByCategoryIdProc(int categoryId);
}
