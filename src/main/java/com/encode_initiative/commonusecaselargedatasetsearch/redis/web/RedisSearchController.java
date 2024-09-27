package com.encode_initiative.commonusecaselargedatasetsearch.redis.web;

import com.encode_initiative.commonusecaselargedatasetsearch.dto.UserDTO;
import com.encode_initiative.commonusecaselargedatasetsearch.model.User;
import com.encode_initiative.commonusecaselargedatasetsearch.redis.service.RedisCachingUserService;
import com.encode_initiative.commonusecaselargedatasetsearch.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/redis/user")
public class RedisSearchController {
    private final RedisCachingUserService redisCachingUserService;
    private final UserService userService;
    public RedisSearchController(RedisCachingUserService redisCachingUserService, UserService userService) {
        this.redisCachingUserService = redisCachingUserService;
        this.userService = userService;
    }

    @PostMapping("")
    public ResponseEntity<?> saveUser(@RequestBody UserDTO userDTO) {
        User savedUser = userService.saveUser(userDTO);
        User savedInCacheUser = redisCachingUserService.saveUserInCache(savedUser);
        return ResponseEntity.status(HttpStatus.CREATED).body("Saved user in db and cache: " + savedInCacheUser.getUsername());
    }

    @PutMapping("/{email}")
    public ResponseEntity<?> updateUser(@PathVariable String email, @RequestBody UserDTO userDTO) {
        User savedInCacheUser = redisCachingUserService.updateUserInCache(email, userDTO);
        User savedInDBUser = userService.updateUser(savedInCacheUser);
        return ResponseEntity.ok("Updated User in Cache and DB: " + savedInDBUser);
    }

    @GetMapping("")
    public ResponseEntity<?> getAllUsers() {
        List<User> foundUsers = redisCachingUserService.findAllUsers();
        return ResponseEntity.ok(foundUsers);
    }

    @GetMapping("/{email}")
    public ResponseEntity<?> findUserByEmail(@PathVariable String email) {
        User foundUser = redisCachingUserService.findUserInCache(email);
        return ResponseEntity.ok(foundUser);
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<?> deleteUser(@PathVariable String email) {
        String deleteMessage = redisCachingUserService.removeUserFromCache(email);
        return ResponseEntity.ok(deleteMessage);
    }









}
