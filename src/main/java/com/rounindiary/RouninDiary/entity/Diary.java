package com.rounindiary.RouninDiary.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="diary")
public class Diary {

	@Id
	@Column(name = "id")
	private Integer id;

	@Column(name = "title")
	private String title;

	@Column(name = "content")
	private String content;

	@Column(name = "tag")
	private String tag;

	@Column(name = "created_by")
	private Integer created_by;

	@Column(name = "created_at")
	private Date created_at;

	@Column(name = "favolite_count")
	private Integer favolite_count;
}




