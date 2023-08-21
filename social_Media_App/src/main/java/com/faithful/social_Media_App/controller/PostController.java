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


        //Getting the post from the database
        Post post = postRepo.findById(postId).get();
        Set<Tag> tags = new HashSet<>();
        //getting all the Tags For the post
        tags.addAll(post.getTags());
        //Checking if tag exists Already
        Tag tagsInDb = tagRepo.findById(tag.getId()).get();

       long tagId = tagsInDb.getId();


        if(tag.getId().equals(tagId)){

            tags.add(tagsInDb);

        }else{

            tags.add(tag);
        }


        post.setTags(tags);

//        postRepo.save(post);

// Had to use PostService which accepts Dto and for some strange reasons postRepo.save()
// did not what to save my work
        PostDto postDto = new PostDto();
        postDto.setId(postId);
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setTags(tags);


        PostDto post_New =postServices.createPost(post.getUser().getId(),postDto);




        return new ResponseEntity<>(post_New,HttpStatus.CREATED);
    };






}
