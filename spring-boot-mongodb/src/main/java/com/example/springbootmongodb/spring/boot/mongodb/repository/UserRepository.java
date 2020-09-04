package com.example.springbootmongodb.spring.boot.mongodb.repository;

import com.example.springbootmongodb.spring.boot.mongodb.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
