package th.ac.ku.eatfoodwithyouspringbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import th.ac.ku.eatfoodwithyouspringbackend.model.foodrecipes.Category;
import th.ac.ku.eatfoodwithyouspringbackend.model.foodrecipes.FoodRecipe;
import th.ac.ku.eatfoodwithyouspringbackend.model.users.User;
import th.ac.ku.eatfoodwithyouspringbackend.service.CategoryService;
import th.ac.ku.eatfoodwithyouspringbackend.service.FoodRecipeService;
import th.ac.ku.eatfoodwithyouspringbackend.service.ResponseHandler;
import th.ac.ku.eatfoodwithyouspringbackend.service.UserService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class FoodRecipeController {

    @Autowired
    private FoodRecipeService service;
    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<FoodRecipe> getAll(){
        return service.getAll();
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody FoodRecipe foodRecipe){
        try {
            if (foodRecipe.getUser() != null) {
                User user = userService.findByID(foodRecipe.getUser().getId());
                if (user != null)
                    foodRecipe.setUser(user);
//                List<Category> categoryList = new ArrayList<>();
//                if(!foodRecipe.getCategories().isEmpty()){
//                    for (Category category : foodRecipe.getCategories()){
//                        Category target = categoryService.findByName(category.getName());
//                        if (target != null)
//                            categoryList.add(target);
//                    }
//                }
//                System.out.println(categoryList);
//                foodRecipe.setCategories(categoryList);
                FoodRecipe result =  service.create(foodRecipe);
                return ResponseHandler.generateResponse("Successfull!", HttpStatus.OK, result);
            }
            else {
                return ResponseHandler.generateResponse("Please input user!!",HttpStatus.BAD_REQUEST, null);
            }

        }
        catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(),HttpStatus.BAD_REQUEST, null);
        }

    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getByID(@PathVariable("id") int id){
        try {
            FoodRecipe result = service.getByID(id);
            return ResponseHandler.generateResponse("Successfull!", HttpStatus.OK, result);
        }
        catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(),HttpStatus.MULTI_STATUS, null);
        }
    }

    @GetMapping("/search/{name}")
    public List<FoodRecipe> searchByName(@PathVariable("name") String name){
        return service.searchByName(name);
    }

    @GetMapping("/randoms")
    public List<FoodRecipe> randomFoodRecipes(){
        return service.randomFoodRecipes();
    }

    @GetMapping("/random")
    public FoodRecipe randomFoodRecipe(){
        return service.randomFoodRecipe();
    }

    @GetMapping("/category/{name}")
    public HashSet<FoodRecipe> findByCategoryName(@PathVariable("name") String name){
        String[] allCategory = name.split(",");
        HashSet<FoodRecipe> foodRecipeHashSet = new HashSet<>();
        for(String temp : allCategory){
            foodRecipeHashSet.addAll(service.findByCategoryName(temp));
        }
        return foodRecipeHashSet;
    }

    @PutMapping("/{id}")
    public FoodRecipe update(@PathVariable("id") int id,@RequestBody FoodRecipe foodRecipe){
        return service.update(id,foodRecipe);
    }

    @DeleteMapping("/{id}")
    public FoodRecipe delete(@PathVariable("id") int id){
        return service.delete(id);
    }


}
