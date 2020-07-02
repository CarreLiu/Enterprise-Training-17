package com.njfu.zshop.dao;

import com.njfu.zshop.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author:CarreLiu
 * Date:2020-06-05 13:47
 * Description:<描述>
 */
@Repository
public interface RoleDAO {
    public List<Role> selectAll();
}
