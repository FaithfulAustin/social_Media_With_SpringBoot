package com.faithful.social_Media_App.repo;

import com.faithful.social_Media_App.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepo extends JpaRepository<Tag, Long> {
    List<Tag> findByPostsId(long postId);
}
