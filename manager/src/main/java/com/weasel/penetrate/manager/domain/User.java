package com.weasel.penetrate.manager.domain;

import com.google.common.collect.Sets;
import com.weasel.penetrate.manager.infrastructure.helper.PasswordHelper;
import com.weasel.penetrate.manager.infrastructure.helper.SpringBeanHolder;
import com.weasel.penetrate.manager.infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Dylan
 * @date 2017/2/6.
 */
@Configurable(autowire = Autowire.BY_TYPE)
public class User implements Serializable{

    private Long id;
    private String name;
    private String nickName;
    private String password;
    private String email;
    private int device;
    private int totalDevice=3;
    private int loginCount;
    private boolean locked;
    private String createTime;
    private String loginTime;
    private String salt;
    private Set<Role> roles = Sets.newHashSet();

    @Autowired
    private transient UserRepository repository = SpringBeanHolder.getBean(UserRepository.class);

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public int getDevice() {
        return device;
    }

    public void setDevice(int device) {
        this.device = device;
    }

    public int getTotalDevice() {
        return totalDevice;
    }

    public void setTotalDevice(int totalDevice) {
        this.totalDevice = totalDevice;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


    public User save(){
        if(null != getId() && -1 != getId()){
            repository.update(this);
            return this;
        }
        repository.insert(this);
        return this;
    }

    public boolean notExist(){
        User u = repository.getUserByName(getName());
        if(null != u){
            return false;
        }
        u = repository.getUserByEmail(getEmail());
        if(null != u){
            return false;
        }
        return true;
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

    public User createSalt(){
        setSalt(PasswordHelper.createSalt());
        return this;
    }
    public User encodePassword(){
        setPassword(PasswordHelper.encrypt(getPassword(),getSalt()));
        return this;
    }
}
