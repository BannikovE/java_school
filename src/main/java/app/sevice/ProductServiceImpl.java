package app.sevice;

import app.dao.CategoryDAO;
import app.dao.ProductDAO;
import app.dao.ProductDAOImpl;
import app.model.Category;
import app.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{
    private ProductDAO productDAO;
    private CategoryDAO categoryDAO;

    @Autowired
    public void setCategoryDAO(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @Autowired
    public void setProductDAO(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Transactional
    @Override
    public List<Product> allProducts(int page) {
        return productDAO.allProducts(page);
    }

    @Transactional
    @Override
    public int productCount() {
        return productDAO.productCount();
    }

    @Transactional
    @Override
    public int add(Product product) {
        Category category = categoryDAO.getCategoryById(product.getCategoryId());
        product.setCategory(category);
        return productDAO.add(product);
    }

    @Transactional
    @Override
    public void delete(Product product) {
        productDAO.delete(product);
    }

    @Transactional
    @Override
    public void edit(Product product) {
        productDAO.edit(product);
    }

    @Transactional
    @Override
    public Product getById(int id) {
        return productDAO.getById(id);
    }
}
