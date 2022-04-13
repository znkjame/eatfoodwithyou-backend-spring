package th.ac.ku.eatfoodwithyouspringbackend.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import th.ac.ku.eatfoodwithyouspringbackend.model.foodrecipes.Comment;

public interface CommentRepository extends JpaRepository<Comment,Integer> {

}
