package com.codegym.furamaresortmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Person {
    @NotBlank
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_of_birth", columnDefinition = "DATE")
    private LocalDate birthday;
    private String gender;
    @Column(name = "personal_id")
    @Pattern(regexp = "(^\\d{9}$)||(^\\d{12}$)", message = "Invalid Personal ID: Valid Personal ID should be 9 or 12 digit number! ")
    @NotBlank
    private String personalID;
    @Pattern(regexp = "(^09[01]\\d{7}$)||(\\(^84\\)\\+9[01]\\d{7}$)",
            message = "Invalid phone number: Valid Phone number must starts with 090/091 or (84)+90/91 and should be 10 digit number! ")
    @NotBlank
    private String phoneNumber;
    @NotBlank
    @Pattern(regexp = "[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)",
            message = "Invalid email: Valid email only contain latin letters, numbers, '@' and '.' ! ")
    private String email;
    @NotBlank
    private String address;
    @Column(name = "status",columnDefinition = "INT default 1")
    private int status;

    public Person() {
    }

    public Person(@NotBlank String name, @NotNull LocalDate birthday, @NotBlank String gender, @Pattern(regexp = "(^\\d{9}$)||(^\\d{12}$)") @NotBlank String personalID, @Pattern(regexp = "(^09[01]\\d{7}$)||(\\(^84\\)\\+9[01]\\d{7}$)") @NotBlank String phoneNumber, @NotBlank @Pattern(regexp = "[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)") String email, @NotBlank String address, int status) {
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.personalID = personalID;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPersonalID() {
        return personalID;
    }

    public void setPersonalID(String personalID) {
        this.personalID = personalID;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
