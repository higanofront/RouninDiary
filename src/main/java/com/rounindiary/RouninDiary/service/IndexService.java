package com.rounindiary.RouninDiary.service;

import java.text.ParseException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.rounindiary.RouninDiary.dto.DiaryDto;
import com.rounindiary.RouninDiary.entity.Diary;
import com.rounindiary.RouninDiary.form.SearchForm;

public interface IndexService {

	 Specification<Diary> likeTitle(String searchTitle);

	 Specification<Diary> likeTag(String searchTag);

	 Specification<Diary> likeContent(String searchContent);

	 Specification<Diary> greaterThanCreatedAtFrom(String searchCreatedAtFrom) throws ParseException;

	 Specification<Diary> lessThanCreatedAtTo(String searchCreatedTo) throws ParseException;

	 Specification<Diary> likeCreatedBy(String searchCreatedBy);

	 Specification<Diary> likeExamType(String searchExamType);

	 Page<Diary> findAll(Pageable pageable);

	 DiaryDto searchDiary(SearchForm searchForm, Pageable pageable) throws ParseException;
}
