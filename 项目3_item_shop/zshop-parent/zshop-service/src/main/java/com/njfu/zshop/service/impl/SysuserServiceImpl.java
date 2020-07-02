package com.njfu.zshop.service.impl;

import com.njfu.zshop.constants.Constant;
import com.njfu.zshop.dao.SysuserDAO;
import com.njfu.zshop.entity.Role;
import com.njfu.zshop.entity.Sysuser;
import com.njfu.zshop.exception.SysuserNotExistException;
import com.njfu.zshop.params.SysuserParam;
import com.njfu.zshop.service.SysuserService;
import com.njfu.zshop.service.vo.SysuserVO;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

/**
 * Author:CarreLiu
 * Date:2020-06-05 12:06
 * Description:<描述>
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class SysuserServiceImpl implements SysuserService {

    @Autowired
    private SysuserDAO sysuserDAO;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Sysuser> findAll() {
        return sysuserDAO.selectAll();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Sysuser findById(int id) {
        return sysuserDAO.selectById(id);
    }

    @Override
    public void add(SysuserVO sysuserVO) {
        Sysuser sysuser = new Sysuser();
        try {
            PropertyUtils.copyProperties(sysuser, sysuserVO);
            sysuser.setIsValid(Constant.SYSUSER_VALID);
            sysuser.setCreateDate(new Date());
            Role role = new Role();
            role.setId(sysuserVO.getRoleId());
            sysuser.setRole(role);
            sysuserDAO.insert(sysuser);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean checkName(String loginName) {
        Sysuser sysuser = sysuserDAO.selectByName(loginName);
        if (sysuser != null)
            return false;
        return true;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Sysuser> findByParams(SysuserParam sysuserParam) {
        return sysuserDAO.selectByParams(sysuserParam);
    }

    @Override
    public void modify(SysuserVO sysuserVO) {
        Sysuser sysuser = new Sysuser();
        try {
            PropertyUtils.copyProperties(sysuser, sysuserVO);
            Role role = new Role();
            role.setId(sysuserVO.getRoleId());
            sysuser.setRole(role);
            sysuserDAO.update(sysuser);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void modifyStatus(int id) {
        Sysuser sysuser = sysuserDAO.selectById(id);
        Integer isValid = sysuser.getIsValid();
        if (isValid == Constant.SYSUSER_VALID) {
            isValid = Constant.SYSUSER_INVALID;
        }
        else {
            isValid = Constant.SYSUSER_VALID;
        }
        sysuserDAO.updateStatus(id, isValid);
    }

    @Override
    public Sysuser login(String loginName, String password) throws SysuserNotExistException {
        Sysuser sysuser = sysuserDAO.selectByLoginNameAndPassword(loginName, password, Constant.SYSUSER_VALID);
        if (sysuser != null) {
            return sysuser;
        }
        throw new SysuserNotExistException("用户名或密码不正确");
    }

}
