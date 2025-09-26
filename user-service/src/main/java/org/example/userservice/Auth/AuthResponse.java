package org.example.userservice.Auth;

public class AuthResponse {
    private String token;
    private String email;
    private String role;
    private Long userId;
    private String fullName;
    private String bloodType;
    private Boolean profileCompleted;

    public AuthResponse(String token, String email, String role, Long userId,
                        String fullName, String bloodType, Boolean profileCompleted) {
        this.token = token;
        this.email = email;
        this.role = role;
        this.userId = userId;
        this.fullName = fullName;
        this.bloodType = bloodType;
        this.profileCompleted = profileCompleted;
    }

    // Геттеры и сеттеры
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getBloodType() { return bloodType; }
    public void setBloodType(String bloodType) { this.bloodType = bloodType; }

    public Boolean getProfileCompleted() { return profileCompleted; }
    public void setProfileCompleted(Boolean profileCompleted) { this.profileCompleted = profileCompleted; }
}
