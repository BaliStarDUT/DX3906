package weixin.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;


public class NetWorkHelper {
	/**
	 * 
	 * @param reqUrl
	 * @param requestMethod
	 * @return
	 */
	public String getHttpsResponse(String reqUrl,String requestMethod){
		Logger log = Logger.getLogger(this.getClass().getName());

		URL url;
		InputStream is;
		String resultData="";
		try {
			url = new URL(reqUrl);
			HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
			TrustManager[] tm = null;//{xtm};
			SSLContext ctx = SSLContext.getInstance("TLS");
			ctx.init(null, tm, null);
			
			con.setSSLSocketFactory(ctx.getSocketFactory());
			con.setHostnameVerifier(new HostnameVerifier(){

				@Override
				public boolean verify(String arg0, SSLSession arg1) {
					return true;
				}
				
			});
			con.setDoInput(true);
			con.setDoOutput(false);
			con.setUseCaches(false);
			if(null!=requestMethod && !requestMethod.equals("")){
				con.setRequestMethod(requestMethod);
			}else{
				con.setRequestMethod("GET");
			}
			is = con.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String inputLine;
			while((inputLine=br.readLine())!=null){
				resultData += inputLine+"\n";
			}
			log.log(Level.INFO, resultData);
		} catch (IOException | NoSuchAlgorithmException | KeyManagementException e) {
			e.printStackTrace();
		}

		return resultData;
	}
}
