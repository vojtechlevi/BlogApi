package com.example.blogapiserver;

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
    public BlogPost createPost(@RequestBody BlogPost blogPost) {

        latestPostID++;
        blogPost.setId(latestPostID);
        myBlogPosts.add(blogPost);
        return blogPost;
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ArrayList<BlogPost> listAllPosts() {
        System.out.println("List all BlogPosts");
        return myBlogPosts;
    }

    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public BlogPost listBlogID(@PathVariable("id") int id) {
        System.out.println("Getting post with id " + id);
        return getPostByID(id);
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.POST)
    public BlogPost updatePost(@PathVariable("id") int id, @RequestBody BlogPost postChanges) {
        System.out.println("Update post with id " + id);
        BlogPost postToUpdate = getPostByID(id);

        if (postChanges.getTitle() != null) {
            postToUpdate.setTitle(postChanges.getTitle());
        }
        if (postChanges.getBody() != null) {
            postToUpdate.setBody(postChanges.getBody());
        }
        updatePostByID(id, postToUpdate);
        return postToUpdate;
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public void deletePost(@PathVariable("id") int id) {
        System.out.println("Deleting post with id " + id);
        BlogPost postToDelete = getPostByID(id);
        myBlogPosts.remove(postToDelete);

    }


    // Helper Methods
    private BlogPost getPostByID(int id) {
        for (int i = 0; i < myBlogPosts.size(); i++){
            BlogPost currentPost = myBlogPosts.get(i);
            if(currentPost.getId() == id) {
                return myBlogPosts.get(i);
            }
        }
        return new BlogPost();
    }

    private BlogPost updatePostByID(int id, BlogPost updatedPost){
        for (int i = 0; i < myBlogPosts.size(); i++){
            BlogPost currentPost = myBlogPosts.get(i);
            if(currentPost.getId() == id){
                myBlogPosts.set(i, updatedPost);
                return myBlogPosts.get(i);
            }
        }
        return new BlogPost();
    }

}
