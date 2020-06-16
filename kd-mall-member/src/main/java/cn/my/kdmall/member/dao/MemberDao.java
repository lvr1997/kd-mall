package cn.my.kdmall.member.dao;

import cn.my.kdmall.member.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员
 * 
 * @author wuweiguang
 * @email wuweiguang@gmail.com
 * @date 2020-06-04 20:59:17
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
	
}
