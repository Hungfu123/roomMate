package com.roommate.snakewatchers.adapter.db;

import com.roommate.snakewatchers.domain.model.UserProfile;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DBUserRepository extends CrudRepository<UserProfile, Long> {

    List<UserProfile> findAll();

    UserProfile findByUsername(String username);
}
