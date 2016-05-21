package weixin.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class MessageHandlerUtil {

	public static Map<String,String> parseXml(HttpServletRequest request){
		Logger log = Logger.getLogger("MessageHandlerUtil");
		Map<String,String> map = new HashMap<String, String>();
		InputStream input;
		try {
			input = request.getInputStream();
//			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
//			String lines;
//			StringBuffer sb = new StringBuffer("");
//			while((lines = reader.readLine())!=null){
//				lines = new String(lines.getBytes(),"utf-8");
//				sb.append(lines);
//			}		
//			System.out.println("From:"+request.getRemoteHost());
//			System.out.println("Request:"+request.getRequestURL());
//			System.out.println("Post data:"+sb);
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
	private static String buildTextMessage(Map<String,String> map,String content){
		String fromUserName = map.get("FromUserName");
//		System.out.println(fromUserName);

		String toUserName = map.get("ToUserName");
//		System.out.println(toUserName);

		return String.format("<xml>" +
			      "<ToUserName><![CDATA[%s]]></ToUserName>" +
			      "<FromUserName><![CDATA[%s]]></FromUserName>" +
			      "<CreateTime>%s</CreateTime>" +
			      "<MsgType><![CDATA[text]]></MsgType>" +
			      "<Content><![CDATA[%s]]></Content>" + "</xml>",
			      fromUserName, toUserName, System.currentTimeMillis(), content);
	}
}
