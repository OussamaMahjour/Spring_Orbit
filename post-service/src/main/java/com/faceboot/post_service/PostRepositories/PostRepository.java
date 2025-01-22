package com.faceboot.post_service.PostRepositories;

import com.faceboot.post_service.PostEntities.PostEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PostRepository extends MongoRepository <PostEntity, String> {
    List<PostEntity> findAllByUserid(Long user);
    List<PostEntity> deletePostEntitiesByUserid(Long user);
}
