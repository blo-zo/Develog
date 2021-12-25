package semi.blozo.develog.member.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import semi.blozo.develog.board.model.vo.ThumbImgVO;
import semi.blozo.develog.common.MyRenamePolicy;
import semi.blozo.develog.member.model.service.MemberProfileService;

@WebServlet("/member/profile")
public class ProfileServlet extends HttpServlet{
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/member/profile.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		// doPost 안에서 진행 되어야함
		String method = req.getMethod();
		
		String uri =req.getRequestURI();
		String contextPath = req.getContextPath();
		String command = uri.substring(  (contextPath + "/board/").length() );
		
		String path = null;
		RequestDispatcher dispatcher = null;
		String message = null;
		
		
		
		
		try {
			// 파일 들어오면 담을 객체 선언
			// 서비스 객체 선언
			MemberProfileService service = new MemberProfileService();
			
			
			// 텍스트 타입의 형태들 넣기 
			// 한줄소개, 블로그(디벨로그 제목) 
			
			
			
			// 썸네일 이미지
			HttpSession session = req.getSession();
			
			int maxSize = 1024 * 1024 * 100;
			String root =  session.getServletContext().getRealPath("/");
			String filePath = "/resources/images/profile/";
			String realPath = root + filePath;
			
			MultipartRequest mReq 
			= new MultipartRequest( req, realPath, maxSize, "UTF-8", new MyRenamePolicy() );
			
			// 2) 파일 형식의 파라미터
			Enumeration<String> files = mReq.getFileNames();
			
			ThumbImgVO profileImg = new ThumbImgVO();
			
			if( files.hasMoreElements() ) {
				// 썸네일 추가
				// 썸네일vo가 잘 담겨왔는지 확인 후 result postVO 방식처럼 진행되고
				
				
				
				String name = files.nextElement(); // 다음 요소값(name) 얻어오기
				
				if( mReq.getFilesystemName(name) != null) { 
					
					//변경된 값들을 담을 객체
	//				ThumbImgVO temp = new ThumbImgVO();
					
					profileImg.setThumbImgName(mReq.getFilesystemName(name));
					profileImg.setThumbImgOriginal(mReq.getOriginalFileName(name));
					profileImg.setThumbImgPath(filePath); // 파일이 있는 주소 경로
					
					
					
					// imgList에 추가 
				
				}
				// service로 넘기기
				
//				profileImg 객체에 담으면 된다 
			}
		
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		
	
	
	
	}
		

	
}
