package com.example.shoppingcart;

public class UserRegistration {


    private String fname;
    private String lname;
    private String email;
    private String mobnum;
    private String password;


    public UserRegistration() {
    }

    public UserRegistration(String fname, String lname, String email, String mobnum, String password) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.mobnum = mobnum;
        this.password = password;
    }

    public String getMobnum() {
        return mobnum;
    }

    public void setMobnum(String mobnum) {
        this.mobnum = mobnum;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
