package cn.my.kdmall.product.controller;

import java.util.Arrays;
import java.util.Map;

import cn.my.kdmall.product.vo.AttrGroupRelationVo;
import cn.my.kdmall.product.vo.AttrRespVo;
import cn.my.kdmall.product.vo.AttrVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import cn.my.kdmall.product.entity.AttrEntity;
import cn.my.kdmall.product.service.AttrService;
import cn.my.kdmall.common.utils.PageUtils;
import cn.my.kdmall.common.utils.R;



/**
 * 商品属性
 *
 * @author wuweiguang
 * @email wuweiguang@gmail.com
 * @date 2020-06-04 13:20:30
 */
@RestController
@RequestMapping("product/attr")
public class AttrController {
    @Autowired
    private AttrService attrService;



    /**
     * product/attr/base/list
     * product/attr/sale/list
     * @param params
     * @param catelogId
     * @param attrType 属性类型
     * @return
     */
    @GetMapping("/{attrType}/list/{catelogId}")
    //@RequiresPermissions("product:attr:list")
    public R baselist(@RequestParam Map<String, Object> params,
                      @PathVariable("catelogId")Long catelogId,
                      @PathVariable("attrType")String attrType){
        PageUtils page = attrService.queryAttrPage(params, catelogId, attrType);

        return R.ok().put("page", page);
    }
    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("product:attr:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = attrService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{attrId}")
    //@RequiresPermissions("product:attr:info")
    public R info(@PathVariable("attrId") Long attrId){
//		AttrEntity attr = attrService.getById(attrId);

        AttrRespVo respVo = attrService.getAttrInfo(attrId);

        return R.ok().put("attr", respVo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("product:attr:save")
    public R save(@RequestBody AttrVo attr){
		attrService.saveAttr(attr);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("product:attr:update")
    public R update(@RequestBody AttrVo attr){
		attrService.updateAttr(attr);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:attr:delete")
    public R delete(@RequestBody Long[] attrIds){
		attrService.removeByIds(Arrays.asList(attrIds));

        return R.ok();
    }

}
