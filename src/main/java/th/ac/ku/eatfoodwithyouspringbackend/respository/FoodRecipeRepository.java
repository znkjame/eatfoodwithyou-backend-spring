package th.ac.ku.eatfoodwithyouspringbackend.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import th.ac.ku.eatfoodwithyouspringbackend.model.foodrecipes.FoodRecipe;

import java.util.List;

public interface FoodRecipeRepository extends JpaRepository<FoodRecipe,Integer> {
    List<FoodRecipe> findFoodRecipeByNameContains(String name);
    List<FoodRecipe> findByCategoriesNameContains(String name);
//    List<FoodRecipe> findAllByIs_deleteFalse();

    @Query(nativeQuery = true,value = "SELECT * FROM food_recipe ORDER BY RAND() LIMIT 3")
    List<FoodRecipe> findAllByRandoms();

    @Query(nativeQuery = true,value = "SELECT * FROM food_recipe ORDER BY RAND() LIMIT 1")
    FoodRecipe findAllByRandom();
}
