package com.example.authserver.helper;


import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHelper {

	@ExceptionHandler(TokenRefreshException.class)
	public ResponseAPI<?> handleTokenRefreshException(TokenRefreshException ex) {
		return new ResponseAPI<>(HttpStatus.FORBIDDEN, ex);
	}

	@ExceptionHandler(ExpiredJwtException.class)
	public ResponseAPI<?> expiredException(ExpiredJwtException e) {
		return new ResponseAPI<>(HttpStatus.UNAUTHORIZED, e);
	}
}
