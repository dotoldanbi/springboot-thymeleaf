package com.thyme.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thyme.demo.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
 
    Optional<Post> findByUrl(String url);
}
