package com.example.barberbookapp.domain;

public class Barber {
    private Integer barber_id;
    private String fName;
    private String lName;
    private String phoneNo;
    private String salon_no;

    public Integer getBarber_id() {
        return barber_id;
    }

    public void setBarber_id(Integer barber_id) {
        this.barber_id = barber_id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getSalon_no() {
        return salon_no;
    }

    public void setSalon_no(String salon_no) {
        this.salon_no = salon_no;
    }
}
