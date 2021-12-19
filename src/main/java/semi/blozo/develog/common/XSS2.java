package semi.blozo.develog.common;

public class XSS2 {
	
	public static String replaceParameter(String parameter) {
		
		if(parameter != null) {
			parameter = parameter.replaceAll("&", "&amp;");
			parameter = parameter.replaceAll("<", "&lt;");
			parameter = parameter.replaceAll(">", "&gt;");
			parameter = parameter.replaceAll("\"", "&quot;");
		}
		
		return parameter;
	}
	
}