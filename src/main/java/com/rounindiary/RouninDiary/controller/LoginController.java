package com.rounindiary.RouninDiary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.rounindiary.RouninDiary.entity.MUser;
import com.rounindiary.RouninDiary.service.LoginService;



@Controller
public class LoginController {

	@Autowired
	LoginService loginService;

	/** ログイン画面を表示 */
    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    /** ユーザー一覧画面にリダイレクト */
    @PostMapping("/login")
    public String postLogin() {
        return "redirect:index";
    }

    /** ユーザー一覧画面にリダイレクト */
    @GetMapping("/userRegist")
    public String getUserRegist(
    		@ModelAttribute MUser muser) {
        return "userRegist";
    }

    @PostMapping("/userRegist")
    public String postUserRegist(
    		@ModelAttribute MUser muser) {
    	// ユーザー登録
    	loginService.signup(muser);
        return "redirect:/login";
    }

    /** ログイン画面にリダイレクト */
    @PostMapping("/logout")
    public String postLogout() {
        return "redirect:/login";
    }
}
