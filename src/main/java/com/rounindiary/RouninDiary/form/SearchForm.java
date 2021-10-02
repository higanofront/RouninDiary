package com.rounindiary.RouninDiary.form;

import lombok.Data;

@Data
public class SearchForm {

	private String keyWord;

	private String createdBy;

	private String examType;

	private String createdAtFrom;

	private String createdAtTo;

}
