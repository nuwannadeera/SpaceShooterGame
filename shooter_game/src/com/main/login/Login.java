package com.main.login;

import com.main.api.API;
import com.main.entity.User;

public class Login {
	boolean checkPassword(String username, String passwd) {
		User user = new User(username, passwd);
		API api = API.getInstance();
		return api.playerLogin(user);

	}
}