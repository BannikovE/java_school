package app.sevice;

import app.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> allCategories();
    Category getCategoryById(int id);
}
