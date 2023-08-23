package com.faithful.social_Media_App.Service.impl;

import com.faithful.social_Media_App.Service.TagServices;
import com.faithful.social_Media_App.model.Tag;
import com.faithful.social_Media_App.model.User;
import com.faithful.social_Media_App.repo.TagRepo;
import com.faithful.social_Media_App.repo.UserRepo;
import org.aspectj.weaver.loadtime.DefaultWeavingContext;
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
import java.util.stream.Collectors;

@Service
public class PostServicesImpl implements PostServices {

    private PostRepo postRepo;
    private ModelMapper mapper;
    private UserRepo userRepo;
    private TagRepo tagRepo;
    private TagServices tagServices;

    public PostServicesImpl(PostRepo postRepo, ModelMapper mapper, UserRepo userRepo, TagRepo tagRepo, TagServices tagServices) {
        this.postRepo = postRepo;
        this.mapper = mapper;
        this.userRepo = userRepo;
        this.tagRepo = tagRepo;
        this.tagServices = tagServices;
    }

    @Override
    public PostDto createPost(long user_Id, PostDto postDto) {
        Post post = mapToEntity(postDto);
//Collecting UserId
        User user =userRepo.findById(user_Id).get();

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

//        long tagId = tagsInDb.getId();


        if(!tag.getId().equals(tagsInDb.getId())){

            tags.add(tag);
        }else{

            tags.add(tagsInDb);
        }


        post.setTags(tags);


        Post postRespons = postRepo.save(post);
//
        return mapToDTO(postRespons) ;
    }

    @Override
    public List<PostDto> getPostByUserID(long userId) {

        List<Post> posts =   postRepo.findByUserId(userId);
        System.out.println("Here is a post => "+posts);

        return posts.stream().map(post -> mapToDTO(post)).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getPostBytagId(long tagId) {
        List<Post> posts = postRepo.findByTagsId(tagId);

        return posts.stream().map(post -> mapToDTO(post)).collect(Collectors.toList());
    }


    private  PostDto mapToDTO(Post post){
        return mapper.map(post,PostDto.class);
    }

   private Post mapToEntity(PostDto postDto){
        return mapper.map(postDto,Post.class);
   }
}
