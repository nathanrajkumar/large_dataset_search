package com.encode_initiative.commonusecaselargedatasetsearch.service;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@SuppressWarnings("UnstableApiUsage")
@Service
public class BloomFilterUserService {
    private static final int EXPECTED_INSERTIONS = 1000000;
    private static final double FALSE_POSITIVE_PROBABILITY = 0.01;

    private final BloomFilter<String> bloomFilter;
//    private final UserService userService = new UserService();
    private final UserService userService;

    public BloomFilterUserService(UserService userService) {
        this.userService = userService;
        // Initialize the Bloom filter
        bloomFilter = BloomFilter.create(Funnels.stringFunnel(StandardCharsets.UTF_8), EXPECTED_INSERTIONS, FALSE_POSITIVE_PROBABILITY);
    }

    public boolean userExists(String email) {
        // Check Bloom filter first
        if (bloomFilter.mightContain(email)) {
            // Potential false positive, check the database
            return userService.userExists(email);
        } else {
            // Definitely not in the set
            return false;
        }
    }

    public void addUser(String email) {
        // Add to Bloom filter
        bloomFilter.put(email);
    }
}
