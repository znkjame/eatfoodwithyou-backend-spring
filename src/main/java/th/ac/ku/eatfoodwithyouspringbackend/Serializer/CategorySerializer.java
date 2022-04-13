package th.ac.ku.eatfoodwithyouspringbackend.Serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import th.ac.ku.eatfoodwithyouspringbackend.model.foodrecipes.Category;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CategorySerializer extends StdSerializer<List<Category>> {

    public CategorySerializer(){
        this(null);
    }

    public CategorySerializer(Class<List<Category>> t) {
        super(t);
    }

    @Override
    public void serialize(List<Category> categories, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        List<String> categoryName = new ArrayList<>();
        for (Category category : categories){
            categoryName.add(category.name);
        }
        jsonGenerator.writeObject(categoryName);
    }
}
