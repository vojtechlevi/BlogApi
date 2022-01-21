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
    public ArrayList<BlogPost> listAllPosts() {
        return myBlogPosts;
    }

    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public BlogPost ListBlogId(@PathVariable("id") int id){
        System.out.println("Getting post with id " + id);
        return getPostByID(id);
    }









    private BlogPost getPostByID(int id) {
        for (int i = 0; i < myBlogPosts.size(); i++){
            BlogPost currentPost = myBlogPosts.get(i);
            if(currentPost.getId() == id) {
                return myBlogPosts.get(i);
            }
        }
        return new BlogPost();
    }

}
