package com.rounindiary.RouninDiary.dto;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.rounindiary.RouninDiary.entity.Diary;
import com.rounindiary.RouninDiary.form.SearchForm;

import lombok.Data;

@Data
@Component
public class DiaryDto {

	private List<Diary> searchResults;

	private SearchForm SearchForm;

	private String path = "search";

	private Page<Diary> page;

}


