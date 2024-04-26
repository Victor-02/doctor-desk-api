package com.doctordesk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = "com.doctordesk.*")
//@EnableSwagger2
@EnableWebMvc
public class DoctorDeskApplication {

    public static void main(String[] args) {
        SpringApplication.run(DoctorDeskApplication.class, args);
    }
}

