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

// 클릭한것만 커지게
$(".blog-menu > div").on("click",function(){

  $(".blog-menu > div").removeAttr("class");

  $(this).addClass("under-line-on");

  $(this).siblings().addClass("under-line-off");
  
});

// //  블로그 포스트 클릭 시 동작(파라미터 넘기기)
// function sendBlog(e, pno, blogMemberName, blogIntro, blogTitle){
  
//   e.preventDefault(); // a태그 기본이벤트 제거

//   $("input[name=pno]").val(pno);
//   $("input[name=blogMemberName]").val(blogMemberName);
//   $("input[name=blogIntro]").val(blogIntro);
//   $("input[name=blogTitle]").val(blogTitle);
  
//   document.sendBlogInfo.action = contextPath + "/blog/" + postMemberName + "/view";
// 	document.sendBlogInfo.submit();
// }

// ************* 포스트 *************

// 수정 클릭 시 동작
function updateForm(){
  document.requestForm.action = "updateForm";
  document.requestForm.method = "POST";
  document.requestForm.submit();
};

// 포스트 삭제
function deletePost(){
  
  if(confirm("정말 삭제하시겠습니까?")){

    document.requestForm.action = "delete";
    document.requestForm.method = "POST";
    document.requestForm.submit();
    
  }
};

// 포스트 신고하기
function reportPost(postNumber){

  if(loginMemberNo != ""){  // 회원이 신고를 누르면

    $("#reportPostModal").modal('show');

  }else{

    alert("로그인 후 이용 가능한 서비스입니다.");
    $("#exampleModal").modal('show');

  }

}



// ************* 댓글 *************


// 댓글 조회
function selectReplyList(){

  $.ajax({
      
    url : contextPath + "/blog/" + memberName + "/reply/select",
    data : {"postNo" : postNo},
    type : "GET",
    dataType : "JSON",

    success : function(prList){

      console.log(prList);

      $(".post-reply-box").empty();
  
      $.each( prList, function( index, reply ){


        const replyWrap = $('<div class="post-reply-wrap">');
        const replyHeader = $('<div class="reply-header">');
        const replyWriter = $('<div class="reply-writer" style="margin-left: 30px;">');
        const rWriter = $('<a>').attr("href", contextPath + "/blog/" + reply.memberName ).text(reply.memberName);
        const replyCreateDate = $('<span style="margin-left:10px; font-size:10px">').text(reply.replyCreateDate);

        const replyBody = $('<div class="reply-body" style="word-break:break-all;">');
        const rContent = $('<p>');

        replyWriter.append(rWriter);
        replyWriter.append(replyCreateDate);
        replyHeader.append(replyWriter);

        if(loginMemberNo == reply.memberNo){

          const replyMenu = $('<div class="reply-menu" style="margin-right: 30px;">');
          const showUpdate = $('<span>').attr("onclick", "showUpdateReply(" + reply.replyNo +", this)" ).text("수정하기");
          const deleteReply = $('<span>').attr("onclick", "deleteReply(" + reply.replyNo + ", this)").text("삭제하기");

          replyMenu.append(showUpdate, deleteReply);
       
          replyHeader.append(replyMenu);

        } else{

          const replyMenu = $('<div class="reply-menu" style="margin-right: 30px;">');
          const reportReply = $('<span>').attr("onclick", "reportReply(" + reply.replyNo + ", this)").text("신고하기");

          replyMenu.append(reportReply);
       
          replyHeader.append(replyMenu);
          
        }

        replyWrap.append(replyHeader, replyBody);

        if(reply.replyStatusName == "블라인드"){

          replyBody.append(rContent);
          rContent.text("블라인드 처리된 댓글입니다.").css("color","red").css("font-size", "20px");

        }else if(reply.replyStatusName == "비밀"){

          // 댓글 작성자 == 로그인멤버 OR 게시글 작성자 == 로그인멤버인 경우
          if(reply.memberNo == loginMemberNo || loginMemberNo == postMemberNo){
            
            replyBody.append(rContent);
            rContent.html(reply.replyContent);

          }else{
            
            replyBody.append(rContent);
            rContent.text("비밀 댓글입니다.").css("color", "blue").css("font-size","20px");

          }

        }else{
          
          replyBody.append(rContent);
          rContent.html(reply.replyContent);

        }

        $(".post-reply-box").append(replyWrap);

      });

      // 조회마다 댓글 개수 변경
      $(".reply-count > span").text(prList.length);

      // 비밀글 체크박스 체크 해제
      $("#secretReply").prop("checked", false);

    },

    error : function(req, status, error){
      console.log("댓글 목록 조회 실패")
      console.log(req.responseText)
    }

  });

}


