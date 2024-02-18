package com.io.Ogani.service;

import com.io.Ogani.model.Category;
import com.io.Ogani.repository.CategoryRepository;
import org.hibernate.annotations.Parent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repo;


    public List<Category> listAll() {

        return (List<Category>) repo.findAll();
    }

    public Category save(Category category) {
        Category parent = category.getParent();
//        if (parent != null) {
//            String allParentIds = parent.getAllParentIDs() == null ? "-" : parent.getAllParentIDs();
//            allParentIds += String.valueOf(parent.getId()) + "-";
//            category.setAllParentIDs(allParentIds);
//        }

        return repo.save(category);
    }
    public List<Category> listCategoriesUsedInForm() {

        List<Category> categoriesUsedInform = new ArrayList<>();
        Iterable<Category> categoriesInDB = repo.findAll();
        for (Category category : categoriesInDB) {
            if (category.getParent() == null) {
                categoriesUsedInform.add(new Category(category.getName()));
                Set<Category> children = category.getChildren();
                for (Category subCategory : children) {
                   String name ="--"+subCategory.getName();
                    categoriesUsedInform.add(new Category(name));
                    listChildren(categoriesUsedInform, subCategory, 1);
                }
            }
        }
        return categoriesUsedInform;
    }

    public void listChildren(List<Category> categoriesUsedInform, Category parent, int sublevel) {

        int newSublevel = sublevel + 1;
        Set<Category> children = parent.getChildren();
        for (Category subcategory : children) {
            String name="";
            for (int i = 0; i < newSublevel; i++) {

                name+="--";
            }
            name+=subcategory.getName();
            categoriesUsedInform.add(new Category(name));
            listChildren(categoriesUsedInform, subcategory, newSublevel);
        }

    }
}