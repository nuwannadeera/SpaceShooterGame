package com.main.signup;

import com.main.api.API;

public class Register {
	boolean check(String username, String passwd, String conpaswd) { 
		if( !passwd.isEmpty() && passwd.equals(conpaswd) && !username.isEmpty() && !conpaswd.isEmpty()) {
			API api = API.getInstance();
			return api.playerRegister(username, passwd);
		} else {
			return false; 
		}
	}
}
