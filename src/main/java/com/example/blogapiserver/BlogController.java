package com.example.blogapiserver;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/api/v1/blog")
public class BlogController {
    private BlogService blogService;


    ArrayList<BlogPost> myBlogPosts;


    public BlogController() {
        blogService = new BlogService();
    }


    @RequestMapping(value = "create", method = RequestMethod.POST)
    public ResponseEntity<BlogPost> createPost(@RequestBody BlogPost blogPost) {
        BlogPost newPost = blogService.createPost(blogPost);
        if(newPost.getTitle() == null) {
            return new ResponseEntity<BlogPost>(newPost, HttpStatus.BAD_REQUEST);
        }if(newPost.getBody() == null) {
            return new ResponseEntity<BlogPost>(newPost, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<BlogPost>(newPost, HttpStatus.CREATED);
        }


    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ArrayList<BlogPost> listAllPosts() {
        myBlogPosts = blogService.listAllPosts();
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
