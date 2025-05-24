package com.terenceqin.gulimall.search.controller;

import com.terenceqin.gulimall.search.service.SearchService;
import com.terenceqin.gulimall.search.vo.SearchParam;
import com.terenceqin.gulimall.search.vo.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>Title: SearchController</p>
 * Description�? */
@Controller
public class SearchController {

	@Autowired
	private SearchService searchService;

	@GetMapping("/list.html")
	public String listPage(SearchParam searchParam, Model model, HttpServletRequest request){

		// 获取路径原生的查询属�?		searchParam.set_queryString(request.getQueryString());
		// ES中检索到的结�?传递给页面
		SearchResult result = searchService.search(searchParam);
		model.addAttribute("result", result);
		return "list";
	}
}
