package semi.blozo.develog.common;

public class XSS {
	
	// 크로스사이트스크립트 공격을 방지하기 위한 메서드
	public static String repalceParameter(String parameter) {
		
		if(parameter != null) { // 전달된 값이 null이 아니라면 
			parameter = parameter.replaceAll("&", "&amp;");
			parameter = parameter.replaceAll("<", "&lt;");
			parameter = parameter.replaceAll(">", "&gt;");
			parameter = parameter.replaceAll("\"", "&quot;");
			
		}
		return parameter;
		
		
	}
	
}
