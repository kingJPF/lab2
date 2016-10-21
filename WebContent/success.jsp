<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title>Successful search</title>
</head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js">
</script>
<script type="text/javascript">
function details(e)
{
	var BookTitle = document.getElementById("BookTitle");
	window.location.href = "showDetails?BookTitle="+e.innerHTML;
}
</script>
<body bgcolor="grey">
   <div style="width:100%" align="center" ><h1>作者所著书籍(点击可查看详细信息)</h1></div>
		<s:property value = "name"/>
		
		<table border="1">
			<s:iterator value="BookList" id="String" status = "status">
			<tr>

				<p id="BookTitle"><p clickable="clickable" onclick="details(this);"><s:property value="String"/> </p> </p> 
				<hr>
			</tr>
		</s:iterator>
</body>
</html>