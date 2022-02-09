package th.ac.ku.eatfoodwithyouspringbackend.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import th.ac.ku.eatfoodwithyouspringbackend.model.foodrecipes.Process;

public interface ProcessRepository extends JpaRepository<Process,Integer> {
}
