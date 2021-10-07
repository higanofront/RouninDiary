package com.rounindiary.RouninDiary.service;

import java.util.Date;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import com.rounindiary.RouninDiary.dto.DiaryDto;
import com.rounindiary.RouninDiary.entity.Diary;
import com.rounindiary.RouninDiary.form.SearchForm;
import com.rounindiary.RouninDiary.repository.DiaryRepository;

@Service
public class IndexServiceImpl implements IndexService {

	@Autowired
	DiaryRepository diaryRepository;

	@Autowired
	DiaryDto diaryDto;

	@Override
	public Page<Diary> findAll(Pageable pageable) {
		return diaryRepository.findAll(pageable);
	}

	public DiaryDto searchDiary(SearchForm searchForm, Pageable pageable) {
		Page<Diary> diarys = diaryRepository.findAll(Specification
				.where(this.likeTitle(searchForm.getKeyWord()))
				.or(this.likeTag(searchForm.getKeyWord()))
				.or(this.likeContent(searchForm.getKeyWord()))
				.and(this.greaterThanCreatedAtFrom(searchForm.getCreatedAtFrom()))
				.and(this.likeCreatedBy(searchForm.getCreatedBy()))
				.and(this.likeExamType(searchForm.getExamType()))
				.and(this.lessThanCreatedAtTo(searchForm.getCreatedAtTo())),
				pageable);
		diaryDto.setPage(diarys);
		diaryDto.setSearchResults(diarys.getContent());
		diaryDto.setIsError(false);
		return diaryDto;
	}

	public DiaryDto returnError() {
		diaryDto.setIsError(true);
		return diaryDto;
	}

	@Override
	public Specification<Diary> likeTitle(String searchTitle) {
        return (root, query, cb) -> {
        	if(StringUtils.isEmpty(searchTitle)) {
				return cb.conjunction();
			}
            return cb.like(root.get("title"), "%" + searchTitle + "%");
        };
    }

	@Override
	public Specification<Diary> likeTag(String searchTag) {
        return (root, query, cb) -> {
        	if(StringUtils.isEmpty(searchTag)) {
				return cb.conjunction();
			}
            return cb.like(root.get("tag"), "%" + searchTag + "%");
        };
    }

	@Override
	public Specification<Diary> likeContent(String searchContent) {
        return (root, query, cb) -> {
        	if(StringUtils.isEmpty(searchContent)) {
				return cb.conjunction();
			}
            return cb.like(root.get("content"), "%" + searchContent + "%");
        };
    }

	@Override
	public Specification<Diary> greaterThanCreatedAtFrom(Date searchCreatedAtFrom) {
        return (root, query, cb) -> {
        	if(Objects.isNull(searchCreatedAtFrom)) {
				return cb.conjunction();
			}
            return cb.greaterThan(root.get("createdAt"), searchCreatedAtFrom);
        };
    }

	@Override
	public Specification<Diary> lessThanCreatedAtTo(Date searchCreatedAtTo) {
        return (root, query, cb) -> {
        	if(Objects.isNull(searchCreatedAtTo)) {
				return cb.conjunction();
			}
            return cb.lessThan(root.get("createdAt"), searchCreatedAtTo);
        };
    }

	@Override
	public Specification<Diary> likeCreatedBy(String searchCreatedBy) {
		return (root, query, cb) -> {
			if(StringUtils.isEmpty(searchCreatedBy)) {
				return cb.conjunction();
			}
			return cb.equal(root.get("created_by"), searchCreatedBy);
        };
	}

	@Override
	public Specification<Diary> likeExamType(String searchExamType) {
		return (root, query, cb) -> {
			if(StringUtils.isEmpty(searchExamType)) {
				return cb.conjunction();
			}
            return cb.equal(root.get("examType"), searchExamType);
        };
	}
}
