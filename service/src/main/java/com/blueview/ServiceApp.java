package com.blueview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName ServiceApp
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/7/19
 * @Version V1.0
 **/
@SpringBootApplication(scanBasePackages = "com.blueview")
public class ServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(ServiceApp.class, args);
    }
}
