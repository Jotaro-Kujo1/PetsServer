package com.vmlebedev.petsbackend.repository;

import com.vmlebedev.petsbackend.models.Post;
import com.vmlebedev.petsbackend.postBuilder.ClassicPostBuilder;
import com.vmlebedev.petsbackend.postBuilder.PostBuilder;
import com.vmlebedev.petsbackend.postBuilder.PostDirector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;




@DataJpaTest
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Test
    public void savePostTest(){
        PostBuilder builder = new ClassicPostBuilder();
        PostDirector postDirector = new PostDirector(builder);
        Post post = postDirector.createPost(null,"testAddress","testArea","testDescr","testLogin","testHandler",true,"testProfImg");
        postRepository.save(post);
        Assertions.assertNotNull(post.getId());
    }

    @Test
    public void getPostTest(){
        savePostTest();
        List<Post> post = postRepository.findAllByLogin("testLogin");
        Assertions.assertNotEquals(post.size(),0);
    }

    @Test
    public void updatePostTest(){
        savePostTest();
        List<Post> post = postRepository.findAllByLogin("testLogin");
        Post postUpd = post.get(0);
        postUpd.setLogin("test2");
        Post change = postRepository.save(postUpd);
        Assertions.assertEquals(change.getLogin(),"test2");
    }

    @Test
    void findAllByArea() {

    }

    @Test
    void findAllByState() {
    }

    @Test
    void findAllByStateAndArea() {
    }

    @Test
    void findAllByLogin() {
    }

    @Test
    void updatePosts() {
    }


}