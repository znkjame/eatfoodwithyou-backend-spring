package th.ac.ku.eatfoodwithyouspringbackend.model.foodrecipes;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import th.ac.ku.eatfoodwithyouspringbackend.Serializer.CategorySerializer;
import th.ac.ku.eatfoodwithyouspringbackend.Serializer.LikeUserSerializer;
import th.ac.ku.eatfoodwithyouspringbackend.model.users.User;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Entity
public class FoodRecipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    private String name;

    private String detail;

    @Column(nullable = true)
    private String photo;

    @CreationTimestamp
    private Timestamp created_at;

    @UpdateTimestamp
    private Timestamp updated_at;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    private User user;

    @JsonManagedReference
    @OneToMany(mappedBy = "foodRecipe", cascade = CascadeType.ALL)
    private List<Ingredient> ingredients;
    @JsonManagedReference
    @OneToMany(mappedBy = "foodRecipe", cascade = CascadeType.ALL)
    private List<Process> processes;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "category_foodRecipe",
            joinColumns = @JoinColumn(name = "foodRecipe_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"))
//    @JsonManagedReference
    @JsonSerialize(using = CategorySerializer.class)
    private List<Category> categories;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "likes",
            joinColumns = @JoinColumn(name = "foodRecipe_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"))
    @JsonSerialize(using = LikeUserSerializer.class)
    private List<User> likeUsers;

//    @OneToMany(mappedBy = "likeFoodRecipe")
//    List<Like> likes;

    @OneToMany(mappedBy = "foodRecipe")
    private List<Comment> comments;

    @Column(columnDefinition = "boolean default false")
    private Boolean is_delete;

    public List<User> getUsers() {
        return likeUsers;
    }

    public void setUsers(List<User> users) {
        this.likeUsers = users;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public int getId() {
        return id;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Process> getProcesses() {
        return processes;
    }

    public void setProcesses(List<Process> processes) {
        this.processes = processes;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detaill) {
        this.detail = detaill;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(Boolean is_delete) {
        this.is_delete = is_delete;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
