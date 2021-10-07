package com.rounindiary.RouninDiary.service;

import com.rounindiary.RouninDiary.entity.Diary;
import com.rounindiary.RouninDiary.form.EditForm;

public interface EditService {

	Diary finById(int id);

	void update(Diary updateTarget);

	Diary setFormInput(EditForm editForm, Diary updateTarget);

	EditForm setDiaryInfo(Diary editTarget, EditForm editForm);


}
