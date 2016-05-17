package weixin.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NetWorkHelper {
	/**
	 * 
	 * @param reqUrl
	 * @param requestMethod
	 * @return
	 */
	public String getHttpsResponse(String reqUrl,String requestMethod){
		URL url;
		InputStream is;
		String resultData="";
		try {
			url = new URL(reqUrl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return resultData;
	}
}
