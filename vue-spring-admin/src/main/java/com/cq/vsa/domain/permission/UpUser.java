package com.cq.vsa.domain.permission;

import com.cq.vsa.enumobj.permission.UserLockStatus;
import com.cq.vsa.validator.annotation.IntegerEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 用户实体
 * @Author: chengqi
 * @Date: Created in 11:02 2019/4/8
 */
@Entity
@Table(name = "up_user")
@EntityListeners(AuditingEntityListener.class)
public class UpUser implements UserDetails, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Size(min = 5, max = 18, message = "The username must between 5 and 18 digits")
    private String userName;

    @NotEmpty
    @Size(max = 6, message = "The name con`t be more than 6 digits")
    private String name;

    @JsonIgnore
    private String password;

    @NotNull
    @IntegerEnum(value = {0,1}, message = "The isLock must in 0,1")
    private Integer isLock;

    @NotNull
    @IntegerEnum(value = {0,1}, message = "The sex must in 0,1")
    private Integer sex;

    @CreatedBy
    private String createdBy;

    @LastModifiedBy
    private String modifiedBy;

    @CreatedDate
    private Date createdTime;

    @LastModifiedDate
    private Date modifiedTime;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "up_user_role",
            joinColumns = @JoinColumn(name = "userId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "roleId", referencedColumnName = "id"))
    private List<UpRole> roles;

    @Transient
    private List<UpMenu> menus;

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Optional.of(roles).get().stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).
                collect(Collectors.toList());
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return userName;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return !UserLockStatus.isLock(isLock);
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }

    public List<UpMenu> getMenus() {
        if (menus == null || menus.size() == 0) {
            menus = new ArrayList<>();
            for (UpRole role: roles) {
                for (UpMenu pMenu: role.getMenus()) {
                    UpMenu firstMenu = menus.stream().filter(item -> item.getId().equals(pMenu.getId())).findFirst().orElse(null);
                    if (firstMenu == null) {
                        menus.add(pMenu);
                    } else {
                        for (UpMenu sMenu: pMenu.getChildren()) {
                            if (firstMenu.getChildren().stream().noneMatch(item -> item.getId().equals(sMenu.getId()))) {
                                firstMenu.getChildren().add(sMenu);
                            }
                        }
                    }
                }
            }
            menus = menus.stream().sorted(Comparator.comparingInt(UpMenu::getOrderNo)).collect(Collectors.toList());
            menus.forEach(item -> item.setChildren(item.getChildren().stream().sorted(Comparator.comparingInt(UpMenu::getOrderNo)).collect(Collectors.toList())));
        }
        return menus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getIsLock() {
        return isLock;
    }

    public void setIsLock(int isLock) {
        this.isLock = isLock;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public List<UpRole> getRoles() {
        return roles;
    }

    public void setRoles(List<UpRole> roles) {
        this.roles = roles;
    }
}
