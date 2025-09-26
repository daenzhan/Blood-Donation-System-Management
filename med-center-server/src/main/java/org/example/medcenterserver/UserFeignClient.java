package org.example.medcenterserver;


import org.example.courseservice.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")
public interface UserFeignClient {
    @GetMapping("/users/{user_id}")
    public UserDto get_user_by_id (@PathVariable Long user_id);
}
