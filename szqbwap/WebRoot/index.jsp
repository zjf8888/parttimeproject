<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page language="java" import="com.szqb.web.IndexBean" pageEncoding="utf-8"%>
<%@ page language="java" import="com.szqb.bean.Title" %>
<%@ page language="java" import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<meta content="yes" name="apple-mobile-web-app-capable" />
		<meta content="index,follow" name="robots" />
		<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
		<link href="pics/homescreen.gif" rel="apple-touch-icon" />
		<meta
			content="minimum-scale=1.0, width=device-width, maximum-scale=0.6667, user-scalable=no"
			name="viewport" />
		<link href="css/style.css" rel="stylesheet" media="screen"
			type="text/css" />
		<script src="javascript/functions.js" type="text/javascript"></script>
		<title>深圳侨报手机门户</title>
		<link href="pics/startup.png" rel="apple-touch-startup-image" />
		<meta
			content="iPod,iPhone,Webkit,iWebkit,Website,Create,mobile,Tutorial,free"
			name="keywords" />
		<meta
			content="Try out all the new features of iWebKit 5 with a simple touch of a finger and a smooth screen rotation!"
			name="description" />
	</head>

	<body>

		<div id="topbar">
			<div id="title">
				深圳侨报手机门户
			</div>
			<div id="leftbutton">
				<a href="http://www.sz-qb.com" class="noeffect">电脑端</a>
			</div>
		</div>
		<div id="tributton">
			<div class="links">
				<a id="pressed" href="#">首页</a><a href="link.html">联系方式</a><a
					href="about.html">关于</a>
			</div>
		</div>
		<div id="content">
			<span class="graytitle">今日关注</span>
			<ul class="pageitem">
			<%
				List<Title> titleList=IndexBean.getTitleList();
				for(int i=0;i<titleList.size();i++){
					Title title=titleList.get(i);
			 %>
				<li class="menu">
					<a href="page.shtml?id=<%=title.getId()%>"> <img alt="list"
							src="thumbs/dots1.gif" /><span class="name"><%=title.getTitle()%></span><span
						class="arrow"></span>
					</a>
				</li>
			<%
				} 
			%>
			</ul>
		</div>

	</body>

</html>
