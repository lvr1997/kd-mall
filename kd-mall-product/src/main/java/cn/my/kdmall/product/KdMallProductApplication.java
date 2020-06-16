package cn.my.kdmall.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@MapperScan("cn.my.kdmall.product.dao")
@SpringBootApplication
public class KdMallProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(KdMallProductApplication.class, args);
    }

}
