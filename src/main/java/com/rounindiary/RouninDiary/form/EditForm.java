package com.rounindiary.RouninDiary.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditForm {

	Integer id;

	String title;

	String tag;

	String examType;

	String content;
}
