package com.thyme.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.thyme.demo.dto.PostDto;
import com.thyme.demo.entity.Post;
import com.thyme.demo.mapper.PostMapper;
import com.thyme.demo.repository.PostRepository;
import com.thyme.demo.service.PostService;

@Service
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<PostDto> findAllPosts() {

        List<Post> posts = postRepository.findAll();

        return posts.stream().map((post) -> PostMapper.mapToPostDto(post)).collect(Collectors.toList());
    } 
}
