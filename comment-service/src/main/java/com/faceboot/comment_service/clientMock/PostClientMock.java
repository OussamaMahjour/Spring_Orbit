package com.faceboot.comment_service.clientMock;

import com.faceboot.comment_service.client.PostClient;
import com.faceboot.comment_service.model.Post;
import com.faceboot.comment_service.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.random.RandomGenerator;


@Component

public class PostClientMock implements PostClient {
    UserClientMock userClientMock = new UserClientMock();
    @Override
    public Post getPostById(String id) {
        User user = userClientMock.getUserById(RandomGenerator.getDefault().nextLong());
        return Post.builder()
                .votes(12L)
                .user(user)
                .medias(null)
                .createdAt(LocalDateTime.now())
                .id(id)
                .build();
    }
}
