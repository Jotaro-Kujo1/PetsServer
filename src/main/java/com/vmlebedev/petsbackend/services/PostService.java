package com.vmlebedev.petsbackend.services;

import com.vmlebedev.petsbackend.models.Post;
import com.vmlebedev.petsbackend.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@Service
public class PostService {
    private PostRepository postRepository;

    @Autowired
    public PostService (PostRepository postRepository){
        this.postRepository = postRepository;
    }

    public List<Post> findAll(){
        return (List<Post>) postRepository.findAll();
    }

    public void deleteById(String id){
        postRepository.deleteById(id);
    }

    public boolean checkPost(Post post){
        boolean check =
        findAll()
                .stream()
                .anyMatch(s -> s.getDescription().equals(post.getDescription()) && s.getAddress().equals(post.getAddress()));

        if(check){
            return false;
        }else return true;
    }

    public Post savePost(Post post){
        String uniqueKey = UUID.randomUUID().toString();
        post.setId(uniqueKey);
        return postRepository.save(post);
    }

    public byte[] toByteArrConverter(MultipartFile file){
        try(InputStream is = file.getInputStream()){
            return is.readAllBytes();
        }catch (IOException ex){
            ex.printStackTrace();
            return null;
        }
        /*
        BufferedImage bImage = ImageIO.read(file);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(bImage, "png", bos);
        return bos.toByteArray();
         */
    }
}
