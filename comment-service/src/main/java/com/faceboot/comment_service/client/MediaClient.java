package com.faceboot.comment_service.client;

import com.faceboot.comment_service.model.Media;
import com.faceboot.comment_service.model.Post;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(value = "media",url="http://localhost:8081")
public interface MediaClient {
    @RequestMapping(method= RequestMethod.GET,value="/media/byId/{id}")
    Media getMediaById(@PathVariable("id") Long id);
}