package com.faceboot.comment_service.mapper;

import com.faceboot.comment_service.client.MediaClient;
import com.faceboot.comment_service.client.PostClient;
import com.faceboot.comment_service.client.UserClient;
import com.faceboot.comment_service.clientMock.PostClientMock;
import com.faceboot.comment_service.clientMock.UserClientMock;
import com.faceboot.comment_service.dto.CommentRequestDTO;
import com.faceboot.comment_service.dto.CommentResponseDTO;
import com.faceboot.comment_service.entity.Comment;
import com.faceboot.comment_service.model.Media;
import com.faceboot.comment_service.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.mapper.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class CommentMapper implements CommentMapperInterface {

    private final UserClient userClient;
    private final PostClientMock postClient;



    public  CommentResponseDTO commentToCommentResponseDTO(Comment comment){
        return new CommentResponseDTO(
                comment.getId(),
                userClient.getUserById(comment.getUserId()),
                postClient.getPostById(comment.getPostId()),
                comment.getContent(),
                comment.getCreateTime());
    }

   public Comment commentRequestDTOToComment(CommentRequestDTO commentRequestDTO){
        return new Comment().builder()
                .userId(commentRequestDTO.userId())
                .postId(commentRequestDTO.postId())
                .createTime(commentRequestDTO.createTime())
                .content(commentRequestDTO.content())
                .build();
    }

}
