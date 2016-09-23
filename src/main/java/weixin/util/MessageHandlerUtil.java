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

import org.apache.juli.logging.Log;
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
				if(node.getNodeValue().trim().equals("")){
					continue;
				}
				log.log(Level.INFO, "node name:"+node.getNodeName()+" node value:"+node.getNodeValue()+" node textcontent:"+node.getNodeValue());
				map.put(node.getNodeName(), node.getNodeValue());
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
			music.setTitle("黑默丁格");
			music.setDescription("嗯，非常有趣");
			music.setMusicurl("http://lol.52pk.com/pifu/sounds/heimodingge/8.mp3");
			music.setHqmusicurl("http://lol.52pk.com/pifu/sounds/heimodingge/8.mp3");
			music.setThumbmediaid("E1T8i5Bw6UJAcI_GW_STBei3HCEJOWdwN34Qi4bptD9MJkcbNIo1p0xHghTggGnZ");
			responseMessage = buildMusicMessage(map, music);
			break;
		case "亚索":
			Music music2 = new Music();
			music2.setTitle("亚索");
			music2.setDescription("亚索：我的剑比什么都重要！除了美酒");
			music2.setMusicurl("http://lol.52pk.com/pifu/sounds/yasuo/30.mp3");
			music2.setHqmusicurl("http://lol.52pk.com/pifu/sounds/yasuo/30.mp3");
			music2.setThumbmediaid("E1T8i5Bw6UJAcI_GW_STBei3HCEJOWdwN34Qi4bptD9MJkcbNIo1p0xHghTggGnZ");
			responseMessage = buildMusicMessage(map, music2);
			break;
		case "提莫":
			Music music3 = new Music();
			music3.setTitle("提莫");
			music3.setDescription("提莫：提莫队长正在待命。");
			music3.setMusicurl("http://lol.52pk.com/pifu/sounds/Teemo.mp3");
			music3.setHqmusicurl("http://lol.52pk.com/pifu/sounds/Teemo.mp3");
			music3.setThumbmediaid("E1T8i5Bw6UJAcI_GW_STBei3HCEJOWdwN34Qi4bptD9MJkcbNIo1p0xHghTggGnZ");
			responseMessage = buildMusicMessage(map, music3);
			break;
		case "伊泽瑞尔":
			Music music4 = new Music();
			music4.setTitle("伊泽瑞尔");
			music4.setDescription("伊泽瑞尔：是时候表演真正的技术了");
			music4.setMusicurl("http://lol.52pk.com/pifu/sounds/Ezreal.mp3");
			music4.setHqmusicurl("http://lol.52pk.com/pifu/sounds/Ezreal.mp3");
			music4.setThumbmediaid("E1T8i5Bw6UJAcI_GW_STBei3HCEJOWdwN34Qi4bptD9MJkcbNIo1p0xHghTggGnZ");
			responseMessage = buildMusicMessage(map, music4);
			break;
		case "费德提克":
			Music music5 = new Music();
			music5.setTitle("费德提克");
			music5.setDescription("费德提克：我知道你怕");
			music5.setMusicurl("http://lol.52pk.com/pifu/sounds/feidetike/7.mp3");
			music5.setHqmusicurl("http://lol.52pk.com/pifu/sounds/feidetike/7.mp3");
			music5.setThumbmediaid("E1T8i5Bw6UJAcI_GW_STBei3HCEJOWdwN34Qi4bptD9MJkcbNIo1p0xHghTggGnZ");
			responseMessage = buildMusicMessage(map, music5);
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
		String MediaId  = map.get("MediaId");
		
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
		String event = map.get("Event");
		if(event.equals("subscribe")){
			responseMessage = buildTextMessage(map, "欢迎订阅DX3960，这是我在开发中的一个订阅号");
		}else if(event.equals("unsubscribe")){
			responseMessage = buildTextMessage(map, "感谢您的关注，下次再见");
		}else if(event.equals("SCAN")){
			String ticket = map.get("Ticket");
			responseMessage = buildTextMessage(map, "二维码的ticket:"+ticket);
		}else if(event.equals("LOCATION")){
			String Latitude = map.get("Latitude");
			String Longitude = map.get("Longitude");
			String Precision = map.get("Precision");
			String content = "经度："+Longitude+"\n"
					+ "纬度："+Latitude+"\n"
					+ "地理位置精度："+Precision+"\n";
			responseMessage = buildTextMessage(map, content);
		}else if(event.equals("CLICK")){
			String key = map.get("EventKey");
			if(key.equals("yinxiao")){
				Music music2 = new Music();
				music2.setTitle("费德提克");
				music2.setDescription("费德提克：是，我的主人");
				music2.setMusicurl("http://lol.52pk.com/pifu/sounds/feidetike/4.mp3");
				music2.setHqmusicurl("http://lol.52pk.com/pifu/sounds/feidetike/4.mp3");
				music2.setThumbmediaid("E1T8i5Bw6UJAcI_GW_STBei3HCEJOWdwN34Qi4bptD9MJkcbNIo1p0xHghTggGnZ");
				responseMessage = buildMusicMessage(map, music2);
			}else if(key.equals("tupian")){
				Music music = new Music();
				music.setTitle("黑默丁格");
				music.setDescription("嗯，非常有趣");
				music.setMusicurl("http://lol.52pk.com/pifu/sounds/heimodingge/8.mp3");
				music.setHqmusicurl("http://lol.52pk.com/pifu/sounds/heimodingge/8.mp3");
				music.setThumbmediaid("E1T8i5Bw6UJAcI_GW_STBei3HCEJOWdwN34Qi4bptD9MJkcbNIo1p0xHghTggGnZ");
				responseMessage = buildMusicMessage(map, music);
			}else{
				responseMessage = buildTextMessage(map,"事件KEY值："+key);
			}
		}else if(event.equals("VIEW")){
			String  url= map.get("EventKey");
			responseMessage = buildTextMessage(map,"将跳转到："+url);
		}else{
			responseMessage = buildXml(map);
		}
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
		String MediaId = map.get("MediaId");
		if(map.containsKey("Recognition")){
			String Recognition = map.get("Recognition");
			responseMessage = buildTextMessage(map,"你是说："+Recognition+"，对吧？");
		}else{
			responseMessage = buildTextMessage(map,MsgType+"-"+Format);
		}
		return responseMessage;
	}
}
