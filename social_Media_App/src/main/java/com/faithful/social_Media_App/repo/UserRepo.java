package com.faithful.social_Media_App.repo;

import com.faithful.social_Media_App.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {

}
