package com.rounindiary.RouninDiary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rounindiary.RouninDiary.entity.Diary;
import com.rounindiary.RouninDiary.form.EditForm;
import com.rounindiary.RouninDiary.repository.DiaryRepository;

@Service
public class EditServiceImpl implements EditService{

	@Autowired
	DiaryRepository diaryRepository;

	@Override
	public void update(Diary diary) {
		diaryRepository.save(diary);
	}

	@Override
	public Diary finById(int id) {
		return diaryRepository.findById(id).get();
	}

	@Override
	public Diary setFormInput(EditForm editForm, Diary updateTarget) {
		updateTarget.setId(editForm.getId());
		updateTarget.setTitle(editForm.getTitle());
		updateTarget.setTag(editForm.getTag());
		updateTarget.setExamType(editForm.getExamType());
		updateTarget.setContent(editForm.getContent());
		updateTarget.setCreatedBy(editForm.getCreatedBy());
		return updateTarget;
	}

}
