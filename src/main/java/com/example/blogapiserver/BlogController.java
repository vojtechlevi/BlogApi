package com.example.blogapiserver;


import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@RestController
@RequestMapping(value = "/api/v1/blog")
public class BlogController {

    ArrayList<BlogPost> myBlogPosts;
    int latestPostID;

    public BlogController(){
        myBlogPosts = new ArrayList<BlogPost>();
        latestPostID = 0;

    }

    @RequestMapping(value ="create", method = RequestMethod.POST)
    public BlogPost createPost(@RequestBody BlogPost blogPost){

        latestPostID++;
        blogPost.setId(latestPostID);
        myBlogPosts.add(blogPost);
        return blogPost;
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ArrayList<BlogPost> listPosts() {
        return myBlogPosts;
    }

}
