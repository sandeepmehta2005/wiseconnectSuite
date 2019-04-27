package com.wisilica.wiseconnect.model.entity;
/**
 *  UserRole Entity
 *
 *  @author Josny Jose
 *  @Date  2-Apr-2019
 *
 * Description : Entity for tbl_user_role
 *
 * */
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tbl_user_role")
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long userRoleId;

    private int userId;

    private User user;

    private Long roleId;

    private Roles roles;

    @Id
    @GeneratedValue
    @Column(name = "user_role_id", nullable = false)
    public Long getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(Long userRoleId) {
        this.userRoleId = userRoleId;
    }

    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "role_id")
    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", insertable = false, updatable = false)
    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }
}
