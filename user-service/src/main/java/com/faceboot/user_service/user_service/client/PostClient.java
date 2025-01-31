package com.faceboot.user_service.user_service.client;

import com.faceboot.user_service.user_service.feignModel.Post;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
@FeignClient(value = "post",url="http://gateway-service:8080/POST-SERVICE/post")
public interface PostClient {
    @RequestMapping(method= RequestMethod.DELETE,value="/delete/user/{user_id}")
    String deletePostByUserId(@PathVariable("user_id") Long id);
}
