package cn.my.kdmall.product.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.my.kdmall.common.utils.PageUtils;
import cn.my.kdmall.common.utils.Query;

import cn.my.kdmall.product.dao.CategoryDao;
import cn.my.kdmall.product.entity.CategoryEntity;
import cn.my.kdmall.product.service.CategoryService;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> listTree() {
        //查出所有的 分类
        List<CategoryEntity> categoryEntities = baseMapper.selectList(null);
        //组装成父子发树形结构
        //获取所有的一级分类
        List<CategoryEntity> categoryLevel1 = categoryEntities.stream()
                .filter(categoryEntity -> categoryEntity.getParentCid() == 0)
                .map((category) -> {
                    category.setChildren(getChildrenCategory(category, categoryEntities));
                    return category;
                })
                .sorted((c1, c2) -> {
                    return (c1.getSort()==null? 0:c1.getSort()) - (c2.getSort()==null? 0:c2.getSort());
                })
                .collect(Collectors.toList());
        return categoryLevel1;
    }

    @Override
    public void removeCategoryByIds(List<Long> asList) {

        baseMapper.deleteBatchIds(asList);
    }

    private List<CategoryEntity> getChildrenCategory(CategoryEntity root, List<CategoryEntity> all) {
        List<CategoryEntity> children = all.stream().filter(categoryEntity -> categoryEntity.getParentCid() == root.getCatId())
                .map((categoryEntity) -> {
                    categoryEntity.setChildren(getChildrenCategory(categoryEntity, all));
                    return categoryEntity;
                }).sorted((c1, c2) -> {
                    return (c1.getSort()==null? 0:c1.getSort()) - (c2.getSort()==null? 0:c2.getSort());
                }).collect(Collectors.toList());

        return children;
    }

}