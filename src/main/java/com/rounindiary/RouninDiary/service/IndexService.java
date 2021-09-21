package com.rounindiary.RouninDiary.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.rounindiary.RouninDiary.commons.DiarysDto;
import com.rounindiary.RouninDiary.entity.Diary;
import com.rounindiary.RouninDiary.form.SearchForm;

public interface IndexService {

	 List<Diary> findAll();

	 Specification<Diary> likeTitle(String searchTitle);

	 Specification<Diary> likeTag(String searchTag);

	 Specification<Diary> likeContent(String searchContent);

	 Specification<Diary> greaterThanCreatedAtFrom(String searchCreatedAtFrom) throws ParseException;

	 Specification<Diary> lessThanCreatedAtTo(String searchCreatedTo) throws ParseException;

	 Specification<Diary> greaterThanFavoliteCountFrom(Integer searchFavoliteCountFrom);

	 Specification<Diary> lessThanFavoliteCountTo(Integer searchFavoliteCountTo);

	 DiarysDto searchDiary(SearchForm searchForm) throws ParseException;

	 Specification<Diary> likeCreatedBy(String searchCreatedBy);

	 Specification<Diary> likeExamType(String searchExamType);
}
