package com.example.shoppingcart.Model;

public class Users {

    private String FirstName;
    private String LastName;
    private String Email;
    private String Phone;
    private String password;
    private String address;
    private String image;


    public Users() {
    }

    public Users(String firstName, String lastName, String email, String phone, String password, String address, String image) {
        FirstName = firstName;
        LastName = lastName;
        Email = email;
        Phone = phone;
        this.password = password;
        this.address = address;
        this.image = image;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
