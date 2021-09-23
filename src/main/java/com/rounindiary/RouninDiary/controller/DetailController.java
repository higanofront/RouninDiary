package com.rounindiary.RouninDiary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rounindiary.RouninDiary.entity.Diary;
import com.rounindiary.RouninDiary.service.DetailService;

@Controller
public class DetailController {

	@Autowired
	DetailService detailService;

	@GetMapping("detail")
	public String getPostDetail (
			Model model,
			@RequestParam final Integer id) {
		Diary detailTarget = detailService.findById(id);
		model.addAttribute("diary", detailTarget);

		return "detail";
	}

	@GetMapping("delete")
	public String getDelete (
			@RequestParam int id,
			Model model) {
		detailService.deleteById(id);

		return "redirect:/";
	}

}
