package com.mgcoin.ar_department.lbs_redpacket.bean;

/**
 * Created by SkyCheng on 2017/10/12.
 */

public class CouponBean {
    private int id;
    private String p_nname;//商铺名称
    private String p_vname;//vip名称
    private String p_amount;//优惠券
    private String p_time;//日期
    private String p_switchstate;//状态
    private String p_months;//月

    private String p_year;//年

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getP_nname() {
        return p_nname;
    }

    public void setP_nname(String p_nname) {
        this.p_nname = p_nname;
    }

    public String getP_vname() {
        return p_vname;
    }

    public void setP_vname(String p_vname) {
        this.p_vname = p_vname;
    }

    public String getP_amount() {
        return p_amount;
    }

    public void setP_amount(String p_amount) {
        this.p_amount = p_amount;
    }

    public String getP_time() {
        return p_time;
    }

    public void setP_time(String p_time) {
        this.p_time = p_time;
    }

    public String getP_switchstate() {
        return p_switchstate;
    }

    public void setP_switchstate(String p_switchstate) {
        this.p_switchstate = p_switchstate;
    }

    public String getP_months() {
        return p_months;
    }

    public void setP_months(String p_months) {
        this.p_months = p_months;
    }

    public String getP_year() {
        return p_year;
    }

    public void setP_year(String p_year) {
        this.p_year = p_year;
    }
}
