package com.berkaycumbur.pribastask.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.berkaycumbur.pribastask.model.User;

public interface UserRepo extends MongoRepository<User, Integer>{
}
