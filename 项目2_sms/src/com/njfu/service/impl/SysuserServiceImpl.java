package com.njfu.service.impl;

import com.njfu.dao.SysuserDao;
import com.njfu.entity.Sysuser;
import com.njfu.entity.vo.SysuserVO;
import com.njfu.exception.OldPassWrongException;
import com.njfu.exception.SysuserUsernameExistException;
import com.njfu.exception.UserOrPassWrongException;
import com.njfu.factory.ObjectFactory;
import com.njfu.service.SysuserService;

public class SysuserServiceImpl implements SysuserService {

	@Override
	public Sysuser findUserByUsernamePass(SysuserVO sysuserVO) throws UserOrPassWrongException {
		SysuserDao sysuserDao = (SysuserDao) ObjectFactory.getObject("sysuserDao");
		Sysuser sysuser = sysuserDao.selectUserByUsernamePass(sysuserVO);
		if (sysuser == null) {
			throw new UserOrPassWrongException("用户名或密码错误");
		}
		
		return sysuser;
	}
	
	@Override
	public Sysuser findUserByIdAndPass(SysuserVO sysuserVO) throws OldPassWrongException {
		SysuserDao sysuserDao = (SysuserDao)ObjectFactory.getObject("sysuserDao");
		Sysuser sysuser = sysuserDao.selectUserByIdAndPass(sysuserVO);
		if(sysuser == null){
			throw new OldPassWrongException("旧密码错误");
		}
		return sysuser;
	}

	@Override
	public void modifyPassById(SysuserVO sysuserVO) {
		SysuserDao sysuserDao = (SysuserDao)ObjectFactory.getObject("sysuserDao");
		sysuserDao.updatePassById(sysuserVO);
		
	}
	
	@Override
	public Sysuser findByUsername(String username) throws SysuserUsernameExistException {
		SysuserDao sysuserDao = (SysuserDao)ObjectFactory.getObject("sysuserDao");
		Sysuser sysuser = sysuserDao.selectByUsername(username);
		if (sysuser != null) {
			throw new SysuserUsernameExistException("用户名(" + username + ")已经被占用");
		}
		
		return sysuser;
	}

	@Override
	public void addSysuser(Sysuser sysuser) {
		SysuserDao sysuserDao = (SysuserDao)ObjectFactory.getObject("sysuserDao");
		sysuserDao.insertSysuser(sysuser);
	}

}
