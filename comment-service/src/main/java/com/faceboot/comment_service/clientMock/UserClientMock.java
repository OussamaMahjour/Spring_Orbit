package com.faceboot.comment_service.clientMock;

import com.faceboot.comment_service.client.UserClient;
import com.faceboot.comment_service.model.Post;
import com.faceboot.comment_service.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
public class UserClientMock  implements UserClient {

    @Override
    public User getUserById(Long id) {
        User user = new User().builder()
                .id(id)
                .name("oussama")
                .createdAt(LocalDateTime.now())
                .email("oussama@gmail.com")
                .deletedAt(null)
                .gender("male")
                .verified(false)
                .build();
        return user;

    }
}
