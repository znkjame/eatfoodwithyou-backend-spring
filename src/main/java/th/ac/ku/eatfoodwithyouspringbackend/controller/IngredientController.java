package th.ac.ku.eatfoodwithyouspringbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import th.ac.ku.eatfoodwithyouspringbackend.model.foodrecipes.Ingredient;
import th.ac.ku.eatfoodwithyouspringbackend.service.IngredientService;

import java.util.List;

@RestController
@RequestMapping("/api/ingredients")
public class IngredientController {
    @Autowired
    private IngredientService ingredientService;

    @GetMapping
    public List<Ingredient> findAll(){
        return ingredientService.findAll();
    }

    @GetMapping("/{id}")
    public Ingredient findById(@PathVariable("id") int id){
        return ingredientService.findById(id);
    }

    @PostMapping
    public Ingredient create(@RequestBody Ingredient ingredient){
        return ingredientService.create(ingredient);
    }

    @PutMapping("/{id}")
    public Ingredient update(@PathVariable("id") int id, @RequestBody Ingredient ingredient){
        return ingredientService.update(id,ingredient);
    }

    @DeleteMapping("/{id}")
    public Ingredient delete(@PathVariable("id") int id){
        return ingredientService.delete(id);
    }
}
