package com.rounindiary.RouninDiary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rounindiary.RouninDiary.entity.Diary;
import com.rounindiary.RouninDiary.repository.DiaryRepository;

@Service
public class DetailServiceImpl implements DetailService {

	@Autowired
	DiaryRepository diaryRepository;

	public Diary findById(Integer id) {
		return diaryRepository.findById(id).get();
	}

	public void deleteById(Integer id) {
		diaryRepository.deleteById(id);
	}

}
