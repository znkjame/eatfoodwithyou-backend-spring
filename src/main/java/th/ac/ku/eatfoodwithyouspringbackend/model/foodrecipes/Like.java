//package th.ac.ku.eatfoodwithyouspringbackend.model.foodrecipes;
//
//import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.UpdateTimestamp;
//import th.ac.ku.eatfoodwithyouspringbackend.model.users.User;
//
//import javax.persistence.*;
//import java.sql.Timestamp;
//
//@Entity
//public class Like {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
//    @ManyToOne
//    @JoinColumn(name = "foodRecipe_id")
//    public FoodRecipe likeFoodRecipe;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    public User likeUser;
//
//    @CreationTimestamp
//    private Timestamp created_at;
//
//    @UpdateTimestamp
//    private Timestamp updated_at;
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public FoodRecipe getLikeFoodRecipe() {
//        return likeFoodRecipe;
//    }
//
//    public void setLikeFoodRecipe(FoodRecipe likeFoodRecipe) {
//        this.likeFoodRecipe = likeFoodRecipe;
//    }
//
//    public User getLikeUser() {
//        return likeUser;
//    }
//
//    public void setLikeUser(User likeUser) {
//        this.likeUser = likeUser;
//    }
//
//    public Timestamp getCreated_at() {
//        return created_at;
//    }
//
//    public void setCreated_at(Timestamp created_at) {
//        this.created_at = created_at;
//    }
//
//    public Timestamp getUpdated_at() {
//        return updated_at;
//    }
//
//    public void setUpdated_at(Timestamp updated_at) {
//        this.updated_at = updated_at;
//    }
//}
