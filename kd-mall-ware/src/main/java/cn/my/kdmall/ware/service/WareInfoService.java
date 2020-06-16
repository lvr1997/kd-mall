package cn.my.kdmall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.my.kdmall.common.utils.PageUtils;
import cn.my.kdmall.ware.entity.WareInfoEntity;

import java.util.Map;

/**
 * 仓库信息
 *
 * @author wuweiguang
 * @email wuweiguang@gmail.com
 * @date 2020-06-04 21:05:52
 */
public interface WareInfoService extends IService<WareInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

