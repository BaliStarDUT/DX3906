package weixin.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
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
		if(msgType.equals("text")){
			result = buildTextMessage(map ,"Hello world!");
		}else{
			String fromUserName = map.get("FromUserName");
			String toUserName = map.get("ToUserName");
			String content = "Reply:text,blog,date,你是谁,等";
			result =  String.format("<xml>" +
				      "<ToUserName><![CDATA[%s]]></ToUserName>" +
				      "<FromUserName><![CDATA[%s]]></FromUserName>" +
				      "<CreateTime>%s</CreateTime>" +
				      "<MsgType><![CDATA[text]]></MsgType>" +
				      "<Content><![CDATA[%s]]></Content>" + "</xml>",
				      fromUserName, toUserName, System.currentTimeMillis(), content );
		}
		return result;

	}
	
	public static String buildResponseMessage(Map map){
		String responseMessage = "";
		String msgType = map.get("MsgType").toString();
		MessageType messageEnumType = MessageType.valueOf(MessageType.class, msgType.toUpperCase());
		switch(messageEnumType){
			case TEXT:
				responseMessage = handleTextMessage(map);
				break;
			case IMAGE:
				responseMessage = handleImageMessage(map);
				break;
			case VOICE:
				responseMessage = handleVoiceMessage(map);
				break;
			case VIDEO:
				responseMessage = handleVideoMessage(map);
				break;
			case SHORTVIDEO:
				responseMessage = handleShortVideoMessage(map);
				break;
			case LOCATION:
				responseMessage = handleLocationMessage(map);
				break;
			case LINK:
				responseMessage = handleLinkMessage(map);
				break;
			case EVENT:
				responseMessage = handleEventMessage(map);
				break;
			default:
				responseMessage = buildXml(map);
				break;
		}
		return responseMessage;
	}
	
	private static String handleTextMessage(Map<String,String> map){
		String responseMessage;
		String content = map.get("Content");
		switch(content){
		case "blog":
			String msgText = "<a href=\"http://balistardut.github.io\">DX3906的博客</a>";
			responseMessage = buildTextMessage(map,msgText);
			break;
		case "你是谁":
			String msgText2 = "您好，我是杨振的公众号";
			responseMessage = buildTextMessage(map,msgText2);
			break;
		case "date":
			Date date = new Date();
			String msgText3 = "现在的时间是"+(date.toString());
			responseMessage = buildTextMessage(map,msgText3);
			break;
		case "picture":
			String imgMeidaId = "5I0urdYNfz0hLJSD2-HZdoHQG01hxqw-ZUnEUQEJZEdz7aR0yWsX9-jb6qw-E6dg";
			responseMessage = buildImageMessage(map,imgMeidaId);
			break;
		case "music":
			Music music = new Music();
			music.setTitle("上邪 ");
			music.setDescription("小曲儿");
			music.setMusicurl("http://music.163.com/outchain/player?type=2&id=28188382&auto=1&height=66");
			music.setHqmusicurl("http://music.163.com/outchain/player?type=2&id=28188382&auto=1&height=66");
			responseMessage = buildMusicMessage(map, music);
			break;
		case "video":
			Video vi = new Video();
			vi.setTitle("FiddleSticks");
			vi.setMediaid("pQE42wN_NCgDuTC9s0ohWdlhp0h0qc1TPFnnve-z9mKqJ6bbfRqTlt2hB6jua-2u");
			vi.setDescription("FiddleSticks");
			responseMessage = buildVideoMessage(map,vi);
			break;
		case "new":
			responseMessage= buildArticleMessage(map,"1");
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
	private static String buildArticleMessage(Map<String,String> map,String articleCount){
		String fromUserName = map.get("FromUserName");
		String toUserName = map.get("ToUserName");
		return String.format("<xml>" +
			      "<ToUserName><![CDATA[%s]]></ToUserName>" +
			      "<FromUserName><![CDATA[%s]]></FromUserName>" +
			      "<CreateTime>%s</CreateTime>" +
			      "<MsgType><![CDATA[news]]></MsgType>" +
			      "<ArticleCount>1</ArticleCount>"
			      + "<Articles>"
			      + "<item>"
			      + "<Title><![CDATA[Burano, in the Venetian Lagoon, Italy]]></Title>"
			      + "<Description><![CDATA[If nearby Venice is too crowded and drab, make your way to Burano, a tightly packed collection of small islands in the Venetian Lagoon. Legend holds that all the houses were painted in varying, bright colors so that fishermen could pick out their homes even while out casting nets. Today, if you want to paint your villa in Burano, you send a request to the local government, which will reply with the color options available to you.]]></Description>"
			      + "<PicUrl><![CDATA[http://global.bing.com/az/hprichbg/rb/Burano_EN-US12610622868_1920x1080.jpg]]></PicUrl>"
			      + "<Url><![CDATA[http://global.bing.com/?FORM=HPCNEN&setmkt=en-us&setlang=en-us]]></Url>"
			      + "</item></Articles>" 
			      + "</xml>",
			      fromUserName, toUserName, System.currentTimeMillis());
	}
	private static String handleImageMessage(Map<String,String> map){
		String responseMessage;
		String MsgType = map.get("MsgType");
		System.out.println(MsgType);
		String MediaId  = map.get("MediaId");
		System.out.println(MediaId);

		responseMessage = buildImageMessage(map,MediaId);
		return responseMessage;
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
	private static String handleLocationMessage(Map<String,String> map){
		String responseMessage;
		String Location_X = map.get("Location_X");
		String Location_Y = map.get("Location_Y");
		String Scale = map.get("Scale");
		String Label = map.get("Label");
		String content = "经度："+Location_X+"\n"
				+ "纬度："+Location_Y+"\n"
				+ "地图缩放大小："+Scale+"\n"
				+ "位置信息："+Label+"\n";
		responseMessage = buildTextMessage(map, content);
		return responseMessage;
	}
	private static String handleEventMessage(Map<String,String> map) {
		String responseMessage;
		responseMessage = buildXml(map);
		return responseMessage;
	}
	private static String handleLinkMessage(Map<String,String> map) {
		String responseMessage;
		String Title = map.get("Title");
		String Description = map.get("Description");
		String Url = map.get("Url");
		String content = "消息标题："+Title+"\n"
				+ "消息描述 ："+Description+"\n"
				+ "消息链接："+Url+"\n";
		responseMessage = buildTextMessage(map,content);
		return responseMessage;
	}
	private static String handleShortVideoMessage(Map<String,String> map) {
		String responseMessage;
		String MsgType = map.get("MsgType");
		responseMessage = buildTextMessage(map,MsgType);
		return responseMessage;
	}
	private static String handleVideoMessage(Map<String,String> map) {
		String responseMessage;
		String MsgType = map.get("MsgType");
		responseMessage = buildTextMessage(map,MsgType);
		return responseMessage;
	}
	private static String handleVoiceMessage(Map<String,String> map) {
		String responseMessage;
		String MsgType = map.get("MsgType");
		String Format = map.get("Format");
		responseMessage = buildTextMessage(map,MsgType+"-"+Format);
		return responseMessage;
	}
}
