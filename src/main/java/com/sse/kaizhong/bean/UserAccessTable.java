package com.sse.kaizhong.bean;


import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @name: UserAccessTable
 * @author: xiangyf
 * @create: 2019-10-27 07:46
 * @description:
 */
public class UserAccessTable {

    private Integer id;
    private String ip;
    private String ipRealAddress;
    private String addressJson;
    private Date accessTime;
    private String keyWord;
    private String createdAt;
    private String possiblePersonName;
    private String userAgent;
    private String device;
    private String os;

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    //    public UserAccessTable() {
//    }

//    public UserAccessTable(String ip, String ipRealAddress, String keyWord, Time createdAt) {
//        this.ip = ip;
//        this.ipRealAddress = ipRealAddress;
//        this.keyWord = keyWord;
//        this.createdAt = createdAt;
//    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIpRealAddress() {
        return ipRealAddress;
    }

    public void setIpRealAddress(String ipRealAddress) {
        this.ipRealAddress = ipRealAddress;
    }

    public String getAddressJson() {
        return addressJson;
    }

    public void setAddressJson(String addressJson) {
        this.addressJson = addressJson;
    }

    public Date getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(Date accessTime) {
        this.accessTime = accessTime;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getPossiblePersonName() {
        return possiblePersonName;
    }

    public void setPossiblePersonName(String possiblePersonName) {
        this.possiblePersonName = possiblePersonName;
    }
}
