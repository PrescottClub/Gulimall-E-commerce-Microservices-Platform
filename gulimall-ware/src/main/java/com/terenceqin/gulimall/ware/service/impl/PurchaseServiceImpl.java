package com.terenceqin.gulimall.ware.service.impl;

import com.atguigu.common.constant.WareConstant;
import com.terenceqin.gulimall.ware.entity.PurchaseDetailEntity;
import com.terenceqin.gulimall.ware.service.PurchaseDetailService;
import com.terenceqin.gulimall.ware.service.WareSkuService;
import com.terenceqin.gulimall.ware.vo.MergeVo;
import com.terenceqin.gulimall.ware.vo.PurchaseDoneVo;
import com.terenceqin.gulimall.ware.vo.PurchaseItemDoneVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.common.utils.Query;

import com.terenceqin.gulimall.ware.dao.PurchaseDao;
import com.terenceqin.gulimall.ware.entity.PurchaseEntity;
import com.terenceqin.gulimall.ware.service.PurchaseService;
import org.springframework.transaction.annotation.Transactional;


@Service("purchaseService")
public class PurchaseServiceImpl extends ServiceImpl<PurchaseDao, PurchaseEntity> implements PurchaseService {

    @Autowired
    private PurchaseDetailService detailService;

    @Autowired
    private WareSkuService wareSkuService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PurchaseEntity> page = this.page(
                new Query<PurchaseEntity>().getPage(params),
                new QueryWrapper<PurchaseEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * 分页查询
     */
    @Override
    public PageUtils queryPageUnreceivePurchase(Map<String, Object> params) {
        IPage<PurchaseEntity> page = this.page(
                new Query<PurchaseEntity>().getPage(params),
//				采购状态只能是0,1 ：新�?已分�?                new QueryWrapper<PurchaseEntity>().eq("status", 0).or().eq("status", 1)
        );

        return new PageUtils(page);
    }

    /**
     * 根据情况修改、创建采购单   [没有更改分配状态]
     */
    @Transactional
    @Override // PurchaseServiceImpl
    public void mergePurchase(MergeVo mergeVo) {
        Long purchaseId = mergeVo.getPurchaseId();
        // 如果采购id为null 说明没选采购单
        if (purchaseId == null) {
            // 新建采购�?            PurchaseEntity purchaseEntity = new PurchaseEntity();
            purchaseEntity.setStatus(WareConstant.PurchaseStatusEnum.CREATED.getCode());// 新建状�?            purchaseEntity.setCreateTime(new Date());
            purchaseEntity.setUpdateTime(new Date());
            this.save(purchaseEntity);
            purchaseId = purchaseEntity.getId();// 自动返回id
        }

        // 合并采购�?[其实就是修改上面创建的采购单]
        List<Long> items = mergeVo.getItems(); // 获取带过来的采购需�?
        // 从数据库查询所有要合并的采购单，然后过滤所有大�?[已分配] 状态的订单，就是说已经去采购了就不能改�?        PurchaseEntity purchaseEntity = new PurchaseEntity();
        // 过滤掉已分配的采购需�?        List<PurchaseDetailEntity> detailEntities =
                detailService.getBaseMapper().selectBatchIds(items).stream()
                        .filter(entity -> {
//                            // 如果正在合并采购异常的项就把这个采购项之前所在的采购单的状�?wms_purchase 表的状态修改为 已分�?//                            if (entity.getStatus() == WareConstant.PurchaseDetailStatusEnum.HASERROR.getCode()) {
//                                purchaseEntity.setStatus(WareConstant.PurchaseDetailStatusEnum.ASSIGNED.getCode());
//                                purchaseEntity.setId(entity.getPurchaseId());
//                                this.updateById(purchaseEntity);
//                            }
                            // 如果没还去采购，就可以更�?// 采购需求有问题可以再去重新采购
                            return entity.getStatus() < WareConstant.PurchaseDetailStatusEnum.BUYING.getCode()
                                    || entity.getStatus() == WareConstant.PurchaseDetailStatusEnum.HASERROR.getCode();
                        }).collect(Collectors.toList());
        // 得到过滤好的需求id
        items = detailEntities.stream().map(entity -> entity.getId()).collect(Collectors.toList());
        if (items == null || items.size() == 0) {
            return;
        }
        // 设置仓库id // 采购单得是同个仓库的
        purchaseEntity.setWareId(detailEntities.get(0).getWareId());
        Long finalPurchaseId = purchaseId;
        // 给采购需求设置所属采购单和状态等信息
        List<PurchaseDetailEntity> collect = items.stream().map(item -> {
            PurchaseDetailEntity entity = new PurchaseDetailEntity();
            entity.setId(item);
            entity.setPurchaseId(finalPurchaseId);
            entity.setStatus(WareConstant.PurchaseDetailStatusEnum.ASSIGNED.getCode());
            return entity;
        }).collect(Collectors.toList());

        // 更新时间采购单最后更新时�?// 可以通过mp的@TableField(fill=FieldFill.INSERT_UPDATE)来完成，给spring中注入MetaObjectHandler
        detailService.updateBatchById(collect);
        purchaseEntity.setId(purchaseId);
        purchaseEntity.setUpdateTime(new Date());
        this.updateById(purchaseEntity);
    }

    /**
     * 领取采购�?     * @param ids：采购单id
     * 过滤采购需求，并同步采购需求的状�?     */
    @Override
    public void received(List<Long> ids) {
        // 没有采购需求直接返回，否则会破坏采购单
        if (ids == null || ids.size() == 0) {
            return;
        }
        // 1.确认当前采购单是已分配状�?// 优化成查询list
        List<PurchaseEntity> purchaseEntityList = this.listByIds(ids);
        purchaseEntityList =
                purchaseEntityList.stream()
                        // 只能采购已分�?新创建的
                        .filter(item -> item.getStatus() == WareConstant.PurchaseStatusEnum.ASSIGNED.getCode()
                                || item.getStatus() == WareConstant.PurchaseStatusEnum.CREATED.getCode())
                        .map(item -> {
                            // 更新状态和时间
                            item.setStatus(WareConstant.PurchaseStatusEnum.RECEIVE.getCode());
                            item.setUpdateTime(new Date());
                            return item;
                        }).collect(Collectors.toList());
        // 2.被领取之后重新设置采购单状�?        this.updateBatchById(purchaseEntityList);

        // 3.改变采购需求状�?        // 打包所有的采购单id(获取过滤后的)
        List<Long> purchaseIdList = purchaseEntityList.stream().map(purchaseEntity -> purchaseEntity.getId()).collect(Collectors.toList());
        System.out.println(purchaseIdList);
        // 通过采购单id查到所有的采购需�?注意这里把所有的采购单需求都混合了，可能不太�?
//        List<PurchaseDetailEntity> purchaseDetailEntityList =detailService.listDetailByPurchaseId(purchaseIdList);// 这里的参数我都加上了List<Long>
        // 别用eq，得用in
        QueryWrapper<PurchaseDetailEntity> purchase_ids = new QueryWrapper<PurchaseDetailEntity>().in("purchase_id", ids);
        // 查询采购单详�?        List<PurchaseDetailEntity> purchaseDetailEntityList = detailService.list(purchase_ids);
        System.out.println(purchaseDetailEntityList);
        // 更改采购需求的状态：BUYING
        purchaseDetailEntityList = purchaseDetailEntityList.stream().map(purchaseDetailEntity -> {
            purchaseDetailEntity.setStatus(WareConstant.PurchaseDetailStatusEnum.BUYING.getCode());
            return purchaseDetailEntity;
        }).collect(Collectors.toList());
        System.out.println(purchaseDetailEntityList);
        detailService.updateBatchById(purchaseDetailEntityList);
        // 或者直接更新status字段即可，不用查�?
//        purchaseEntityList.forEach(item -> {
//            // 查询采购单关联的采购需�?//            List<PurchaseDetailEntity> entities = detailService.listDetailByPurchaseId(item.getId());
//
//            // 收集所有需要更新的采购需求id
//            List<PurchaseDetailEntity> detailEntities = entities.stream().map(entity -> {
//                PurchaseDetailEntity detailEntity = new PurchaseDetailEntity();
//                detailEntity.setId(entity.getId());//采购需求号
//                detailEntity.setStatus(WareConstant.PurchaseDetailStatusEnum.BUYING.getCode());
//                return detailEntity;
//            }).collect(Collectors.toList());
//            // 根据需求id  批量更新
//            detailService.updateBatchById(detailEntities);
//        });
    }

    /**
     * {
     * "id":"1",
     * "items":[
     *         {"itemId":1,"status":3,"reason":"完成"},
     *        {"itemId":3,"status":4,"reason":"无货"}
     *        ]
     * }
     * id�?	采购单id
     * items�?采购�?     * itemId�?采购需求id
     * status�?采购状�?     */
    @Transactional
    @Override
    public void done(PurchaseDoneVo doneVo) {
        // 1.改变采购单状�?        Long id = doneVo.getId();
        Boolean flag = true;
        List<PurchaseItemDoneVo> items = doneVo.getItems();
        ArrayList<PurchaseDetailEntity> updates = new ArrayList<>();
        double price;
        double p = 0;
        double sum = 0;
        // 2.改变采购项状�?// 我也懒得改成lambda了，这块已经熟练�?        for (PurchaseItemDoneVo item : items) {
            // 采购失败的情�?            PurchaseDetailEntity detailEntity = new PurchaseDetailEntity();
            if (item.getStatus() == WareConstant.PurchaseDetailStatusEnum.HASERROR.getCode()) {
                flag = false;
                detailEntity.setStatus(item.getStatus());
            } else {
                detailEntity.setStatus(WareConstant.PurchaseDetailStatusEnum.FINISH.getCode());
                // 3.将成功采购的进行入库
                // 查出当前采购项的详细信息
                PurchaseDetailEntity entity = detailService.getById(item.getItemId());
                // 新增库存�?skuId、到那个仓库、sku名字
                price = wareSkuService.addStock(entity.getSkuId(), entity.getWareId(), entity.getSkuNum());
                if (price != p) {
                    p = entity.getSkuNum() * price;
                }
                detailEntity.setSkuPrice(new BigDecimal(p));
                sum += p;
            }
            // 设置采购成功的id
            detailEntity.setId(item.getItemId());
            updates.add(detailEntity);
        }
        // 批量更新采购�?        detailService.updateBatchById(updates);

        // 对采购单的状态进行更�?        PurchaseEntity purchaseEntity = new PurchaseEntity();
        purchaseEntity.setId(id);
        purchaseEntity.setAmount(new BigDecimal(sum));
        purchaseEntity.setStatus(flag ? WareConstant.PurchaseStatusEnum.FINISH.getCode() : WareConstant.PurchaseStatusEnum.HASERROR.getCode());
        purchaseEntity.setUpdateTime(new Date());
        this.updateById(purchaseEntity);
    }
}
