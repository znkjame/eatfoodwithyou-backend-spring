package th.ac.ku.eatfoodwithyouspringbackend.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import th.ac.ku.eatfoodwithyouspringbackend.model.users.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
