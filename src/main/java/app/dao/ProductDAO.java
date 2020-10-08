package app.dao;

import app.model.Product;
import app.model.ProductFilter;

import java.util.List;

public interface ProductDAO {
    List<Product> allProducts(int page, ProductFilter filter);
    int productCount();
    int add(Product product);
    void delete(Product product);
    void edit(Product product);
    Product getById(int id);
}


