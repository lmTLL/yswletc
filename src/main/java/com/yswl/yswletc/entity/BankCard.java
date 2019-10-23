package com.yswl.yswletc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * User: jang
 * Date: 2019/10/22
 * Time: 15:11
 * Description: No Description
 */
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "t_BankCard")
public class BankCard {

    @TableId(type = IdType.AUTO)
    private Integer id;//id

    private Integer uid;//用户id属于谁

    private String uname; //用户姓名

    private String name; //持卡人姓名

    @TableField(value = "bankCardNumber")
    private String  bankcardnumber; //银行卡卡号

    @TableField(value = "validDate")
    private String validdate; //银行卡有效期

    @TableField(value = "bankCardType")
    private String bankcardtype;//银行卡类型

    @TableField(value = "bankName")
    private String bankname; //银行名

    @TableField(value = "creationTime")
    private Date creationtime; //绑卡时间

    @TableField(value = "cardPath")
    private String cardpath; //图片路径

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBankcardnumber() {
        return bankcardnumber;
    }

    public void setBankcardnumber(String bankcardnumber) {
        this.bankcardnumber = bankcardnumber;
    }

    public String getValiddate() {
        return validdate;
    }

    public void setValiddate(String validdate) {
        this.validdate = validdate;
    }

    public String getBankcardtype() {
        return bankcardtype;
    }

    public void setBankcardtype(String bankcardtype) {
        this.bankcardtype = bankcardtype;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public Date getCreationtime() {
        return creationtime;
    }

    public void setCreationtime(Date creationtime) {
        this.creationtime = creationtime;
    }

    public String getCardpath() {
        return cardpath;
    }

    public void setCardpath(String cardpath) {
        this.cardpath = cardpath;
    }
}