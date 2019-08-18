package com.khan.vaquar.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.khan.vaquar.model.Message;

@Repository("msgRepository")
public interface MessagesRepository extends ReactiveMongoRepository<Message, String> {

}
