package th.ac.ku.eatfoodwithyouspringbackend.Serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import th.ac.ku.eatfoodwithyouspringbackend.model.foodrecipes.FoodRecipe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomFoodRecipeSerializer extends StdSerializer<List<FoodRecipe>> {

    public CustomFoodRecipeSerializer() {
        this(null);
    }

    public CustomFoodRecipeSerializer(Class<List<FoodRecipe>> t) {
        super(t);
    }

    public void serialize(List<FoodRecipe> foodRecipes, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        List<Integer> foodRecipeIds = new ArrayList<>();
        for (FoodRecipe foodRecipe : foodRecipes){
            foodRecipeIds.add(foodRecipe.id);
        }
        jsonGenerator.writeObject(foodRecipeIds);
    }
}
