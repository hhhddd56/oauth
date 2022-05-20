package com.oauth.oauthserver.repository;

import com.oauth.oauthserver.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    User findByClientId(String clientId);
}
