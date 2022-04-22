package th.ac.ku.eatfoodwithyouspringbackend.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import th.ac.ku.eatfoodwithyouspringbackend.model.users.Role;
import th.ac.ku.eatfoodwithyouspringbackend.model.users.User;
import th.ac.ku.eatfoodwithyouspringbackend.respository.UserRepository;

import java.util.Collections;
import java.util.List;


@Component
public class MyUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        String ROLE_PREFIX = "ROLE_";
        List<User> userList = userRepository.findByEmail(email);
        if(userList.isEmpty())
            throw new UsernameNotFoundException("Couldn't found user with email" + email);
        User user = userList.get(0);
        return new org.springframework.security.core.userdetails.User(email,user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(ROLE_PREFIX + user.getRole())));
    }
}
