package com.example.skycheng.apidemo.bean;

/**
 * Created by SkyCheng on 2017/9/28.
 */

public class BuyerBean {
    private int id;//会员的编号
    private String v_name; //会员姓名
    private String v_sex; //会员性别
    private String vl_id; //会员等级
    private String v_phone; //会员电话
    private String v_birthday;//会员生日
    private String v_address;//家庭住址
    private String v_company;//公司
    private String v_score;//会员积分
    private String v_status;//会员状态
    private String v_documents;//证件

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getV_name() {
        return v_name;
    }

    public void setV_name(String v_name) {
        this.v_name = v_name;
    }

    public String getV_sex() {
        return v_sex;
    }

    public void setV_sex(String v_sex) {
        this.v_sex = v_sex;
    }

    public String getVl_id() {
        return vl_id;
    }

    public void setVl_id(String vl_id) {
        this.vl_id = vl_id;
    }

    public String getV_phone() {
        return v_phone;
    }

    public void setV_phone(String v_phone) {
        this.v_phone = v_phone;
    }

    public String getV_birthday() {
        return v_birthday;
    }

    public void setV_birthday(String v_birthday) {
        this.v_birthday = v_birthday;
    }

    public String getV_address() {
        return v_address;
    }

    public void setV_address(String v_address) {
        this.v_address = v_address;
    }

    public String getV_company() {
        return v_company;
    }

    public void setV_company(String v_company) {
        this.v_company = v_company;
    }

    public String getV_score() {
        return v_score;
    }

    public void setV_score(String v_score) {
        this.v_score = v_score;
    }

    public String getV_status() {
        return v_status;
    }

    public void setV_status(String v_status) {
        this.v_status = v_status;
    }

    public String getV_documents() {
        return v_documents;
    }

    public void setV_documents(String v_documents) {
        this.v_documents = v_documents;
    }

    public String getV_balance() {
        return v_balance;
    }

    public void setV_balance(String v_balance) {
        this.v_balance = v_balance;
    }

    public String getV_Email() {
        return v_Email;
    }

    public void setV_Email(String v_Email) {
        this.v_Email = v_Email;
    }

    public String getV_registrationtime() {
        return v_registrationtime;
    }

    public void setV_registrationtime(String v_registrationtime) {
        this.v_registrationtime = v_registrationtime;
    }

    public String getV_lasttime() {
        return v_lasttime;
    }

    public void setV_lasttime(String v_lasttime) {
        this.v_lasttime = v_lasttime;
    }

    public String getV_amount() {
        return v_amount;
    }

    public void setV_amount(String v_amount) {
        this.v_amount = v_amount;
    }

    private String v_balance;//余额
    private String v_Email;//电子邮件
    private String v_registrationtime;//注册时间
    private String v_lasttime;//上次上线时间
    private String v_amount;//会员头像
}
