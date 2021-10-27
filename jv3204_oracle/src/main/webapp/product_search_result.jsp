<%@page import="dto.CategoryDTO"%>
<%@page import="dto.ProductDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList"%>
    <%
    String strName = (String)session.getAttribute("NAME");
    String cfName = (String)session.getAttribute("CFNAME");
    if (strName == null) {
    	String result = "セッションが無効です。<br>再度ログインしてください。";
		String backPages = "login.jsp";
		request.setAttribute("RESULT", result);
		request.setAttribute("BACKPAGES", backPages);
		
		RequestDispatcher rd = request.getRequestDispatcher("/error.jsp"); 
		
		rd.forward(request, response);
		return;
    }
    
	ArrayList<ProductDTO> productDtoList = (ArrayList<ProductDTO>) request.getAttribute("productDtoList");
	ArrayList<CategoryDTO> categoryDtoList = (ArrayList<CategoryDTO>) request.getAttribute("categoryDtoList");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品検索結果</title>
</head>
<body>
<p>【<% out.print(cfName); %>】：<% out.print(strName); %>さん</p>
<h1>商品検索結果</h1>
<table>
	<tr>
		<td>商品名</td>
		<td>カテゴリ</td>
		<td>価格</td>
	</tr>
	<% for(ProductDTO productDto : productDtoList){ %>
	<tr>
		<td><%= productDto.getName() %> </td>
		<%
		for(CategoryDTO categoryDto : categoryDtoList){
			if (productDto.getCategoryId() == categoryDto.getId()) {%>
		<td><%= categoryDto.getName()%>	</td>
		<% }} %>
		<td><%= productDto.getPrice() %> </td>
	</tr>
	<% } %>
</table>
<hr>
<a href="product_search.jsp">商品検索へ</a>　
<a href="select_page.jsp">TOPへ</a>
</body>
</html>