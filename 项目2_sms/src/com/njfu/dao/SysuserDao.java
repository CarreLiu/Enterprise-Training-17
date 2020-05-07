package com.njfu.dao;

import com.njfu.entity.Sysuser;
import com.njfu.entity.vo.SysuserVO;

public interface SysuserDao {

	Sysuser selectUserByUsernamePass(SysuserVO sysuserVO);
	
	Sysuser selectUserByIdAndPass(SysuserVO sysuserVO);

	public void updatePassById(SysuserVO sysuserVO);

	Sysuser selectByUsername(String username);
	
	public void insertSysuser(Sysuser sysuser);
	
}
