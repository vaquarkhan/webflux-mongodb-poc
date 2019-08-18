package com.khan.vaquar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.khan.vaquar.model.Message;
import com.khan.vaquar.repository.MessagesRepository;
import com.khan.vaquar.service.FluxDemoService;

import reactor.core.publisher.Flux;

/**
 * 
 * @author vaquar khan
 *
 */
@RestController
public class FluxDemoController {
	@Autowired
	private MessagesRepository msgRepository;
	
	@Autowired
	FluxDemoService swFluxDemoService;

	/**
	 * Messages are Sent to the client as Server Sent Events
	 * 
	 * @return
	 */

	@GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
	public Flux<String> pushEventSignal() {
		return swFluxDemoService.getStringStreem();
	}

	/**
	 * mogodb read
	 * 
	 * @return
	 */
	
	 @GetMapping(value = "/stream/messages", produces =MediaType.TEXT_EVENT_STREAM_VALUE)
	 public Flux<Message> streamAllMessages()
	 { 
		 return msgRepository.findAll();
		 }
	
}
