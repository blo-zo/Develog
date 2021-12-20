<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

			  <!-- 댓글 -->
	          
	          <!-- 댓글 개수 나타내는 영역 -->
          <div class="reply-count">
            총 <span style="font-size:20px; color:blue;"> ${replyCount} </span> 개의 댓글
          </div>

          <!-- 댓글 작성 영역 -->
          <div class="post-reply-write-area">

              <textarea name="post-reply" id="post-reply"></textarea>
  
              <button class="btn btn-primary btn-sm" id="post-reply-btn" style="float: right;">
                댓글 작성
              </button>


          </div>


          <!-- 작성된 댓글들 -->
          <div class="post-reply-box">

            <!-- 댓글 박스 -->
            
            <c:forEach items="${prList}" var="reply">
            
	            <div class="post-reply-wrap">
	              
	              <div class="reply-header">
	                <div class="reply-writer" style="margin-left: 30px;">
	                  <a href="${contextPath}/blog/${reply.memberName}">${reply.memberName}</a>
	                  <span style="margin-left:10px; font-size:10px">
	                  	${reply.replyCreateDate}
	                  </span>
	                </div>
	                
	                <div class="reply-menu" style="margin-right: 30px;">
	                  
	                  <c:choose>

	                  	<c:when test="${loginMember.memberNo == reply.memberNo}">
		                  <span onclick="">수정하기</span>
		                  <span onclick="">삭제하기</span>
	                  	</c:when>

						<c:otherwise>
		                  <span onclick="">신고하기</span>
						</c:otherwise>

	                  </c:choose>
	                  
	                </div>
	              </div>
	
	              <div class="reply-body" style="word-break:break-all;">
	                ${reply.replyContent}
	              </div>
	
	                <%-- <button class="btn btn-primary btn-sm" id="post-reply-btn" style="margin-right: 40px; margin-bottom: 20px;">댓글 수정</button> --%>
	
	            </div>
            
            </c:forEach>
           </div>
