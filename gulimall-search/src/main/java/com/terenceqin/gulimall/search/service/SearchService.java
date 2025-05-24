package com.terenceqin.gulimall.search.service;


import com.terenceqin.gulimall.search.vo.SearchParam;
import com.terenceqin.gulimall.search.vo.SearchResult;

/**
 * <p>Title: MallService</p>
 * Descriptionï¼? * dateï¼?020/6/12 23:05
 */
public interface SearchService {

	/**
	 * æ£€ç´¢æ‰€æœ‰å‚æ•?	 */
	SearchResult search(SearchParam Param);
}
