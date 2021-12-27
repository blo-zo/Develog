//console.log("search.js");
// ****** 공통 검색창 JS *******

	/* function search(){
   		const searchInput = document.getElementById("search-input").value
   		console.log(searchInput);
  		if(searchInput.trim().length != 0){
	 	$.ajax({
  				
				url : contextPath + "/search",
  				
	 			type : "GET",
  				
  				dataType : "JSON",
				data : {"searchInput" : searchInput},
				success : function(searchPost){
					
  					console.log(searchPost);
  			

					},
           error : function(req, status, error){
               console.log(req.responseText);
            console.log(status);
               console.log(error);
         				 }
 	 				 });
 				}	


		 }*/


	





//ready 함수
// $(function(){

//   // 최근 검색어 삭제
//   $(".search-delete").on("click", function(){
  
//     const recentText = $(this).parent();
  
//     recentText.remove();
  
//   });
  
//   // 전체 삭제하기
//   $("#search-delete-all").on("click",function(){
  
//     const recentTexts = $(this).parent().siblings();
  
//     recentTexts.remove();
  
//   });
  
  
  
//   //검색어 value 값으로 화면에 결과 표시
  
//   $("#search-form").on("submit",function(){
//     const inputValue = $("#search-input").val();
//     const searchResult =  $(".search-result-large > span");
    
//     searchResult.text(inputValue);
//     console.log("gggggggggg");
//     console.log(inputValue);
//     console.log(searchResult);
//   });


//   $("#search-input").on("input",function(){
    
//     $(".search-result-large > span").text($("#search-input").val());
    
//   });

  
  
  
  
  
  
  
  
  
  
//  });

// // 검색 함수
// function test() {
//   const inputValue = $("#search-input").val();

//   const searchResult = $(".search-result-large > span");
//   searchResult.text(inputValue);

//   return false;
// }
