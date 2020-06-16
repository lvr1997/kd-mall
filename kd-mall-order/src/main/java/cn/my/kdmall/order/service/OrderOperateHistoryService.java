package cn.my.kdmall.order.service;

import cn.my.kdmall.common.utils.PageUtils;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.my.kdmall.order.entity.OrderOperateHistoryEntity;

import java.util.Map;

/**
 * 订单操作历史记录
 *
 * @author wuweiguang
 * @email wuweiguang@gmail.com
 * @date 2020-06-04 16:41:14
 */
public interface OrderOperateHistoryService extends IService<OrderOperateHistoryEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

