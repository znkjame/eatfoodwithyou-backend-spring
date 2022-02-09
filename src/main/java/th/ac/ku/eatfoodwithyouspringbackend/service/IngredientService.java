package th.ac.ku.eatfoodwithyouspringbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import th.ac.ku.eatfoodwithyouspringbackend.model.foodrecipes.Ingredient;
import th.ac.ku.eatfoodwithyouspringbackend.respository.IngredientRepository;

import java.util.List;

@Service
public class IngredientService {
    @Autowired
    private IngredientRepository ingredientRepository;

    public List<Ingredient> findAll(){
        return ingredientRepository.findAll();
    }

    public Ingredient findById(int id){
        return ingredientRepository.findById(id).get();
    }

    public Ingredient create(Ingredient ingredient){
        ingredientRepository.save(ingredient);
        return ingredient;
    }

    public Ingredient update(int id,Ingredient modifyIngredient){
        Ingredient ingredient = ingredientRepository.findById(id).get();
        if(modifyIngredient.getName() != null)
            ingredient.setName(modifyIngredient.getName());
        if(modifyIngredient.getQuantity() != 0)
            ingredient.setQuantity(modifyIngredient.getQuantity());
        if(modifyIngredient.getUnit() != null)
            ingredient.setUnit(modifyIngredient.getUnit());
        ingredientRepository.save(ingredient);
        return ingredient;
    }

    public Ingredient delete(int id){
        Ingredient ingredient = ingredientRepository.findById(id).get();
        ingredientRepository.delete(ingredient);
        return ingredient;
    }
}
