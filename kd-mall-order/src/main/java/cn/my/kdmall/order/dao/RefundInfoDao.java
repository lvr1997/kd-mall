package cn.my.kdmall.order.dao;

import cn.my.kdmall.order.entity.RefundInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 退款信息
 * 
 * @author wuweiguang
 * @email wuweiguang@gmail.com
 * @date 2020-06-04 16:41:14
 */
@Mapper
public interface RefundInfoDao extends BaseMapper<RefundInfoEntity> {
	
}
