package com.terenceqin.gulimall.order.service.impl;

import com.atguigu.common.utils.PageUtils;
import com.atguigu.common.utils.Query;
import com.terenceqin.gulimall.order.dao.OrderOperateHistoryDao;
import com.terenceqin.gulimall.order.entity.OrderOperateHistoryEntity;
import com.terenceqin.gulimall.order.service.OrderOperateHistoryService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("orderOperateHistoryService")
public class OrderOperateHistoryServiceImpl
        extends ServiceImpl<OrderOperateHistoryDao, OrderOperateHistoryEntity>
        implements OrderOperateHistoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OrderOperateHistoryEntity> page = this.page(
                new Query<OrderOperateHistoryEntity>().getPage(params),
                new QueryWrapper<OrderOperateHistoryEntity>()
        );

        return new PageUtils(page);
    }

}
