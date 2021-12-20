// ****** 공통 검색창 JS *******

// 최근 검색어 삭제
$(".search-delete").on("click", function(){

  const recentText = $(this).parent();

  recentText.remove();

});

// 전체 삭제하기
$("#search-delete-all").on("click",function(){

  const recentTexts = $(this).parent().siblings();

  recentTexts.remove();

});


// 정렬 방식 선택하기
// 클릭한것만 커지게
$(".blog-menu > div").on("click",function(){

  $(".blog-menu > div").removeAttr("class");

  $(this).addClass("under-line-on");

  $(this).siblings().addClass("under-line-off");
  
});





// ************* 댓글 *************

