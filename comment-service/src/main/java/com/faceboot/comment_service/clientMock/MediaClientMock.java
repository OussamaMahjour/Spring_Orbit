package com.faceboot.comment_service.clientMock;

import com.faceboot.comment_service.client.MediaClient;
import com.faceboot.comment_service.model.Media;
import org.springframework.stereotype.Component;

@Component
public class MediaClientMock implements MediaClient {
    @Override
    public Media getMediaById(Long id) {
        return null;
    }
}
