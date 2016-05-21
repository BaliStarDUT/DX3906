package weixin.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.annotation.WebInitParam;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.protocol.SSLProtocolSocketFactory;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import weixin.entity.AccessTokenInfo;

/**
 * 
 * @date 2016年5月21日 下午3:25:22
 * @author James Yang
 * @version 1.0
 * @since
 */
public class WeChatApiUtil {
	private static Logger log = Logger.getLogger("WeChatApiUtil"); 
	private static final String UPLOAD_MEDIA = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=%s&type=%s";
	private static final String DOWNLOAD_MEDIA = "https://api.weixin.qq.com/cgi-bin/media/get?access_token=%s&media_id=%s";
	
	public static String getTokenUrl(String appId,String appSecret){
		return String.format(DOWNLOAD_MEDIA, appId,appSecret);
	}
	public static String getDownloadUrl(String token,String mediaId){
		return String.format(DOWNLOAD_MEDIA, token,mediaId);
	}
	/**
	 * 
	 * @param file
	 * @param token
	 * @param type
	 * @return
	 */
	public static JSONObject uploadMedia(File file,String token,String type){
		if(file == null||token==null||type==null){
			return null;
		}
		if(!file.exists()){
			log.log(Level.WARNING, "file does't exists");
		}
		String url = UPLOAD_MEDIA;
		JSONObject jsonob = null;
		PostMethod post = new PostMethod(url);
		post.setRequestHeader("Connection", "Keep-Alive");
		post.setRequestHeader("Cache-Control","no-cache");
		FilePart media;
		HttpClient httpClient = new HttpClient();
//		Protocol myhttps = new Protocol();//("https", new SSLProtocolSocketFactory(), 443);
		try {
			media = new FilePart("media", file);
			Part[] parts = new Part[]{
					new StringPart("access_token", token),
					new StringPart("type", type),
					media
			};
			MultipartRequestEntity entity = new MultipartRequestEntity(parts, post.getParams());
			post.setRequestEntity(entity);
			int status = httpClient.executeMethod(post);
			if(status==HttpStatus.SC_OK){
				String text = post.getResponseBodyAsString();
				jsonob = (JSONObject) new JSONParser().parse(text);
			}else{
				String text = post.getResponseBodyAsString();
				jsonob = (JSONObject) new JSONParser().parse(text);
				log.log(Level.WARNING, "upload Media failed ,status is:"+status);
			}
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		return jsonob;
	}
	/**
	 * 
	 * @param fileName
	 * @param path
	 * @param method
	 * @param body
	 * @return
	 */
	public static File httpRequestToFile(String fileName,String path,String method,String body){
		if(fileName ==null|| path==null||method==null){
			return null;
		}
		File file = null;
		HttpURLConnection conn = null;
		InputStream is = null ;
		FileOutputStream fileout = null;
		try {
			URL url = new URL(path);
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod(method);
			if(null != body){
				OutputStream os = conn.getOutputStream();
				os.write(body.getBytes(Charset.forName("UTF-8")));
				os.close();
			}
			is = conn.getInputStream();
			if(is != null){
				file = new File(fileName);
			}else{
				return file;
			}
			fileout = new FileOutputStream(file);
			if(fileout!=null){
				int c = is.read();
				while(c!=-1){
					fileout.write(c);
					c=is.read();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(conn!=null){
				conn.disconnect();
			}
			try {
				is.close();
				fileout.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return file;
	}
	public static JSONObject uploadMedia(String filePath,String type){
		File  f = new File(filePath);
		String appId = "wx438a019f03a2c758";
		String appSecret = "34acd375ac856acd30d3671925aad8ee";
		String token = AccessTokenInfo.accessToken.getAccessToken();
		JSONObject jsonob = uploadMedia(f,token,type);
		return jsonob;
	}
}
