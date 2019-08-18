package com.khan.vaquar.exception;


public class NotFoundException extends RuntimeException {

    /**
	 * Custom business exception
	 */
	private static final long serialVersionUID = 1L;

	public NotFoundException(String tweetId) {
        super("Message not found with id " + tweetId);
    }
}
