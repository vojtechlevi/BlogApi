package com.example.blogapiserver;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/api/v1/blog")
public class BlogController {

    ArrayList<BlogPost> myBlogPosts;
    int latestPostID;

    public BlogController() {
        myBlogPosts = new ArrayList<BlogPost>();
        latestPostID = 0;

    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public ResponseEntity<BlogPost> createPost(@RequestBody BlogPost blogPost) {

        if (blogPost.getTitle() == "") {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (blogPost.getBody() == "") {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            latestPostID++;
            blogPost.setId(latestPostID);
            myBlogPosts.add(blogPost);
            System.out.println("Added post, ID: " + blogPost.getTitle());
            return new ResponseEntity<BlogPost>(blogPost, HttpStatus.CREATED);
        }
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ArrayList<BlogPost> listAllPosts() {
        System.out.println("Listing all Posts");
        return myBlogPosts;
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<BlogPost> getPost(@PathVariable("id") int id) {
        System.out.println("Fetching post with ID: " + id);
        BlogPost fetchedPost = getPostByID(id);
        if (fetchedPost == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(fetchedPost, HttpStatus.OK);
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.POST)
    public ResponseEntity<BlogPost> updatePost(@PathVariable("id") int id, @RequestBody BlogPost postChanges) {
        System.out.println("Updated post with ID: " + id);
        BlogPost postToUpdate = getPostByID(id);
        updatePostByID(id, postToUpdate);
        if (postToUpdate != null) {
            if (postChanges.getTitle() != null) {
                postToUpdate.setTitle(postChanges.getTitle());
            }
            if (postChanges.getBody() != null) {
                postToUpdate.setBody(postChanges.getBody());
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(postToUpdate, HttpStatus.OK);
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletePost(@PathVariable("id") int id) {
        System.out.println("Deleting post with ID: " + id);
        BlogPost postToDelete = getPostByID(id);
        if (postToDelete != null) {
            myBlogPosts.remove(postToDelete);
            return new ResponseEntity(HttpStatus.OK);
        } else {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @RequestMapping(value = "clear", method = RequestMethod.GET)
    public void clearAllPosts() {
        System.out.println("Clearing List");
        latestPostID = 0;
        myBlogPosts.clear();
    }

    // Helper Methods
    private BlogPost getPostByID(int id) {
        for (int i = 0; i < myBlogPosts.size(); i++) {
            BlogPost currentPost = myBlogPosts.get(i);
            if (currentPost.getId() == id) {
                return myBlogPosts.get(i);
            }
        }
        return null;
    }

    private BlogPost updatePostByID(int id, BlogPost updatedPost) {
        for (int i = 0; i < myBlogPosts.size(); i++) {
            BlogPost currentPost = myBlogPosts.get(i);
            if (currentPost.getId() == id) {
                myBlogPosts.set(i, updatedPost);
                return myBlogPosts.get(i);
            }
        }
        return null;
    }

}
