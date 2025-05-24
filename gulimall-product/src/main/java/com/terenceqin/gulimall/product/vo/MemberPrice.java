package com.terenceqin.gulimall.product.vo;

import lombok.Data;

import java.math.BigDecimal;
/**
 * <p>Title: AttrRespVo</p>
 * Description：会员价�? * date�?020/6/2 19:56
 */
@Data
public class MemberPrice {

    private Long id;

    private String name;

    private BigDecimal price;
}
