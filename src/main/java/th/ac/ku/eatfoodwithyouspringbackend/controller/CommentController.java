package th.ac.ku.eatfoodwithyouspringbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import th.ac.ku.eatfoodwithyouspringbackend.model.foodrecipes.Comment;
import th.ac.ku.eatfoodwithyouspringbackend.model.foodrecipes.FoodRecipe;
import th.ac.ku.eatfoodwithyouspringbackend.model.users.User;
import th.ac.ku.eatfoodwithyouspringbackend.service.CommentService;
import th.ac.ku.eatfoodwithyouspringbackend.service.FoodRecipeService;
import th.ac.ku.eatfoodwithyouspringbackend.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private FoodRecipeService foodRecipeService;
    @Autowired
    private UserService userService;

    @GetMapping
    public List<Comment> getAll(){
        return commentService.findAll();
    }

    @GetMapping("{id}")
    public Comment findByid(@PathVariable("id") int id){
        return commentService.findById(id);
    }

    @PostMapping
    public Comment create(@RequestBody Comment comment){
        FoodRecipe foodRecipe = foodRecipeService.findByID(comment.getFoodRecipe().getId());
        User user = userService.findByID(comment.getUser().getId());
        comment.setFoodRecipe(foodRecipe);
        comment.setUser(user);
        return commentService.create(comment);
    }

    @DeleteMapping("{id}")
    public Comment delete(@PathVariable("id") int id){
        Comment comment = commentService.findById(id);
        commentService.delete(id);
        return comment;
    }

    @PutMapping("{id}")
    public Comment update(@PathVariable("id") int id, @RequestBody Comment modifyComment){
        Comment comment = commentService.findById(id);
        if(!modifyComment.getIsComment().isEmpty())
        comment.setIsComment(modifyComment.getIsComment());
        commentService.update(id,comment);
        return comment;
    }
}
