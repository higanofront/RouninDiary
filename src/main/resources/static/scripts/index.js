// ヘッダー、ハンバーガーメニュー
$('.drower-icon').click(function(e) {
  e.preventDefault();
  
  $('.drower-icon').toggleClass('is-active');
  $('.drower-icon-bars').children().toggleClass('is-active');
  $('.drower-content').toggleClass('is-active');
  $('.drower-background').toggleClass('is-active');

  return false;
});