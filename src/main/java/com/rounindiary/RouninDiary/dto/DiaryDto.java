package com.rounindiary.RouninDiary.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DiaryDto {

	@Id
	private Integer id;
	private String keyWord;
	private String title;
	private String content;
	private String tag;
	private Integer userId;
	private String name;
	private String examType;
	private String createdAtFrom;
	private String createdAtTo;
	private Date createdAt;
	private Integer favoliteCountFrom;
	private Integer favoliteCountTo;
	private Integer favoliteCount;

}
