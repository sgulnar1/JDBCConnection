import domains.Category;
import services.CategoryService;
import services.impl.CategoryServiceImpl;

import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        System.out.println("get category by id");
        CategoryService categoryService = new CategoryServiceImpl();
        System.out.println(categoryService.getCategoryById(3));
        System.out.println("**************************************");
        System.out.println("get category by name");
        System.out.println(categoryService.getCategoryByNamePrepare("'Beverages'; drop table branches4"));
        System.out.println("**************************************");
        System.out.println("save category");
//        System.out.println(categoryService.saveCategory(
//                new Category(12,"test category", "description ... ")));
//        System.out.println("**************************************");
        System.out.println("update category");
        categoryService.updateCategory(13, "category_name"," new name ");
        System.out.println("**************************************");
        System.out.println("delete category");
        categoryService.deleteCategory(17);
        System.out.println("**************************************");
        System.out.println("insert categories");
        List<Category> categories = new ArrayList<>();
        for(int i=35; i<40; i++) {
            categories.add(new Category(i, "name " + i, "desc " + i));
        }
//        categoryService.saveCategories(categories);
        System.out.println("**************************************");
        System.out.println("get category name by category id");
        System.out.println(categoryService.getCaregoryNameByCategoryId(13));
        System.out.println("**************************************");
        System.out.println("get category name by category id proc");
        System.out.println(categoryService.getCaregoryNameByCategoryIdProc(13));

    }
    
    
    //build tools ant maven gradle order & auto jar
}
