<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<base href="<%= basePath %>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link type="text/css" rel="stylesheet" href="<%= basePath %>/static/jquery/css/blitzer/jquery-ui-1.10.2.custom.min.css" />
	<link type="text/css" rel="stylesheet" href="<%= basePath %>/static/common/css/bamboo-ui.css" />
	<script type="text/javascript" src="<%= basePath %>/static/jquery/js/jquery-1.9.1.js"></script>
	<script type="text/javascript" src="<%= basePath %>/static/jquery/js/jquery-ui-1.10.2.custom.min.js"></script>
	<script type="text/javascript" src="<%= basePath %>/static/common/js/jquery.form.js"></script>
	<script type="text/javascript" src="<%= basePath %>/static/common/js/bamboo-tool.js"></script>
	<script type="text/javascript" src="<%= basePath %>/static/business/js/index.js"></script>
	<title>spring mvc</title>
</head>
<body>
	<div align="center">
		<div style="position: relative;">
			<h1>spring mvc</h1>
		</div>
		<table id="tableId"></table>
	</div>
	
	<!--消息提示框-->
	<div id="tipMessageId"></div>
	<!--遮罩层-->
	<div id="maskLayerId"></div>
</body>
</html>
