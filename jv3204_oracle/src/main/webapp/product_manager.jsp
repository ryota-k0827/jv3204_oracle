<%@page import="dto.CategoryDTO"%>
<%@page import="model.CategoryModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList"%>
    <%
    String strName = (String)session.getAttribute("NAME");
	// CategoryModelをインスタンス化
 	CategoryModel categoryModel = new CategoryModel();
 	// データをbeanListとして取得
 	ArrayList<CategoryDTO> categoryDtoList = categoryModel.getCategoryList();
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品管理</title>
</head>
<body>
<p>【管理者】：<% out.print(strName); %>さん</p>
<h1>商品管理</h1>
<form action="./ProductRegisterServlet" method="post">
	<p>商品番号<input type="text" name="no" value=""></p>
	<p>商品名<input type="text" name="name" value=""></p>
	<p>カテゴリー
	<select name="category">
		<option value="0">-----</option>
		<% for(CategoryDTO dto: categoryDtoList) { %>
		<option value="<%= dto.getId() %>"><%= dto.getName() %></option>
		<% } %>
	</select></p>
	<p>価格<input type="text" name="price" value=""></p>
	<button type ="submit" name ="sb" value="register">実行</button>
</form>
</body>
</html>