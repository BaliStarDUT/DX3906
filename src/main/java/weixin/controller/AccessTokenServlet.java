package weixin.controller;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.json.JacksonJsonParser;

import weixin.entity.AccessToken;
import weixin.entity.AccessTokenInfo;
import weixin.util.NetWorkHelper;

/**
 * Servlet implementation class AccessTokenServlet
 */
@WebServlet(
		name = "AccessTokenServlet",
		urlPatterns = { "/AccessTokenServlet" }, 
		loadOnStartup = 10,
		initParams = { 
				@WebInitParam(name = "appId", value = "wx438a019f03a2c758"), 
				@WebInitParam(name = "appSecret", value = "34acd375ac856acd30d3671925aad8ee")
		})
public class AccessTokenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger log = Logger.getLogger(this.getClass().getName());
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccessTokenServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		log.log(Level.INFO, "Starting WebServlet");
		super.init(config);
		final String appId = getInitParameter("appId");
		final String appSecret = getInitParameter("appSecret");
		
		Thread thread = new Thread(new Runnable(){

			@Override
			public void run() {
				while(true){
					try{
						AccessTokenInfo.accessToken = getAccessToken(appId,appSecret);
						if(AccessTokenInfo.accessToken!=null){
							Thread.sleep(7000*1000);
						}else{
							Thread.sleep(1000*3);
						}
					}catch (Exception e){
						log.log(Level.WARNING, "get token error");
						e.printStackTrace();
						try {
							Thread.sleep(1000*10);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}						
				}
				
			}
			
		});
		thread.start();
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	public AccessToken getAccessToken(String appid,String appsecret){
		NetWorkHelper netHelper = new NetWorkHelper();
		String url = String.format("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s", appid,appsecret);
		String result = netHelper.getHttpsResponse(url, "GET");
		log.log(Level.INFO, "get response:"+result);
		JacksonJsonParser parser = new JacksonJsonParser();
		Map<String ,Object> jsonMap = parser.parseMap(result);
		AccessToken token = new AccessToken();
		token.setAccessToken((String) jsonMap.get("access_token"));
		token.setExpiresin((int) jsonMap.get("expires_in"));
		return token;
	}
}
