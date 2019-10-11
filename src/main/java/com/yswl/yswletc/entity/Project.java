package com.yswl.yswletc.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;


@TableName(value = "t_projects")
public class Project {

    private Integer id;

    private String log;

    private String projectname;

    private String introduce;

    private Integer remainders;

    private BigDecimal commission;

    private String qrcodepath;

    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log == null ? null : log.trim();
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname == null ? null : projectname.trim();
    }

    public String getintroduce() {
        return introduce;
    }

    public void setintroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }

    public Integer getRemainders() {
        return remainders;
    }

    public void setRemainders(Integer remainders) {
        this.remainders = remainders;
    }

    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    public String getQrcodepath() {
        return qrcodepath;
    }

    public void setQrcodepath(String qrcodepath) {
        this.qrcodepath = qrcodepath == null ? null : qrcodepath.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}