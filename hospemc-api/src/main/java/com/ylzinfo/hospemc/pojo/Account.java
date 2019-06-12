package com.ylzinfo.hospemc.pojo;

import java.io.Serializable;

/**
 * (Account)实体类
 *
 * @author lmr
 * @since 2019-01-08 21:09:08
 */
public class Account implements Serializable {
        
    private Integer id;
    
    private String userName;
    
    private String passWord;
    
    private String constellation;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getConstellation() {
        return constellation;
    }

    public void setConstellation(String constellation) {
        this.constellation = constellation;
    }

}