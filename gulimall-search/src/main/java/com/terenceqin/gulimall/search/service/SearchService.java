package com.terenceqin.gulimall.search.service;


import com.terenceqin.gulimall.search.vo.SearchParam;
import com.terenceqin.gulimall.search.vo.SearchResult;

/**
 * <p>Title: MallService</p>
 * Description�? * date�?020/6/12 23:05
 */
public interface SearchService {

	/**
	 * 检索所有参�?	 */
	SearchResult search(SearchParam Param);
}
