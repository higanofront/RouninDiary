package com.rounindiary.RouninDiary.form;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EditForm {

	Integer id;

	@NotBlank
	@Length(min = 1, max = 20)
	String title;

	@Length(min = 0, max = 20)
	String tag;

	@NotBlank
	@Length(min = 1, max = 20)
	String examType;

	@NotBlank
	@Length(min = 1, max = 200)
	String content;
}
