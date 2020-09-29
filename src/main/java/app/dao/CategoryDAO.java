package app.dao;

import app.model.Category;
import app.model.Product;

import java.util.List;

public interface CategoryDAO {
    List<Category> allCategories();
    Category getCategoryById(int id);
}
