
class Index {

	constructor() {
		let thisClass = this;

		/*検索*/
		$('.search-button').click(function(e) {
			e.preventDefault();
			let searchConds = thisClass.getSearchConds();
			let callback = thisClass.rebuildSearchResult();
			let url = 'search';
			thisClass.ajaxGet(callback, searchConds, url);
		});

		// ヘッダー、ハンバーガーメニュー
		$('.drower-icon').click(function(e) {
		  e.preventDefault();
		  $('.drower-icon').toggleClass('is-active');
		  $('.drower-icon-bars').children().toggleClass('is-active');
		  $('.drower-content').toggleClass('is-active');
		  $('.drower-background').toggleClass('is-active');
		  return false;
		});

		// 検索条件、アコーディオン
		$('.question-bars').click(function(){
		  $(this).parent().next().slideToggle();
		  $(this).children('.question-bar2').toggleClass('is-open');
		});

		/*ページネーション*/
		thisClass.watchPagenationEvent();

		/*日記詳細ページ遷移*/
		thisClass.watchDetailEvent();

		// 日記を探すスムーススクロール
		$('a[href^="#"]').click(function() {
		  // 移動速度を指定
		  let speed = 400;
		  // hrefで指定されたidを取得
		  let id = $(this).attr("href");
		  // idの値が#のみだった場合、topへ戻るようにする
		  let target = $("#" == id ? "html" : id);
		  // ページのトップを基準にターゲットの位置を取得
		  let position = $(target).offset().top;
		  $("html, body").animate(
		    {
		      scrollTop: position
		    },
		    speed
		  );
		  return false;
		});

		/*並び替え*/
		$('.sort-select').change(function() {
		    var sort = $(this).val();
			if(sort === "Unselected") {
				return false;
			}
			let searchConds = thisClass.getSearchConds();
			let callback = thisClass.rebuildSearchResult();
			let url = 'search?sort=' + sort;
			thisClass.ajaxGet(callback, searchConds, url);
		});

		/*並び替えボタン、ホバー時デザイン変更処理*/
		$('.sort-select').hover(function(){
			$('.sort-select').toggleClass('hoverd');
			$('.sort-select-container').toggleClass('hoverd');
		});
	}

	rebuildSearchResult() {
		let thisClass = this;
		return function(data) {
			if(data.isError) {
				thisClass.createErrorMsg();
			} else {
				thisClass.removeErrorMsg();
				thisClass.createResultCount(data);
				thisClass.createDiarys(data);
				thisClass.createPagenation(data);
				thisClass.watchPagenationEvent();
				thisClass.watchDetailEvent();
			}
		}
	}

	ajaxGet(callback, searchConds, url) {
		console.log("ajaxGetきました");
		$.ajax({
			url: url,
			type: "Get",
			data: {keyWord: searchConds.keyWord,
				   createdBy: searchConds.createdBy,
				   examType: searchConds.examType,
				   createdAtFrom: searchConds.createdAtFrom,
				   createdAtTo: searchConds.createdAtTo
				   },
			dataType: "Json",
			timeout: 30000,
			success: function(data) {
				console.log("ajax成功しました");
				if(callback) {
					callback(data);
				}
			},
			error: function(data) {
				console.log("ajaxエラーです");
			}
		});
	}

	getSearchConds() {
		let keyWord = $('input[name="keyword"]').val();
		let createdBy = $('input[name="created_by"]').val();
		let examType = $('input[name="exam_type"]').val();
		let createdAtFrom = $('input[name="created_at-from"]').val();
		let createdAtTo = $('input[name="created_at-to"]').val();
		let favoliteCountFrom = $('input[name="favolite_count-from"]').val();
		let favoliteCountTo = $('input[name="favolite_count-to"]').val();
		let searchConds = {'keyWord': keyWord,
						   'name': name,
						   'examType': examType,
						   'createdAtFrom': createdAtFrom,
						   'createdAtTo': createdAtTo
						   };
		return searchConds;
	}

