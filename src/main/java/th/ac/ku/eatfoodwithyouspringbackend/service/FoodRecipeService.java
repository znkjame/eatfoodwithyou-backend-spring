package th.ac.ku.eatfoodwithyouspringbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import th.ac.ku.eatfoodwithyouspringbackend.model.foodrecipes.FoodRecipe;
import th.ac.ku.eatfoodwithyouspringbackend.model.foodrecipes.Ingredient;
import th.ac.ku.eatfoodwithyouspringbackend.model.foodrecipes.Process;
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

    public FoodRecipe getByID(int id){
        return foodRecipeRepository.findById(id).get();
    }

    public FoodRecipe create(FoodRecipe foodRecipe){
        List<Ingredient> ingredients = foodRecipe.getIngredients();
        if(ingredients != null) {
            for (Ingredient ingredient : ingredients) {
                ingredient.setFoodRecipe(foodRecipe);
                ingredientRepository.save(ingredient);
            }
        }
        List<Process> processes = foodRecipe.getProcesses();
        if(processes != null) {
            for (Process process : processes) {
                process.setFoodRecipe(foodRecipe);
                processRepository.save(process);
            }
        }
        foodRecipeRepository.save(foodRecipe);
        return foodRecipe;
    }

    public FoodRecipe update(int foodRecipe_id, FoodRecipe modifyFoodRecipe){
        FoodRecipe foodRecipe = foodRecipeRepository.findById(foodRecipe_id).get();
        if(modifyFoodRecipe.getName() != null)
        foodRecipe.setName(modifyFoodRecipe.getName());
        if(modifyFoodRecipe.getDetail() != null)
        foodRecipe.setDetail(modifyFoodRecipe.getDetail());
        if(modifyFoodRecipe.getPhoto() != null)
        foodRecipe.setPhoto(modifyFoodRecipe.getPhoto());
        foodRecipeRepository.save(foodRecipe);
        return foodRecipe;
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
}
