package com.faceboot.media_service.MediaServices;

import com.faceboot.media_service.MediaDTO.MediaResponseDTO;
import com.faceboot.media_service.MediaEntities.MediaEntity;
import com.faceboot.media_service.MediaEntities.MediaTypeEnum;
import com.faceboot.media_service.MediaMapper.MediaMapperInterface;
import com.faceboot.media_service.MediaRepositories.MediaRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
        System.out.println("helooooooooo");
        return mediaResponseDTOList;
    }

    @Override
    public Optional<MediaResponseDTO> findById(String id) {
        MediaEntity mediaEntity = mediaRepository.findById(id).orElse(null);
        return Optional.ofNullable(mediaMapper.toMediaResponseDTO(mediaEntity));
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
    public Optional<MediaEntity> addMedia(String user_id,
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
            System.out.println("Just before building the entity !");
            MediaEntity mediatosave = MediaEntity.builder()
                    .postId(postId)
                    .type(MediaTypeEnum.valueOf((media_type)))
                    .path(filePath)
                    .content(media_content)
                    .build();
            System.out.println(mediatosave.toString());
            return Optional.of(mediaRepository.save(mediatosave));
        }
        else {
            boolean post_folder_created = postFolder.mkdirs();
            if(post_folder_created) {
                file.transferTo(destinationFile); // Save the file
                MediaEntity mediatosave = MediaEntity.builder()
                        .postId(postId)
                        .type(MediaTypeEnum.valueOf((media_type)))
                        .path(filePath)
                        .content(media_content)
                        .build();
                System.out.println(mediatosave.toString());
                return Optional.of(mediaRepository.save(mediatosave));
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
    public ResponseEntity<Resource> getMediaFile(String id_media) {
        MediaEntity mediaEntity =  mediaRepository.findById(id_media).orElse(null);

        Path filePath = Paths.get(mediaEntity.getPath());


        try {
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists() && resource.isReadable()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            }
        } catch (Exception ignored) {
            // Handle error for individual files
            ignored.printStackTrace();

        }
        return ResponseEntity.notFound().build();

    }
}

