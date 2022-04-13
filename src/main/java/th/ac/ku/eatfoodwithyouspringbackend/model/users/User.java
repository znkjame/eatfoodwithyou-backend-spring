package th.ac.ku.eatfoodwithyouspringbackend.model.users;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import th.ac.ku.eatfoodwithyouspringbackend.Serializer.CustomFoodRecipeSerializer;
import th.ac.ku.eatfoodwithyouspringbackend.model.foodrecipes.FoodRecipe;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @JsonIgnore
    private String password;

    @Column(unique = true)
    private String email;

    @JsonIgnore
    @CreationTimestamp
    private Timestamp created_at;

    @JsonIgnore
    @UpdateTimestamp
    private Timestamp updated_at;

    @Enumerated(EnumType.STRING)
    private Role role;

    private int age;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @JsonBackReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<FoodRecipe> foodRecipes;

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public List<FoodRecipe> getFoodRecipes() {
        return foodRecipes;
    }

    public void setFoodRecipes(List<FoodRecipe> foodRecipes) {
        this.foodRecipes = foodRecipes;
    }

    public int getId() {
        return id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
