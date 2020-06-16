package cn.my.kdmall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.my.kdmall.common.utils.PageUtils;
import cn.my.kdmall.coupon.entity.SkuLadderEntity;

import java.util.Map;

/**
 * 商品阶梯价格
 *
 * @author wuweiguang
 * @email wuweiguang@gmail.com
 * @date 2020-06-04 17:04:02
 */
public interface SkuLadderService extends IService<SkuLadderEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

