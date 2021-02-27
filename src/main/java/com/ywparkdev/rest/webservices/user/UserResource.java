package com.ywparkdev.rest.webservices.user;

import com.ywparkdev.rest.webservices.post.Post;
import com.ywparkdev.rest.webservices.post.PostMissingFieldException;
import com.ywparkdev.rest.webservices.post.PostNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserResource {

    @Autowired
    private UserDaoService service;

    @GetMapping("/users")
    public List<User> retriveAllUsers() {
        return service.findAll();
    }

    @GetMapping("/users/{id}")
    public User retrieveUserById(@PathVariable int id) {
        User user = service.findOne(id);

        if(user == null) {
            throw new UserNotFoundException("id-" + id);
        }

        return user;
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User savedUser = service.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/users/{id}/posts")
    public List<Post> retrieveUserAllPosts(@PathVariable int id) {
        User user = retrieveUserById(id);

        return user.getPosts();
    }

    @GetMapping("/users/{id}/posts/{postId}")
    public Post retrieveUserPostById(@PathVariable int id, @PathVariable int postId) {
        User user = retrieveUserById(id);

        List<Post> posts = user.getPosts();

        for(Post post : posts) {
            if(post.getId() == postId) {
                return post;
            }
        }

        throw new PostNotFoundException("postId-" + postId);
    }

    @PostMapping("/users/{id}/posts")
    public ResponseEntity<Object> createUserPost(@PathVariable int id, @RequestBody Post post) {
        User user = retrieveUserById(id);

        if(user == null) {
            throw new UserNotFoundException("id-" + id);
        }

        Post savedPost = service.saveUserPost(user, post);

        if(savedPost == null) {
            throw new PostMissingFieldException("Post required fields are missing");
        }

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPost.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        User user = service.deleteById(id);

        if(user == null) {
            throw new UserNotFoundException("id-" + id);
        }
    }
}
