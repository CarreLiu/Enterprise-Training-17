package com.njfu.service;

import com.njfu.entity.Sysuser;
import com.njfu.exception.UserNotFoundException;
import com.njfu.exception.UserStatusException;
import com.njfu.form.SysuserForm;

public interface SysuserService {
	//登录
	public Sysuser findByLoginNameAndLoginPassword(SysuserForm sysuserForm) throws UserNotFoundException, UserStatusException;
	
}
