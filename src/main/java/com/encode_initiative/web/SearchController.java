package com.encode_initiative.web;

import com.encode_initiative.service.BloomFilterUserService;
import com.encode_initiative.service.CachingBloomFilterUserService;
import com.encode_initiative.service.CachingUserService;
import com.encode_initiative.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
public class SearchController {

    private final UserService userService;
    private final CachingUserService cachingUserService;
    private final BloomFilterUserService bloomFilterUserService;
    private final CachingBloomFilterUserService cachingBloomFilterUserService;

    public SearchController(UserService userService, CachingUserService cachingUserService, BloomFilterUserService bloomFilterUserService, CachingBloomFilterUserService cachingBloomFilterUserService) {
        this.userService = userService;
        this.cachingUserService = cachingUserService;
        this.bloomFilterUserService = bloomFilterUserService;
        this.cachingBloomFilterUserService = cachingBloomFilterUserService;
    }

    @GetMapping("/direct/{email}")
    public ResponseEntity<?> getDataFromDirectDBQuery(@PathVariable String email) {
        userService.userExists("testuser@test.com");
        return ResponseEntity.ok("This is a simulated db query.  This will return a sequential direct to db search for the provided user" + email);
    }


    @GetMapping("/caching/{email}")
    public ResponseEntity<?> getDataFromConcurrentHashMapDataStructureCache(@PathVariable String email) {
        boolean userExists = cachingUserService.userExists(email);
        return ResponseEntity.ok("User in cache found? " + userExists);

    }

//    @GetMapping("/caching/{email}")
//    public ResponseEntity<?> getDataFromRedisCache() {
//
//    }

    @GetMapping("/bloomfilter/{email}")
    public ResponseEntity<?> getDataFromBloomFilter(@PathVariable String email) {
        boolean userExists = bloomFilterUserService.userExists(email);
        return ResponseEntity.ok("Does user exist?" + userExists);
    }

    @GetMapping("/combined/{email}")
    public ResponseEntity<?> getDataFromDirectDBQueryRedisAndBloomFilter(@PathVariable String email) {
        boolean userExists = cachingBloomFilterUserService.userExists(email);
        return ResponseEntity.ok("Does the username exist? " + userExists);
    }
}
