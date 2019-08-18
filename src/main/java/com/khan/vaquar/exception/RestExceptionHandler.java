package com.khan.vaquar.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.khan.vaquar.payload.ErrorResponse;

@RestControllerAdvice
public class RestExceptionHandler {

	private static final Logger log = LoggerFactory.getLogger(RestExceptionHandler.class);

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = EmptyResultDataAccessException.class)
	public ErrorResponse handleEmptyResultDataAccessException(HttpServletRequest request,
			EmptyResultDataAccessException e) {
		log.error("RestException Handler=>" + e.getMessage(), e);
		// log.error(prinStackTrace(e));
		return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "No records found");
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = JsonProcessingException.class)
	public ErrorResponse handleJsonProcessingException(HttpServletRequest request, JsonProcessingException e) {
		log.error("RestException Handler=>" + e.getMessage(), e);
		// log.error(prinStackTrace(e));
		return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getOriginalMessage());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = IllegalArgumentException.class)
	public ErrorResponse handleIllegalArgumentException(HttpServletRequest request, IllegalArgumentException e) {
		log.error("RestException Handler=>" + e.getMessage(), e);
		// log.error(prinStackTrace(e));
		return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = UnsupportedOperationException.class)
	public ErrorResponse handleUnsupportedOperationException(HttpServletRequest request,
			UnsupportedOperationException e) {
		log.error("RestException Handler=>" + e.getMessage(), e);
		// log.error(prinStackTrace(e));
		return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = MissingServletRequestParameterException.class)
	public ErrorResponse handleMissingServletRequestParameterException(HttpServletRequest request,
			MissingServletRequestParameterException e) {
		log.error("RestException Handler=>" + e.getMessage(), e);
		// log.error(prinStackTrace(e));
		return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = NoHandlerFoundException.class)
	public ErrorResponse handleNoHandlerFoundException(HttpServletRequest request, NoHandlerFoundException e) {
		log.error("RestException Handler=>" + e.getMessage(), e);
		return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = NotFoundException.class)
	public ErrorResponse handleTweetNotFoundException(HttpServletRequest request, Exception e) {
		log.error("RestException Handler=>" + e.getMessage(), prinStackTrace(e));
		// log.error(prinStackTrace(e));
		return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
	}

	/**
	 * 
	 * @param request
	 * @param e
	 * @return
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = Exception.class)
	public ErrorResponse handleException(HttpServletRequest request, Exception e) {
		log.error("RestException Handler=>" + e.getMessage(), e);
		// log.error(prinStackTrace(e));
		return new ErrorResponse(HttpStatus.OK.value(), e.getMessage());
	}

	/**
	 * converting exception stack trace to String
	 * @param exception
	 * @return
	 */
	private String prinStackTrace(Exception exception) {
		if (null == exception) {
			return null;
		}
		StringWriter errors = new StringWriter();
		exception.printStackTrace(new PrintWriter(errors));
		return errors.toString();
	}

}
