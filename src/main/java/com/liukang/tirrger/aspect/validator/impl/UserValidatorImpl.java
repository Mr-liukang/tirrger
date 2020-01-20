package com.liukang.tirrger.aspect.validator.impl;

import com.liukang.tirrger.aspect.validator.UserValidator;
import com.liukang.tirrger.pojo.User;


public class UserValidatorImpl implements UserValidator {

	@Override
	public boolean validate(User user) {
		System.out.println("引入新的接口："+ UserValidator.class.getSimpleName());
		return user != null;
	}

}
