package th.ac.ku.eatfoodwithyouspringbackend.service.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {
    private Key key;

    @PostConstruct
    public void init(){
        key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    }

    public String generateToken(String email) throws IllegalArgumentException{
        Map<String,String> emailMap = new HashMap<>();
        emailMap.put("email",email);
        return Jwts.builder()
                .setSubject(email)
                .signWith(key).compact();
    }

    public Boolean validateToken(String jwt){
        Jwts.parser().setSigningKey(key).parseClaimsJws(jwt).getBody().getSubject();
        return true;
    }

    public String getUsernameFromJwt(String jwt) {
        Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(jwt).getBody();
        return claims.getSubject();
    }

}
