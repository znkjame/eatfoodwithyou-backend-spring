package th.ac.ku.eatfoodwithyouspringbackend.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import th.ac.ku.eatfoodwithyouspringbackend.model.users.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByEmail(String email);
}
