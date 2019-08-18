package com.sse.kaizhong.bean;

public class Friend {
    private String collegeName;
    private String major;
    private String name;
    private String relationship;
    private String graduateSchool;//研究生学校
    private String graduateSchool_academy;//研究生学院
    private String graduateSchool_major;//研究生专业
    private String moreInfo;


    @Override
    public String toString() {
        return "Friend{" +
                "collegeName='" + collegeName + '\'' +
                ", major='" + major + '\'' +
                ", name='" + name + '\'' +
                ", relationship='" + relationship + '\'' +
                ", graduateSchool='" + graduateSchool + '\'' +
                ", graduateSchool_academy='" + graduateSchool_academy + '\'' +
                ", graduateSchool_major='" + graduateSchool_major + '\'' +
                ", moreInfo='" + moreInfo + '\'' +
                '}';
    }

    public String getGraduateSchool_academy() {
        return graduateSchool_academy;
    }

    public void setGraduateSchool_academy(String graduateSchool_academy) {
        this.graduateSchool_academy = graduateSchool_academy;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getGraduateSchool() {
        return graduateSchool;
    }

    public void setGraduateSchool(String graduateSchool) {
        this.graduateSchool = graduateSchool;
    }

    public String getGraduateSchool_major() {
        return graduateSchool_major;
    }

    public void setGraduateSchool_major(String graduateSchool_major) {
        this.graduateSchool_major = graduateSchool_major;
    }

    public String getMoreInfo() {
        return moreInfo;
    }

    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }
}
