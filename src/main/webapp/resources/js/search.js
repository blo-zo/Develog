
// ****** 공통 검색창 JS *******

// ready 함수
$(function(){

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
  
  
  
  // 검색어 value 값으로 화면에 결과 표시
  
  // $("#search-form").on("submit",function(){
  //   const inputValue = $("#search-input").val();
  //   const searchResult =  $(".search-result-large > span");
    
  //   searchResult.text(inputValue);
  //   console.log("gggggggggg");
  //   console.log(inputValue);
  //   console.log(searchResult);
  // });


  // $("#search-input").on("input",function(){
    
  //   $(".search-result-large > span").text($("#search-input").val());
    
  // });

  
  
  
  
  
  
  
  
  
  
});

// 검색 함수
function test() {
  const inputValue = $("#search-input").val();

  const searchResult = $(".search-result-large > span");
  searchResult.text(inputValue);

  return false;
}
