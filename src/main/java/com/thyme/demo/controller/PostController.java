package com.thyme.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.thyme.demo.dto.PostDto;
import com.thyme.demo.service.PostService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/admin/posts")
    public String posts(Model model) {
        List<PostDto> posts = postService.findAllPosts();
        model.addAttribute("posts", posts);
        return "admin/posts";
    }

    @GetMapping("/admin/posts/newpost")
    public String newPostForm(Model model) {
        PostDto postDto = new PostDto();
        model.addAttribute("post", postDto);
        return "admin/create_post";
    }

    @PostMapping("/admin/post")
    // 폼 에러처리 DTO 검증
    public String createPost(@Valid @ModelAttribute("post") PostDto postDto, BindingResult result, Model model) {
        if(result.hasErrors()){
            model.addAttribute("post", postDto);
            return "admin/create_post";
        }
        postDto.setUrl(getUrl(postDto.getTitle()));
        postService.createPost(postDto);
        return "redirect:/admin/posts";
    }

    //    handler edit post request
    @GetMapping("/admin/posts/{postId}/edit")
    public String editPostForm(@PathVariable("postId") Long postId, Model model) {
        // @pathVariable로 URL에서 postId를 추출하여 해당 포스트를 조회
        PostDto postDto = postService.findPostById(postId);
        model.addAttribute("post", postDto);
        return "admin/edit_post";
    }

    @PostMapping("/admin/posts/{postId}")
    public String updatePost(@PathVariable("postId") Long postId, @Valid @ModelAttribute("post") PostDto postDto, BindingResult result, Model model) {
        if(result.hasErrors()){
            model.addAttribute("post", postDto);
            return "admin/edit_post";
        }

        postDto.setId(postId);
        postService.updatePost(postDto);
        return "redirect:/admin/posts";
    }

      
    @GetMapping("/admin/posts/{postId}/delete")
    public String deletePost(@PathVariable("postId") Long postId, Model model) {
        postService.deletePost(postId);
        
        return "redirect:/admin/posts";
    }

    private static String getUrl(String postTitle){
        String title = postTitle.trim().toLowerCase();
        String url = title.replaceAll("\\s+", "-");
        url = url.replaceAll("[^A-Za-z0-9]", "-");
        return url;
    }

}
