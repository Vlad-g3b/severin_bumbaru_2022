package com.antii.IntershipREST.models;

public class LoginUser extends User implements IUserDetails {
	private static final long serialVersionUID = -5855811901193739535L;

	public LoginUser(int id, String email, String password, String role) {
		super(id, email, password, role);
	}

	public LoginUser(int id, String email, String password) {
		super(id, email, password, null);
	}
	public LoginUser(User user) {
		super(user.getId(),user.getUsername(), user.getPassword(), user.getRole());
	}
	public LoginUser() {}

}
