package com.rounindiary.RouninDiary.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.rounindiary.RouninDiary.commons.DiarysDto;
import com.rounindiary.RouninDiary.commons.HandleDate;
import com.rounindiary.RouninDiary.entity.Diary;
import com.rounindiary.RouninDiary.form.SearchForm;
import com.rounindiary.RouninDiary.repository.DiaryRepository;
import com.rounindiary.RouninDiary.repository.UserRepository;

@Service
public class IndexServiceImpl implements IndexService {

	@Autowired
	DiaryRepository diaryRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	DiarysDto diarysDto;

	@Override
	public List<Diary> findAll() {
		return diaryRepository.findAll();
	}

	@Override
	public DiarysDto searchDiary(SearchForm searchForm) throws ParseException {
		List<Diary> diarys = diaryRepository.findAll(Specification
				.where(this.likeTitle(searchForm.getKeyWord()))
				.or(this.likeTag(searchForm.getKeyWord()))
				.or(this.likeContent(searchForm.getKeyWord()))
				.and(this.greaterThanCreatedAtFrom(searchForm.getCreatedAtFrom()))
				.and(this.lessThanCreatedAtTo(searchForm.getCreatedAtFrom()))
				.and(this.greaterThanFavoliteCountFrom(searchForm.getFavoliteCountFrom()))
				.and(this.likeCreatedBy(searchForm.getCreatedBy()))
				.and(this.likeExamType(searchForm.getExamType()))
				);
		System.out.println("検索結果" + diarys);
		diarysDto.setPostsList(diarys);
		diarysDto.setSearchForm(searchForm);

		return diarysDto;
	}

	@Override
	public Specification<Diary> likeTitle(String searchTitle) {
        return (root, query, cb) -> {
        	if(searchTitle.isEmpty()) {
				return cb.conjunction();
			}
            return cb.like(root.get("title"), "%" + searchTitle + "%");
        };
    }

	@Override
	public Specification<Diary> likeTag(String searchTag) {
        return (root, query, cb) -> {
        	if(searchTag.isEmpty()) {
				return cb.conjunction();
			}
            return cb.like(root.get("tag"), "%" + searchTag + "%");
        };
    }

	@Override
	public Specification<Diary> likeContent(String searchContent) {
        return (root, query, cb) -> {
        	if(searchContent.isEmpty()) {
				return cb.conjunction();
			}
            return cb.like(root.get("content"), "%" + searchContent + "%");
        };
    }

	@Override
	public Specification<Diary> greaterThanCreatedAtFrom(String searchCreatedAtFrom) throws ParseException {
		Date parsedDate = HandleDate.parseStringToDate(searchCreatedAtFrom);
        return (root, query, cb) -> {
        	if(searchCreatedAtFrom.isEmpty()) {
				return cb.conjunction();
			}
            return cb.greaterThan(root.get("createdAt"), parsedDate);
        };
    }

	@Override
	public Specification<Diary> lessThanCreatedAtTo(String searchCreatedTo) throws ParseException {
		Date parsedDate = HandleDate.parseStringToDate(searchCreatedTo);
        return (root, query, cb) -> {
        	if(searchCreatedTo.isEmpty()) {
				return cb.conjunction();
			}
            return cb.lessThan(root.get("createdAt"), parsedDate);
        };
    }

	@Override
	public Specification<Diary> greaterThanFavoliteCountFrom(Integer searchFavoliteCountFrom) {
        return (root, query, cb) -> {
        	if(Objects.isNull(searchFavoliteCountFrom)) {
				return cb.conjunction();
			}
            return cb.greaterThan(root.get("favoliteCount"), searchFavoliteCountFrom);
        };
    }

	@Override
	public Specification<Diary> lessThanFavoliteCountTo(Integer searchFavoliteCountTo) {
        return (root, query, cb) -> {
        	if(Objects.isNull(searchFavoliteCountTo)) {
				return cb.conjunction();
			}
            return cb.lessThan(root.get("favoliteCount"), searchFavoliteCountTo);
        };
    }

	@Override
	public Specification<Diary> likeCreatedBy(String searchCreatedBy) {
		return (root, query, cb) -> {
			if(Objects.isNull(searchCreatedBy)) {
				return cb.conjunction();
			}
			return cb.equal(root.get("created_by"), searchCreatedBy);
        };
	}

	@Override
	public Specification<Diary> likeExamType(String searchExamType) {
		return (root, query, cb) -> {
			if(searchExamType.isEmpty()) {
				return cb.conjunction();
			}
            return cb.equal(root.get("examType"), searchExamType);
        };
	}

}
