package weixin.controller;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class WxServlet
 * created by James yang on 2016/05/05
 */
@WebServlet(urlPatterns="/WxServlet")
public class WxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final String TOKEN = "yangzhen";
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
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("开始校验签名");
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		
		String sortString = sort(TOKEN,timestamp,nonce);
		String mySignature = sha1(sortString);
		if(mySignature!=null&&mySignature!=""&&mySignature.equals(signature)){
			System.out.println("签名校验通过");
			response.getWriter().write(echostr);
			response.getWriter().flush();
		}else{
			System.out.println("签名校验失败");
		}
		doGet(request, response);
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
