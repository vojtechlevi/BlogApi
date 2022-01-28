package com.example.blogapiserver;

import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class BlogService {
    ArrayList<BlogPost> myBlogPosts;
    int latestPostID;

    public BlogService() {
        myBlogPosts = new ArrayList<>();
        latestPostID = 0;
    }


    public BlogPost createPost(BlogPost newPost) {

        latestPostID++;
        if(newPost.getTitle() == ""){
            return newPost;
        }
        if(newPost.getBody() == ""){
            return newPost;
        } else {
            newPost.setId(latestPostID);
            myBlogPosts.add(newPost);
            return newPost;
        }
    }

    public ArrayList<BlogPost> listAllPosts() {

        return myBlogPosts;
    }

    public BlogPost getPost(int id) {

        BlogPost fetchedPost = getPostByID(id);
        return fetchedPost;
    }

    public BlogPost updatePost(int id, BlogPost postChanges) {

        BlogPost postToUpdate;
        if(postChanges.getTitle() == ""){
            return postChanges;
        }
        if(postChanges.getBody() == ""){
            return postChanges;
        } else {
            postToUpdate = updatePostByID(id, postChanges);
            return postToUpdate;
        }
    }

    public BlogPost deletePost(int id) {

        BlogPost postToDelete = getPostByID(id);

        if (postToDelete != null) {
            myBlogPosts.remove(postToDelete);
        }

        return postToDelete;
    }

    public void clearAllPosts() {
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

