package weixin.controller;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import weixin.util.MessageHandlerUtil;

/**
 * Servlet implementation class WxServlet
 * created by James yang on 2016/05/05
 */
@WebServlet(urlPatterns="/")
public class WxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final String TOKEN = "yangzhen";
	private Logger log = Logger.getLogger(this.getClass().getName());

    /**
     * @see HttpServlet#HttpServlet()
     */
    public WxServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//		System.out.println(request.getRequestURL().toString());
//		System.out.println(request.getQueryString());
//		System.out.println(request.getRemoteAddr());
//		System.out.println(request.getRemoteHost());
//		System.out.println(request.getMethod());
//		System.out.println(request.getProtocol());
		Logger log = Logger.getLogger(this.getClass().getName());
		

		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		if(signature==null||signature.length()==0
				||timestamp==null||timestamp.length()==0
				||nonce==null||nonce.length()==0
				||echostr==null||echostr.length()==0){
			System.out.println("Param error.....");
			return;
		}
//		System.out.println(signature);
//		System.out.println(timestamp);
//		System.out.println(nonce);
//		System.out.println(echostr);

		String sortString = sort(TOKEN,timestamp,nonce);
//		System.out.println(sortString);

		String mySignature = sha1(sortString);
//		System.out.println(mySignature);

		if(mySignature!=null&&mySignature!=""&&mySignature.equals(signature)){ 
//			System.out.println("success!");
			log.log(Level.INFO, "check out success");;
			response.getWriter().write(echostr);
			response.getWriter().flush();
		}else{
			System.out.println("failed!");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		log.log(Level.INFO, "request coming……");
		String result ="";
		Map<String,String> map = MessageHandlerUtil.parseXml(request);
		result = MessageHandlerUtil.buildResponseMessage(map);//.buildXml(map);
		if(result.equals("")){
			result = "response failed";
		}
		System.out.println(result);
		response.getWriter().println(result);
	}
	public String sort(String token,String timestamp,String nonce){
		String[] strArray = {token,timestamp,nonce};
		Arrays.sort(strArray);
		StringBuilder sb = new StringBuilder();
		for(String str:strArray){
			sb.append(str);
		}
		return sb.toString();
	}
	public String sha1(String str){
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			digest.update(str.getBytes());
			byte messageDigest[] = digest.digest();
			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < messageDigest.length; i++) {
				String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexString.append(0);
				}
				hexString.append(shaHex);
			}
			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

}
