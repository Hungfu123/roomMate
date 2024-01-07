package com.roommate.snakewatchers.adapter.db;
import com.roommate.snakewatchers.applicationservice.UserRepository;
import com.roommate.snakewatchers.domain.DTO.UserDTO;
import com.roommate.snakewatchers.domain.model.UserProfile;
import com.roommate.snakewatchers.mapper.UserMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final DBUserRepository repository;

    public UserRepositoryImpl(DBUserRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<UserDTO> findAll() {
        List<UserProfile> users = repository.findAll();

        return users.stream().map(UserMapper::mapToUserDTO).toList();
    }

    @Override
    public UserDTO findbyId(Long userId) {
        Optional<UserProfile> maybeUser = repository.findById(userId);
        UserDTO user = UserMapper.mapToUserDTO(maybeUser
                .orElseThrow(() -> new NoSuchElementException("Kein UserProfile mit der ID" + userId + "gefunden")));
        return user;
    }

    @Override
    public UserDTO findbyName(String username) {
        UserProfile user = repository.findByUsername(username);
        UserDTO userDTO = UserMapper.mapToUserDTO(user);
        return userDTO;
    }

    @Override
    public UserDTO save(UserDTO newUser) {
        UserProfile user = repository.save(UserMapper.mapToUser(newUser));
        System.out.println("Generated UserProfile ID:" + user.getId());
        return UserMapper.mapToUserDTO(user);
    }


}
