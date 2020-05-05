package com.njfu.service;

import com.njfu.entity.Sysuser;
import com.njfu.entity.vo.SysuserVO;
import com.njfu.exception.OldPassWrongException;
import com.njfu.exception.UserOrPassWrongException;

public interface SysuserService {

	public Sysuser findUserByUsernamePass(SysuserVO sysuserVO) throws UserOrPassWrongException;
	
	public Sysuser findUserByIdAndPass(SysuserVO sysuserVO) throws OldPassWrongException;
	
	public void modifyPassById(SysuserVO sysuserVO);

	public void addSysuser(Sysuser sysuser);

}
