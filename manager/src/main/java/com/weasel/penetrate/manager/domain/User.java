package com.weasel.penetrate.manager.domain;

import com.google.common.collect.Sets;
import com.weasel.penetrate.manager.infrastructure.helper.PasswordHelper;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Dylan
 * @date 2017/2/6.
 */
public class User {

    private long id;
    private String name;
    private String nickName;
    private String password;
    private String email;
    private int loginCount;
    private boolean locked;
    private String createTime;
    private String loginTime;
    private String salt;
    private Set<Role> roles = Sets.newHashSet();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(int loginCount) {
        this.loginCount = loginCount;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


    /**
     * @return
     */
    public Set<String> getRolesAsString(){
        Set<String> roles = new HashSet<String>();
        for(Role role : getRoles()){
            roles.add(role.getCode());
        }
        return roles;
    }

    public User encodePassword(){
        setPassword(PasswordHelper.encrypt(getPassword(),getSalt()));
        return this;
    }
}
