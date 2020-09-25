package com.lhc.web.domain;

import java.io.Serializable;
import java.util.Date;

public class OrgOperationUser implements Serializable {
    private String oou_id;

    private String oou_carrier_id;

    private String oou_dept_id;

    private String oou_code;

    private String oou_name;

    private Short oou_sex;

    private String oou_password;

    private String oou_superior;

    private String oou_identity_card;

    private Date oou_birth;

    private String oou_phone;

    private String oou_mail;

    private Short oou_education;

    private Date oou_entry_date;

    private Date oou_departure_date;

    private Short oou_type;

    private Short oou_status;

    private String oou_create_by;

    private Date oou_create_time;

    private static final long serialVersionUID = 1L;

    public String getOou_id() {
        return oou_id;
    }

    public void setOou_id(String oou_id) {
        this.oou_id = oou_id == null ? null : oou_id.trim();
    }

    public String getOou_carrier_id() {
        return oou_carrier_id;
    }

    public void setOou_carrier_id(String oou_carrier_id) {
        this.oou_carrier_id = oou_carrier_id == null ? null : oou_carrier_id.trim();
    }

    public String getOou_dept_id() {
        return oou_dept_id;
    }

    public void setOou_dept_id(String oou_dept_id) {
        this.oou_dept_id = oou_dept_id == null ? null : oou_dept_id.trim();
    }

    public String getOou_code() {
        return oou_code;
    }

    public void setOou_code(String oou_code) {
        this.oou_code = oou_code == null ? null : oou_code.trim();
    }

    public String getOou_name() {
        return oou_name;
    }

    public void setOou_name(String oou_name) {
        this.oou_name = oou_name == null ? null : oou_name.trim();
    }

    public Short getOou_sex() {
        return oou_sex;
    }

    public void setOou_sex(Short oou_sex) {
        this.oou_sex = oou_sex;
    }

    public String getOou_password() {
        return oou_password;
    }

    public void setOou_password(String oou_password) {
        this.oou_password = oou_password == null ? null : oou_password.trim();
    }

    public String getOou_superior() {
        return oou_superior;
    }

    public void setOou_superior(String oou_superior) {
        this.oou_superior = oou_superior == null ? null : oou_superior.trim();
    }

    public String getOou_identity_card() {
        return oou_identity_card;
    }

    public void setOou_identity_card(String oou_identity_card) {
        this.oou_identity_card = oou_identity_card == null ? null : oou_identity_card.trim();
    }

    public Date getOou_birth() {
        return oou_birth;
    }

    public void setOou_birth(Date oou_birth) {
        this.oou_birth = oou_birth;
    }

    public String getOou_phone() {
        return oou_phone;
    }

    public void setOou_phone(String oou_phone) {
        this.oou_phone = oou_phone == null ? null : oou_phone.trim();
    }

    public String getOou_mail() {
        return oou_mail;
    }

    public void setOou_mail(String oou_mail) {
        this.oou_mail = oou_mail == null ? null : oou_mail.trim();
    }

    public Short getOou_education() {
        return oou_education;
    }

    public void setOou_education(Short oou_education) {
        this.oou_education = oou_education;
    }

    public Date getOou_entry_date() {
        return oou_entry_date;
    }

    public void setOou_entry_date(Date oou_entry_date) {
        this.oou_entry_date = oou_entry_date;
    }

    public Date getOou_departure_date() {
        return oou_departure_date;
    }

    public void setOou_departure_date(Date oou_departure_date) {
        this.oou_departure_date = oou_departure_date;
    }

    public Short getOou_type() {
        return oou_type;
    }

    public void setOou_type(Short oou_type) {
        this.oou_type = oou_type;
    }

    public Short getOou_status() {
        return oou_status;
    }

    public void setOou_status(Short oou_status) {
        this.oou_status = oou_status;
    }

    public String getOou_create_by() {
        return oou_create_by;
    }

    public void setOou_create_by(String oou_create_by) {
        this.oou_create_by = oou_create_by == null ? null : oou_create_by.trim();
    }

    public Date getOou_create_time() {
        return oou_create_time;
    }

    public void setOou_create_time(Date oou_create_time) {
        this.oou_create_time = oou_create_time;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", oou_id=").append(oou_id);
        sb.append(", oou_carrier_id=").append(oou_carrier_id);
        sb.append(", oou_dept_id=").append(oou_dept_id);
        sb.append(", oou_code=").append(oou_code);
        sb.append(", oou_name=").append(oou_name);
        sb.append(", oou_sex=").append(oou_sex);
        sb.append(", oou_password=").append(oou_password);
        sb.append(", oou_superior=").append(oou_superior);
        sb.append(", oou_identity_card=").append(oou_identity_card);
        sb.append(", oou_birth=").append(oou_birth);
        sb.append(", oou_phone=").append(oou_phone);
        sb.append(", oou_mail=").append(oou_mail);
        sb.append(", oou_education=").append(oou_education);
        sb.append(", oou_entry_date=").append(oou_entry_date);
        sb.append(", oou_departure_date=").append(oou_departure_date);
        sb.append(", oou_type=").append(oou_type);
        sb.append(", oou_status=").append(oou_status);
        sb.append(", oou_create_by=").append(oou_create_by);
        sb.append(", oou_create_time=").append(oou_create_time);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}