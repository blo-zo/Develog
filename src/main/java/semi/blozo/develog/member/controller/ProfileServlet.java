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
import semi.blozo.develog.member.model.vo.Member;
import semi.blozo.develog.member.model.vo.ProfileImgVO;
import semi.blozo.develog.member.model.vo.ProfileVO;
import semi.blozo.develog.post.model.vo.Blog;
import semi.blozo.develog.common.MyRenamePolicy;
import semi.blozo.develog.member.model.service.MemberProfileService;

@WebServlet("/member/profile/*")
public class ProfileServlet extends HttpServlet{
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		
		
		
		// doPost 안에서 진행 되어야함
		String method = req.getMethod();
		
		String uri =req.getRequestURI();
		String contextPath = req.getContextPath();
		String command = uri.substring(  (contextPath + "/member/").length() );
		
		String path = null;
		RequestDispatcher dispatcher = null;
		String message = null;
		
		
		HttpSession session = req.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		
		try {

			// 파일 들어오면 담을 객체 선언
			// 서비스 객체 선언
			MemberProfileService service = new MemberProfileService();
			
			
			// 텍스트 타입의 형태들 넣기 
			// 로그인 멤버로 부터 받기
			// member 닉네임, 한줄소개, blog 블로그(디벨로그 제목) ,member_sns 소셜정보 // member-img 프로필 이미지 
			// session에서 loginMember얻어오기
			int memberNo = loginMember.getMemberNo();
			
			// memberNo을 통해 해당 회원의 정보조회 있는지 찾음) 
			ProfileVO profileVO = service.selectProfile(memberNo);
			
			ProfileImgVO memberImg = service.selectProfileImg(memberNo);
			
			req.setAttribute("profileVO", profileVO);
			

			System.out.println("여기지나가니?");
			
			if(command.equals("profile/update")) {
				System.out.println("profile");
				// Member 객체를 생성하여 파라미터를 하나의 객체에 저장			
//				String memberNm = profileVO.getMemberNm();
//				String intro = profileVO.getIntro();
//				String blogTitle = profileVO.getBlogTitle();
//				
//				
//				// 소셜 정보 담기
//				String snsEmail = profileVO.getSnsEmail();
//				String snsGit = profileVO.getSnsGit();
//				String snsTwitt =  profileVO.getSnsTwitt();
//				String snsFbook =  profileVO.getSnsFbook();
//				String snsHome = profileVO.getSnsHome();
				// 개인 프로필  이미지
				int maxSize = 1024 * 1024 * 100;
				String root =  session.getServletContext().getRealPath("/");
				String filePath = "/resources/images/profile/";
				String realPath = root + filePath;
				
				MultipartRequest mReq 
				= new MultipartRequest( req, realPath, maxSize, "UTF-8", new MyRenamePolicy() );
				
				profileVO.setMemberNm(mReq.getParameter("nickname"));
				profileVO.setIntro(mReq.getParameter("line-intro"));
				profileVO.setBlogTitle(mReq.getParameter("devel-input"));
				
				// 2) 파일 형식의 파라미터
				Enumeration<String> files = mReq.getFileNames();
				
				memberImg = new ProfileImgVO();
				memberImg.setBlogNo(memberNo);
				memberImg.setMemberImgName("default");
				memberImg.setMemberImgOriginal("user.png");
				memberImg.setMemberImgPath("/resources/images/common/"); // 파일이 있는 주소 경로
				
				if( files.hasMoreElements() ) {
					// 썸네일 추가
					// 썸네일vo가 잘 담겨왔는지 확인 후 result postVO 방식처럼 진행되고
					
					String name = files.nextElement(); // 다음 요소값(name) 얻어오기
					
					if( mReq.getFilesystemName(name) != null) { 
						
						memberImg.setMemberImgName(mReq.getFilesystemName(name));
						memberImg.setMemberImgOriginal(mReq.getOriginalFileName(name));
						memberImg.setMemberImgPath(filePath); // 파일이 있는 주소 경로
						
					}
				}
				
				
				//조회 후 살리기
				int result = service.updateProfile(profileVO, memberImg);
				
					System.out.println(result);
				
				if(result > 0) {
					session.setAttribute("message", "회원 정보가 수정 되었습니다.");
					
					// 수정된 값이 화면에서도 보이게 세팅
					
				} else {
					session.setAttribute("message", "회원 정보 수정 실패. return값 확인하세요.");
				}
				
				resp.sendRedirect(req.getContextPath() +"/member/profile");
				
			}else {
				req.getRequestDispatcher("/WEB-INF/views/member/profile.jsp").forward(req, resp);				
			}
				
		} catch (Exception e) {
			e.printStackTrace();
			
			req.setAttribute("errorMessage", "회원 정보 수정 과정에서 오류 발생");
			req.setAttribute("e", e);
			
			req.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(req, resp);
		}
		
		
	}
	
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}