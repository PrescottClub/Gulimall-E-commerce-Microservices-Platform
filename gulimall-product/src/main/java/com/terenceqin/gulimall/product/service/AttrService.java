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
 * ååå±æ? *
 * @author hh
 * @email 55333@qq.com
 * @date 2020-06-23 20:09:14
 */
public interface AttrService extends IService<AttrEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveAttr(AttrVo attrVo);

    /**
     * è§æ ¼åæ°çåé¡µæ¨¡ç³æ¥è¯?     */
    PageUtils queryBaseAttrPage(Map<String, Object> params, Long catelogId, String attrType);

    /**
     *
     */
    AttrRespVo getAttrInfo(Long attrId);

    /**
     * æ´æ¹è§æ ¼åæ°ï¼åæ°åãåæ°idãåæ°ãç¶æçä¸ä¸å¯¹åº
     */
    void updateAttr(AttrVo attrVo);

    List<AttrEntity> getRelationAttr(Long attrgroupId);

    void deleteRelation(AttrGroupRelationVo[] vos);

    PageUtils getNoRelationAttr(Map<String, Object> params, Long attrgroupId);

    /**
     * å¨æå®çéåéé¢æåºå¯æ£ç´¢çå±æ?     */
    List<Long> selectSearchAttrIds(List<Long> attrIds);
}

