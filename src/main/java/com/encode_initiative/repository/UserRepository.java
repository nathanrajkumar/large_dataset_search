package com.encode_initiative.repository;

import com.encode_initiative.model.ESUser;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface UserRepository extends ElasticsearchRepository<ESUser, Long> {
}
