package cn.my.kdmall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.my.kdmall.common.utils.PageUtils;
import cn.my.kdmall.member.entity.MemberReceiveAddressEntity;

import java.util.Map;

/**
 * 会员收货地址
 *
 * @author wuweiguang
 * @email wuweiguang@gmail.com
 * @date 2020-06-04 20:59:17
 */
public interface MemberReceiveAddressService extends IService<MemberReceiveAddressEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

