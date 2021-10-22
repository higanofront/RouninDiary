package com.rounindiary.RouninDiary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rounindiary.RouninDiary.entity.Diary;
import com.rounindiary.RouninDiary.service.DetailService;
import com.rounindiary.RouninDiary.service.LoginService;

@Controller
public class DetailController {

	@Autowired
	DetailService detailService;

	@Autowired
	LoginService loginService;

	@GetMapping("detail")
	public String getPostDetail (
			Model model,
			Authentication loginUser,
			@RequestParam final Integer id) {
		Diary detailTarget = detailService.findById(id);
		String loginUserName = loginService.findById(loginUser.getName()).getName();
		model.addAttribute("diary", detailTarget);
		model.addAttribute("isCreatedBy", detailTarget.getCreatedBy().equals(loginUserName));

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
