package com.example.skycheng.apidemo;

import java.io.Serializable;

/**
 * Created by SkyCheng on 2017/9/28.
 */

public class SellerBean implements Serializable {
    private  String ID;
    private  String name;
    private  double redPacket;
    private double lat;
    private double lon;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRedPacket() {
        return redPacket;
    }

    public void setRedPacket(double redPacket) {
        this.redPacket = redPacket;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
}
