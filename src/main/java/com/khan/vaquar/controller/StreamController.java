package com.khan.vaquar.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.khan.vaquar.exception.NotFoundException;
import com.khan.vaquar.model.Message;
import com.khan.vaquar.payload.ErrorResponse;
import com.khan.vaquar.repository.MessagesRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class StreamController {

	@Autowired
	private MessagesRepository messagesRepository;

	/**
	 * 
	 * @return
	 */
	@GetMapping("/messages")
	public Flux<Message> getAllMessages() {
		return messagesRepository.findAll();
	}

	/**
	 * 
	 * @param tweet
	 * @return
	 */
	@PostMapping("/messages")
	public Mono<Message> createMessage(@Valid @RequestBody Message message) {
		return messagesRepository.save(message);
	}

	/**
	 * 
	 * @param tweetId
	 * @return
	 */
	@GetMapping("/messages/{id}")
	public Mono<ResponseEntity<Message>> getMessageById(@PathVariable(value = "id") String messageId) {
		return messagesRepository.findById(messageId).map(savedMessage -> ResponseEntity.ok(savedMessage))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	/**
	 * 
	 * @param tweetId
	 * @param tweet
	 * @return
	 */
	@PutMapping("/messages/{id}")
	public Mono<ResponseEntity<Message>> updateMessage(@PathVariable(value = "id") String messageId,
			@Valid @RequestBody Message message) {
		return messagesRepository.findById(messageId).flatMap(existingMessage -> {
			existingMessage.setText(message.getText());
			return messagesRepository.save(existingMessage);
		}).map(updateTweet -> new ResponseEntity<>(updateTweet, HttpStatus.OK))
				.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	/**
	 * 
	 * @param tweetId
	 * @return
	 */
	@DeleteMapping("/messages/{id}")
	public Mono<ResponseEntity<Void>> deleteMessage(@PathVariable(value = "id") String messageId) {

		return messagesRepository.findById(messageId)
				.flatMap(existingMessage -> messagesRepository.delete(existingMessage)
						.then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK))))
				.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	/**
	 * Exception Handling Examples (These can be put into a @ControllerAdvice to
	 * handle exceptions globally)
	 */

	@ExceptionHandler(DuplicateKeyException.class)
	public ResponseEntity handleDuplicateKeyException(DuplicateKeyException ex) {
		return ResponseEntity.status(HttpStatus.CONFLICT)
				.body(new ErrorResponse("A messages with the same text already exists"));
	}

	/**
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity handleMessageNotFoundException(NotFoundException ex) {
		return ResponseEntity.notFound().build();
	}

}
