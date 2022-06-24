package com.ssau.bookrecommend.repository;

import com.ssau.bookrecommend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepos extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String login);
}
