package semi.common;

public class XSS {
	
	// 크로스 사이트 스크립트 공격을 방지하기 위한 메소드
	public static String replaceParameter(String parameter) {
		if(parameter != null) {
			parameter = parameter.replaceAll("&", "&amp");
			parameter = parameter.replaceAll("<", "&lt");
			parameter = parameter.replaceAll(">", "&gt");
			parameter = parameter.replaceAll("\"", "&quot");
			
		}
		return parameter;
	}
}
