package com.terenceqin.gulimall.ware.service.impl;

import com.alibaba.fastjson.TypeReference;
import com.atguigu.common.utils.R;
import com.terenceqin.gulimall.ware.feign.MemberFeignService;
import com.terenceqin.gulimall.ware.vo.FareVo;
import com.terenceqin.gulimall.ware.vo.MemberAddressVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Random;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.common.utils.Query;

import com.terenceqin.gulimall.ware.dao.WareInfoDao;
import com.terenceqin.gulimall.ware.entity.WareInfoEntity;
import com.terenceqin.gulimall.ware.service.WareInfoService;
import org.springframework.util.StringUtils;


@Service("wareInfoService")
public class WareInfoServiceImpl extends ServiceImpl<WareInfoDao, WareInfoEntity> implements WareInfoService {

    @Autowired
    private MemberFeignService memberFeignService;

    @Override // WareInfoServiceImpl
    public PageUtils queryPage(Map<String, Object> params) { // 传入分页信息
        QueryWrapper<WareInfoEntity> wrapper = new QueryWrapper<>();

        // 查询关键�?        String key = (String) params.get("key");
        if (!StringUtils.isEmpty(key)) {
            // 仓库编号、仓库名字、仓库地址、区域编�?            wrapper.eq("id", key).or().like("name", key).or().like("address", key).or().like("areacode", key);
        }
        // 执行
        IPage<WareInfoEntity> page = this.page(
                new Query<WareInfoEntity>().getPage(params),
                wrapper
        );
        return new PageUtils(page);
    }

    @Override
    public FareVo getFare(Long addrId) {

        R info = memberFeignService.addrInfo(addrId);
        FareVo fareVo = new FareVo();
        // 获取用户地址
        MemberAddressVo addressVo = info.getData("memberReceiveAddress", new TypeReference<MemberAddressVo>() {
        });
        fareVo.setMemberAddressVo(addressVo);
        if (addressVo != null) {
            // 假设电话�?位为运费
            String phone = addressVo.getPhone();
            if (phone == null || phone.length() < 2) {
                phone = new Random().nextInt(100) + "";
            }
            BigDecimal decimal = new BigDecimal(phone.substring(phone.length() - 1));
            fareVo.setFare(decimal);
        } else {
            fareVo.setFare(new BigDecimal("20"));
        }
        return fareVo;
    }
}
