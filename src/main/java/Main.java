import domains.Category;
import services.CategoryService;
import services.impl.CategoryServiceImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        System.out.println("get category by id");
        CategoryService categoryService = new CategoryServiceImpl();
        System.out.println(categoryService.getCategoryById(3));
        System.out.println("**************************************");
        System.out.println("get category by name");
        System.out.println(categoryService.getCategoryByName("Beverages; delete from products where id = 4"));
    }
    //build tools ant maven gradle order & auto jar
}
