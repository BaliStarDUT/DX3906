package top.hunaner.weixin.util;

import org.dom4j.io.SAXReader;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.springframework.util.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/** 
* MessageHandlerUtil Tester. 
* 
* @author <Authors name> 
* @version 1.0 
*/ 
public class MessageHandlerUtilTest {

    private Logger log = Logger.getLogger(this.getClass().getName());

    @Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: parseXml(HttpServletRequest request) 
* 
*/ 
@Test
public void testParseXml() throws Exception {
    File file = new File("E:\\project4\\cplatform\\manage\\src\\main\\webapp\\WEB-INF\\web.xml");

    DocumentBuilderFactory docBuilderFac =  DocumentBuilderFactory.newInstance();
    DocumentBuilder docBuilder = docBuilderFac.newDocumentBuilder();

    Document document = docBuilder.parse(file);

    NodeList nodeList = document.getElementsByTagName("context-param");
    for (int i=0;i<nodeList.getLength();i++){
        Element element = (Element) nodeList.item(i);
        Node name = element.getElementsByTagName("param-name").item(0);
        Node age = element.getElementsByTagName("param-value").item(0);
        log.log(Level.INFO,"param-name"+name.getFirstChild().getNodeValue());
        log.log(Level.INFO,"param-value:"+age.getFirstChild().getNodeValue());

    }
    NodeList nodeList1 =  document.getElementsByTagName("name");
    log.log(Level.INFO,nodeList.toString());
}

/** 
* 
* Method: buildXml(Map<String,String> map) 
* 
*/ 
@Test
public void testBuildXml() throws Exception {
    XMLReader xmlReader = new com.sun.org.apache.xerces.internal.parsers.SAXParser();
    File file = new File("E:\\project4\\cplatform\\manage\\src\\main\\webapp\\WEB-INF\\web.xml");
    FileInputStream fileInputStream = new FileInputStream("E:\\project4\\cplatform\\manage\\src\\main\\webapp\\WEB-INF\\web.xml");
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
    log.log(Level.INFO,bufferedReader.readLine());
    InputSource inputSource = new InputSource(fileInputStream);
    //xmlReader.setContentHandler(new );
    xmlReader.parse(inputSource);
    log.log(Level.INFO,"hello");
}

/** 
* 
* Method: buildResponseMessage(Map map) 
* 
*/ 
@Test
public void testBuildResponseMessage() throws Exception {
    DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
    log.log(Level.INFO,documentBuilderFactory.getClass().getName());
    DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
    log.log(Level.INFO,documentBuilder.getClass().getName());

    File studentsFile = new File("E:\\mycode\\DX3906\\src\\test\\java\\top\\hunaner\\weixin\\util\\students.xml");
    Document document = documentBuilder.parse(studentsFile);

    NodeList nodeList = document.getElementsByTagName("student");
    for (int i=0;i<nodeList.getLength();i++){
        Element element = (Element) nodeList.item(i);
        Node name = element.getElementsByTagName("name").item(0);
        Node age = element.getElementsByTagName("age").item(0);
        log.log(Level.INFO,"name:"+name.getFirstChild().getNodeValue());
        log.log(Level.INFO,"age:"+age.getFirstChild().getNodeValue());

    }
    NodeList nodeList1 =  document.getElementsByTagName("name");
    log.log(Level.INFO,nodeList.toString());
}


/** 
* 
* Method: handleTextMessage(Map<String,String> map) 
* 
*/ 
@Test
public void testHandleTextMessage() throws Exception {
    StringBuilder body = new StringBuilder("<xml> <ToUserName><![CDATA[toUser]]></ToUserName> <FromUserName><![CDATA[fromUser]]></FromUserName> <CreateTime>1348831860</CreateTime> <MsgType><![CDATA[text]]></MsgType> <Content><![CDATA[this 是你一个 a test]]></Content> <MsgId>1234567890123456</MsgId> </xml>");
    InputSource inputSource = new InputSource(
            new ByteArrayInputStream(body.toString().getBytes()));
    DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
    log.log(Level.INFO,documentBuilderFactory.getClass().getName());
    DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
    log.log(Level.INFO,documentBuilder.getClass().getName());
    Document document = documentBuilder.parse(inputSource);
    Node firstnode = document.getFirstChild();

    NodeList nodelist = firstnode.getChildNodes();//document.getChildNodes();
    for(int i=0;i<nodelist.getLength();i++){
        Node node = nodelist.item(i);
//        Element element = (Element) node;
//        Node name = element.getElementsByTagName("name").item(0);
//        Node age = element.getElementsByTagName("age").item(0);
//        log.log(Level.INFO,"name:"+name.getFirstChild().getNodeValue());
//        log.log(Level.INFO,"age:"+age.getFirstChild().getNodeValue());
        log.log(Level.INFO, "node name:"+node.getNodeName()+
                " node value:"+node.getNodeValue()+
                " text content:"+node.getTextContent());
//        if(StringUtils.isEmpty(node.getNodeValue())){
//            continue;
//        }
    }
} 

/** 
* 
* Method: buildTextMessage(Map<String,String> map, String content) 
* 
*/ 
@Test
public void testBuildTextMessage() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = MessageHandlerUtil.getClass().getMethod("buildTextMessage", Map<String,String>.class, String.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

/** 
* 
* Method: buildArticleMessage(Map<String,String> map, String articleCount) 
* 
*/ 
@Test
public void testBuildArticleMessage() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = MessageHandlerUtil.getClass().getMethod("buildArticleMessage", Map<String,String>.class, String.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

/** 
* 
* Method: handleImageMessage(Map<String,String> map) 
* 
*/ 
@Test
public void testHandleImageMessage() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = MessageHandlerUtil.getClass().getMethod("handleImageMessage", Map<String,String>.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

/** 
* 
* Method: buildImageMessage(Map<String,String> map, String mediaId) 
* 
*/ 
@Test
public void testBuildImageMessage() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = MessageHandlerUtil.getClass().getMethod("buildImageMessage", Map<String,String>.class, String.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

/** 
* 
* Method: buildMusicMessage(Map<String,String> map, Music music) 
* 
*/ 
@Test
public void testBuildMusicMessage() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = MessageHandlerUtil.getClass().getMethod("buildMusicMessage", Map<String,String>.class, Music.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

/** 
* 
* Method: buildVideoMessage(Map<String,String> map, Video video) 
* 
*/ 
@Test
public void testBuildVideoMessage() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = MessageHandlerUtil.getClass().getMethod("buildVideoMessage", Map<String,String>.class, Video.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

/** 
* 
* Method: handleLocationMessage(Map<String,String> map) 
* 
*/ 
@Test
public void testHandleLocationMessage() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = MessageHandlerUtil.getClass().getMethod("handleLocationMessage", Map<String,String>.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

/** 
* 
* Method: handleEventMessage(Map<String,String> map) 
* 
*/ 
@Test
public void testHandleEventMessage() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = MessageHandlerUtil.getClass().getMethod("handleEventMessage", Map<String,String>.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

/** 
* 
* Method: handleLinkMessage(Map<String,String> map) 
* 
*/ 
@Test
public void testHandleLinkMessage() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = MessageHandlerUtil.getClass().getMethod("handleLinkMessage", Map<String,String>.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

/** 
* 
* Method: handleShortVideoMessage(Map<String,String> map) 
* 
*/ 
@Test
public void testHandleShortVideoMessage() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = MessageHandlerUtil.getClass().getMethod("handleShortVideoMessage", Map<String,String>.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

/** 
* 
* Method: handleVideoMessage(Map<String,String> map) 
* 
*/ 
@Test
public void testHandleVideoMessage() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = MessageHandlerUtil.getClass().getMethod("handleVideoMessage", Map<String,String>.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

/** 
* 
* Method: handleVoiceMessage(Map<String,String> map) 
* 
*/ 
@Test
public void testHandleVoiceMessage() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = MessageHandlerUtil.getClass().getMethod("handleVoiceMessage", Map<String,String>.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

} 
