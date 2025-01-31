package com.faceboot.media_service.client;


import com.faceboot.media_service.model.Post;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
@FeignClient(value = "post",url="http://gateway-service:8080/POST-SERVICE/post")
public interface PostClient {
    @RequestMapping(method= RequestMethod.GET,value="/{id}")
    Post getPostById(@PathVariable("id") String id);
}
