package com.example.skycheng.apidemo.bean;

/**
 * Created by SkyCheng on 2017/10/12.
 */

public class PacketFragmentBean {


    private int id;
    private String vi_nname;//商铺名称
    private String vi_vname;//vip名称
    private String vi_time;//日期
    private String vi_year;//年
    private String vi_months;//月
    private String vi_chipcol;//碎片

    private String vi_switchstate;//状态

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVi_nname() {
        return vi_nname;
    }

    public void setVi_nname(String vi_nname) {
        this.vi_nname = vi_nname;
    }

    public String getVi_vname() {
        return vi_vname;
    }

    public void setVi_vname(String vi_vname) {
        this.vi_vname = vi_vname;
    }

    public String getVi_time() {
        return vi_time;
    }

    public void setVi_time(String vi_time) {
        this.vi_time = vi_time;
    }

    public String getVi_year() {
        return vi_year;
    }

    public void setVi_year(String vi_year) {
        this.vi_year = vi_year;
    }

    public String getVi_months() {
        return vi_months;
    }

    public void setVi_months(String vi_months) {
        this.vi_months = vi_months;
    }

    public String getVi_chipcol() {
        return vi_chipcol;
    }

    public void setVi_chipcol(String vi_chipcol) {
        this.vi_chipcol = vi_chipcol;
    }

    public String getVi_switchstate() {
        return vi_switchstate;
    }

    public void setVi_switchstate(String vi_switchstate) {
        this.vi_switchstate = vi_switchstate;
    }
}