// 댓글 삽입
function addReply(){

  // 테스트 할 때는 !=로 바꿔서 로그인 없이 작성되도록

  if(loginMemberNo == ""){  // 로그인이 되어있지 않은 경우
    
    alert("로그인 후 댓글 작성이 가능합니다.");
    $("#exampleModal").modal('show');

  }else{

    // 로그인 한 경우

    if( $("#post-reply").val().trim().length == 0 ){ // 댓글 내용을 작성하지 않은 경우
      
      alert("댓글 내용을 작성해주세요.");
      $("#post-reply").focus();

    }else{    // 댓글 내용을 작성한 경우


      $.ajax({

        url : contextPath + "/blog/" + memberName + "/reply/insert",
        data : {  "memberNo" : loginMemberNo , 
                  "postNo" : postNo , 
                  "replyContent" : $("#post-reply").val(),
                  "secretReply" :  $("#secretReply").is(":checked")
               },
        type : "POST",
        
        success : function(result){

          if(result > 0){

            $("#post-reply").val(""); // 작성한 댓글 내용 비우기
            selectReplyList(); // 댓글 조회 함수를 호출하여 댓글 화면 다시 만들기

          }else{
            alert("댓글 작성 실패");
          }

        },

        error : function(req, status, error){
          console.log("댓글 삽입 실패")
          console.log(req.responseText)
        }

      });

    }

  }

}

// -----------------------------  댓글 수정 ----------------------------
// 댓글 수정폼 열기
function showUpdateReply(replyNo, el){

  //console.log($(".replyUpdateContent").length);

  // 이미 열려있는 댓글 수정 창이 있을 경우 닫아주기
  if ($(".replyUpdateContent").length > 0) {
      
    if(confirm("확인 클릭 시 작성한 내용이 사라집니다.")){

        $(".replyUpdateContent").eq(0).parent().html(beforeReplyRow);

    }else{
        return;
    }
  }

  // 수정 전 댓글 요소 저장
  beforeReplyRow = $(el).parent().parent().parent().html();
  // console.log(beforeReplyRow);

  // 수정 전 댓글 내용
  let beforeContent = $(el).parent().parent().next().children().html();
  // console.log(beforeContent);

  // XSS 해제
  beforeContent = beforeContent.replace(/&amp;/g, "&");
  beforeContent = beforeContent.replace(/&lt;/g, "<");
  beforeContent = beforeContent.replace(/&gt;/g, ">");
  beforeContent = beforeContent.replace(/&quot;/g, "\"");

  beforeContent = beforeContent.replace(/<br>/g, "\n");

  // 수정 textarea
  $(el).parent().parent().next().remove();
  const textarea = $('<textarea class="replyUpdateContent" style="width:500px; height:100px; resize:none; background-color:#eeeeee50; outline:none; margin-left:40px; margin-top:10px; margin-bottom:5px; padding:10px;">').val(beforeContent);
  $(el).parent().parent().after(textarea);

  // 수정 버튼
  const updateReply = $('<button style="color:white; margin-left:40px; background-color:#3278FE;">').addClass("btn btn-sm ml-1 mb-4").text("댓글 수정").attr("onclick", "updateReply(" + replyNo + ", this)");
  
  // 취소 버튼
  const cancelBtn = $('<button style="color:white; background-color:#3278FE; margin-left:10px;">').addClass("btn btn-sm ml-1 mb-4").text("취소").attr("onclick", "updateCancel(this)");

  const btnArea = $(el).parent().parent().parent();

  // $(btnArea).empty();
  $(btnArea).append(updateReply);
  $(btnArea).append(cancelBtn);

};

// 취소 버튼 누르면 원래 작성 화면으로
function updateCancel(el) {
  
  // el : 클릭된 취소 버튼
  $(el).parent().html(beforeReplyRow);

};

