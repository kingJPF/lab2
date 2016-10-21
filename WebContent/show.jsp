<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>书目介绍</title>
<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js">
</script>
<script type="text/javascript">
function deletes(e)
{
	var ISBN = document.getElementById("ISBN");
	alert(ISBN.innerHTML);
	window.location.href = "delete?ISBN="+ISBN.innerHTML;
}

</script>
</head>
	<body bgcolor="grey">
		<h1>图书简介</h1>
		<p id="ISBN">
		<s:property value = "ISBN"/></p>

		AuthorID:<s:property value = "AuthorID"/>
		<hr>
		Publisher:<s:property value = "Publisher"/>
		<hr>
		PublishDate:<s:property value = "PublishDate"/>
		<hr>
		Price:<s:property value = "Price"/>
		<hr>
		<input type="button" value="删除" onclick="deletes(this);">

	</body>
</html>