package com.retrofit.workingschedule.bean;

/**
 * Created by admin on /10/21/0021.
 * 跟新时间表
 */

public class UpdateTimeBean {
    private String token;
    private String monday_plan;
    private String tuesday_plan;
    private String wednesday_plan;
    private String thursday_plan;
    private String friday_plan;
    private String saturday_plan;
    private String sunday_plan;
    private String code;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMonday_plan() {
        return monday_plan;
    }

    public void setMonday_plan(String monday_plan) {
        this.monday_plan = monday_plan;
    }

    public String getTuesday_plan() {
        return tuesday_plan;
    }

    public void setTuesday_plan(String tuesday_plan) {
        this.tuesday_plan = tuesday_plan;
    }

    public String getWednesday_plan() {
        return wednesday_plan;
    }

    public void setWednesday_plan(String wednesday_plan) {
        this.wednesday_plan = wednesday_plan;
    }

    public String getThursday_plan() {
        return thursday_plan;
    }

    public void setThursday_plan(String thursday_plan) {
        this.thursday_plan = thursday_plan;
    }

    public String getFriday_plan() {
        return friday_plan;
    }

    public void setFriday_plan(String friday_plan) {
        this.friday_plan = friday_plan;
    }

    public String getSaturday_plan() {
        return saturday_plan;
    }

    public void setSaturday_plan(String saturday_plan) {
        this.saturday_plan = saturday_plan;
    }

    public String getSunday_plan() {
        return sunday_plan;
    }

    public void setSunday_plan(String sunday_plan) {
        this.sunday_plan = sunday_plan;
    }
}
