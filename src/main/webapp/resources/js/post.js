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


console.log("hi");


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

          replyBody.text("블라인드 처리된 댓글입니다.").css("color","red").css("font-size", "20px");

        }else if(reply.replyStatusName == "비밀"){

          // 댓글 작성자 == 로그인멤버 OR 게시글 작성자 == 로그인멤버인 경우
          if(reply.memberNo == loginMemberNo || loginMemberNo == postMemberNo){
            
            replyBody.text(reply.replyContent);

          }else{
            
            replyBody.text("비밀 댓글입니다.").css("color", "blue").css("font-size","20px");

          }

        }else{
          
          replyBody.text(reply.replyContent);

        }

        $(".post-reply-box").append(replyWrap);

      });

      // 조회마다 댓글 개수 변경
      $(".reply-count > span").text(prList.length);

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
                  "replyContent" : $("#post-reply").val() 
               },
        type : "POST",
        
        success : function(result){

          if(result > 0){

            alert("댓글 작성 성공");
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


// 댓글 수정
function showUpdateReply(replyNo, el){

};



// 댓글 삭제
function deleteReply(){

};




// 댓글 신고
function reportReply(replyNo){

  console.log("hi");

};











