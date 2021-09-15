package com.rounindiary.RouninDiary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rounindiary.RouninDiary.entity.Diary;
import com.rounindiary.RouninDiary.repository.DiaryRepository;

@Service
public class IndexServiceImpl implements IndexService {

	@Autowired
	DiaryRepository diaryRepository;

	@Override
	public List<Diary> findAll() {
		return diaryRepository.findAll();
	}

}
