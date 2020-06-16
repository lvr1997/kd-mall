package cn.my.kdmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.my.kdmall.common.utils.PageUtils;
import cn.my.kdmall.product.entity.SkuInfoEntity;

import java.util.Map;

/**
 * sku信息
 *
 * @author wuweiguang
 * @email wuweiguang@gmail.com
 * @date 2020-06-04 13:20:30
 */
public interface SkuInfoService extends IService<SkuInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

