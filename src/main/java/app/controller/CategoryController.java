package app.controller;

import app.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CategoryController {
    private CategoryService categoryService;

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

//    @GetMapping("/")
//    public ModelAndView allCategories(){
//        List<Category> categoryList = categoryService.allCategories();
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("editPage");
//        modelAndView.addObject("categoryList", categoryList);
//        return modelAndView;
//    }
}
