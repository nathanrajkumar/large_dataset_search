package com.encode_initiative.commonusecaselargedatasetsearch.redis.redisrepository;

import com.encode_initiative.commonusecaselargedatasetsearch.model.User;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository("userRedisDAO")
public interface UserRedisDAO extends CrudRepository<User, Long> {
    User findUserByUsername(String email);
}
