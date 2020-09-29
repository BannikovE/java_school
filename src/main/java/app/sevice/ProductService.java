package app.sevice;

import app.model.Category;
import app.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> allProducts(int page);
    int productCount();
    void add(Product product);
    void delete(Product product);
    void edit(Product product);
    Product getById(int id);
}

