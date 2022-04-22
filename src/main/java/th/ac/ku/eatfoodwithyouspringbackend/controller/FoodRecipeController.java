package th.ac.ku.eatfoodwithyouspringbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import th.ac.ku.eatfoodwithyouspringbackend.model.foodrecipes.Category;
import th.ac.ku.eatfoodwithyouspringbackend.model.foodrecipes.FoodRecipe;
import th.ac.ku.eatfoodwithyouspringbackend.model.users.User;
import th.ac.ku.eatfoodwithyouspringbackend.service.CategoryService;
import th.ac.ku.eatfoodwithyouspringbackend.service.FoodRecipeService;
import th.ac.ku.eatfoodwithyouspringbackend.response.ResponseHandler;
import th.ac.ku.eatfoodwithyouspringbackend.service.UserService;
import th.ac.ku.eatfoodwithyouspringbackend.service.storage.FileSystemStorageService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class FoodRecipeController {

    @Autowired
    private FoodRecipeService service;
    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private FileSystemStorageService fileSystemStorageService;

    @GetMapping
    public List<FoodRecipe> getAll(){
        return service.getAll();
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody FoodRecipe foodRecipe){
        try {
            if (foodRecipe.getUser() != null) {
                User user = userService.findByID(foodRecipe.getUser().getId());
                if (user != null)
                    foodRecipe.setUser(user);
                List<Category> categoryList = new ArrayList<>();
                if(!foodRecipe.getCategories().isEmpty()){
                    for (Category category : foodRecipe.getCategories()){
                        Category target = categoryService.findByName(category.getName());
                        if (target != null)
                            categoryList.add(target);
                    }
                }
                foodRecipe.setCategories(categoryList);
//                String fileName = fileSystemStorageService.store(file,"foodRecipes");
//                foodRecipe.setPhoto(fileName);
                FoodRecipe result =  service.create(foodRecipe);
                return ResponseHandler.generateResponse("Successfull!", HttpStatus.OK, result);
            }
            else {
                return ResponseHandler.generateResponse("Please input user!!",HttpStatus.BAD_REQUEST, null);
            }

        }
        catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(),HttpStatus.BAD_REQUEST, null);
        }
    }
//    @PostMapping
//    public ResponseEntity<Object> create(@RequestParam("name") String name, @RequestParam("detail") String detail,
//                                         @RequestParam("user_id") int user_id, @RequestParam("ingredients")List<Ingredient> ingredients,
//                                         @RequestParam("processes") List<Process> processes, @RequestParam("categories") String categories,
//                                         @RequestParam("photo") MultipartFile photo
//                                        ){
//        try {
//            FoodRecipe foodRecipe = new FoodRecipe();
//            if (user_id != 0) {
//                User user = userService.findByID(user_id);
//                if (user != null)
//                    foodRecipe.setUser(user);
//                List<Category> categoryList = new ArrayList<>();
//                if(!categories.isEmpty()){
//                    String[] categoriesArr = categories.split(",");
//                    for (String category : categoriesArr){
//                        Category target = categoryService.findByName(category);
//                        if (target != null)
//                            categoryList.add(target);
//                    }
//                }
//                foodRecipe.setCategories(categoryList);
//                foodRecipe.setIngredients(ingredients);
//                foodRecipe.setProcesses(processes);
//                String fileName = fileSystemStorageService.store(photo,"foodRecipes");
//                foodRecipe.setPhoto(fileName);
//                FoodRecipe result =  service.create(foodRecipe);
//                return ResponseHandler.generateResponse("Successfull!", HttpStatus.OK, result);
//            }
//            else {
//                return ResponseHandler.generateResponse("Please input user!!",HttpStatus.BAD_REQUEST, null);
//            }
//
//        }
//        catch (Exception e){
//            return ResponseHandler.generateResponse(e.getMessage(),HttpStatus.BAD_REQUEST, null);
//        }
//    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getByID(@PathVariable("id") int id){
        try {
            FoodRecipe result = service.findByID(id);
            return ResponseHandler.generateResponse("Successfull!", HttpStatus.OK, result);
        }
        catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(),HttpStatus.MULTI_STATUS, null);
        }
    }

    @GetMapping("/search/{name}")
    public List<FoodRecipe> searchByName(@PathVariable("name") String name){
        return service.searchByName(name);
    }

    @GetMapping("/randoms")
    public List<FoodRecipe> randomFoodRecipes(){
        return service.randomFoodRecipes();
    }

    @GetMapping("/random")
    public FoodRecipe randomFoodRecipe(){
        return service.randomFoodRecipe();
    }

    @GetMapping("/category/{name}")
    public HashSet<FoodRecipe> findByCategoryName(@PathVariable("name") String name){
        String[] allCategory = name.split(",");
        HashSet<FoodRecipe> foodRecipeHashSet = new HashSet<>();
        for(String temp : allCategory){
            foodRecipeHashSet.addAll(service.findByCategoryName(temp));
        }
        return foodRecipeHashSet;
    }

    @PutMapping("/{id}")
    public FoodRecipe update(@PathVariable("id") int id,@RequestBody FoodRecipe foodRecipe){
        return service.update(id,foodRecipe);
    }

    @DeleteMapping("/{id}")
    public FoodRecipe delete(@PathVariable("id") int id){
        return service.delete(id);
    }


//    @PostMapping("/recipe/photo/upload/{id}")
//    public ResponseEntity<Object> uploadImage(@RequestParam("photo") MultipartFile photo, @PathVariable int id) throws IOException {
//        FoodRecipe foodRecipe = service.findByID(id);
//        foodRecipe.setPhoto(photo.getBytes());
//        FoodRecipe result = service.update(id,foodRecipe);
//        return ResponseHandler.generateResponse("Upload photo sucess!!", HttpStatus.OK,result);
//    }
//
//    @GetMapping("/photo/{id}")
//    public ResponseEntity<Object> getImage(@PathVariable int id){
//        FoodRecipe foodRecipe = service.findByID(id);
//        final Optional<Image> image = foodRecipe.getPhoto();
//
//    }
}
