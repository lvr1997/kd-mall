package cn.my.kdmall.order.service;

import cn.my.kdmall.common.utils.PageUtils;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.my.kdmall.order.entity.PaymentInfoEntity;

import java.util.Map;

/**
 * 支付信息表
 *
 * @author wuweiguang
 * @email wuweiguang@gmail.com
 * @date 2020-06-04 16:41:14
 */
public interface PaymentInfoService extends IService<PaymentInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

