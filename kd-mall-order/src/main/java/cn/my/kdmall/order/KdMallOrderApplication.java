package cn.my.kdmall.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class KdMallOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(KdMallOrderApplication.class, args);
    }

}
