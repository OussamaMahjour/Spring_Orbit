package com.faceboot.media_service.MediaServices;

import com.faceboot.media_service.MediaDTO.MediaRequestDTO;
import com.faceboot.media_service.MediaDTO.MediaResponseDTO;
import com.faceboot.media_service.MediaEntities.MediaEntity;
import com.faceboot.media_service.MediaEntities.MediaType;
import com.faceboot.media_service.MediaMapper.MediaMapperInterface;
import com.faceboot.media_service.MediaRepositories.MediaRepository;
import com.faceboot.media_service.client.PostClient;
import com.faceboot.media_service.client.UserClient;
import com.faceboot.media_service.model.Post;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Component

public class MediaService implements MediaServiceInterface {
    MediaRepository mediaRepository;
    MediaMapperInterface mediaMapper;
    PostClient postClient;

    String uploadDir = new File("/media/storage").getAbsolutePath();

    public MediaService(MediaRepository mediaRepository, MediaMapperInterface mediaMapper, PostClient postClient) {
        this.mediaRepository = mediaRepository;
        this.mediaMapper = mediaMapper;
        this.postClient = postClient;
    }

    @Override
    public List<MediaResponseDTO> findAll() {
        List<MediaEntity> mediaList = mediaRepository.findAll();
        List<MediaResponseDTO> mediaResponseDTOList = new ArrayList<>();
        for (MediaEntity mediaEntity : mediaList) {
            mediaResponseDTOList.add(mediaMapper.toMediaResponseDTO(mediaEntity));
        }
        return mediaResponseDTOList;
    }

    @Override
    public List<MediaResponseDTO> findByPostId(String post_id) {
        List<MediaEntity> mediaList = mediaRepository.findAllByPostId(post_id);
        List<MediaResponseDTO> mediaResponseDTOList = new ArrayList<>();
        for (MediaEntity mediaEntity : mediaList) {
            mediaResponseDTOList.add(mediaMapper.toMediaResponseDTO(mediaEntity));
        }
        return mediaResponseDTOList;
    }

    @Override
    public MediaResponseDTO addMedia(String user_id,
                                               String postId,
                                               String media_type,
                                               String media_content,
                                               MultipartFile file) throws IOException {
        //String uploadDir = new File("/media/storage").getAbsolutePath();
        File userFolder = new File(this.uploadDir+"/"+"User_"+user_id);
        File postFolder = new File(userFolder+"/"+"Post_"+postId);
        String filePath = postFolder + "/"+file.getOriginalFilename();
        File destinationFile = new File(filePath);
        if(userFolder.exists() && postFolder.exists()) {
            file.transferTo(destinationFile); // Save the file
            MediaRequestDTO mediaRequestDTO = MediaRequestDTO.builder()
                    .postId(postId)
                    .type(MediaType.valueOf((media_type)))
                    .path(filePath)
                    .content(media_content)
                    .build();
            return mediaMapper.toMediaResponseDTO(mediaRepository.save(mediaMapper.toMediaEntity(mediaRequestDTO)));
        }
        else {
            boolean post_folder_created = postFolder.mkdirs();
            if(post_folder_created) {
                file.transferTo(destinationFile); // Save the file
                MediaRequestDTO mediaRequestDTO = MediaRequestDTO.builder()
                        .postId(postId)
                        .type(MediaType.valueOf((media_type)))
                        .path(filePath)
                        .content(media_content)
                        .build();
                return mediaMapper.toMediaResponseDTO(mediaRepository.save(mediaMapper.toMediaEntity(mediaRequestDTO)));
            }else{

                return null;
            }
        }
    }





    @Override
    public boolean deleteBypostId(String post_id) {
        Post post = postClient.getPostById(post_id);
        File userFolder = new File(this.uploadDir+"/User_"+post.getUser().getId());
        File postFolder = new File(userFolder+"/Post_"+post_id);
        if(postFolder.exists()) {
            try{
                // for windows use those 2 lines below
                //String[] path = new String[]{"cmd", "/c", "rmdir","/s", "/q",userFolder+"\\Post_"+post_id};//enable this if you
                //wana test it on windows
                int deletedMedias = mediaRepository.deleteAllByPostId(post_id);
                String[] path = {"/bin/sh", "-c", "rm -rf " + userFolder + "/Post_" + post_id}; //enable this if you
                //wana add it to docker
                Runtime.getRuntime().exec(path);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        else {
            return false;
        }
    }

    @Override
    public Resource getMediaPost(String id) {
        try{
            MediaEntity mediaEntity = mediaRepository.findById(id).orElse(null);
            if(mediaEntity != null) {
                Path mediaFile = Paths.get(mediaEntity.getPath());
                return new UrlResource(mediaFile.toUri());
            }
                return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }
}
