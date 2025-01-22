package com.faceboot.media_service.MediaServices;

import com.faceboot.media_service.MediaDTO.MediaRequestDTO;
import com.faceboot.media_service.MediaDTO.MediaResponseDTO;
import com.faceboot.media_service.MediaEntities.MediaEntity;
import com.faceboot.media_service.MediaEntities.MediaType;
import com.faceboot.media_service.MediaMapper.MediaMapperInterface;
import com.faceboot.media_service.MediaRepositories.MediaRepository;
import org.springframework.http.ResponseEntity;
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
public class MediaService implements MediaServiceInterface {
    MediaRepository mediaRepository;
    MediaMapperInterface mediaMapper;
    String uploadDir = new File("/media/storage").getAbsolutePath();

    public MediaService(MediaRepository mediaRepository, MediaMapperInterface mediaMapper) {
        this.mediaRepository = mediaRepository;
        this.mediaMapper = mediaMapper;
    }

    @Override
    public List<Optional<MediaResponseDTO>> findAll() {
        List<MediaEntity> mediaList = mediaRepository.findAll();
        List<Optional<MediaResponseDTO>> mediaResponseDTOList = new ArrayList<>();
        for (MediaEntity mediaEntity : mediaList) {
            mediaResponseDTOList.add(Optional.ofNullable(mediaMapper.toMediaResponseDTO(mediaEntity)));
        }
        return mediaResponseDTOList;
    }

    @Override
    public List<Optional<MediaResponseDTO>> findByPostId(String post_id) {
        List<MediaEntity> mediaList = mediaRepository.findAllByPostId(post_id);
        List<Optional<MediaResponseDTO>> mediaResponseDTOList = new ArrayList<>();
        for (MediaEntity mediaEntity : mediaList) {
            mediaResponseDTOList.add(Optional.ofNullable(mediaMapper.toMediaResponseDTO(mediaEntity)));
        }
        return mediaResponseDTOList;
    }

    @Override
    public Optional<MediaResponseDTO> addMedia(String user_id,
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
            return Optional.ofNullable(mediaMapper.toMediaResponseDTO(mediaRepository.save(mediaMapper.toMediaEntity(mediaRequestDTO))));
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
                return Optional.ofNullable(mediaMapper.toMediaResponseDTO(mediaRepository.save(mediaMapper.toMediaEntity(mediaRequestDTO))));
            }else{

                return Optional.empty();
            }
        }
    }




    @Transactional
    @Override
    public String deleteBypostId(String user_id, String post_id) {
        //String uploadDir = new File("/media/storage").getAbsolutePath();
        File userFolder = new File(this.uploadDir+"/User_"+user_id);
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
                return deletedMedias+" rows Deleted successfully !";
            } catch (Exception e) {
                e.printStackTrace();
                return e.getMessage();
            }
        }
        else {
            return "Not found ";
        }
    }

    @Override
    public ResponseEntity<MediaResponseDTO> getFile(MediaRequestDTO mediaRequestDTO) {
    return null;
    }
}
