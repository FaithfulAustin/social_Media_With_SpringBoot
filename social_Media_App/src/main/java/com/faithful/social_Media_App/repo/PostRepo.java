package com.faithful.social_Media_App.repo;

import com.faithful.social_Media_App.DTO.PostDto;
import com.faithful.social_Media_App.model.Post;
import com.faithful.social_Media_App.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface PostRepo extends JpaRepository<Post, Long> {

    List<Post> findByUserId(long userId);
    List<Post> findByTagsId(long tagId);
//    List<Tag>  findTagsByPostId(long postId);

}
