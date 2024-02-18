package com.io.Ogani.admin.user;

import com.io.Ogani.model.Category;
import com.io.Ogani.repository.CategoryRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class CategoryRepositoryTest {

@Autowired
private CategoryRepository repo;


@Test
public void testCreateRootCategory(){

    Category category = new Category("Computers");
    Category savedCategory = repo.save(category);
    assertThat(savedCategory.getId()).isGreaterThan(0);
}

    @Test
    public void testCreateSubCategory(){

        Category parent = new Category(2);
        Category SmartPhones = new Category("Laptops", parent);
        Category Cameras = new Category("Computer Components", parent);
         repo.saveAll(List.of(SmartPhones, Cameras));

    }

    @Test
    public void testCategory(){
        Category category = repo.findById(1).get();
        System.out.println(category.getName());
        Set<Category> children = category.getChildren();

        for(Category subCategory : children){
            System.out.println(subCategory.getName());
        }

        assertThat(children.size()).isGreaterThan(0);

    }




}
