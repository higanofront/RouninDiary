package com.rounindiary.RouninDiary.commons;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import com.rounindiary.RouninDiary.entity.Diary;

public class HandleDate {

	public static Date parseStringToDate(String string) throws ParseException {
		if(Objects.isNull(string) || string.isEmpty()) {
			return null;
		}
		String[] muchDateInfo = string.split("-");
		String formatedString = muchDateInfo[0] + "/" + muchDateInfo[1] + "/" + muchDateInfo[2];
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
		return simpleDateFormat.parse(formatedString);
	}

	public static void setCreationDate(Diary diary) {
//    	現在日時の取得
    	java.util.Date nowDate = new java.util.Date();
    	diary.setCreatedAt(nowDate);
    }
}
