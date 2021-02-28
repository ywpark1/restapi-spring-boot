package com.ywparkdev.rest.webservices.post;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ywparkdev.rest.webservices.user.User;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String content;

    @CreationTimestamp
    private Date timestamp;

    @ManyToOne(fetch=FetchType.LAZY) // it won't read the details of the user unless post.getUser() is called
    @JsonIgnore
    private User user;

    public Post() {
    }

    public Post(Integer id, String title, String content, Date timestamp) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.timestamp = timestamp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
