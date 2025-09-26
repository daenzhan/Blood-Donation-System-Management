package org.example.userservice;


import org.example.userservice.Auth.DonorProfileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User create_user(User user) {
        return userRepository.save(user);
    }

    public User get_user_by_id(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
    public List<User> get_all_users() {
        return userRepository.findAll();
    }

    public User registerDonor(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("DONOR");
        user.setDonationCount(0);
        user.setProfileCompleted(false);

        return userRepository.save(user);
    }

    public User registerMedCenter(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("MED_CENTER");
        user.setProfileCompleted(true);

        return userRepository.save(user);
    }

    public User completeDonorProfile(Long userId, DonorProfileDTO profileDTO) {
        User user = get_user_by_id(userId);

        if (profileDTO.getFullName() == null || profileDTO.getFullName().trim().isEmpty()) {
            throw new RuntimeException("Full name is required");
        }

        if (profileDTO.getBloodType() == null || profileDTO.getBloodType().trim().isEmpty()) {
            throw new RuntimeException("Blood type is required");
        }

        List<String> validBloodTypes = List.of("A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-");
        if (!validBloodTypes.contains(profileDTO.getBloodType().toUpperCase())) {
            throw new RuntimeException("Invalid blood type");
        }

        user.setFullName(profileDTO.getFullName());
        user.setBloodType(profileDTO.getBloodType().toUpperCase());
        user.setPhoneNumber(profileDTO.getPhoneNumber());
        user.setAddress(profileDTO.getAddress());
        user.setBirthDate(profileDTO.getBirthDate());
        user.setHealthInfo(profileDTO.getHealthInfo());
        user.setProfileCompleted(true);

        return userRepository.save(user);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User update_user(Long id, User user) {
        User u = get_user_by_id(id);
        u.setFullName(user.getFullName());
        u.setPhoneNumber(user.getPhoneNumber());
        u.setAddress(user.getAddress());
        u.setHealthInfo(user.getHealthInfo());

        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            u.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        return userRepository.save(u);
    }

    public void delete_user(Long id) {
        User u = get_user_by_id(id);
        userRepository.delete(u);
    }

    public List<User> findDonorsByBloodType(String bloodType) {
        return userRepository.findByRoleAndBloodType("DONOR", bloodType);
    }
}
