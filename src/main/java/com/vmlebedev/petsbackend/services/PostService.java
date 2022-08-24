package com.vmlebedev.petsbackend.services;

import com.vmlebedev.petsbackend.models.Post;
import com.vmlebedev.petsbackend.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PostService {
    private PostRepository postRepository;

    @Autowired
    public PostService (PostRepository postRepository){
        this.postRepository = postRepository;
    }

    public List<Post> findAll(){
        List<Post> list = (List<Post>) postRepository.findAll();
        Collections.reverse(list);
        return list;
    }

    public List<Post> findAllByArea(String area){
        List<Post> list = postRepository.findAllByArea(area);
        Collections.reverse(list);
        return list;
    }

    public List<Post> findAllUsersPosts(String login){
        List<Post> list = postRepository.findAllByLogin(login);
        Collections.reverse(list);
        return list;
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
        post.setImg(byteEncoded(post.getHandler()));
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        post.setDate(formatter.format(new Date()));
        post.setArea(areaParse(post.getAddress()));
        post.setAddress(addressParse(post.getAddress()));
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
    }

    public byte[] byteEncoded(String str){
        return Base64.getEncoder().encode(str.getBytes());
    }

    public String areaParse(String address){
        char [] tmp = new char[address.length()-(address.trim().indexOf(",")+1)];
        address.getChars((address.trim().indexOf(","))+1,address.length(),tmp,0);
        System.out.println(tmp);
        return new String(tmp);
    }

    public String addressParse(String address){
        char [] tmp = new char[address.length()-(address.trim().indexOf(",")+1)];
        address.getChars((address.trim().indexOf(","))+1,address.length(),tmp,0);
        String newStr = new String(tmp);
        address = address.replace(newStr, "");
        address = address.substring(0,address.length()-1);
        return address;
    }


    public List<Post> findAllByState(boolean state){
        List<Post> list = postRepository.findAllByState(state);
        Collections.reverse(list);
        return list;
    }

    public List<Post> findAllByStateAndArea(boolean state, String area){
        List<Post> list = postRepository.findAllByStateAndArea(state,area);
        Collections.reverse(list);
        return list;
    }


}
