package cn.my.kdmall.thirdparty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class KdmallThirdPartyApplication {

    public static void main(String[] args) {
        SpringApplication.run(KdmallThirdPartyApplication.class, args);
    }

}
