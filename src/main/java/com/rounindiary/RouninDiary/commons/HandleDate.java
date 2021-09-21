package com.rounindiary.RouninDiary.commons;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HandleDate {

	public static Date parseStringToDate(String string) throws ParseException {
		if(string.isEmpty()) {
			return null;
		}
		String[] muchDateInfo = string.split("-");
		String formatedString = muchDateInfo[0] + "/" + muchDateInfo[1] + "/" + muchDateInfo[2];
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
		return simpleDateFormat.parse(formatedString);
	}
}
