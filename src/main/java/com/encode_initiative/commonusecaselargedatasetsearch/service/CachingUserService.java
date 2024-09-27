package com.encode_initiative.commonusecaselargedatasetsearch.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class CachingUserService {
    private final ConcurrentHashMap<String, Boolean> cache = new ConcurrentHashMap<>();

    private final UserService userService;

    public CachingUserService(UserService userService) {
        this.userService = userService;
    }

    public boolean userExists(String email) {
        // Check cache first
        if (cache.containsKey(email)) {
            return cache.get(email);
        }

        // Fallback to database check
        boolean exists = userService.userExists(email);
        // Store result in cache
        cache.put(email, exists);
        return exists;
    }

}
