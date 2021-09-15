package com.rounindiary.RouninDiary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.rounindiary.RouninDiary.service.IndexServiceImpl;

@Controller
public class IndexController {

	@Autowired
	IndexServiceImpl indexServiceImpl;

	/**
	 * トップページへのリクエスト
	 * @return output.htmlのパス
	 */
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("Diarys", indexServiceImpl.findAll());
		return "index";
	}
}
