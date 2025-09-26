package org.example.medcenterserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;

@SpringBootApplication
@FeignClient
@EnableFeignClients
public class MedCenterServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MedCenterServerApplication.class, args);
    }

}
