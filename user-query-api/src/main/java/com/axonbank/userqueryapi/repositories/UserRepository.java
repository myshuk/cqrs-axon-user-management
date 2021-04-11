package com.axonbank.userqueryapi.repositories;

import com.axonbank.usercoreapi.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
