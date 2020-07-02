package com.njfu.zshop.service;

import com.njfu.zshop.entity.Sysuser;
import com.njfu.zshop.exception.SysuserNotExistException;
import com.njfu.zshop.params.SysuserParam;
import com.njfu.zshop.service.vo.SysuserVO;

import java.util.List;

/**
 * Author:CarreLiu
 * Date:2020-06-05 12:06
 * Description:<描述>
 */
public interface SysuserService {

    public List<Sysuser> findAll();

    public Sysuser findById(int id);

    public void add(SysuserVO sysuserVO);

    public boolean checkName(String loginName);

    public List<Sysuser> findByParams(SysuserParam sysuserParam);

    public void modify(SysuserVO sysuserVO);

    public void modifyStatus(int id);

    public Sysuser login(String loginName, String password) throws SysuserNotExistException;
}
