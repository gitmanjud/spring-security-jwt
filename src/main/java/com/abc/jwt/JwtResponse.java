package com.abc.jwt;

public class JwtResponse {
	
	private  String jwttoken;
	private  String expiration_time;
	
	public String getJwttoken() {
		return jwttoken;
	}
	public void setJwttoken(String jwttoken) {
		this.jwttoken = jwttoken;
	}
	public String getExpiration_time() {
		return expiration_time;
	}
	public void setExpiration_time(String expiration_time) {
		this.expiration_time = expiration_time;
	}
	@Override
	public String toString() {
		return "JwtResponse [jwttoken=" + jwttoken + ", expiration_time=" + expiration_time + "]";
	}
	

}
