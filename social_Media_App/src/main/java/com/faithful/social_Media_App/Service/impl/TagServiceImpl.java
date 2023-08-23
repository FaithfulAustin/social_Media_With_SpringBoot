package com.faithful.social_Media_App.Service.impl;

import com.faithful.social_Media_App.Service.TagServices;
import com.faithful.social_Media_App.model.Tag;
import com.faithful.social_Media_App.repo.PostRepo;
import com.faithful.social_Media_App.repo.TagRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagServices {
    public TagServiceImpl(TagRepo tagRepo,
                          PostRepo postRepo) {
        this.tagRepo = tagRepo;
        this.postRepo = postRepo;
    }

    private TagRepo tagRepo;
    private final PostRepo postRepo;

    @Override
    public Tag createTag(Tag tag) {
        return tagRepo.save(tag);
    }

    @Override
    public List<Tag> getTagsByPostId(long postId) {

      return   tagRepo.findByPostsId(postId);
//        return null;
    }
}
