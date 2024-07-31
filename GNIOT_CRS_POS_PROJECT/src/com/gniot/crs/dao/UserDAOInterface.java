package com.gniot.crs.dao;

import com.gniot.crs.bean.User;
/**
 * Interface for User Data Access Object.
 * This interface defines methods for user authentication and management.
 */
public interface UserDAOInterface {
	/**
     * Performs user login.
     * This method is responsible for authenticating users.
     */
	 void login();
	 /**
	     * Registers a new user.
	     * This method is responsible for adding a new user to the system.
	     */
	 void register();
	 /**
	     * Changes the password of a user.
	     * This method allows users to change their passwords.
	     */
	 void changePassword();
	 /**
	     * Finds a user by username.
	     * This method retrieves user information based on the username.
	     *
	     * @param username the username of the user to find
	     * @return the User object corresponding to the username, or null if not found
	     */
	 User findUserByUsername(String username) ;
}