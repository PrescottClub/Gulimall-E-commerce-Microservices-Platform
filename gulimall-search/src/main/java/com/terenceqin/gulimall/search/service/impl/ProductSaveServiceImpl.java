package com.terenceqin.gulimall.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.atguigu.common.to.es.SkuEsModel;
import com.terenceqin.gulimall.search.config.GuliESConfig;
import com.terenceqin.gulimall.search.constant.EsConstant;
import com.terenceqin.gulimall.search.service.ProductSaveService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>Title: ProductSaveServiceImpl</p>
 * Description�? * date�?020/6/8 21:16
 */
@Slf4j
@Service
public class ProductSaveServiceImpl implements ProductSaveService {

	@Resource
	private RestHighLevelClient client;

	/**
	 * 将数据保存到ES
	 * 用bulk代替index，进行批量保�?	 * BulkRequest bulkRequest, RequestOptions options
	 */
	@Override // ProductSaveServiceImpl
	public boolean productStatusUp(List<SkuEsModel> skuEsModels) throws IOException {
		// 1. 批量保存
		BulkRequest bulkRequest = new BulkRequest();
		// 2.构造保存请�?		for (SkuEsModel esModel : skuEsModels) {
			// 设置es索引 gulimall_product
			IndexRequest indexRequest = new IndexRequest(EsConstant.PRODUCT_INDEX);
			// 设置索引id
			indexRequest.id(esModel.getSkuId().toString());
			// json格式
			String jsonString = JSON.toJSONString(esModel);
			indexRequest.source(jsonString, XContentType.JSON);
			// 添加到文�?			bulkRequest.add(indexRequest);
		}
		// bulk批量保存
		BulkResponse bulk = client.bulk(bulkRequest, GuliESConfig.COMMON_OPTIONS);
		// TODO 是否拥有错误
		boolean hasFailures = bulk.hasFailures();
		if(hasFailures){
			List<String> collect = Arrays.stream(bulk.getItems()).map(item -> item.getId()).collect(Collectors.toList());
			log.error("商品上架错误：{}",collect);
		}
		return hasFailures;
	}
}
