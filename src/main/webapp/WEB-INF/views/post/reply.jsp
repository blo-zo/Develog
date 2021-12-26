<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

			  <!-- 댓글 -->
	          
	          <!-- 댓글 개수 나타내는 영역 -->
          <div class="reply-count">
            총 <span style="font-size:20px; color:#3278FE;"> ${replyCount} </span> 개의 댓글
          </div>

          <!-- 댓글 작성 영역 -->
          <div class="post-reply-write-area">

              <textarea name="post-reply" id="post-reply"></textarea>
              
			  <input class="form-check-input" type="checkbox" id="secretReply">
			  <label class="form-check-label" for="secretReply">
			     비밀 댓글
			  </label>
  
              <button class="btn btn-primary btn-sm" id="post-reply-btn" style="float: right;" onclick="addReply()">
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
		                  <span onclick="showUpdateReply(${reply.replyNo}, this)">수정하기</span>
		                  <span onclick="deleteReply(${reply.replyNo})">삭제하기</span>
	                  	</c:when>

						<c:otherwise>
						  
						  <c:if test="${loginMember.memberNo == post.memberNo}">
						  	<span onclick="deleteReply(${reply.replyNo})">삭제하기</span>
						  </c:if>

		                  <span onclick="reportReply(${reply.replyNo}, this)">신고하기</span>

						</c:otherwise>

	                  </c:choose>
	                  
	                </div>
	              </div>
	              
	              <c:choose>
	              	<c:when test="${reply.replyStatusCode == 601}">
	              		<div class="reply-body" style="word-break:break-all; font-size:20px; color:red;">
	              			<p>블라인드 처리된 댓글입니다.</p>
	              		</div>
	              	</c:when>
	              	
	              	<c:when test="${(reply.replyStatusCode == 602) && (loginMember.memberNo == reply.memberNo || loginMember.memberNo == post.memberNo)}">
	              		<div class="reply-body" style="word-break:break-all;">
			                <p>${reply.replyContent}</p>
		                	
		              	</div>
	              	</c:when>

	              	<c:when test="${reply.replyStatusCode == 602 }">
						<div class="reply-body" style="word-break:break-all; color:#0000ffb5; font-size:20px;">
							<p>비밀 댓글입니다.</p>
		              	</div>
	              	</c:when>
	              	
					<c:otherwise>
		              <div class="reply-body" style="word-break:break-all;">
		                <p>${reply.replyContent}</p>
		              </div>
					</c:otherwise>	              	
	              	
	              </c:choose>
	
	
	                <%-- <button class="btn btn-primary btn-sm" id="post-reply-btn" style="margin-right: 40px; margin-bottom: 20px;">댓글 수정</button> --%>
	
	            </div>
            
            </c:forEach>
           </div>
