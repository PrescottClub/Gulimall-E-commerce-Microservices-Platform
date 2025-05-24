package com.terenceqin.gulimall.product.vo;

import com.terenceqin.gulimall.product.entity.SkuImagesEntity;
import com.terenceqin.gulimall.product.entity.SkuInfoEntity;
import com.terenceqin.gulimall.product.entity.SpuInfoDescEntity;
import lombok.Data;

import java.util.List;

/**
 * <p>Title: SkuItemVo</p>
 * Description�? */
@Data
public class SkuItemVo {

    /*** 1 sku基本信息的获�?如标�?/
    SkuInfoEntity info;

    boolean hasStock = true;

    /*** 2 sku的图片信�?/
    List<SkuImagesEntity> images;

    /*** 3 获取spu的销售属性组合。每个attrName对应一个value-list*/
    List<ItemSaleAttrVo> saleAttr;

    /*** 4 获取spu的介�?/
    SpuInfoDescEntity desc;

    /*** 5 获取spu的规格参数信息，每个分组的包含list*/
    List<SpuItemAttrGroup> groupAttrs;

    /*** 6 秒杀信息*/
    SeckillInfoVo seckillInfoVo;
}
