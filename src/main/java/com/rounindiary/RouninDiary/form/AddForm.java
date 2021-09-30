package com.rounindiary.RouninDiary.form;

import lombok.Data;

@Data
public class AddForm {

	private Integer id;

	private String title;

	private String content;

	private String tag;

	private String createdBy;

	private String examType;

}
