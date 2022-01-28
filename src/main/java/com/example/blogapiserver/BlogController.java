package com.example.blogapiserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/api/v1/blog")
public class BlogController {
    private BlogService blogService;
    private Logger logger;

    // Dependency Injection
    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = new BlogService();
        logger = LoggerFactory.getLogger(BlogController.class);
    }


    @RequestMapping(value = "create", method = RequestMethod.POST)
    public ResponseEntity<BlogPost> createPost(@RequestBody BlogPost blogPost) {
        BlogPost newPost = blogService.createPost(blogPost);
        if(newPost.getTitle() == null) {
            return new ResponseEntity<BlogPost>(newPost, HttpStatus.BAD_REQUEST);
        }if(newPost.getBody() == null) {
            return new ResponseEntity<BlogPost>(newPost, HttpStatus.BAD_REQUEST);
        }
        logger.info("Added Post, ID: " + newPost.getId());
        return new ResponseEntity<BlogPost>(newPost, HttpStatus.CREATED);
        }


    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ArrayList<BlogPost> listAllPosts() {
        ArrayList<BlogPost> myBlogPosts = blogService.listAllPosts();
        return myBlogPosts;

    }


    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<BlogPost> getPost(@PathVariable("id") int id) {
        BlogPost fetchedPost = blogService.getPost(id);
        if (fetchedPost == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<BlogPost>(fetchedPost, HttpStatus.OK);
    }


    @RequestMapping(value = "update/{id}", method = RequestMethod.POST)
    public ResponseEntity<BlogPost> updatePost(@PathVariable("id") int id, @RequestBody BlogPost postChanges) {
        postChanges.setId(id);
        BlogPost postToUpdate = blogService.updatePost(id, postChanges);
        if (postToUpdate != null) {
            if (postChanges.getTitle() != null) {
                postToUpdate.setTitle(postChanges.getTitle());
                if (postChanges.getTitle() == "") {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
                if (postChanges.getBody() == "") {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
            }
            if (postChanges.getBody() != null) {
                postToUpdate.setBody(postChanges.getBody());
                if (postChanges.getTitle() == "") {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
                if (postChanges.getBody() == "") {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(postToUpdate, HttpStatus.OK);
    }


    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletePost(@PathVariable("id") int id) {
        BlogPost postToDelete = blogService.deletePost(id);

        if (postToDelete != null) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }


    @RequestMapping(value = "clear", method = RequestMethod.GET)
    public void clearAllPosts() {
        blogService.clearAllPosts();

    }


}
