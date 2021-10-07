package com.rounindiary.RouninDiary.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.rounindiary.RouninDiary.entity.Diary;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Integer>, JpaSpecificationExecutor<Diary>  {

	void save(DiaryRepository diary);

	Page<Diary> findAll(Pageable pageable);

}
