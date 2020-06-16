package cn.my.kdmall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.my.kdmall.common.utils.PageUtils;
import cn.my.kdmall.coupon.entity.SeckillSessionEntity;

import java.util.Map;

/**
 * 秒杀活动场次
 *
 * @author wuweiguang
 * @email wuweiguang@gmail.com
 * @date 2020-06-04 17:04:02
 */
public interface SeckillSessionService extends IService<SeckillSessionEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

