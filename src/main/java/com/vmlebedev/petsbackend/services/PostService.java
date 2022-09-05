package com.vmlebedev.petsbackend.services;

import com.vmlebedev.petsbackend.models.Post;
import com.vmlebedev.petsbackend.postBuilder.ClassicPostBuilder;
import com.vmlebedev.petsbackend.postBuilder.PostBuilder;
import com.vmlebedev.petsbackend.postBuilder.PostDirector;
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
        //list.sort((o1,o2)->o2.getDate().compareTo(o1.getDate()));
        Collections.sort(list);
        return list;
    }

    public List<Post> findAllByArea(String area){
        List<Post> list = postRepository.findAllByArea(area);
        //list.sort((o1,o2)->o2.getDate().compareTo(o1.getDate()));
        Collections.sort(list);
        return list;
    }

    public List<Post> findAllUsersPosts(String login){
        List<Post> list = postRepository.findAllByLogin(login);
        //list.sort((o1,o2)->o2.getDate().compareTo(o1.getDate()));
        Collections.sort(list);
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
        return postRepository.save(
                createNewPost(
                        getImg(post.getHandler()),
                        getAddress(post.getAddress()),
                        getArea(post.getAddress()),
                        post.getDescription(),
                        post.getLogin(),
                        post.getHandler(),
                        post.isState(),
                        post.getProfimg()
                )
        );
    }

    public byte [] getImg(String handler){
        return byteEncoded(handler);
    }

    public String getAddress(String address){
        return addressParse(address);
    }

    public String getArea(String address){
        return areaParse(address);
    }


    public Post createNewPost(byte [] arr, String address, String area,String description, String login, String handler, boolean state, String profimg){
        PostBuilder builder = new ClassicPostBuilder();
        PostDirector postDirector = new PostDirector(builder);
        return postDirector.createPost(arr,address,area,description,login,handler,state,profimg);
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
        //list.sort((o1,o2)->o2.getDate().compareTo(o1.getDate()));
        Collections.sort(list);
        return list;
    }

    public List<Post> findAllByStateAndArea(boolean state, String area){
        List<Post> list = postRepository.findAllByStateAndArea(state,area);
        //list.sort((o1,o2)->o2.getDate().compareTo(o1.getDate()));
        Collections.sort(list);
        return list;
    }

    public void updatePictureAllUsersPosts(String login, String profimg){
        postRepository.updatePosts(login,profimg);
    }

}