// 댓글 수정
function updateReply(replyNo, el) {

  // 댓글 수정 버튼의 부모의 이전 요소 값
  const replyContent = $(el).prev().val();

  $.ajax({

    url : contextPath + "/blog/" + memberName + "/reply/update",
    data : {"replyNo" : replyNo , "replyContent" : replyContent },
    type : "POST",

    success : function(result){

      if(result > 0){   // 수정 성공

        alert("댓글이 수정되었습니다.");
        selectReplyList(); 
        
      }else{

        alert("댓글 수정 실패");

      }

    },

    error : function(req, status, error){
      console.log("댓글 수정 실패")
      console.log(req.responseText)
    }

  });

}


// 댓글 삭제
function deleteReply(replyNo){

  $.ajax({

    url : contextPath + "/blog/" + memberName + "/reply/delete",
    data : { "replyNo" : replyNo },
    type : "POST",

    success : function(result){

      if(confirm( "정말 삭제하시겠습니까?" )){

        if(result > 0){
          
          alert("댓글이 삭제되었습니다.");
          selectReplyList();
  
        }else{
          alert("댓글 삭제 실패");
        }
  
      }else{

        return;

      }

    },

    error : function(req, status, error){
      console.log("댓글 삭제 실패")
      console.log(req.responseText)
    }

  });

};

function insertEnquiry(){
	const title =$("#enquiryTitle").val()
	const content = $("#enquiryContent").val()	
	console.log(title);
	if(title.trim().length == 0){
		alert("문의 제목을 입력하세요");
		$("#enquiryTitle").focus();
		return false;
	}
	if(content.trim().length == 0 ){
		alert("문의사항을 등록해주세요");
		$("#enquiryContent").focus();
		return false;
	}
	if(confirm("문의사항을 등록하시겠습니까?")){
		return;
	}else{
		return false;
	}
}

// 댓글 신고창 띄우기
function reportReply(replyNo){

  if(loginMemberNo == ""){  // 비회원이 신고를 누르면

    alert("로그인 후 이용 가능한 서비스입니다.");
    $("#exampleModal").modal('show');
    
  }else{
    
    $("#reportReplyModal").modal('show');

    $(".report-btn").on("click", function(){

      if($(".reportReplyContent").val().trim().length == 0 ){
  
        alert("신고 내용을 입력해주세요");
        $(".reportReplyContent").focus();
  
      }else{
  
        $.ajax({
  
          url : contextPath + "/blog/" + memberName + "/reply/report",
          data : {  "memberNo" : loginMemberNo , 
                    "postNo" : postNo , 
                    "reportReplyContent" : $(".reportReplyContent").val(),
                    "replyNo" : replyNo,
                    "memberName" : memberName
                },
          type : "POST",
          
          success : function(result){
  
            if(result > 0){
  
              $("#reportReplyModal").modal('hide');
              alert("신고가 정상 접수되었습니다.");
              location.href= contextPath + "/blog/" + memberName + "/view/?pno=" + postNo;
  
            }else{
              alert("신고 처리과정 중 문제 발생");
            }
  
          },
  
          error : function(req, status, error){
            console.log("신고 과정 중 문제 발생")
            console.log(req.responseText)
          }
  
        });
  
      }
  
    });

  }


};




/* ------------ 블로그 ---------------- */


/* 카테고리 메뉴 삽입, 수정, 삭제 */

// 카테고리 목록 조회
function selectCategoryList(){

  $.ajax({

    url : contextPath + "/blog/" + memberName + "/category/select",
    data : {"blogNo" : blogNo, "memberName" : memberName},
    type : "GET",
    dataType : "JSON",

    success : function(categoryList){

      console.log(categoryList);

      $(".list-box").empty();

      $.each( categoryList, function( index, category){

        const listBox = $(".list-box");

        const li = $('<li class="list-group-item category userCategory" onclick="findOrDelete(this, ${category.categoryCode}, "${category.categoryName}");">');

        if(category.categoryName != "없음"){

          li.text(category.categoryName);
  
          listBox.append(li);

        }
      });

    },

    error : function(req, status, error){
      console.log("카테고리 목록 조회 실패")
      console.log(req.responseText)
    }

  });

}


var flag = true;  // 중복 클릭 방지용


