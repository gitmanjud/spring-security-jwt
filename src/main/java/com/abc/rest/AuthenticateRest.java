package com.abc.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.abc.jwt.JwtRequest;
import com.abc.jwt.JwtResponse;
import com.abc.jwt.JwtUserDetail;
import com.abc.jwt.JwtUtil;

@RestController
@RequestMapping(value = "/authenticate")
public class AuthenticateRest {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUserDetail userDetailsService;
	
	@Autowired
	private JwtUtil jwtTokenUtil;
	
	
	@RequestMapping( method = RequestMethod.POST)
	public JwtResponse  createAuthenticationToken(@RequestBody JwtRequest jwtRequest) throws Exception {
		authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());

		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(jwtRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);
		
		JwtResponse jwtResponse =  new JwtResponse();
		jwtResponse.setJwttoken(token);
		jwtResponse.setExpiration_time(jwtTokenUtil.getExpirationDateFromToken(token).toString());
		return jwtResponse;
	}
	
	
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

}
