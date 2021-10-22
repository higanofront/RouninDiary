package com.rounindiary.RouninDiary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rounindiary.RouninDiary.dto.DiaryDto;
import com.rounindiary.RouninDiary.entity.Diary;
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
	public String getIndex(
			Model model,
			@PageableDefault(page = 0, size = 5) Pageable pageable) {
		Page<Diary> page = indexService.findAll(pageable);
		model.addAttribute("page", page);
		model.addAttribute("Diarys", page.getContent());
        model.addAttribute("path", "/");
		return "index";
	}

	/**
	 * 検索リクエスト
	 *
	 * @return 検索結果をセットしたインスタンス
	 */
	@GetMapping("search")
	@ResponseBody
	public DiaryDto getSearch(
			@ModelAttribute @Validated SearchForm searchForm,
			BindingResult bindingResult,
			@PageableDefault(page = 0, size = 5) Pageable pageable) {
		if(bindingResult.hasErrors()){
			return indexService.returnError();
		}
		return indexService.searchDiary(searchForm, pageable);
	}

}
