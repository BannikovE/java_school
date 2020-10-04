package app.dao;

import app.model.Product;

import java.util.List;

public interface ProductDAO {
    List<Product> allProducts(int page);
    int productCount();
    int add(Product product);
    void delete(Product product);
    void edit(Product product);
    Product getById(int id);
}
