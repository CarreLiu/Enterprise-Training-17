package com.njfu.zshop.dao;

import com.njfu.zshop.entity.Sysuser;
import com.njfu.zshop.params.SysuserParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author:CarreLiu
 * Date:2020-06-05 11:36
 * Description:<描述>
 */
@Repository
public interface SysuserDAO {

    public List<Sysuser> selectAll();

    public Sysuser selectById(int id);

    public void insert(Sysuser sysuser);

    public Sysuser selectByName(String loginName);

    public List<Sysuser> selectByParams(SysuserParam sysuserParam);

    public void update(Sysuser sysuser);

    public void updateStatus(@Param("id")int id, @Param("isValid")int isValid);

    public Sysuser selectByLoginNameAndPassword(@Param("loginName") String loginName, @Param("password") String password, @Param("isValid") Integer sysuserValid);
}
