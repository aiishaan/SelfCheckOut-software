package com.autovend.software.controllers;

public class AttendantController {
	// Handles the Operations of the attendant
	
	// Attendant user ID
	public String user_id;
	// Attendant Password
	public String password;
	
	/** Constructs an Attendant with user ID and Password 
	 * 
	 * 
	 * 
	 * @param username
	 * @param key
	 */
	
	public AttendantController(String username, String key) {
		this.user_id= username;
		this.password=key;
		
	}

}
