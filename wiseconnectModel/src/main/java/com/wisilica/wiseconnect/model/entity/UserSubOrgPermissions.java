package com.wisilica.wiseconnect.model.entity;
/**
 *  WiseConnect Entity UserSubOrgPermissions
 *
 *  @author Bhaskaran
 *  @Modified Josny Jose
 *  @Date  05-Apr-2019
 *
 * Description : For table tbl_user_sub_org_permissions
 *
 * */
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tbl_user_sub_org_permissions")
public class UserSubOrgPermissions implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long userId;

    private Long organizationId;

    private Long parentId;

    private Long permissionId;

    private double timeStamp;

    private Date createdTime;

    private Date lastUpdatedTime;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "user_id")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


    @Column(name = "organization_id")
    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    @Column(name = "parent_id")
    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Column(name = "permission_id")
    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    @Column(name = "timestamp")
    public double getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(double timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Column(name = "created_time")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    @Column(name = "last_updated")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(Date lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }
}
