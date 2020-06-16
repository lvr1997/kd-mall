package cn.my.kdmall.coupon.dao;

import cn.my.kdmall.coupon.entity.SeckillSessionEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 秒杀活动场次
 * 
 * @author wuweiguang
 * @email wuweiguang@gmail.com
 * @date 2020-06-04 17:04:02
 */
@Mapper
public interface SeckillSessionDao extends BaseMapper<SeckillSessionEntity> {
	
}
