package com.encode_initiative.commonusecaselargedatasetsearch.repository;

import com.encode_initiative.commonusecaselargedatasetsearch.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchUserRepository extends JpaRepository<User, Long> {

}
