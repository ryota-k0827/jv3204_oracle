<%@page import="dto.CategoryDTO"%>
<%@page import="model.CategoryModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList"%>
    <%
    String strName = (String)session.getAttribute("NAME");
    if (strName == null) {
    	String result = "セッションが無効です。<br>再度ログインしてください。";
		String backPages = "login.jsp";
		request.setAttribute("RESULT", result);
		request.setAttribute("BACKPAGES", backPages);
		
		RequestDispatcher rd = request.getRequestDispatcher("/error.jsp"); 
		
		rd.forward(request, response);
		return;
    }
    int cf = (int)session.getAttribute("CF");
    if (cf != 2) {
    	String result = "このページにアクセスする権限がありません。";
		String backPages = "select_page.jsp";
		request.setAttribute("RESULT", result);
		request.setAttribute("BACKPAGES", backPages);
		
		RequestDispatcher rd = request.getRequestDispatcher("/error.jsp"); 
		
		rd.forward(request, response);
		return;
    }
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
<a href="login.jsp">ログアウト</a><hr>
<h1>商品管理</h1>
<form action="./ProductRegisterServlet" method="post" autocomplete="off">
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
<hr>
<a href="select_page.jsp">TOPへ</a>
</body>
</html>