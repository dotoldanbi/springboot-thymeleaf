package com.thyme.demo.service;

import java.util.List;

import com.thyme.demo.dto.PostDto;


public interface PostService {
    List<PostDto> findAllPosts();

    void createPost(PostDto postDto);

    PostDto findPostById(Long postId);

    void updatePost(PostDto postDto);

    void deletePost(Long postId);
}
