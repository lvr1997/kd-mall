package cn.my.kdmall.prodect;

import cn.my.kdmall.product.KdMallProductApplication;
import cn.my.kdmall.product.entity.BrandEntity;
import cn.my.kdmall.product.service.BrandService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.util.Lists.list;

@SpringBootTest(classes = KdMallProductApplication.class)
class KdMallProdectApplicationTests {

    @Autowired
    BrandService brandService;
    @Test
    void contextLoads() {

        List<BrandEntity> list =  brandService.list();

            System.out.println("success...!"+list);


    }

}
