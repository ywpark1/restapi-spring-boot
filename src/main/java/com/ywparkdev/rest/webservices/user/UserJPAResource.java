package com.ywparkdev.rest.webservices.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJPAResource {

    @Autowired
    private UserDaoService service;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> retrieveUserById(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);

        if(!user.isPresent()) {
            throw new UserNotFoundException("id-" + id);
        }

        //"all-users", SERVER_PATH + "/users"
        //retrieveAllUsers
        EntityModel<User> resource = EntityModel.of(user.get());

        WebMvcLinkBuilder linkTo =
                linkTo(methodOn(this.getClass()).retrieveAllUsers());

        resource.add(linkTo.withRel("all-users"));

        //HATEOAS
        return resource;
    }

    @PostMapping("/jpa/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User savedUser = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
    }

//    @GetMapping("/users/{id}/posts")
//    public List<Post> retrieveUserAllPosts(@PathVariable int id) {
//        EntityModel<User> user = retrieveUserById(id);
//
//        return user.getPosts();
//    }
//
//    @GetMapping("/users/{id}/posts/{postId}")
//    public Post retrieveUserPostById(@PathVariable int id, @PathVariable int postId) {
//        User user = retrieveUserById(id);
//
//        List<Post> posts = user.getPosts();
//
//        for(Post post : posts) {
//            if(post.getId() == postId) {
//                return post;
//            }
//        }
//
//        throw new PostNotFoundException("postId-" + postId);
//    }
//
//    @PostMapping("/users/{id}/posts")
//    public ResponseEntity<Object> createUserPost(@PathVariable int id, @RequestBody Post post) {
//        User user = retrieveUserById(id);
//
//        if(user == null) {
//            throw new UserNotFoundException("id-" + id);
//        }
//
//        Post savedPost = service.saveUserPost(user, post);
//
//        if(savedPost == null) {
//            throw new PostMissingFieldException("Post required fields are missing");
//        }
//
//        URI location = ServletUriComponentsBuilder
//                .fromCurrentRequest()
//                .path("/{id}")
//                .buildAndExpand(savedPost.getId()).toUri();
//
//        return ResponseEntity.created(location).build();
//    }


}
