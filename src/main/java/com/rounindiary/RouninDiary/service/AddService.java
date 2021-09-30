package com.rounindiary.RouninDiary.service;

import com.rounindiary.RouninDiary.entity.Diary;
import com.rounindiary.RouninDiary.form.AddForm;

public interface AddService {

	void saveDiary(Diary diary);

	Diary setForminput(AddForm addForm, Diary diary);

}