// 카테고리 추가하기
function addCategory(){

  const input = $('<input size="15" type="text" name="categoryName" autocomplete="off" place placeholder="생성할 카테고리(15자 이내)" style="width:200px; height:30px; outline:none; margin-left:10px; margin-top:10px; border:0.5px solid lightgray;">');

  const listBox = $(".list-box");
  
  if(flag){

    flag = false;
    
    listBox.append(input);


    input.on("blur",function(){
      input.remove();
      flag = true;
    });


    input.on("keyup", function(e){
      
      if(e.keyCode == 13){  // 엔터키 키업

        if(input.val().trim().length== 0){
          alert("카테고리명을 입력해주세요");
          input.focus();
        }else{

        }

        $.ajax({

          url : contextPath + "/blog/" + memberName + "/category/add",
          data : {  "categoryName" : input.val(), 
                    "blogNo" : blogNo , 
                 },
          type : "POST",
          
          success : function(result){
  
            if(result > 0){ // 삽입 성공
  
              const li = $('<li class="list-group-item category userCategory" onclick="findOrDelete(this, ${category.categoryCode}, "${category.categoryName}");">');
        
              li.text(input.val());
              listBox.append(li);
              input.remove();

              selectCategoryList();
              flag = true;

  
            }else{

              alert("카테고리 추가 중 문제 발생");
              input.remove();
              flag = true;

            }
  
          },
  
          error : function(req, status, error){
            console.log("카테고리 추가 실패")
            console.log(req.responseText)
            flag=true;
          }
  
        });
  
      }
  
    });

  }

}


// 카테고리 삭제하기
// 기존 카테고리에 있는 글을 카테고리 '없음'으로 변경 
// --> 카테고리 삭제

// 삭제폼 전환시키기
function deleteCategory(){

  console.log($(".userCategory"));

  const categories = $(".userCategory");

  if(categories.hasClass("delete-form")){

    categories.removeClass("delete-form");

  }else{

    categories.addClass("delete-form");

  }

}

function findOrDelete(el, categoryCode, categoryName){

  // 제출 주소 : category/remove
  // result > 0 이면 삭제 성공

  const categories = $(".userCategory");
  
    if($(el).hasClass("delete-form")){

      $.ajax({

        url : contextPath + "/blog/" + memberName + "/category/remove",
        data : {"blogNo" : blogNo, "categoryName" : categoryName,
                 "categoryCode" : categoryCode },
        type : "POST",
    
        success : function(result){
          
          if(result > 0){ 
            alert("카테고리가 삭제되었습니다.");
            $(el).remove();
            selectCategoryList();
          }else{
            alert("카테고리 삭제 중 문제 발생");
          }
          
    
        },
    
        error : function(req, status, error){
          console.log("카테고리 삭제 실패")
          console.log(req.responseText);
        }
    
      });

    }

    
  //   // delete-form이 없을 때는 해당 카테고리 게시글 조회하기
    
  //   console.log("검색용");
  //   console.log(this);

  // }

}







// --------------------------- 좋아요 -----------------------------


// 좋아요 버튼
$("#like-btn").on("click", function() {

  if (loginMemberNo != "") {
    
    $.ajax({
      url : contextPath + "/blog/" + memberName + "/like",
      type : "GET",
      data : {"postNo" : postNo},
      success : function(favoriteCount) {
      
        if (favoriteCount > 0) {
          
          
          if ($("#like-btn > img").hasClass("liked")) {
            $("#like-btn > img").removeClass("liked");
            $("#like-btn > img").attr("src", contextPath + "/resources/images/KYJ/emptyHeart.svg");
            
          } else { 
            $("#like-btn > img").addClass("liked");
            $("#like-btn > img").attr("src", contextPath+ "/resources/images/KYJ/filledHeart.svg");
          }

        }

        selectPostLike();

      }

    }); 
    
  } else  // 로그인 하지 않은 경우 로그인창으로 이동
    alert("로그인 후 이용 가능한 서비스입니다.");
    $(".login-btn").click();
});


// 포스트 좋아요 수 조회
function selectPostLike(){

  $.ajax({
    url : contextPath + "/blog/" + memberName + "/selectPostLike",
    data : {"postNo" : postNo},
    success : function(postLikeCount) {
      
      $(".favorite-count").text(postLikeCount);

    }
  }); 

}


// 공유하기 버튼 누르면 url 복사되도록
$("#share-btn").on("click",function(){

  var url = '';
	var textarea = document.createElement("textarea");
	document.body.appendChild(textarea);
	url = window.document.location.href;
	textarea.value = url;
	textarea.select();
	document.execCommand("copy");
	document.body.removeChild(textarea);
	alert("URL이 복사되었습니다.")

});





