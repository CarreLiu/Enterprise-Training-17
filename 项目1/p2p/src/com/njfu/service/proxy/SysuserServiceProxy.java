package com.njfu.service.proxy;

import com.njfu.entity.Sysuser;
import com.njfu.exception.DataAccessException;
import com.njfu.exception.ServiceException;
import com.njfu.exception.UserNotFoundException;
import com.njfu.exception.UserStatusException;
import com.njfu.factory.ObjectFactory;
import com.njfu.form.SysuserForm;
import com.njfu.service.SysuserService;
import com.njfu.transaction.TransactionManager;

public class SysuserServiceProxy implements SysuserService {

	public Sysuser findByLoginNameAndLoginPassword(SysuserForm sysuserForm) throws UserNotFoundException, UserStatusException {
		// TODO Auto-generated method stub
		TransactionManager tran = (TransactionManager) ObjectFactory.getObject("transaction");
		SysuserService sysuserService = (SysuserService) ObjectFactory.getObject("sysuserService");
		Sysuser user = null;
		
		try {
			tran.beginTransaction();
			user = sysuserService.findByLoginNameAndLoginPassword(sysuserForm);
			tran.commit();
		} catch (DataAccessException e) {
			tran.rollback();
			throw new ServiceException(e);
		} catch (UserNotFoundException e) {
			tran.rollback();
			throw e;
		} catch (UserStatusException e) {
			tran.rollback();
			throw e;
		}
		
		return user;
	}
}
