package com.faithful.social_Media_App.controller;

import com.faithful.social_Media_App.DTO.PostDto;
import com.faithful.social_Media_App.Service.TagServices;
import com.faithful.social_Media_App.model.Tag;
import com.faithful.social_Media_App.repo.TagRepo;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class TagController {
    TagServices tagServices;
    TagRepo tagRepo;

    public TagController(TagServices tagServices, TagRepo tagRepo) {
        this.tagServices = tagServices;
        this.tagRepo = tagRepo;
    }

    @PostMapping("/tags")
    public ResponseEntity<Tag> createPost( @Valid @RequestBody Tag tag) {
        tagServices.createTag(tag);
        return new ResponseEntity<>(tag, HttpStatus.CREATED);
    }


}
