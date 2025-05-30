package com.terenceqin.gulimall.product.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.terenceqin.gulimall.product.entity.ProductAttrValueEntity;
import com.terenceqin.gulimall.product.service.ProductAttrValueService;
import com.terenceqin.gulimall.product.vo.AttrRespVo;
import com.terenceqin.gulimall.product.vo.AttrVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.terenceqin.gulimall.product.entity.AttrEntity;
import com.terenceqin.gulimall.product.service.AttrService;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.common.utils.R;



/**
 * ååå±æ? *
 * @author hh
 * @email 55333@qq.com
 * @date 2020-06-23 21:00:59
 */
@RestController
@RequestMapping("product/attr")
public class AttrController {

    @Autowired
    private AttrService attrService;

    @Autowired
    private ProductAttrValueService productAttrValueService;

    @PostMapping("/update/{spuId}")
    public R updateSpiAttr(@PathVariable("spuId") Long spuId, @RequestBody List<ProductAttrValueEntity> entities){
        productAttrValueService.updateSpuAttr(spuId, entities);
        return R.ok();
    }

    /**
     * æ¥è¯¢å±æ§è§æ ?     */
    @GetMapping("/base/listforspu/{spuId}")
    public R baseAttrListForSpu(@PathVariable("spuId") Long spuId){
        List<ProductAttrValueEntity> entities = productAttrValueService.baseAttrListForSpu(spuId);
        return R.ok().put("data", entities);
    }

    @GetMapping("/{attrType}/list/{catelogId}")
    public R baseAttrList(@RequestParam Map<String, Object> params ,@PathVariable("catelogId") Long catelogId, @PathVariable("attrType") String attrType){

        PageUtils page = attrService.queryBaseAttrPage(params, catelogId, attrType);
        return R.ok().put("page", page);
    }

    /**
     * åè¡¨
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = attrService.queryPage(params);
        return R.ok().put("page", page);
    }

    /**
     * ä¿¡æ¯
     */
    @RequestMapping("/info/{attrId}")
    public R info(@PathVariable("attrId") Long attrId){
        AttrRespVo respVo = attrService.getAttrInfo(attrId);
        R ok = R.ok();
        ok.put("data", respVo);
        return ok.put("attr", respVo);
    }

    /**
     * ä¿å­
     */
    @RequestMapping("/save")
    public R save(@RequestBody AttrVo attrVo){
        attrService.saveAttr(attrVo);

        return R.ok();
    }

    /**
     * æ´æ¹è§æ ¼åæ°ï¼åæ°åãåæ°idãåæ°ãç¶æçä¸ä¸å¯¹åº
     */
    @RequestMapping("/update")
    public R update(@RequestBody AttrVo attrVo){
        attrService.updateAttr(attrVo);
        return R.ok();
    }

    /**
     * å é¤
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] attrIds){
        attrService.removeByIds(Arrays.asList(attrIds));

        return R.ok();
    }
}
