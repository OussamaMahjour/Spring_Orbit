package com.faceboot.post_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.faceboot.post_service.model.Media;

import java.util.List;
import java.util.Optional;

@Component
@FeignClient(value = "media",url="http://gateway-service:8080/MEDIA-SERVICE/media/")
public interface MediaClient {
    @RequestMapping(method= RequestMethod.GET,value="/post/{post_id}")
    List<Media> getMediaByPostId(@PathVariable("post_id") String post_id);

    @RequestMapping(method = RequestMethod.POST, value = "/upload")
    Optional<Media> addMedia(@RequestPart("user_id") Long userId,
                      @RequestPart("post_id") String postId,
                      @RequestPart("type") String mediaType,
                      @RequestPart("content") String mediaContent,
                      @RequestPart("media") MultipartFile file);

    @RequestMapping(method = RequestMethod.DELETE, value="/delete/postid/{user_id}/{post_id}")
    void deleteMediaByPostId(@PathVariable("user_id") String user_id, @PathVariable("post_id") String post_id);
}
