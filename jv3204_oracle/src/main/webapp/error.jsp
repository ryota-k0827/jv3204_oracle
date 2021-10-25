<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    String result = (String)request.getAttribute("RESULT");
    String backPages = (String)request.getAttribute("BACKPAGES");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>エラー</title>
</head>
<body>
<p><% out.print(result); %></p>
<a href=<% out.print(backPages); %>>戻る</a>

</body>
</html>