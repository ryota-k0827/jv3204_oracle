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
    String cfName = (String)session.getAttribute("CFNAME");
    
    // CategoryModelをインスタンス化
	CategoryModel categoryModel = new CategoryModel();
	// データをbeanListとして取得
	ArrayList<CategoryDTO> categoryDtoList = categoryModel.getCategoryList();
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>選択ページ</title>
</head>
<body>
<p>【<% out.print(cfName);  %>】：<% out.print(strName); %>さん</p>
<a href="login.jsp">ログアウト</a><hr>
<h1>ページを選択</h1>
<input type="button" onclick="location.href='./product_search.jsp'" value="商品検索">
<input type="button" onclick="location.href='./product_manager.jsp'" <% if(cf==1){ %>disabled<% } %> value="商品管理">
</body>
</html>