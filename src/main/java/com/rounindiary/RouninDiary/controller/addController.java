package com.rounindiary.RouninDiary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class addController {

	/**
	 * 日記追加ページへのリクエスト
	 *
	 * @return add.htmlのパス
	 */
	@GetMapping("add")
	public String index() {
		return "add";
	}
}
