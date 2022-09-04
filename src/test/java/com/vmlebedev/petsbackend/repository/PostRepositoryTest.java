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
        init();
        List<Post> posts = postRepository.findAllByArea("testArea");
        Assertions.assertEquals(posts.size(),3);
    }

    @Test
    void findAllByState() {
        init();
        List<Post> posts = postRepository.findAllByState(true);
        Assertions.assertEquals(posts.size(),3);
    }

    @Test
    void findAllByStateAndArea() {
        init();
        List<Post> posts = postRepository.findAllByStateAndArea(true,"testArea");
        Assertions.assertEquals(posts.size(),3);
    }

    @Test
    void findAllByLogin() {
        init();
        List<Post> posts = postRepository.findAllByLogin("testLogin");
        Assertions.assertEquals(posts.size(),3);
    }


    private void init(){
        PostBuilder builder = new ClassicPostBuilder();
        PostDirector postDirector = new PostDirector(builder);
        Post post1 = postDirector.createPost(null,"testAddress","testArea","testDescr","testLogin","testHandler",true,"testProfImg");
        Post post2 = postDirector.createPost(null,"testAddress2","testArea","testDescr2","testLogin","testHandler2",true,"testProfImg2");
        Post post3 = postDirector.createPost(null,"testAddress3","testArea","testDescr3","testLogin","testHandler3",true,"testProfImg3");
        postRepository.save(post1);
        postRepository.save(post2);
        postRepository.save(post3);
    }

}