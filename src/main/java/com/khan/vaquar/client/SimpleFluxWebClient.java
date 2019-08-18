package com.khan.vaquar.client;

import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;
/**
 * 
 * @author vaquar khan
 *
 */
public class SimpleFluxWebClient {
	public static void main(String[] args) throws InterruptedException {
		WebClient webClient = WebClient.create("http://localhost:9090");
		Mono<String> result = webClient.get().retrieve().bodyToMono(String.class);
		System.out.println("------------------------------------------------------------");
		System.out.println("-----------result-----------"+result.toString());
		System.out.println("------------------------------------------------------------");

		

	}

}
