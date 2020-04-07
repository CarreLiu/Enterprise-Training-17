package com.njfu.service.impl;

import com.njfu.dao.SysuserDao;
import com.njfu.entity.Sysuser;
import com.njfu.exception.UserNotFoundException;
import com.njfu.exception.UserStatusException;
import com.njfu.factory.ObjectFactory;
import com.njfu.form.SysuserForm;
import com.njfu.service.SysuserService;

public class SysuserServiceImpl implements SysuserService {

	public Sysuser findByLoginNameAndLoginPassword(SysuserForm sysuserForm) throws UserNotFoundException, UserStatusException {
		// TODO Auto-generated method stub
		SysuserDao sysuserDao = (SysuserDao) ObjectFactory.getObject("sysuserDao");
		Sysuser user = sysuserDao.selectByLoginNameAndLoginPassword(sysuserForm);
		if (user == null) {
			throw new UserNotFoundException("用户名或密码错误");
		}
		
		return user;
	}
	
}
