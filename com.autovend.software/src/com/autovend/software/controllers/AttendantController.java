package com.autovend.software.controllers;
import java.util.HashMap;

public class AttendantController {
	// Handles the Operations of the attendant
	
	// Attendant user ID
	public String user_id;
	// Attendant Password
	public String password;
	
	// Attendant Lists
	public HashMap<String, String> AttendantList = new HashMap<String, String>();
	
	
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
	
	// Adds a new attendant to the system
	public void add_attendant(String username, String Key) {
		AttendantList.put(username, Key);
		
	}
	
	// Getter method for Attendant ID
	public String getUser_id() {
		return user_id;
	}

	public String getPassword() {
		return password;
	}

}
