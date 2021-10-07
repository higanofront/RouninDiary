package com.rounindiary.RouninDiary.form;

import java.util.Date;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class SearchForm {

	@Length(min = 0, max = 20)
	private String keyWord;

	@Length(min = 0, max = 20)
	private String createdBy;

	@Length(min = 0, max = 20)
	private String examType;

	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAtFrom;

	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAtTo;

}
