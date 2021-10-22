package com.rounindiary.RouninDiary.service;

import com.rounindiary.RouninDiary.entity.MUser;

public interface LoginService {

	public void signup(MUser MUser);

	public MUser findById(String name);
}
