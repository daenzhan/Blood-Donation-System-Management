package org.example.userservice;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create_user(User user) {
        return userRepository.save(user);
    }

    public Optional<User> get_user_by_id(Long id) {
        return userRepository.findById(id);
    }

    public List<User> get_all_users() {
        return userRepository.findAll();
    }

    public User update_user(Long id, User user) {
        User u = get_user_by_id(id).get();
        u.setRole(user.getRole());
        if (user.getPassword() != null) {
            u.setPassword(user.getPassword());
        }
        return userRepository.save(u);
    }

    public void delete_user(Long id) {
        User u = get_user_by_id(id).get();
        userRepository.delete(u);
    }
}
