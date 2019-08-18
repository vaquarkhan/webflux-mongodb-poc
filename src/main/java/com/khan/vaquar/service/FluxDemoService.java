package com.khan.vaquar.service;

import java.time.Duration;
import java.util.Date;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

/**
 * 
 * @author vaquar khan
 *
 */
@Service
public class FluxDemoService {

	public Flux<String> getStringStreem() {

		Flux<Long> interval = Flux.interval(Duration.ofSeconds(1));
		interval.subscribe((i) -> createStreamMessage());
		Flux<String> messageFlux = Flux.fromStream(Stream.generate(() -> createStreamMessage()));
		return Flux.zip(interval, messageFlux).map(Tuple2::getT2);

	}

	private String createStreamMessage() {
		Date theDate = new Date();
		String newMessage = "Webflux dummy Stream response at: " + theDate.toString() + "<BR>";
		return newMessage;

	}
	
	
}
