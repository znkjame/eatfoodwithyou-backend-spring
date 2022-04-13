package th.ac.ku.eatfoodwithyouspringbackend.Serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import th.ac.ku.eatfoodwithyouspringbackend.model.foodrecipes.FoodRecipe;
import th.ac.ku.eatfoodwithyouspringbackend.model.users.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LikeUserSerializer extends StdSerializer<List<User>> {
    public LikeUserSerializer(){
        this(null);
    }

    public LikeUserSerializer(Class<List<User>> t) {
        super(t);
    }

    @Override
    public void serialize(List<User> users, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        List<Integer> likeUserId = new ArrayList<>();
        for (User user : users){
            likeUserId.add(user.getId());
        }
        jsonGenerator.writeObject(likeUserId);
    }
}
