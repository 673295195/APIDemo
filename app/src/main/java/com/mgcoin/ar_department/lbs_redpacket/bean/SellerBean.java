package com.mgcoin.ar_department.lbs_redpacket.bean;

import java.io.Serializable;

/**
 * Created by SkyCheng on 2017/9/28.
 */

public class SellerBean implements Serializable {


    private  int id;
    private String n_shops;//商铺名称
    private String n_envelope;//红包数

    private double n_location;//经纬度
    private double n_lat;
    /*@DateTimeFormat( pattern = "yyyy-MM-dd" )*/
    private String n_starttime;//活动开始时间
    /*@DateTimeFormat( pattern = "yyyy-MM-dd" )*/
    private String n_endtime;//活动结束日期
    private String n_picture;//商家头像
    private String n_redstate;//红包状态
    private String n_amount;//金额
    private String n_max;//红包最大值
    private String n_small;//最小值

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getN_shops() {
        return n_shops;
    }

    public void setN_shops(String n_shops) {
        this.n_shops = n_shops;
    }

    public String getN_envelope() {
        return n_envelope;
    }

    public void setN_envelope(String n_envelope) {
        this.n_envelope = n_envelope;
    }

    public double getN_location() {
        return n_location;
    }

    public void setN_location(double n_location) {
        this.n_location = n_location;
    }

    public double getN_lat() {
        return n_lat;
    }

    public void setN_lat(double n_lat) {
        this.n_lat = n_lat;
    }

    public String getN_starttime() {
        return n_starttime;
    }

    public void setN_starttime(String n_starttime) {
        this.n_starttime = n_starttime;
    }

    public String getN_endtime() {
        return n_endtime;
    }

    public void setN_endtime(String n_endtime) {
        this.n_endtime = n_endtime;
    }

    public String getN_picture() {
        return n_picture;
    }

    public void setN_picture(String n_picture) {
        this.n_picture = n_picture;
    }

    public String getN_redstate() {
        return n_redstate;
    }

    public void setN_redstate(String n_redstate) {
        this.n_redstate = n_redstate;
    }

    public String getN_amount() {
        return n_amount;
    }

    public void setN_amount(String n_amount) {
        this.n_amount = n_amount;
    }

    public String getN_max() {
        return n_max;
    }

    public void setN_max(String n_max) {
        this.n_max = n_max;
    }

    public String getN_small() {
        return n_small;
    }

    public void setN_small(String n_small) {
        this.n_small = n_small;
    }
}
