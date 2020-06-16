package cn.my.kdmall.member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "cn.my.kdmall.member.feign")
@EnableDiscoveryClient
@SpringBootApplication
public class KdMallMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(KdMallMemberApplication.class, args);
    }

}
