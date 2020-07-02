package com.njfu.zshop.entity;

import java.io.Serializable;

/**
 * Author:CarreLiu
 * Date:2020-06-05 11:33
 * Description:<描述>
 */
public class Role implements Serializable {

    private Integer id;

    private String roleName;

    public Role() {
    }

    public Role(Integer id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
