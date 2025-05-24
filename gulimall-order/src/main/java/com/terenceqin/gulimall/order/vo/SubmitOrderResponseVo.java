package com.terenceqin.gulimall.order.vo;

import com.terenceqin.gulimall.order.entity.OrderEntity;
import lombok.Data;

/**
 * <p>Title: SubmitOrderResponseVo</p>
 * Description�? * date�?020/7/1 22:50
 */
@Data
public class SubmitOrderResponseVo {

	private OrderEntity orderEntity;

	/**
	 * 错误状态码�?0----成功
	 * 1 库存不足
	 * 2 验证失败
	 */
	private Integer code;
}
