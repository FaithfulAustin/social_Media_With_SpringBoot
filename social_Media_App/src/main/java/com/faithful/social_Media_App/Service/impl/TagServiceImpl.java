package com.faithful.social_Media_App.Service.impl;

import com.faithful.social_Media_App.Service.TagServices;
import com.faithful.social_Media_App.model.Tag;
import com.faithful.social_Media_App.repo.TagRepo;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl implements TagServices {
    public TagServiceImpl(TagRepo tagRepo) {
        this.tagRepo = tagRepo;
    }

    private TagRepo tagRepo;
    @Override
    public Tag createTag(Tag tag) {
        return tagRepo.save(tag);
    }
}
