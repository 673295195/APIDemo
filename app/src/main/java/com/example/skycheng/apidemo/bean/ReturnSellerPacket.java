package com.example.skycheng.apidemo.bean;

/**
 * Created by SkyCheng on 2017/10/11.
 */

public class ReturnSellerPacket {
    private int id;
    private String ca_nname;//鍟嗗鍚嶇О
    private String ca_cashcol;//浼氬憳鍚嶇О
    private String ca_amount;//閲戦
    private String ca_time;//鏃ユ湡
    private String ca_year;//骞�
    private String ca_months;//鏈�
    private String ca_dname;//鐢ㄦ潵鍌ㄥ瓨鐢ㄦ埛鍙戠孩鍖�

    private String ca_switchstate;//绾㈠寘鐘舵�0鏈紑 1鎵撳紑

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

    public String getCa_cashcol() {
        return ca_cashcol;
    }

    public void setCa_cashcol(String ca_cashcol) {
        this.ca_cashcol = ca_cashcol;
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
