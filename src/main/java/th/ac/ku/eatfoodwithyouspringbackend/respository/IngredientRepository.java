package th.ac.ku.eatfoodwithyouspringbackend.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import th.ac.ku.eatfoodwithyouspringbackend.model.foodrecipes.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient,Integer> {

}
