package com.ap.advisor.data;


import java.io.Serializable;

public class Advisor implements Serializable {
    private String name;
    private String advisorType;
    private String mobileNumber;

    public Advisor() {

    }

    public Advisor(String advisorType, String name, String mobileNumber) {
        this.advisorType = advisorType;
        this.name = name;
        this.mobileNumber = mobileNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdvisorType() {
        return advisorType;
    }

    public void setAdvisorType(String advisorType) {
        this.advisorType = advisorType;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
