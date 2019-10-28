package com.abc.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRest {
	

	@RequestMapping({ "/user" })
	public String firstPage() {
		return "Hello new user";
	}
	

}
