package com.faithful.social_Media_App.Service.impl;

import com.faithful.social_Media_App.Service.UserService;
import com.faithful.social_Media_App.model.User;
import com.faithful.social_Media_App.repo.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class UserServicesImpl implements UserService {

    private UserRepo userRepo;

    public UserServicesImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User createUser(User user) {

        return userRepo.save(user);
    }
}
