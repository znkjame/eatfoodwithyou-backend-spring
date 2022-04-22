package th.ac.ku.eatfoodwithyouspringbackend.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class LoginResponse {
    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status,
                                                          Object responseObj,String token){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("token", token);
        map.put("message",message);
        map.put("status",status.value());
        map.put("data",responseObj);
        return new ResponseEntity<Object>(map,status);
    }
}
