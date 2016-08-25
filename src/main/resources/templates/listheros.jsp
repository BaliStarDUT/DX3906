<%@page import="lol.entity.Lolhero"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*," %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

  <link rel="stylesheet" href="./bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" href="./bootstrap/css/bootstrap-theme.min.css">
  <script src="./bootstrap/js/jquery-1.10.0.min.js"></script>
  <script src="./bootstrap/js/bootstrap.min.js"></script>
<title>league of legends</title>
</head>
<body>
<div class="container theme-showcase" role="main">
	<div class="jumbotron">
		<h1>League of Legend</h1>
		<p>英雄联盟</p>
	</div>
	<hr>
	<div class="div">
	<%
	if(request.getAttribute("allheroslist")!=null){
		List<Lolhero> list=(List<Lolhero>)request.getAttribute("herosList");
		for(Lolhero hero : list){
			String nickname = hero.getNickname();
			String cnname = hero.getNameCn();
			String enname = hero.getNameEn();
			String type = hero.getType();
			String story = hero.getStory();
			String headpic = hero.getHeadpicUrl();
			String sound = hero.getSoundsUrl();
	%>
	<div class="col-md-6">
		<fieldset >
			<legend><%=nickname %></legend>
			<table>
			<tr>
				<p><span class ="label label-default"><%=cnname %></span></p>
			</tr>
			<tr>
				<p><span class ="label label-default"><%=enname %></span></p>
			</tr>
			<tr>
				<p><span class ="label label-default"><%=type %></span></p>	
			</tr>
			<tr>
				<p><img class="img-thumbnail" alt="200*200" src="./assets/images/champions/<%=headpic %>"></img></p>
			</tr>
			<tr>
				<p><audio src="./assets/images/champions/<%=sound %>" controls="controls">Your browser does not support the audio tag.</audio></p>
			</tr>
			<tr>
				
			</tr>
			<tr>
				<p><%=story %></p>
			</tr>
		</table>
		</fieldset>
	</div>


	<%
			}
		}
	%>
	</div>
</div>	
<hr>
<%
	if(request.getAttribute("fileNameMap")!=null){
		Map<String,String> fileNameMap =(Map<String,String>)request.getAttribute("fileNameMap");
		for(String key:fileNameMap.keySet()){
			
			String value = key.substring(0, key.indexOf('.'));
			out.print("<img src='./assets/images/champions/"+value+".png'></img>");
			out.print("<audio src='./assets/images/champions/"+value+".mp3' controls='controls'>Your browser does not support the audio tag.</audio>");
			out.print("<hr>");
			
		}
	}
%>

<hr>

</body>
</html>