package com.aidassist.model;

import javax.persistence.Id;

public class UserDTO {

    @Id
    private String nationalIdentityNo;
    private String name;
    private String surname;

    public UserDTO() {}

    public UserDTO(User user) {
        this.nationalIdentityNo = user.getNationalIdentityNo();
        this.name = user.getName();
        this.surname = user.getSurname();
    }

    public String getNationalIdentityNo() {
        return nationalIdentityNo;
    }

    public void setNationalIdentityNo(String nationalIdentityNo) {
        this.nationalIdentityNo = nationalIdentityNo;
    }

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
}
