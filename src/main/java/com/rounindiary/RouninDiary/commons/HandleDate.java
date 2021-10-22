package com.rounindiary.RouninDiary.commons;

import com.rounindiary.RouninDiary.entity.Diary;

public class HandleDate {

	public static void setCreationDate(Diary diary) {
//    	現在日時の取得
    	java.util.Date nowDate = new java.util.Date();
    	diary.setCreatedAt(nowDate);
    }
}
