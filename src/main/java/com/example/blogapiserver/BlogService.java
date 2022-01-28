package com.example.blogapiserver;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

public class BlogService {
    ArrayList<BlogPost> myBlogPosts;
    int latestPostID;

    public BlogService() {
        myBlogPosts = new ArrayList<>();
        latestPostID = 0;
    }


    public BlogPost createPost(BlogPost newPost) {

        latestPostID++;
        newPost.setId(latestPostID);
        myBlogPosts.add(newPost);
        System.out.println("Added post, ID: " + newPost.getTitle());
        return newPost;

    }

    public ArrayList<BlogPost> listAllPosts() {
        System.out.println("Listing all Posts");
        return myBlogPosts;
    }

    public BlogPost getPost(int id) {
        System.out.println("Fetching post with ID: " + id);
        BlogPost fetchedPost = getPostByID(id);
        return fetchedPost;
    }

    public BlogPost updatePost(int id, BlogPost postChanges) {
        System.out.println("Updated post with ID: " + id);
        BlogPost postToUpdate;
        postToUpdate = updatePostByID(id, postChanges);
        return postToUpdate;
    }

    public BlogPost deletePost(int id) {
        System.out.println("Deleting post with ID: " + id);
        BlogPost postToDelete = getPostByID(id);

        if (postToDelete != null) {
            myBlogPosts.remove(postToDelete);
        } else {
            System.out.println("Couldn't find ID: " + id);
        }
        return postToDelete;
    }

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

