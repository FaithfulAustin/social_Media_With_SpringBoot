package com.faithful.social_Media_App.Service;

import com.faithful.social_Media_App.DTO.PostDto;
import com.faithful.social_Media_App.model.Tag;

import java.util.List;

public interface TagServices {
   Tag createTag(Tag tag);
   List<Tag> getTagsByPostId(long postId);
}
