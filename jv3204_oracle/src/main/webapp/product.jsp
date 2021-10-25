<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    String strName = (String)session.getAttribute("NAME");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品登録完了</title>
</head>
<body>
<p>【管理者】：<% out.print(strName); %>さん</p>
<h1>商品登録完了です。</h1>
<a href="index.html">TOPへ</a><br>
</body>
</html>