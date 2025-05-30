package com.terenceqin.gulimall.product.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.common.utils.Query;

import com.terenceqin.gulimall.product.dao.SkuImagesDao;
import com.terenceqin.gulimall.product.entity.SkuImagesEntity;
import com.terenceqin.gulimall.product.service.SkuImagesService;


@Service("skuImagesService")
public class SkuImagesServiceImpl
        extends ServiceImpl<SkuImagesDao, SkuImagesEntity>
        implements SkuImagesService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SkuImagesEntity> page = this.page(
                new Query<SkuImagesEntity>().getPage(params),
                new QueryWrapper<SkuImagesEntity>()
        );
        return new PageUtils(page);
    }

    @Override // SkuImagesServiceImpl
    public List<SkuImagesEntity> getImagesBySkuId(Long skuId) {

        return baseMapper.selectList(
                new QueryWrapper<SkuImagesEntity>().eq("sku_id", skuId));
    }
}
