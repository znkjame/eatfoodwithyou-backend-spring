package th.ac.ku.eatfoodwithyouspringbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import th.ac.ku.eatfoodwithyouspringbackend.model.foodrecipes.Category;
import th.ac.ku.eatfoodwithyouspringbackend.respository.CategoryRepository;

import java.util.List;
@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getAll(){
        return categoryRepository.findAll();
    }

    public Category create(Category category){
        categoryRepository.save(category);
        return category;
    }

    public List<Category> findAllByName(String name){
        return categoryRepository.findAllByNameContains(name);
    }

    public Category findByName(String name){
        return categoryRepository.findByNameContains(name);
    }
}
