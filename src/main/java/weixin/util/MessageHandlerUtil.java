package weixin.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import weixin.entity.MessageType;
import weixin.entity.Music;
import weixin.entity.Video;


public class MessageHandlerUtil {

	public static Map<String,String> parseXml(HttpServletRequest request){
		Logger log = Logger.getLogger("MessageHandlerUtil");
		Map<String,String> map = new HashMap<String, String>();
		InputStream input;
		try {
			input = request.getInputStream();
			DocumentBuilderFactory docBuilderFac =  DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFac.newDocumentBuilder();
			Document document = docBuilder.parse(input);

//			System.out.println(document.getDocumentElement());

//			System.out.println(document.getFirstChild());
			Node firstnode = document.getFirstChild();
			
			NodeList nodelist = firstnode.getChildNodes();//document.getChildNodes();
			for(int i=0;i<nodelist.getLength();i++){
				Node node = nodelist.item(i);
				if(node.getTextContent().trim().equals("")){
					continue;
				}
				log.log(Level.INFO, "node name:"+node.getNodeName()+" node value:"+node.getNodeValue()+" node textcontent:"+node.getTextContent());
				map.put(node.getNodeName(), node.getTextContent());
			}
		} catch (IOException | ParserConfigurationException | SAXException e) {
			log.log(Level.INFO, "parse xml error");
			e.printStackTrace();
		}
		return map;
	}
	public static String buildXml(Map<String,String> map){
		String result;
		String msgType = map.get("MsgType").toString();
//		System.out.println(msgType);
		if(msgType.equals("text")){
			result = buildTextMessage(map ,"Hello world!");
		}else{
			String fromUserName = map.get("FromUserName");
//			System.out.println(fromUserName);

			String toUserName = map.get("ToUserName");
//			System.out.println(toUserName);

			String content = "Reply:Text";
			result =  String.format("<xml>" +
				      "<ToUserName><![CDATA[%s]]></ToUserName>" +
				      "<FromUserName><![CDATA[%s]]></FromUserName>" +
				      "<CreateTime>%s</CreateTime>" +
				      "<MsgType><![CDATA[text]]></MsgType>" +
				      "<Content><![CDATA[%s]]></Content>" + "</xml>",
				      fromUserName, toUserName, System.currentTimeMillis(), content );
		}
//		System.out.println(result);

		return result;

	}
	
	public static String buildResponseMessage(Map map){
		String responseMessage = "";
		String msgType = map.get("MsgType").toString();
		System.out.println(msgType);
		MessageType messageEnumType = MessageType.valueOf(MessageType.class, msgType.toUpperCase());
		switch(messageEnumType){
			case TEXT:
				responseMessage = handleTextMessage(map);
				break;
			default:
				responseMessage = buildXml(map);
		}
		
		return responseMessage;
	}
	private static String handleTextMessage(Map<String,String> map){
		String responseMessage;
		String content = map.get("Content");
		switch(content){
		case "text":
			String msgText = "DX3906的博客\n"
					+ "<a href=\"http://balistardut.github.io\"></a>";
			responseMessage = buildTextMessage(map,msgText);
			break;
		case "picture":
			String imgMeidaId = "";
			responseMessage = buildImageMessage(map,imgMeidaId);
			break;
		case "music":
			Music music = new Music();
			music.setTitle("Jhin");
			music.setDescription("Hero Jhin");
			music.setMusicurl("http://localhost/Jhin.mp3");
			music.setHqmusicurl("http://localhost/Jhin.mp3");
			responseMessage = buildMusicMessage(map, music);
			break;
		case "video":
			Video vi = new Video();
			vi.setTitle("FiddleSticks");
			vi.setMediaid("");
			vi.setDescription("FiddleSticks");
			responseMessage = buildVideoMessage(map,vi);
			break;
		default:
			responseMessage = buildXml(map);
			break;
		}
		return responseMessage;
	}
	private static String buildTextMessage(Map<String,String> map,String content){
		String fromUserName = map.get("FromUserName");
		String toUserName = map.get("ToUserName");
		return String.format("<xml>" +
			      "<ToUserName><![CDATA[%s]]></ToUserName>" +
			      "<FromUserName><![CDATA[%s]]></FromUserName>" +
			      "<CreateTime>%s</CreateTime>" +
			      "<MsgType><![CDATA[text]]></MsgType>" +
			      "<Content><![CDATA[%s]]></Content>" + "</xml>",
			      fromUserName, toUserName, System.currentTimeMillis(), content);
	}
	/**
	 * 
	 * @param map
	 * @param mediaId
	 * @return
	 */
	private static String buildImageMessage(Map<String,String> map,String mediaId){
		String fromUserName = map.get("FromUserName");
		String toUserName = map.get("ToUserName");
		return String.format("<xml>" +
			      "<ToUserName><![CDATA[%s]]></ToUserName>" +
			      "<FromUserName><![CDATA[%s]]></FromUserName>" +
			      "<CreateTime>%s</CreateTime>" +
			      "<MsgType><![CDATA[image]]></MsgType>" +
			      "<Image><MediaId>"
			      + "<![CDATA[%s]]>"
			      + "</MediaId></Image>" + "</xml>",
			      fromUserName, toUserName, System.currentTimeMillis(), mediaId);
	}
	/**
	 * 
	 * @param map
	 * @return
	 */
	private static String buildMusicMessage(Map<String,String> map,Music music){
		String fromUserName = map.get("FromUserName");
		String toUserName = map.get("ToUserName");
		return String.format("<xml>"
				+ "<ToUserName><![CDATA[%s]]></ToUserName>"
				+ "<FromUserName><![CDATA[%s]]></FromUserName>"
				+ "<CreateTime>%s</CreateTime>"
				+ "<MsgType><![CDATA[music]]></MsgType>"
				+ "<Music>"
				+ "<Title><![CDATA[%s]]></Title>"
				+ "<Description><![CDATA[%s]]></Description>"
				+ "<MusicUrl><![CDATA[%s]]></MusicUrl>"
				+ "<HQMusicUrl><![CDATA[%s]]></HQMusicUrl>"
				+ "<ThumbMediaId><![CDATA[%s]]></ThumbMediaId>"
				+ "</Music>"
				+ "</xml>",
			      fromUserName, toUserName, System.currentTimeMillis(), music.getTitle(),
			      music.getMusicurl(),music.getDescription(),music.getHqmusicurl(),
			      music.getThumbmediaid());
	}
	/**
	 * 
	 * @param map
	 * @param video
	 * @return
	 */
	private static String buildVideoMessage(Map<String,String> map,Video video){
		String fromUserName = map.get("FromUserName");
		String toUserName = map.get("ToUserName");
		return String.format("<xml>"
				+ "<ToUserName><![CDATA[%s]]></ToUserName>"
				+ "<FromUserName><![CDATA[%s]]></FromUserName>"
				+ "<MsgType><![CDATA[video]]></MsgType>"
				+ "<Video>"
				+ "<MediaId><![CDATA[%s]]></MediaId>"
				+ "<Title><![CDATA[%s]]></Title>"
				+ "<Description><![CDATA[%s]]></Description>"
				+ "</Video> "
				+ "</xml>",
			      fromUserName, toUserName, System.currentTimeMillis(), video.getMediaid(),
			      video.getTitle(),video.getDescription());
	}
}
