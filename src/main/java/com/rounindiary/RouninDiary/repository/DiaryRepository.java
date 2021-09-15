package com.rounindiary.RouninDiary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rounindiary.RouninDiary.entity.Diary;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Integer> {

	/**
	 * IDの降順で全検索した結果を返却します
	 * @return 全検索結果（降順）
	 */
	public List<Diary> findAllByOrderByIdDesc();

}
