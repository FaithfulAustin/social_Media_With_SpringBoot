package com.faithful.social_Media_App.Service.impl;

import com.faithful.social_Media_App.model.Tag;
import com.faithful.social_Media_App.model.User;
import com.faithful.social_Media_App.repo.TagRepo;
import com.faithful.social_Media_App.repo.UserRepo;
import org.modelmapper.ModelMapper;
import com.faithful.social_Media_App.DTO.PostDto;
import com.faithful.social_Media_App.Service.PostServices;
import com.faithful.social_Media_App.model.Post;
import com.faithful.social_Media_App.repo.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PostServicesImpl implements PostServices {

    private PostRepo postRepo;
    private ModelMapper mapper;
    private UserRepo userRepo;
    private TagRepo tagRepo;

    public PostServicesImpl(PostRepo postRepo, ModelMapper mapper, UserRepo userRepo, TagRepo tagRepo) {
        this.postRepo = postRepo;
        this.mapper = mapper;
        this.userRepo = userRepo;
        this.tagRepo = tagRepo;
    }

    @Override
    public PostDto createPost(long user_Id, PostDto postDto) {
        Post post = mapToEntity(postDto);
//Collecting UserId
        User user =userRepo.findById(user_Id).get();
//
        post.setUser(user);
        Post postRespons = postRepo.save(post);
//
        return mapToDTO(postRespons) ;

    }

    @Override
    public PostDto addTagsToPost(long postId, Tag tag) {



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


        Post postRespons = postRepo.save(post);
//
        return mapToDTO(postRespons) ;
    }





    private  PostDto mapToDTO(Post post){
        return mapper.map(post,PostDto.class);
    }

   private Post mapToEntity(PostDto postDto){
        return mapper.map(postDto,Post.class);
   }
}
