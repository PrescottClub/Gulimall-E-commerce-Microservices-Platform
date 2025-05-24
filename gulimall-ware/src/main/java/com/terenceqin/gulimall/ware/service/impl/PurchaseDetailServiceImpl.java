package com.terenceqin.gulimall.ware.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.common.utils.Query;

import com.terenceqin.gulimall.ware.dao.PurchaseDetailDao;
import com.terenceqin.gulimall.ware.entity.PurchaseDetailEntity;
import com.terenceqin.gulimall.ware.service.PurchaseDetailService;
import org.springframework.util.StringUtils;


@Service("purchaseDetailService")
public class PurchaseDetailServiceImpl extends ServiceImpl<PurchaseDetailDao, PurchaseDetailEntity> implements PurchaseDetailService {

    /**
     * 采购需求模糊查�?     * status: 0
     * wareId: 1
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<PurchaseDetailEntity> wrapper = new QueryWrapper<>();

        String key = (String) params.get("key");
        if(!StringUtils.isEmpty(key)){
            // 连续拼接
            wrapper.and(w-> w.eq("purchase_id",key).or().eq("sku_id",key));
        }

        String status = (String) params.get("status");
        if(!StringUtils.isEmpty(status)){
            wrapper.eq("status", status);
        }
        String wareId = (String) params.get("wareId");
        if(!StringUtils.isEmpty(wareId)){
            wrapper.eq("ware_id", wareId);
        }

        IPage<PurchaseDetailEntity> page = this.page(
                new Query<PurchaseDetailEntity>().getPage(params),
                wrapper
        );

        return new PageUtils(page);
    }

    /**
     * 根据采购单id 改变采购�?     * 自己优化了一下而已
     */
    @Override
    public List<PurchaseDetailEntity> listDetailByPurchaseId(List<Long> ids) {
        // 获取所有采购项 SELECT * FROM wms_purchase_detail WHERE purchase_id IN (4,6);
        QueryWrapper<PurchaseDetailEntity> purchase_ids = new QueryWrapper<PurchaseDetailEntity>().in("purchase_id", ids);
        List<PurchaseDetailEntity> list = this.list(purchase_ids);
//        List<PurchaseDetailEntity> entities = this.list(new QueryWrapper<PurchaseDetailEntity>().eq("purchase_id", ids));
        return list;
    }
}
