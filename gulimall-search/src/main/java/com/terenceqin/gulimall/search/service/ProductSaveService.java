package com.terenceqin.gulimall.search.service;


import com.atguigu.common.to.es.SkuEsModel;

import java.io.IOException;
import java.util.List;

/**
 * <p>Title: ProductSaveService</p>
 * Description�? * date�?020/6/8 21:15
 */
public interface ProductSaveService {


	boolean productStatusUp(List<SkuEsModel> skuEsModels) throws IOException;
}
