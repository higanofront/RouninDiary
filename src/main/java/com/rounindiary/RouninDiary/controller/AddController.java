package com.rounindiary.RouninDiary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.rounindiary.RouninDiary.commons.HandleDate;
import com.rounindiary.RouninDiary.entity.Diary;
import com.rounindiary.RouninDiary.service.AddService;

@Controller
public class AddController {

	@Autowired
	AddService addService;

	/**
	 * 日記追加ページへのリクエスト
	 *
	 * @return add.htmlのパス
	 */
	@GetMapping("add")
	public String getAdd (
			Model model) {
		Diary diary = new Diary();
		model.addAttribute("Diary", diary);

		return "add";
	}

	@PostMapping("regist")
	public String getRegist (
			Diary diary,
			Model model) {
		HandleDate.setCreationDate(diary);
		addService.saveDiary(diary);

		return "redirect:/";
	}
}
