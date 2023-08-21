package com.faithful.social_Media_App.Service;

import com.faithful.social_Media_App.DTO.PostDto;
import com.faithful.social_Media_App.model.Tag;

import java.util.List;

public interface PostServices {

    PostDto createPost(long user_Id, PostDto postDto);
    PostDto addTagsToPost(long postId, Tag tag);


}
