package app.controller;

import app.model.Category;
import app.model.Product;
import app.model.ProductFilter;
import app.model.User;
import app.service.CategoryService;
import app.service.ProductService;
import app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class ProductController {

    public static final Logger log = LoggerFactory.getLogger("ProductController");

    private int page;
    private ProductService productService;
    private CategoryService categoryService;

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public ModelAndView mainPage(@ModelAttribute("user") User user){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @GetMapping("/products")
    public ModelAndView allProducts(@RequestParam(defaultValue = "1") int page){
        this.page = page;
        List<Product> products = productService.allProducts(page, null);
        return getProducts(products);
    }

    @PostMapping("/products/filter")
    public ModelAndView allProducts(ProductFilter filter) {
        List<Product> products = productService.allProducts(page, filter);
        return getProducts(products);
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editPage(@PathVariable("id") int id){
        List<Category> categories = categoryService.allCategories();
        Product product = productService.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        modelAndView.addObject("product", product);
        modelAndView.addObject("categoryList", categories);
        return modelAndView;
    }

    @PostMapping("/edit")
    public ModelAndView editProduct(@ModelAttribute("product") Product product){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/products/?page=" + this.page);
        productService.edit(product);
        return modelAndView;
    }

     @GetMapping("/add")
    public ModelAndView addPage(){
        List<Category> categories = categoryService.allCategories();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        modelAndView.addObject("categoryList", categories);
        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView addProduct(Product product, HttpServletRequest request, HttpServletResponse response){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/products/?page=" + this.page);
        try {
            int resultId = productService.add(product);
            log.info("Created product with id {}", resultId);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteProduct(@PathVariable("id") int id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/products/?page=" + this.page);
        Product product = productService.getById(id);
        productService.delete(product);
        return modelAndView;
    }

    private ModelAndView getProducts(List<Product> products) {
        List<Category> categories = categoryService.allCategories();
        int productsCount = productService.productCount();
        int pagesCount = (productsCount + 9) / 10;
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("products");
        modelAndView.addObject("page", page);
        modelAndView.addObject("productList", products);
        modelAndView.addObject("productsCount", productsCount);
        modelAndView.addObject("pagesCount", pagesCount);
        modelAndView.addObject("categoryList", categories);
        return modelAndView;
    }
}
