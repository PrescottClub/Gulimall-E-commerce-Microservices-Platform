package com.terenceqin.gulimall.product.vo;

import lombok.Data;

/**
 * <p>Title: AttrRespVo</p>
 * Description�? * date�?020/6/2 19:56
 */
@Data
public class AttrRespVo extends AttrVo{

	private String catelogName;

	private String groupName;

	private Long[] catelogPath;
}
