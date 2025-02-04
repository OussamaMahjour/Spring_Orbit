package com.faceboot.authentification_service.client;

import com.faceboot.authentification_service.dto.UserCheckCredentialsDTO;
import com.faceboot.authentification_service.dto.UserCreatDTO;
import com.faceboot.authentification_service.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@FeignClient(value = "user",url="http://gateway-service:8080/USER-SERVICE/api/v1")
public interface UserClient {
    @RequestMapping(method= RequestMethod.GET,value="/users/{id}")
    User getUserById(@PathVariable("id") Long id);

    @RequestMapping(method= RequestMethod.GET,value="/users/email/{email}")
    User getUserByEmail(@PathVariable("email") String email);

    @RequestMapping(method=RequestMethod.POST,value="/users/check-credentials")
    Boolean checkCredentials(@RequestBody UserCheckCredentialsDTO userCheckCredentialsDTO);

    @RequestMapping(method=RequestMethod.POST,value="/users")
    User addUser(@RequestBody UserCreatDTO user);

}
