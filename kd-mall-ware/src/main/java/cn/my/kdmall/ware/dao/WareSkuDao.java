package cn.my.kdmall.ware.dao;

import cn.my.kdmall.ware.entity.WareSkuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品库存
 * 
 * @author wuweiguang
 * @email wuweiguang@gmail.com
 * @date 2020-06-04 21:05:52
 */
@Mapper
public interface WareSkuDao extends BaseMapper<WareSkuEntity> {
	
}
