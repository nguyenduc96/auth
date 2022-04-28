package com.example.authserver.controller;

import com.example.authserver.domain.model.UserPrinciple;
import com.example.authserver.helper.ResponseAPI;
import com.example.authserver.repository.IUserRepository;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/test")
public class Controller {

	@Autowired
	IUserRepository userRepository;

	@PostMapping ("/admin")
	public ResponseAPI<?> get(Authentication authentication) {
		try {
			UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
			return new ResponseAPI<>(HttpStatus.OK, userPrinciple);

		} catch (ExpiredJwtException ex) {
			return new ResponseAPI<>(HttpStatus.UNAUTHORIZED, ex);
		}
	}

	@PostMapping("/user")
	public ResponseAPI<?> get2(Authentication authentication) {
		try {
			return new ResponseAPI<>(HttpStatus.OK, userRepository.findAll());
		} catch (BadCredentialsException | ExpiredJwtException ex) {
			return new ResponseAPI<>(HttpStatus.UNAUTHORIZED, ex);
		}
	}
}
