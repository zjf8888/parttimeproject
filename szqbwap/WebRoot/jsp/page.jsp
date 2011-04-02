<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-nested.tld" prefix="nested"%>
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
			<div id="leftnav">
				<a href="javascript:history.back()" class="noeffect">返回</a>
			</div>
			<div id="title">
				<bean:write name="page" property="title" />
			</div>
		</div>
		<div id="tributton">
			<div class="links">
				<a id="pressed" href="index.jsp">首页</a><a href="link.html">联系方式</a><a
					href="about.html">关于</a>
			</div>
		</div>
		<div id="content">
			<span class="graytitle"><bean:write name="page"
					property="title" /> </span>
			<ul class="pageitem">
				<logic:iterate property="list" id="list" name="page">
					<li class="menu">
						<a
							href="content.shtml?id=<bean:write
									name="list" property="id" />">
							<img alt="list" src="/thumbs/checkbox.gif" /><span
							class="name"><bean:write name="list" property="title" />
						</span><span class="arrow"></span> </a>
					</li>
				</logic:iterate>
			</ul>
		</div>

	</body>

</html>
