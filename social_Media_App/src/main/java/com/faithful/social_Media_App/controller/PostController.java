package com.faithful.social_Media_App.controller;

import com.faithful.social_Media_App.DTO.PostDto;
import com.faithful.social_Media_App.Service.PostServices;
import com.faithful.social_Media_App.model.Post;
import com.faithful.social_Media_App.model.Tag;
import com.faithful.social_Media_App.repo.PostRepo;
import com.faithful.social_Media_App.repo.TagRepo;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1")
public class PostController {

    private PostServices postServices;

    private final PostRepo postRepo;
    private final TagRepo tagRepo;

    public PostController(PostServices postServices,
                          PostRepo postRepo,
                          TagRepo tagRepo) {
        this.postServices = postServices;
        this.postRepo = postRepo;
        this.tagRepo = tagRepo;
    }
    @PostMapping("/{user_id}/post")
    public ResponseEntity<PostDto> createPost(@PathVariable(value = "user_id") long userId ,
                                           @Valid @RequestBody PostDto postDto) {

        PostDto post_New =postServices.createPost(userId,postDto);
        return  new ResponseEntity<>(post_New,HttpStatus.CREATED);
    }

    @PostMapping("/{post_id}/tags")
    public ResponseEntity<PostDto> addTagsToPost(@PathVariable(value = "post_id") long postId ,
                                                 @Valid @RequestBody Tag tag) {

        PostDto post_New =postServices.addTagsToPost(postId,tag);
        return  new ResponseEntity<>(post_New,HttpStatus.CREATED);

    };

    @GetMapping("/{userId}/post")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable(value = "userId") long userId){

        List<PostDto>   postByUserId =postServices.getPostByUserID(userId);
        return new ResponseEntity<>(postByUserId,HttpStatus.OK);
    }

    @GetMapping("/{tagId}/tags")
    public ResponseEntity<List<PostDto>> getPostBytagId(@PathVariable(value = "tagId") long tagId){
        List<PostDto>   postByTagId =postServices.getPostBytagId(tagId);
        return new ResponseEntity<>(postByTagId,HttpStatus.OK);

    }






}
