package cn.my.kdmall.product.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import cn.my.kdmall.product.entity.AttrEntity;
import cn.my.kdmall.product.service.AttrAttrgroupRelationService;
import cn.my.kdmall.product.service.AttrService;
import cn.my.kdmall.product.service.CategoryService;
import cn.my.kdmall.product.vo.AttrGroupRelationVo;
import cn.my.kdmall.product.vo.AttrGroupWithAttrsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import cn.my.kdmall.product.entity.AttrGroupEntity;
import cn.my.kdmall.product.service.AttrGroupService;
import cn.my.kdmall.common.utils.PageUtils;
import cn.my.kdmall.common.utils.R;



/**
 * 属性分组
 *
 * @author wuweiguang
 * @email wuweiguang@gmail.com
 * @date 2020-06-04 13:20:30
 */
@RestController
@RequestMapping("product/attrgroup")
public class AttrGroupController {

    @Autowired
    private AttrGroupService attrGroupService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    AttrService attrService;

    @Autowired
    AttrAttrgroupRelationService relationService;

    @GetMapping("/{catelogId}/withattr")
    public  R getAttrGroupWithAttrs(@PathVariable("catelogId") Long catelogId){

        //1.查出当前分类下的所有属性分组
      List<AttrGroupWithAttrsVo>  vos= attrGroupService.getAttrGroupWithAttrsByCatelogId(catelogId);
        //2. 查出每个属性分组的所有属性
        return R.ok().put("data",vos);
    }

    @PostMapping("/attr/relation")
     public R addRelation(@RequestBody List<AttrGroupRelationVo> vos){
        relationService.saveBatch(vos);
        return R.ok();
     }

    @PostMapping("/attr/relation/delete")
    public R deleteRelation(@RequestBody AttrGroupRelationVo[] vos){
        attrService.deleteRelation(vos);
        return  R.ok();
    }

    @GetMapping("/{attrgroupId}/attr/relation")
    public R  attrRelation(@PathVariable("attrgroupId") Long  attrgroupId){
        List<AttrEntity> entities= attrService.getRelationAttr(attrgroupId);
        return R.ok().put("data",entities);
    }
   // http://localhost:88/api/product/attrgroup/1/noattr/relation?t=1593155645262&page=1&limit=10&key=

   @GetMapping("/{attrgroupId}/noattr/relation")
   public R  attrNoRelation(@PathVariable("attrgroupId") Long  attrgroupId,
                            @RequestParam Map<String, Object> params){
     PageUtils page=  attrService.getNoRelationAttr(attrgroupId,params);
       return R.ok().put("page",page);
   }
    /**
     * 列表
     */
    @RequestMapping("/list/{catelogId}")
    //@RequiresPermissions("product:attrgroup:list")
    public R list(@RequestParam Map<String, Object> params, @PathVariable("catelogId") Long catelogId){
//        PageUtils page = attrGroupService.queryPage(params);

        PageUtils page = attrGroupService.queryPage(params, catelogId);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{attrGroupId}")
    //@RequiresPermissions("product:attrgroup:info")
    public R info(@PathVariable("attrGroupId") Long attrGroupId){
		AttrGroupEntity attrGroup = attrGroupService.getById(attrGroupId);

		Long[] catelogIds = categoryService.getCatelogIds(attrGroup.getCatelogId());
        attrGroup.setCatelogIds(catelogIds);

        return R.ok().put("attrGroup", attrGroup);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("product:attrgroup:save")
    public R save(@RequestBody AttrGroupEntity attrGroup){
		attrGroupService.save(attrGroup);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("product:attrgroup:update")
    public R update(@RequestBody AttrGroupEntity attrGroup){
		attrGroupService.updateById(attrGroup);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:attrgroup:delete")
    public R delete(@RequestBody Long[] attrGroupIds){
		attrGroupService.removeByIds(Arrays.asList(attrGroupIds));

        return R.ok();
    }

}
