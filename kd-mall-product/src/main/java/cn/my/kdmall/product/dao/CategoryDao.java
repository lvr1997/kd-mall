package cn.my.kdmall.product.dao;

import cn.my.kdmall.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 * 
 * @author wuweiguang
 * @email wuweiguang@gmail.com
 * @date 2020-06-04 13:20:30
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
