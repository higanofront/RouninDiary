package com.rounindiary.RouninDiary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.rounindiary.RouninDiary.entity.Diary;
import com.rounindiary.RouninDiary.form.EditForm;
import com.rounindiary.RouninDiary.service.EditService;

@Controller
public class EditController {

	@Autowired
	EditService editService;

	@GetMapping("edit")
	public String getEdit (
			@RequestParam int id,
			Model model) {
		Diary editTarget = editService.finById(id);
		EditForm editForm = new EditForm();
		EditForm settedEditForm = editService.setDiaryInfo(editTarget, editForm);
		model.addAttribute("editForm", settedEditForm);
		return "edit";
	}

	@GetMapping("update")
	public String getUpdate (
			@ModelAttribute @Validated EditForm editForm,
			BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "edit";
		}
		Diary updateTarget = editService.finById(editForm.getId());
		Diary settedInputDiary = editService.setFormInput(editForm, updateTarget);
		editService.update(settedInputDiary);
		return "redirect:/";
	}
}
