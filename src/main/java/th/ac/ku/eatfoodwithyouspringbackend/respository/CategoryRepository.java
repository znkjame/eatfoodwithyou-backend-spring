package th.ac.ku.eatfoodwithyouspringbackend.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import th.ac.ku.eatfoodwithyouspringbackend.model.foodrecipes.Category;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    List<Category> findAllByNameContains(String name);
    Category findByNameContains(String name);
}
