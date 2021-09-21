package com.rounindiary.RouninDiary.commons;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.rounindiary.RouninDiary.entity.Diary;
import com.rounindiary.RouninDiary.form.SearchForm;

import lombok.Data;

@Data
@Component
public class DiarysDto {

	private List<Diary> searchResults;

	private SearchForm SearchForm;

	public void setPostsList(List<Diary> diarys) {
		searchResults = new ArrayList<Diary>();
		diarys.stream().forEach(diary -> searchResults.add(diary));
	}
}


