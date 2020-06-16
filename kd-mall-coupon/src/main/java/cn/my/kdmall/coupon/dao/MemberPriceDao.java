package cn.my.kdmall.coupon.dao;

import cn.my.kdmall.coupon.entity.MemberPriceEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品会员价格
 * 
 * @author wuweiguang
 * @email wuweiguang@gmail.com
 * @date 2020-06-04 17:04:02
 */
@Mapper
public interface MemberPriceDao extends BaseMapper<MemberPriceEntity> {
	
}
