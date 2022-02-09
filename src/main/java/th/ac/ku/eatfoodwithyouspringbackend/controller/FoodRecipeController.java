package th.ac.ku.eatfoodwithyouspringbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import th.ac.ku.eatfoodwithyouspringbackend.model.foodrecipes.FoodRecipe;
import th.ac.ku.eatfoodwithyouspringbackend.service.FoodRecipeService;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class FoodRecipeController {

    @Autowired
    private FoodRecipeService service;

    @GetMapping
    public List<FoodRecipe> getAll(){
        return service.getAll();
    }

    @PostMapping
    public FoodRecipe create(@RequestBody FoodRecipe foodRecipe){
        return service.create(foodRecipe);
    }
    @GetMapping("/{id}")
    public FoodRecipe getByID(@PathVariable("id") int id){
        return service.getByID(id);
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
}
