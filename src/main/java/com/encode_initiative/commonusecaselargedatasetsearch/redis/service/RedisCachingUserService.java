package com.encode_initiative.commonusecaselargedatasetsearch.redis.service;

import com.encode_initiative.commonusecaselargedatasetsearch.dto.UserDTO;
import com.encode_initiative.commonusecaselargedatasetsearch.model.User;
import com.encode_initiative.commonusecaselargedatasetsearch.redis.redisrepository.UserRedisDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Slf4j
@Service
public class RedisCachingUserService {

    private static final String HASH_KEY_USERS = "Users";
    private final UserRedisDAO userRedisDAO;

    public RedisCachingUserService(UserRedisDAO userRedisDAO) {
        this.userRedisDAO = userRedisDAO;
    }

    @CacheEvict(value="userList", allEntries = true)
    public User saveUserInCache(User user) {
        return userRedisDAO.save(user);
    }


    @CacheEvict(value = "userList", allEntries = true)
    @CachePut(key = "#email", value = HASH_KEY_USERS, condition = "#userDTO.username!=null")
    public User updateUserInCache(String email, UserDTO userDTO) {
        User foundUser = userRedisDAO.findUserByUsername(email);
        if(Objects.equals(email, userDTO.getUsername())) {
            foundUser.setUsername(userDTO.getUsername());
            foundUser.setPassword(userDTO.getPassword());
            foundUser.setFirstName(userDTO.getFirstName());
            foundUser.setLastName(userDTO.getLastName());
            foundUser.setStatus(userDTO.getStatus());
            userRedisDAO.save(foundUser);
        }
        return foundUser;
    }

    @Cacheable(value = "userList")
    public List<User> findAllUsers() {
        log.info(">>> GETTING ALL USERS FROM THE DB");
        return (List<User>) userRedisDAO.findAll();
    }

    // only save users that have a status of NOT INACTIVE status, these will be easily retrievable without having
    // a db call since this is more common. Inactive will be pulled up less so we will make a db call then
    @Cacheable(key = "#username", value = HASH_KEY_USERS, unless = "#result.status == 'INACTIVE'")
    public User findUserInCache(String username) {
        log.info(">>>> FINDING USER IN DB");
        return userRedisDAO.findUserByUsername(username);
    }

    @Caching(evict = {
            @CacheEvict(key = "#email", value = HASH_KEY_USERS),
            @CacheEvict(value = "userList", allEntries = true)
    })
    public String removeUserFromCache(String email) {
        User foundUser = userRedisDAO.findUserByUsername(email);
        userRedisDAO.delete(foundUser);
        return "User: " + email + " deleted";
    }





}
