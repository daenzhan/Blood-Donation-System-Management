package org.example.userservice.Auth;

import org.example.userservice.User;
import org.example.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register/donor")
    public ResponseEntity<?> registerDonor(@RequestBody AuthRequest authRequest) {
        try {
            User user = new User();
            user.setEmail(authRequest.getEmail());
            user.setPassword(authRequest.getPassword());

            User registeredUser = userService.registerDonor(user);
            String token = jwtUtil.generateToken(registeredUser.getEmail(), registeredUser.getRole(), registeredUser.getUser_id());

            AuthResponse response = new AuthResponse(
                    token,
                    registeredUser.getEmail(),
                    registeredUser.getRole(),
                    registeredUser.getUser_id(),
                    registeredUser.getFullName(),
                    registeredUser.getBloodType(),
                    registeredUser.getProfileCompleted()
            );
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/register/medcenter")
    public ResponseEntity<?> registerMedCenter(@RequestBody AuthRequest authRequest) {
        try {
            User user = new User();
            user.setEmail(authRequest.getEmail());
            user.setPassword(authRequest.getPassword());

            User registeredUser = userService.registerMedCenter(user);
            String token = jwtUtil.generateToken(registeredUser.getEmail(), registeredUser.getRole(), registeredUser.getUser_id());

            AuthResponse response = new AuthResponse(
                    token,
                    registeredUser.getEmail(),
                    registeredUser.getRole(),
                    registeredUser.getUser_id(),
                    registeredUser.getFullName(),
                    null,
                    registeredUser.getProfileCompleted()
            );
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
            );

            User user = userService.findByEmail(authRequest.getEmail())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            String token = jwtUtil.generateToken(user.getEmail(), user.getRole(), user.getUser_id());

            AuthResponse response = new AuthResponse(
                    token,
                    user.getEmail(),
                    user.getRole(),
                    user.getUser_id(),
                    user.getFullName(),
                    user.getBloodType(),
                    user.getProfileCompleted()
            );
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @PostMapping("/profile/donor")
    public ResponseEntity<?> completeDonorProfile(@RequestBody DonorProfileDTO profileDTO,
                                                  @RequestHeader("Authorization") String tokenHeader) {
        try {
            String token = tokenHeader.replace("Bearer ", "");
            String email = jwtUtil.getEmailFromToken(token);

            User user = userService.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            if (!"DONOR".equals(user.getRole())) {
                return ResponseEntity.badRequest().body("Only donors can complete donor profile");
            }

            User updatedUser = userService.completeDonorProfile(user.getUser_id(), profileDTO);

            return ResponseEntity.ok(updatedUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(@RequestHeader("Authorization") String tokenHeader) {
        try {
            String token = tokenHeader.replace("Bearer ", "");
            String email = jwtUtil.getEmailFromToken(token);

            User user = userService.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            Map<String, Object> userInfo = new HashMap<>();
            userInfo.put("userId", user.getUser_id());
            userInfo.put("email", user.getEmail());
            userInfo.put("role", user.getRole());
            userInfo.put("fullName", user.getFullName());
            userInfo.put("bloodType", user.getBloodType());
            userInfo.put("phoneNumber", user.getPhoneNumber());
            userInfo.put("address", user.getAddress());
            userInfo.put("birthDate", user.getBirthDate());
            userInfo.put("donationCount", user.getDonationCount());
            userInfo.put("profileCompleted", user.getProfileCompleted());
            userInfo.put("lastDonationDate", user.getLastDonationDate());

            return ResponseEntity.ok(userInfo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }
    }
}
