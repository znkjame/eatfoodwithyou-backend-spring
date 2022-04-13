package th.ac.ku.eatfoodwithyouspringbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import th.ac.ku.eatfoodwithyouspringbackend.model.foodrecipes.FoodRecipe;
import th.ac.ku.eatfoodwithyouspringbackend.model.users.User;
import th.ac.ku.eatfoodwithyouspringbackend.service.FoodRecipeService;
import th.ac.ku.eatfoodwithyouspringbackend.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/like")
public class LikeController {
    @Autowired
    private FoodRecipeService foodRecipeService;
    @Autowired
    private UserService userService;

//    @PostMapping
//    public Like createLike(@RequestParam("user_id") int user_id, @RequestParam("foodRecipe_id") int foodRecipe_id){
//        FoodRecipe foodRecipe = foodRecipeService.findByID(foodRecipe_id);
//        User user = userService.findByID(user_id);
//        Like like = new Like();
//        like.setLikeUser(user);
//        like.setLikeFoodRecipe(foodRecipe);
//        System.out.println(like);
//        return likeService.create(like);
//    }
//
//    @DeleteMapping
//    public Like disLike(@Param("user_id") int user_id, @Param("foodRecipe_id") int foodRecipe_id){
//        Like like = likeService.findLikeByUserIdAndFoodRecipeId(user_id,foodRecipe_id);
//        likeService.delete(like);
//        return like;
//    }
    @PostMapping
    public FoodRecipe createLike(@RequestParam("user_id") int user_id, @RequestParam("foodRecipe_id") int foodRecipe_id){
        FoodRecipe foodRecipe = foodRecipeService.findByID(foodRecipe_id);
        User user = userService.findByID(user_id);
        foodRecipe.getUsers().add(user);
        return foodRecipeService.update(foodRecipe_id,foodRecipe);
    }

    @DeleteMapping
    public FoodRecipe disLike(@Param("user_id") int user_id, @Param("foodRecipe_id") int foodRecipe_id){
        FoodRecipe foodRecipe = foodRecipeService.findByID(foodRecipe_id);
        User user = userService.findByID(user_id);
        foodRecipe.getUsers().remove(user);
        return foodRecipeService.update(foodRecipe_id,foodRecipe);
    }
}
