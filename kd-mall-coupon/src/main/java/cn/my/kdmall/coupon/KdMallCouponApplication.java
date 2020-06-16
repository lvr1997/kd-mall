package cn.my.kdmall.coupon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class KdMallCouponApplication {

    public static void main(String[] args) {
        SpringApplication.run(KdMallCouponApplication.class, args);
    }

}
