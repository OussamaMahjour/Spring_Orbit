package com.faceboot.media_service.MediaRepositories;

import com.faceboot.media_service.MediaEntities.MediaEntity;
//import jakarta.transaction.Transactional;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MediaRepository extends MongoRepository <MediaEntity, String> {
    List<MediaEntity> findAllByPostId(String id);
    int deleteAllByPostId(String postId);


}
