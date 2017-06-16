package top.hunaner.weixin.controller;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.hunaner.weixin.util.MessageHandlerUtil;


/**
 * Servlet implementation class WxServlet
 * created by James yang on 2016/05/05
 */
@RestController
@RequestMapping("/wx")
public class WxController{
    private final String TOKEN = "yangzhen";
	private Logger log = Logger.getLogger(this.getClass().getName());

	/**
	 * 验证微信服务器
	 * @author yangzhene
	 * @param 
	 */
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String doGet(@RequestParam(value="signature",required=true) String signature,
			@RequestParam(value="timestamp",required=true) String timestamp,
			@RequestParam(value="nonce",required=true) String nonce,
			@RequestParam(value="echostr",required=true) String echostr){
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//		System.out.println(request.getRequestURL().toString());
//		System.out.println(request.getQueryString());
//		System.out.println(request.getRemoteAddr());
//		System.out.println(request.getRemoteHost());
//		System.out.println(request.getMethod());
//		System.out.println(request.getProtocol());
		Logger log = Logger.getLogger(this.getClass().getName());
		if(signature==null||signature.length()==0
				||timestamp==null||timestamp.length()==0
				||nonce==null||nonce.length()==0
				||echostr==null||echostr.length()==0){
			log.log(Level.WARNING,"Param error.....");
			return "Param error.....";
		}
		log.log(Level.INFO,"signature:"+signature);
		log.log(Level.INFO,"timestamp:"+timestamp);
		log.log(Level.INFO,"nonce:"+nonce);
		log.log(Level.INFO,"echostr:"+echostr);
		String sortString = sort(TOKEN,timestamp,nonce);
		log.log(Level.INFO,"sortString:"+sortString);

		String mySignature = sha1(sortString);
		log.log(Level.INFO,"mySignature:"+mySignature);

		if(mySignature!=null&&mySignature!=""&&mySignature.equals(signature)){ 
			log.log(Level.INFO, "check out success");
			log.log(Level.INFO, "echostr："+echostr);
			return (echostr);
		}else{
			System.out.println("failed!");
			log.log(Level.WARNING, "check out failed！");
		}
		return echostr;
	}

	/**
	 * 
	 */
	@RequestMapping(value="/",method=RequestMethod.POST)
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		log.log(Level.INFO, "request coming……");
		String result ="";
		Map<String,String> map = MessageHandlerUtil.parseXml(request);
		result = MessageHandlerUtil.buildResponseMessage(map);//.buildXml(map);
		if(result.equals("")){
			result = "response failed";
		}
		log.log(Level.INFO, "result:"+result);
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
