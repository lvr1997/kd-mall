package cn.my.kdmall.ware.dao;

import cn.my.kdmall.ware.entity.PurchaseEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 采购信息
 * 
 * @author wuweiguang
 * @email wuweiguang@gmail.com
 * @date 2020-06-04 21:05:52
 */
@Mapper
public interface PurchaseDao extends BaseMapper<PurchaseEntity> {
	
}
