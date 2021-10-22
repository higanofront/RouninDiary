package com.rounindiary.RouninDiary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rounindiary.RouninDiary.entity.Diary;
import com.rounindiary.RouninDiary.form.AddForm;
import com.rounindiary.RouninDiary.repository.DiaryRepository;

@Service
public class AddServiceImpl implements AddService {

	@Autowired
	DiaryRepository diaryRepository;

	public void saveDiary(Diary diary) {
		diaryRepository.save(diary);
	}

	public Diary setForminput(AddForm addform, Diary diary) {
		diary.setTitle(addform.getTitle());
		diary.setTag(addform.getTag());
		diary.setContent(addform.getContent());
		diary.setExamType(addform.getExamType());

		return diary;
	}
}
