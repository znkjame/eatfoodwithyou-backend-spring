package th.ac.ku.eatfoodwithyouspringbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import th.ac.ku.eatfoodwithyouspringbackend.model.foodrecipes.Category;
import th.ac.ku.eatfoodwithyouspringbackend.service.CategoryService;
import th.ac.ku.eatfoodwithyouspringbackend.service.ResponseHandler;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<Object> getAll(){
        try{
            List<Category> categoryList = categoryService.getAll();
            return ResponseHandler.generateResponse("Successfully!!", HttpStatus.OK, categoryList);
        }
        catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(),HttpStatus.BAD_REQUEST,null);
        }
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody Category category){
        try {
            Category result = categoryService.create(category);
            return ResponseHandler.generateResponse("Add Successfully !! ",HttpStatus.OK, result);
        }
        catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(),HttpStatus.BAD_REQUEST,null);
        }
    }

    @GetMapping("/{name}")
    public ResponseEntity<Object> findByNameContain(@PathVariable("name") String name){
        try {
            List<Category> result = categoryService.findAllByName(name);
            return ResponseHandler.generateResponse("Successfully !!", HttpStatus.OK, result);
        }
        catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(),HttpStatus.BAD_REQUEST,null);
        }
    }
}
