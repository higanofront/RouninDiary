package com.rounindiary.RouninDiary.service;

import com.rounindiary.RouninDiary.entity.Diary;

public interface DetailService {

	Diary findById(Integer id);

	void deleteById(Integer id);
}
