<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

			  <!-- 댓글 -->
	          
	          <!-- 댓글 개수 나타내는 영역 -->
	          <div class="reply-count">
	            <span> 0 </span> 개의 댓글
	          </div>
	
	          <!-- 댓글 작성 영역 -->
	          <div class="post-reply-write-area">
	            <form action="#">
	
	              <textarea name="post-reply" id="post-reply"></textarea>
	  
	              <button class="btn btn-primary btn-sm" id="post-reply-btn" style="float: right;">
	                댓글 작성
	              </button>
	
	            </form>
	
	          </div>
			
	
	          <!-- 작성된 댓글들 -->
	          <div class="post-replies border" style="margin: auto; margin-top: 100px; margin-bottom: 100px; clear: both; width: 600px; height: 250px;">
	
	            <!-- 댓글 박스 -->
	            <div class="post-reply">
	              <div style="display: flex; justify-content: space-between;">
	                <div>
	                  (사진) + 댓글작성자
	                </div>
	                
	                <div>
	                  <span>삭제하기</span>
	                  <span style="display: none;">신고하기</span>
	                  <span>답글달기</span>
	                </div>
	              </div>
	
	              <div style="width: 500px; min-height: 100px; margin: auto; margin-top: 30px; margin-bottom: 30px; padding: 10px; border: 1px solid black;">
	                <p>댓글 내용 부분</p>
	              </div>
	
	              <div>
	                +<span>n</span>개의 답글
	
	              </div>
	            </div>
	
	
	          </div>
