package com.aidassist.model;

public class User {
    private String name;
    private String surname;
    private String nationalIdentityNo;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getNationalIdentityNo() {
        return nationalIdentityNo;
    }

    public void setNationalIdentityNo(String nationalIdentityNo) {
        this.nationalIdentityNo = nationalIdentityNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
