package com.njfu.dao;

import com.njfu.entity.Sysuser;
import com.njfu.form.SysuserForm;

public interface SysuserDao {
	public Sysuser selectByLoginNameAndLoginPassword(SysuserForm sysuserForm);
}
