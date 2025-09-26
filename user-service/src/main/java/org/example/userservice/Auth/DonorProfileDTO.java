package org.example.userservice.Auth;

import java.time.LocalDate;

public class DonorProfileDTO {
    private String fullName;
    private String bloodType;
    private String phoneNumber;
    private String address;
    private LocalDate birthDate;
    private String healthInfo;

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getBloodType() { return bloodType; }
    public void setBloodType(String bloodType) { this.bloodType = bloodType; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }

    public String getHealthInfo() { return healthInfo; }
    public void setHealthInfo(String healthInfo) { this.healthInfo = healthInfo; }
}
