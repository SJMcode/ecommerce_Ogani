package com.io.Ogani.controller;

import com.io.Ogani.model.Category;
import com.io.Ogani.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/categories")
    public String categoryListing(Model model) {

        List<Category> categoryList = categoryService.listAll();
        model.addAttribute("categoryList", categoryList);
        return "categories";
    }


    @GetMapping("/categories/new")
    public String newCategory(Model model) {
        List<Category> listCategories = categoryService.listCategoriesUsedInForm();
        model.addAttribute("category", new Category());
        model.addAttribute("pageTitle", "Create new Category");
        model.addAttribute("listCategories", listCategories);

        return "category_form";
    }


    @PostMapping("/categories/save")
    public String saveCategory(Category category,
                                             RedirectAttributes ra) throws IOException {
//        if (!multipartFile.isEmpty()) {
//            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
//            category.setImage(fileName);

            Category savedCategory = categoryService.save(category);


            ra.addFlashAttribute("message", "The category has been saved successfully.");

            return "redirect:/categories";
    }

}
