package com.njfu.service.proxy;

import com.njfu.entity.Sysuser;
import com.njfu.entity.vo.SysuserVO;
import com.njfu.exception.DataAccessException;
import com.njfu.exception.OldPassWrongException;
import com.njfu.exception.ServiceException;
import com.njfu.exception.UserOrPassWrongException;
import com.njfu.factory.ObjectFactory;
import com.njfu.service.SysuserService;
import com.njfu.transaction.TransactionManager;

public class SysuserProxy implements SysuserService {

	@Override
	public Sysuser findUserByUsernamePass(SysuserVO sysuserVO) throws UserOrPassWrongException {
		
		TransactionManager tran = (TransactionManager) ObjectFactory.getObject("transaction");
		SysuserService sysuserService = (SysuserService) ObjectFactory.getObject("sysuserService");
		Sysuser sysuser = null;
		try {
			tran.beginTransaction();
			sysuser = sysuserService.findUserByUsernamePass(sysuserVO);
			tran.commit();
		} catch (UserOrPassWrongException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			tran.rollback();
			throw e;
		}
		return sysuser;
	}
	
	@Override
	public Sysuser findUserByIdAndPass(SysuserVO sysuserVO) throws OldPassWrongException {
		TransactionManager tran = (TransactionManager)ObjectFactory.getObject("transaction");
		SysuserService sysuserService = (SysuserService)ObjectFactory.getObject("sysuserService");
		Sysuser sysuser = null;
		try {
			tran.beginTransaction();
			sysuser = sysuserService.findUserByIdAndPass(sysuserVO);
			tran.commit();
		} 
        catch (DataAccessException e) {
        	tran.rollback();
        	throw new ServiceException(e.getMessage());
		}		
		catch (OldPassWrongException e) {
			tran.rollback();
			throw e;
		}
		return sysuser;
		
	}

	@Override
	public void modifyPassById(SysuserVO sysuserVO) {
		TransactionManager tran = (TransactionManager)ObjectFactory.getObject("transaction");
		SysuserService sysuserService = (SysuserService)ObjectFactory.getObject("sysuserService");
		
		try {
			tran.beginTransaction();
			sysuserService.modifyPassById(sysuserVO);
			tran.commit();
		} catch (DataAccessException e) {
			tran.rollback();
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void addSysuser(Sysuser sysuser) {
		TransactionManager tran = (TransactionManager)ObjectFactory.getObject("transaction");
		SysuserService sysuserService = (SysuserService)ObjectFactory.getObject("sysuserService");
		
		try {
			tran.beginTransaction();
			sysuserService.addSysuser(sysuser);
			tran.commit();
		} catch (DataAccessException e) {
			tran.rollback();
			throw new ServiceException(e.getMessage());
		}
	}

}
