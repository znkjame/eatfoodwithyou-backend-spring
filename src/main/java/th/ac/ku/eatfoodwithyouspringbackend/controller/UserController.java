package th.ac.ku.eatfoodwithyouspringbackend.controller;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import th.ac.ku.eatfoodwithyouspringbackend.model.users.User;
import th.ac.ku.eatfoodwithyouspringbackend.request.LoginRequest;
import th.ac.ku.eatfoodwithyouspringbackend.respository.UserRepository;
import th.ac.ku.eatfoodwithyouspringbackend.service.UserService;
import th.ac.ku.eatfoodwithyouspringbackend.service.auth.JwtService;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtService;

    @GetMapping("/jwt/{jwt}")
    public String getAll(@PathVariable("jwt") String jwt){
        return jwtService.getUsernameFromJwt(jwt);
    }



//    @PostMapping("/register")
//    public User create(@RequestBody User user){
//        return userService.create(user);
//    }

    @GetMapping("/{id}")
    public User findbyID(@PathVariable("id") int id){
        return userService.findByID(id);
    }

}
