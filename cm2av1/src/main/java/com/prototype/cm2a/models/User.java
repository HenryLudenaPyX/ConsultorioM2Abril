package com.prototype.cm2a.models;

public class User {
    private String idNumber;
    private String names;
    private String surnames;
    private String gender;
    private String phone;
    private String email;
    private String address;
    private String profession;
    private int experienceYear;
    private String username;
    private String role;
    private String password;
    private String status;

    public User() {}

    public User(String idNumber, String names, String surnames, String gender, String phone, String email,
                String address, String profession, int experienceYear, String username, String role, String password, String status) {
        this.idNumber = idNumber;
        this.names = names;
        this.surnames = surnames;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.profession = profession;
        this.experienceYear = experienceYear;
        this.username = username;
        this.role = role;
        this.password = password;
        this.status = status;
    }

    public User(String names, String surnames, String profession, String role){
        this.names = names;
        this.surnames = surnames;
        this.profession = profession;
        this.role = role;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getSurnames() {
        return surnames;
    }

    public void setSurnames(String surnames) {
        this.surnames = surnames;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public int getExperienceYear() {
        return experienceYear;
    }

    public void setExperienceYear(int experienceYear) {
        this.experienceYear = experienceYear;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
