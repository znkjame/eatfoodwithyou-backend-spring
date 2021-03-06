package th.ac.ku.eatfoodwithyouspringbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import th.ac.ku.eatfoodwithyouspringbackend.Exception.NotFoundException;
import th.ac.ku.eatfoodwithyouspringbackend.model.foodrecipes.FoodRecipe;
import th.ac.ku.eatfoodwithyouspringbackend.respository.FoodRecipeRepository;
import th.ac.ku.eatfoodwithyouspringbackend.respository.IngredientRepository;
import th.ac.ku.eatfoodwithyouspringbackend.respository.ProcessRepository;
import th.ac.ku.eatfoodwithyouspringbackend.respository.UserRepository;

import java.util.List;

@Service
public class FoodRecipeService {
    @Autowired
    private FoodRecipeRepository foodRecipeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private IngredientRepository ingredientRepository;
    @Autowired
    private ProcessRepository processRepository;


    public List<FoodRecipe> getAll(){
        return foodRecipeRepository.findAll();
    }

    public FoodRecipe findByID(int id){
        return foodRecipeRepository.findById(id).orElseThrow(() -> new NotFoundException(id,"FoodRecipe"));
    }

    public FoodRecipe create(FoodRecipe foodRecipe){
        foodRecipeRepository.save(foodRecipe);
        return foodRecipe;
    }

    public FoodRecipe update(int foodRecipe_id, FoodRecipe modifyFoodRecipe){
        FoodRecipe foodRecipe = foodRecipeRepository.findById(foodRecipe_id).orElseThrow(() -> new NotFoundException(foodRecipe_id,"FoodRecipe"));
        if(modifyFoodRecipe.getName() != null)
        foodRecipe.setName(modifyFoodRecipe.getName());
        if(modifyFoodRecipe.getDetail() != null)
        foodRecipe.setDetail(modifyFoodRecipe.getDetail());
        if(modifyFoodRecipe.getPhoto() != null)
        foodRecipe.setPhoto(modifyFoodRecipe.getPhoto());
        foodRecipeRepository.save(foodRecipe);
        return modifyFoodRecipe;
    }

    public List<FoodRecipe> searchByName(String name){
        return foodRecipeRepository.findFoodRecipeByNameContains(name);
    }

//    public List<FoodRecipe> randomFoodRecipe(){
//        List<FoodRecipe> foodRecipes = foodRecipeRepository.findAll();
//        Collections.shuffle(foodRecipes);
//
//        return foodRecipes.subList(0,3);
//    }
    public List<FoodRecipe> randomFoodRecipes(){
        return foodRecipeRepository.findAllByRandoms();
    }

    public FoodRecipe randomFoodRecipe(){
        return foodRecipeRepository.findAllByRandom();
    }

    public FoodRecipe delete(int id){
        FoodRecipe foodRecipe = foodRecipeRepository.findById(id).orElseThrow(() -> new NotFoundException(id,"FoodRecipe"));
        foodRecipeRepository.delete(foodRecipe);
        return foodRecipe;
    }

    public List<FoodRecipe> findByCategoryName(String name){
        return foodRecipeRepository.findByCategoriesNameContains(name);
    }


}
