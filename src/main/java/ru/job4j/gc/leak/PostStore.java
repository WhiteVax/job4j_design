package ru.job4j.gc.leak;

import java.util.*;

public class PostStore {

    private Map<Integer, Post> posts = new HashMap<>();

    private int atomicInteger = 1;

    public Post add(Post post) {
        int id = atomicInteger;
        post.setId(id);
        posts.put(id, post);
        return post;
    }

    public void removeAll() {
        posts.clear();
    }

    public Collection<Post> getPosts() {
        return posts.values();
    }
}