	createDiarys(data) {
		$('.diary-cards').empty();
		data.searchResults.forEach(function(diary) {
			let diaryCard = $('<div class="diary-card">').appendTo('.diary-cards');
			let article = $('<article></article>').appendTo(diaryCard);
			article.append('<a class="article-title" href="detail?id=' + diary.id + '">' + diary.title + '</a>');
			article.append('<h3 class="article-tag" >' + diary.tag + '</h3>');
			let articleInfo = $('<ul class="article-info"></ul>').appendTo(article);
			let articleInfoItemCreatedBy = $('<li class="article-info-item">').appendTo(articleInfo);
			articleInfoItemCreatedBy.append('<i class="fas fa-user-alt"></i>');
			articleInfoItemCreatedBy.append('<span>' + diary.createdBy + '</span>');
			let articleInfoItemExamType = $('<li class="article-info-item">').appendTo(articleInfo);
			articleInfoItemExamType.append('<i class="fas fa-pen"></i>');
			articleInfoItemExamType.append('<span>' + diary.examType + '</span>');
			let articleInfoItemCreatedAt = $('<li class="article-info-item">').appendTo(articleInfo);
			articleInfoItemCreatedAt.append('<i class="fas fa-clock"></i>');
			articleInfoItemCreatedAt.append('<span>' + diary.createdAt + '</span>');
			article.append('<p class="article-text">' + diary.content + '</p>');
		});
	}

	createPagenation(data) {
		$('.pagination-list').empty();
		let paginationPre = $('<li id="pagination-pre" class="pagination-list-item"></li>').appendTo('.pagination-list');
		if(data.page.first) {
			paginationPre.append('<span>&lt;&lt;前</span>');
		} else {
			paginationPre.append('<span data-page="' + (data.page.number - 1) + '">&lt;&lt;前</span>');
		}
		for(var i = 0; i < data.page.totalPages; i++) {
			let paginationNumber = $('<li id="pagination-number" class="pagination-list-item">').appendTo('.pagination-list');
			if(i == data.page.number) {
				paginationNumber.append('<span>' + (i + 1) + '</span>');
				paginationNumber.addClass('appear');
			} else {
				let paginationLink = $('<div class="pagination-link search-pagenation" data-page="' + i + '"></div>').appendTo(paginationNumber);
				paginationLink.append('<span>' + (i + 1) +'</span>');
			}
		}
		let paginationNext = $('<li id="pagination-next" class="pagination-list-item"></li>').appendTo('.pagination-list');
		if(data.page.last) {
			paginationNext.append('<span>次&gt;&gt;</span>');
		} else {
			paginationNext.append('<span data-page="' + (data.page.number + 1) + '">次&gt;&gt;</span>');
		}
	}

	watchPagenationEvent() {
		let thisClass = this;
		/*ページネーションクリック後*/
		$('.pagination-list-item > span, .pagination-list-item > div').click(function(e){
			e.preventDefault();
			let page = $(this).data().page;
			let sort = $('.sort-select').val();
			let url = 'search?page=' + page;
			if(sort !== "Unselected") {
				url = 'search?page=' + page + '&sort=' + sort;
			}
		  	let searchConds = thisClass.getSearchConds();
			let callback = thisClass.rebuildSearchResult();
			thisClass.ajaxGet(callback, searchConds, url);
		});
	}

	watchDetailEvent() {
		$('diary-card, article').click(function(){
			let articleTitle = $(this).find('.article-title');
			let url = articleTitle.attr('href');
			window.open(url);
		});
	}

	removeErrorMsg() {
		$('.error-msg-container').remove();
	}

	createErrorMsg() {
		let errorMsgContainer = $('<div class="error-msg-container"></div>').insertAfter('.search-add-cond-table');
		errorMsgContainer.append('<p>※キーワード・投稿者・浪人種別は20文字以下で入力してください');
	}

	createResultCount(data) {
		$('.count-number').empty();
		$('.count-number').text(data.page.totalElements);
		$('.unit').text('件');
	}

}



