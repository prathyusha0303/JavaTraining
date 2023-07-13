package com.user.demo.entity;

import java.io.Serializable;

public class UserEntity implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
    	this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
