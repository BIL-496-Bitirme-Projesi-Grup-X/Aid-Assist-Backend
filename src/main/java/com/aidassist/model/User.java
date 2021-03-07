package com.aidassist.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "\"User\"")
public class User {
    @Id
    private String nationalIdentityNo;
    private String name;
    private String surname;
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

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", nationalIdentityNo='" + nationalIdentityNo + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
