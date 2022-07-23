package com.example.rms.user.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private Long userId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String middleName;

    @Column(nullable = false)
    private String otherNames;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private Long phoneNumber;

    @Column(nullable = false)
    private String password;

    public User() {
    }

    public User(Long userId, String firstName, String middleName, String otherNames, String email, Long phoneNumber, String password) {
        this.userId = userId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.otherNames = otherNames;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getOtherNames() {
        return otherNames;
    }

    public void setOtherNames(String otherNames) {
        this.otherNames = otherNames;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object y) {
        if (this == y) return true;
        if (!(y instanceof User user)) return false;
        return Objects.equals(email, user.email) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, firstName, middleName, otherNames, email, phoneNumber, password);
    }

    @Override
    public String toString() {
        return "User{" + "user id=" + userId + ", first name='" + firstName + '\'' + ", last name='" + middleName + '\'' + ", other names='" + otherNames + '\'' + ", email='" + email + '\'' + ", phone number='" + phoneNumber + '\'' + ", password='" + password + '\'' + '}';
    }
}
