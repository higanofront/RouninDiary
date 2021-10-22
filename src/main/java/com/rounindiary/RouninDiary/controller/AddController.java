package com.rounindiary.RouninDiary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.rounindiary.RouninDiary.commons.HandleDate;
import com.rounindiary.RouninDiary.entity.Diary;
import com.rounindiary.RouninDiary.form.AddForm;
import com.rounindiary.RouninDiary.service.AddService;
import com.rounindiary.RouninDiary.service.LoginService;

@Controller
public class AddController {

	@Autowired
	AddService addService;

	@Autowired
	LoginService loginService;

	/**
	 * 日記追加ページへのリクエスト
	 *
	 * @return add.htmlのパス
	 */
	@GetMapping("add")
	public String getAdd (
			@ModelAttribute AddForm addForm,
			Model model) {
		return "add";
	}

	@PostMapping("regist")
	public String getRegist (
			@ModelAttribute @Validated AddForm addForm,
			BindingResult bindingResult,
			Authentication loginUser,
			Model model) {
		if(bindingResult.hasErrors()) {
			return "add";
		}
		Diary diary = new Diary();
		Diary settedInputDiary = addService.setForminput(addForm, diary);
		String loginUserName = loginService.findById(loginUser.getName()).getName();
		settedInputDiary.setCreatedBy(loginUserName);
		HandleDate.setCreationDate(settedInputDiary);
		addService.saveDiary(settedInputDiary);

		return "redirect:/";
	}
}
