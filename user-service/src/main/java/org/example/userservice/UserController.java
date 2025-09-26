package org.example.userservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> create_user(@RequestBody User u) {
        User created_user = userService.create_user(u);
        return new ResponseEntity<>(created_user, HttpStatus.CREATED);
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<User> get_user_by_id(@PathVariable Long user_id) {
        User u = userService.get_user_by_id(user_id);
        return ResponseEntity.ok(u);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.get_all_users());
    }

    @PutMapping("/{user_id}")
    public ResponseEntity<User> update_user(@PathVariable Long user_id, @RequestBody User u) {
        return ResponseEntity.ok(userService.update_user(user_id, u));
    }

    @DeleteMapping("/{user_id}")
    public ResponseEntity<Void> delete_user(@PathVariable Long user_id) {
        userService.delete_user(user_id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/donors/{bloodType}")
    public ResponseEntity<List<User>> getDonorsByBloodType(@PathVariable String bloodType) {
        return ResponseEntity.ok(userService.findDonorsByBloodType(bloodType));
    }

}