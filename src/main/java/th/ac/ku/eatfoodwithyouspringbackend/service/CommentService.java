package th.ac.ku.eatfoodwithyouspringbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import th.ac.ku.eatfoodwithyouspringbackend.Exception.NotFoundException;
import th.ac.ku.eatfoodwithyouspringbackend.model.foodrecipes.Comment;
import th.ac.ku.eatfoodwithyouspringbackend.respository.CommentRepository;

import java.util.List;


@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;

    public Comment findById(int id){
        return commentRepository.findById(id).orElseThrow(() -> new NotFoundException(id,"FoodRecipe"));
    }

    public List<Comment> findAll(){
        return commentRepository.findAll();
    }

    public Comment create(Comment comment){
        return commentRepository.save(comment);
    }

    public Comment update(int id,Comment modifyComment){
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new NotFoundException(id,"FoodRecipe"));
        if(!modifyComment.getIsComment().isEmpty())
            comment.setIsComment(modifyComment.getIsComment());
        return commentRepository.save(comment);
    }

    public Comment delete(int id){
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new NotFoundException(id,"FoodRecipe"));
        commentRepository.delete(comment);
        return comment;
    }

}
