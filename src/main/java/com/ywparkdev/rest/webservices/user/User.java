package com.ywparkdev.rest.webservices.user;

import com.ywparkdev.rest.webservices.post.Post;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User {

    private Integer id;
    private String name;
    private Date birthDate;
    private List<Post> posts = new ArrayList<>();

    protected User() {}

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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public Post add(Post post) {
        if(post.getId() == null) {
            post.setId(posts.size() + 1);
        }

        posts.add(post);

        return post;
    }
}
