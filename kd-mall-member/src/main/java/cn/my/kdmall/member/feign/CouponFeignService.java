package cn.my.kdmall.member.feign;

import cn.my.kdmall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("kd-mall-coupon")
public interface CouponFeignService {

    @RequestMapping("/coupon/coupon/memcoupons")
    public R couponXinxi();

}
