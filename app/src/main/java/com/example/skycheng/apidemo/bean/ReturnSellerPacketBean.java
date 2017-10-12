package com.example.skycheng.apidemo.bean;

/**
 * Created by SkyCheng on 2017/10/11.
 */

public class ReturnSellerPacketBean {
    private int id;
    private String ca_nname;//商家名称
    private String ca_amount;//金额
    private String ca_time;//时间
    private String ca_year;//年份
    private String ca_months;//月份
    private String ca_dname;//会员名字
    private String ca_switchstate;//红包领取状态


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCa_nname() {
        return ca_nname;
    }

    public void setCa_nname(String ca_nname) {
        this.ca_nname = ca_nname;
    }


    public String getCa_amount() {
        return ca_amount;
    }

    public void setCa_amount(String ca_amount) {
        this.ca_amount = ca_amount;
    }

    public String getCa_time() {
        return ca_time;
    }

    public void setCa_time(String ca_time) {
        this.ca_time = ca_time;
    }

    public String getCa_switchstate() {
        return ca_switchstate;
    }

    public void setCa_switchstate(String ca_switchstate) {
        this.ca_switchstate = ca_switchstate;
    }

    public String getCa_year() {
        return ca_year;
    }

    public void setCa_year(String ca_year) {
        this.ca_year = ca_year;
    }

    public String getCa_months() {
        return ca_months;
    }

    public void setCa_months(String ca_months) {
        this.ca_months = ca_months;
    }

    public String getCa_dname() {
        return ca_dname;
    }

    public void setCa_dname(String ca_dname) {
        this.ca_dname = ca_dname;
    }

}
