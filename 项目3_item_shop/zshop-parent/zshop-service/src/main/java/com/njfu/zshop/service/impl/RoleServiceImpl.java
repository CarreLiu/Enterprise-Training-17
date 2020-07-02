package com.njfu.zshop.service.impl;

import com.njfu.zshop.dao.RoleDAO;
import com.njfu.zshop.entity.Role;
import com.njfu.zshop.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Author:CarreLiu
 * Date:2020-06-05 13:42
 * Description:<描述>
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDAO roleDAO;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Role> findAll() {
        return roleDAO.selectAll();
    }
}
