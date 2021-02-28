package com.ywparkdev.rest.webservices.user;

import com.ywparkdev.rest.webservices.post.Post;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ApiModel(description = "All details about the user.")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min = 2, message = "Name should be at least 2 characters!")
    @ApiModelProperty(notes = "Birth date should be in the past")
    private String name;

    @Past()
    @ApiModelProperty(notes = "Birth date should be in the past")
    private Date birthDate;

   @OneToMany(mappedBy = "user") // name of the field in Post
    private List<Post> posts;

    protected User() {
    }

    public User(Integer id, String name, Date birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }


//
//    public Post add(Post post) {
//        if (post.getId() == null) {
//            post.setId(posts.size() + 1);
//        }
//
//        posts.add(post);
//
//        return post;
//    }
}
