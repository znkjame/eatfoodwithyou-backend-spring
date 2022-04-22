package th.ac.ku.eatfoodwithyouspringbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import th.ac.ku.eatfoodwithyouspringbackend.model.users.User;
import th.ac.ku.eatfoodwithyouspringbackend.request.LoginRequest;
import th.ac.ku.eatfoodwithyouspringbackend.response.LoginResponse;
import th.ac.ku.eatfoodwithyouspringbackend.respository.UserRepository;
import th.ac.ku.eatfoodwithyouspringbackend.service.UserService;
import th.ac.ku.eatfoodwithyouspringbackend.service.auth.JwtService;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest){
        try{
            UsernamePasswordAuthenticationToken authInputToken = new UsernamePasswordAuthenticationToken(
                    loginRequest.getEmail(),
                    loginRequest.getPassword());
            authenticationManager.authenticate(authInputToken);
            String token = jwtService.generateToken(loginRequest.getEmail());
            User user = userRepository.findByEmail(loginRequest.getEmail()).get(0);
            return LoginResponse.generateResponse("Login success", HttpStatus.OK, user, token);
        }
        catch (AuthenticationException authenticationException){
            throw new RuntimeException("Invalid Login");
        }
    }

//    @PostMapping("/login")
//    public Map<String, Object> loginHandler(@RequestBody LoginRequest body){
//        try {
//            UsernamePasswordAuthenticationToken authInputToken =
//                    new UsernamePasswordAuthenticationToken(body.getEmail(), body.getPassword());
//
//            authManager.authenticate(authInputToken);
//
//            String token = jwtUtil.generateToken(body.getEmail());
//
//            return Collections.singletonMap("jwt-token", token);
//        }catch (AuthenticationException authExc){
//            throw new RuntimeException("Invalid Login Credentials");
//        }
//    }
    @PostMapping("/register")
    public ResponseEntity<Object> create(@RequestBody User user){
        String encrypPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encrypPassword);
        userService.create(user);
        String token = jwtService.generateToken(user.getEmail());
        return LoginResponse.generateResponse("Register success!!", HttpStatus.OK, user,token);
    }
}
