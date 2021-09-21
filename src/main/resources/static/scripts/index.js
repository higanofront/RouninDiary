
class Index {

	constructor(){
		let thisClass = this;
		$('.search-button').click(function(e) {
			e.preventDefault();
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
							   'createdAtTo': createdAtTo,
							   'favoliteCountFrom': favoliteCountFrom,
							   'favoliteCountTo': favoliteCountTo
							   };
			let callback = thisClass.rebuildSearchResult();
			thisClass.ajaxGet(callback, searchConds);
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
	}

	rebuildSearchResult() {
		return function(data) {
			$('.diary-cards').empty();
			data.searchResults.forEach(function(diary) {
				let diaryCard = $('<div class="diary-card">').appendTo('.diary-cards');
				let article = $('<article></article>').appendTo(diaryCard);
				article.append('<a class="article-title" href="detail?id=1' + diary.id + '">' + diary.title + '</a>');
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
				let articleInfoItemFavoliteCount = $('<li class="article-info-item">').appendTo(articleInfo);
				articleInfoItemFavoliteCount.append('<i class="fas fa-thumbs-up"></i>');
				articleInfoItemFavoliteCount.append('<span>' + diary.favoliteCount + '</span>');
				article.append('<p class="article-text">' + diary.content + '</p>');
			});
		}
	}

	ajaxGet(callback, searchConds) {
		console.log("ajaxGetきました");
		$.ajax({
			url: "search",
			type: "Get",
			data: {keyWord: searchConds.keyWord,
				   createdBy: searchConds.createdBy,
				   examType: searchConds.examType,
				   createdAtFrom: searchConds.createdAtFrom,
				   createdAtTo: searchConds.createdAtTo,
				   favoliteCountFrom: searchConds.favoliteCountFrom,
				   favoliteCountTo: searchConds.favoliteCountTo
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
				console.log("エラーです");
			}
		});
	}

}



