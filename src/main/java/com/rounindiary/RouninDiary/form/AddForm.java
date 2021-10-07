package com.rounindiary.RouninDiary.form;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class AddForm {

	@NotBlank
	@Length(min = 1, max = 20)
	private String title;

	@NotBlank
	@Length(min = 1, max = 200)
	private String content;

	@Length(min = 0, max = 20)
	private String tag;

	@NotBlank
	@Length(min = 1, max = 20)
	private String createdBy;

	@NotBlank
	@Length(min = 1, max = 20)
	private String examType;

}
