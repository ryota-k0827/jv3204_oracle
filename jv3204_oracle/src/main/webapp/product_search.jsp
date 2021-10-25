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
<title>商品検索</title>
</head>
<body>
<p>【会員】：<% out.print(strName); %>様</p>
<h1>商品検索</h1>
<form action="./ProductSearchServlet" method="post">
	<p>商品名<input type="text" name="name" value=""></p>
	<p>カテゴリー
	<select name="category">
		<option value="0">-----</option>
		<% for(CategoryDTO dto: categoryDtoList) { %>
		<option value="<%= dto.getId() %>"><%= dto.getName() %></option>
		<% } %>
	</select></p>
	<p>価格<input type="text" name="low_price" value="">円〜
	<input type="text" name="high_price" value="">円</p>
	<button type ="submit" name ="sb" value="submit">実行</button>
</form>
</body>
</html>