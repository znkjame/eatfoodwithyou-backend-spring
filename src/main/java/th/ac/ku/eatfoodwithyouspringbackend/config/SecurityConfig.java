package th.ac.ku.eatfoodwithyouspringbackend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import th.ac.ku.eatfoodwithyouspringbackend.respository.UserRepository;
import th.ac.ku.eatfoodwithyouspringbackend.service.auth.JwtAuthenticationEntryPoint;
import th.ac.ku.eatfoodwithyouspringbackend.service.auth.JwtAuthenticationFilter;
import th.ac.ku.eatfoodwithyouspringbackend.service.auth.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    public SecurityConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers("/api/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/api/recipes/**").permitAll()
                .antMatchers(HttpMethod.POST,"/api/recipes/**").hasRole("USER")
                .antMatchers(HttpMethod.PUT,"/api/recipes/**").hasRole("USER")
                .antMatchers(HttpMethod.DELETE,"/api/recipes/**").hasRole("USER")
                .antMatchers(HttpMethod.GET,"/api/category/**").permitAll()
                .antMatchers(HttpMethod.POST,"/api/category/**").hasRole("USER")
                .antMatchers(HttpMethod.PUT,"/api/category/**").hasRole("USER")
                .antMatchers(HttpMethod.DELETE,"/api/category/**").hasRole("USER")
                .antMatchers(HttpMethod.GET,"/api/comment/**").permitAll()
                .antMatchers(HttpMethod.POST,"/api/comment/**").hasRole("USER")
                .antMatchers(HttpMethod.PUT,"/api/comment/**").hasRole("USER")
                .antMatchers(HttpMethod.DELETE,"/api/comment/**").hasRole("USER")
                .antMatchers(HttpMethod.GET,"/api/ingredients/**").permitAll()
                .antMatchers(HttpMethod.POST,"/api/ingredients/**").hasRole("USER")
                .antMatchers(HttpMethod.PUT,"/api/ingredients/**").hasRole("USER")
                .antMatchers(HttpMethod.DELETE,"/api/ingredients/**").hasRole("USER")
                .antMatchers(HttpMethod.GET,"/api/processes/**").permitAll()
                .antMatchers(HttpMethod.POST,"/api/processes/**").hasRole("USER")
                .antMatchers(HttpMethod.PUT,"/api/processes/**").hasRole("USER")
                .antMatchers(HttpMethod.DELETE,"/api/processes/**").hasRole("USER")
                .antMatchers(HttpMethod.GET,"/api/photo/**").permitAll()
                .antMatchers(HttpMethod.POST,"/api/photo/**").hasRole("USER")
                .antMatchers(HttpMethod.PUT,"/api/photo/**").hasRole("USER")
                .antMatchers(HttpMethod.DELETE,"/api/photo/**").hasRole("USER")

                .and().userDetailsService(myUserDetailsService)
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
        .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//        .and()
//                .exceptionHandling()
//                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
        .and()
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
