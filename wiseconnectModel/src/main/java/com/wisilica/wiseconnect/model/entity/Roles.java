package com.wisilica.wiseconnect.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *  WiseConnect Entity Roles
 *
 *  @author Josny Jose
 *  @Date  24-Mar-2019
 *
 * Description : For table tbl_roles
 *
 * */

@Entity
@Table(name = "tbl_roles")

public class Roles implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long roleId;

    private String roleName;

    private int roleValue;

    private int orgId;

    private int permissionId;

    private Date createdTime;

    private Date lastUpdated;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Column(name = "role_name")
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Column(name = "role_value")
    public int getRoleValue() {
        return roleValue;
    }

    public void setRoleValue(int roleValue) {
        this.roleValue = roleValue;
    }

    @Column(name = "org_id")
    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    @Column(name = "permission_id")
    public int getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(int permissionId) {
        this.permissionId = permissionId;
    }

    @Column(name = "created_time")
    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    @Column(name = "last_updated")
    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}


