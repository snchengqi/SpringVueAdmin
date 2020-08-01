package com.cq.vsa.domain.permission;

import com.cq.vsa.validator.annotation.IntegerEnum;
import com.cq.vsa.validator.annotation.MenuConstraint;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 菜单实体
 * @Author: chengqi
 * @Date: Created in 11:03 2019/4/8
 */
@MenuConstraint
@Entity
@Table(name = "up_menu")
@EntityListeners(AuditingEntityListener.class)
public class UpMenu implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer parentId;

    @NotEmpty
    private String name;

    @NotEmpty
    private String menuName;

    private String icon;

    private String path;

    @IntegerEnum({-1, 0, 1})
    private Integer alwaysShow;

    @IntegerEnum({-1, 0, 1})
    private Integer hidden;

    @IntegerEnum({-1, 0, 1})
    private Integer noCache;

    @NotNull
    @IntegerEnum(value = {1, 2}, nullAble = false)
    private Integer level;

    private Integer orderNo;

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
            name = "up_menu_backendapi",
            joinColumns = @JoinColumn(name = "menuId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "backendApiId", referencedColumnName = "id"))
    private List<UpBackendApi> backendApis;

    @Transient
    private List<UpMenu> children = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getAlwaysShow() {
        return alwaysShow;
    }

    public void setAlwaysShow(int alwaysShow) {
        this.alwaysShow = alwaysShow;
    }

    public Integer getHidden() {
        return hidden;
    }

    public void setHidden(int hidden) {
        this.hidden = hidden;
    }

    public Integer getNoCache() {
        return noCache;
    }

    public void setNoCache(int noCache) {
        this.noCache = noCache;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
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

    public List<UpBackendApi> getBackendApis() {
        return backendApis;
    }

    public void setBackendApis(List<UpBackendApi> backendApis) {
        this.backendApis = backendApis;
    }

    public List<UpMenu> getChildren() {
        return children;
    }

    public void setChildren(List<UpMenu> children) {
        this.children = children;
    }
}
