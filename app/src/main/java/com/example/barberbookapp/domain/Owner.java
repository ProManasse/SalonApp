package com.example.barberbookapp.domain;


public class Owner {
    private Integer owner_id;
    private String fName;
    private String lName;
    private String phoneNo;
    private String password;

	public Integer getId() {
		return owner_id;
	}
	public void setId(Integer id) {
		this.owner_id = id;
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
	public Integer getOwner_id() {
		return owner_id;
	}
	public void setOwner_id(Integer owner_id) {
        this.owner_id = owner_id;
    }

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
