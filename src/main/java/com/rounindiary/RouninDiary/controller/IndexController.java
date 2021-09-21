package com.rounindiary.RouninDiary.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rounindiary.RouninDiary.commons.DiarysDto;
import com.rounindiary.RouninDiary.form.SearchForm;
import com.rounindiary.RouninDiary.service.IndexService;

@Controller
public class IndexController {

	@Autowired
	IndexService indexService;

	/**
	 * トップページへのリクエスト
	 *
	 * @return index.htmlのパス
	 */
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("Diarys", indexService.findAll());
		return "index";
	}

	/**
	 * 検索リクエスト
	 *
	 * @return 検索結果をセットしたインスタンス
	 */
	@GetMapping("search")
	@ResponseBody
	public DiarysDto getSearch(SearchForm searchForm) throws ParseException {
		return indexService.searchDiary(searchForm);
	}

}
