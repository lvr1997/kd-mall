package cn.my.kdmall.order.service;

import cn.my.kdmall.common.utils.PageUtils;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.my.kdmall.order.entity.OrderSettingEntity;

import java.util.Map;

/**
 * 订单配置信息
 *
 * @author wuweiguang
 * @email wuweiguang@gmail.com
 * @date 2020-06-04 16:41:14
 */
public interface OrderSettingService extends IService<OrderSettingEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

