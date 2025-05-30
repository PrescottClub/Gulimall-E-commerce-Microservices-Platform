package com.terenceqin.gulimall.order.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * ιθ΄§εε? * 
 */
@Data
@TableName("oms_order_return_reason")
public class OrderReturnReasonEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * ιθ΄§εε ε
	 */
	private String name;
	/**
	 * ζεΊ
	 */
	private Integer sort;
	/**
	 * ε―η¨ηΆζ?	 */
	private Integer status;
	/**
	 * create_time
	 */
	private Date createTime;

}
