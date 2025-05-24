package com.terenceqin.gulimall.product.service;

import com.terenceqin.gulimall.product.vo.AttrGroupRelationVo;
import com.terenceqin.gulimall.product.vo.AttrRespVo;
import com.terenceqin.gulimall.product.vo.AttrVo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.common.utils.PageUtils;
import com.terenceqin.gulimall.product.entity.AttrEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品属�? *
 * @author hh
 * @email 55333@qq.com
 * @date 2020-06-23 20:09:14
 */
public interface AttrService extends IService<AttrEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveAttr(AttrVo attrVo);

    /**
     * 规格参数的分页模糊查�?     */
    PageUtils queryBaseAttrPage(Map<String, Object> params, Long catelogId, String attrType);

    /**
     *
     */
    AttrRespVo getAttrInfo(Long attrId);

    /**
     * 更改规格参数：参数名、参数id、参数、状态的一一对应
     */
    void updateAttr(AttrVo attrVo);

    List<AttrEntity> getRelationAttr(Long attrgroupId);

    void deleteRelation(AttrGroupRelationVo[] vos);

    PageUtils getNoRelationAttr(Map<String, Object> params, Long attrgroupId);

    /**
     * 在指定的集合里面挑出可检索的属�?     */
    List<Long> selectSearchAttrIds(List<Long> attrIds);
}

