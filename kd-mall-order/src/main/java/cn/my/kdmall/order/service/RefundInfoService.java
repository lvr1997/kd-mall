package cn.my.kdmall.order.service;

import cn.my.kdmall.common.utils.PageUtils;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.my.kdmall.order.entity.RefundInfoEntity;

import java.util.Map;

/**
 * 退款信息
 *
 * @author wuweiguang
 * @email wuweiguang@gmail.com
 * @date 2020-06-04 16:41:14
 */
public interface RefundInfoService extends IService<RefundInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

