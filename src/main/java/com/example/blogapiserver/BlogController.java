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
        if(newPost != null) {
            if (newPost.getTitle() == "") {
                logger.warn("Issue adding post ");
                return new ResponseEntity<BlogPost>(newPost, HttpStatus.BAD_REQUEST);
            }
            if (newPost.getBody() == "") {
                logger.warn("Issue adding post ");
                return new ResponseEntity<BlogPost>(newPost, HttpStatus.BAD_REQUEST);
            }
        }
        logger.info("Added Post, ID: " + newPost.getId());
        return new ResponseEntity<BlogPost>(newPost, HttpStatus.CREATED);
        }


    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ArrayList<BlogPost> listAllPosts() {
        logger.info("Listing all Posts");
        ArrayList<BlogPost> myBlogPosts = blogService.listAllPosts();
        return myBlogPosts;

    }


    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<BlogPost> getPost(@PathVariable("id") int id) {
        BlogPost fetchedPost = blogService.getPost(id);
        if (fetchedPost == null) {
            logger.warn("Issue fetching post with ID: " + id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        logger.info("Fetching post with ID: " + id);
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
                    logger.warn("Issue updating post with ID: " + id);
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
                if (postChanges.getBody() == "") {
                    logger.warn("Issue updating post with ID: " + id);
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
            }
            if (postChanges.getBody() != null) {
                postToUpdate.setBody(postChanges.getBody());
                if (postChanges.getTitle() == "") {
                    logger.warn("Issue updating post with ID: " + id);
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
                if (postChanges.getBody() == "") {
                    logger.warn("Issue updating post with ID: " + id);
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
            }
        } else {
            logger.warn("Cannot find post with ID: " + id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        logger.info("Updated post with ID: " + id);
        return new ResponseEntity<>(postToUpdate, HttpStatus.OK);
    }


    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletePost(@PathVariable("id") int id) {
        BlogPost postToDelete = blogService.deletePost(id);

        if (postToDelete != null) {
            logger.info("Deleted post with ID: " + id);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            logger.warn("Cannot find post with ID: " + id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }


    @RequestMapping(value = "clear", method = RequestMethod.GET)
    public void clearAllPosts() {
        logger.info("Clearing list of posts");
        blogService.clearAllPosts();

    }


}
