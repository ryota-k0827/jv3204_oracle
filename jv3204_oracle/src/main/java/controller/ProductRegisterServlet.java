package controller;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.ProductDTO;
import model.ProductModel;

@WebServlet("/ProductRegisterServlet")
public class ProductRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductRegisterServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//文字化け対策
		request.setCharacterEncoding("utf8");

		//jspから値取得
		String strNo = request.getParameter("no");
		String strName = request.getParameter("name");
		int categoryId = Integer.parseInt(request.getParameter("category"));
		String strPrice = request.getParameter("price");
		int no;
		int price;
		
		//入力桁数を取得
		int noLength = strNo.length();
		int nameLength = strName.length();

		//空白チェック
		if (strNo.equals("") && strName.equals("") && categoryId == 0 && strPrice.equals("")) {
			String result = "商品番号、商品名、カテゴリー、価格を入力してください。";
			String backPages = "product_manager.jsp";
			request.setAttribute("RESULT", result);
			request.setAttribute("BACKPAGES", backPages);
			
			RequestDispatcher rd = request.getRequestDispatcher("/error.jsp"); 
			
			rd.forward(request, response);
			
			System.out.println(result);
			return;
		}
		else if (strNo.equals("")) {
			String result = "商品番号を入力してください。";
			String backPages = "product_manager.jsp";
			request.setAttribute("RESULT", result);
			request.setAttribute("BACKPAGES", backPages);
			
			RequestDispatcher rd = request.getRequestDispatcher("/error.jsp"); 
			
			rd.forward(request, response);
			
			System.out.println(result);
			return;
		}
		else if (strName.equals("")) {
			String result = "商品名を入力してください。";
			String backPages = "product_manager.jsp";
			request.setAttribute("RESULT", result);
			request.setAttribute("BACKPAGES", backPages);
			
			RequestDispatcher rd = request.getRequestDispatcher("/error.jsp"); 
			
			rd.forward(request, response);
			
			System.out.println(result);
			return;
		}
		else if (categoryId == 0) {
			String result = "カテゴリーを選択してください。";
			String backPages = "product_manager.jsp";
			request.setAttribute("RESULT", result);
			request.setAttribute("BACKPAGES", backPages);
			
			RequestDispatcher rd = request.getRequestDispatcher("/error.jsp"); 
			
			rd.forward(request, response);
			
			System.out.println(result);
			return;
		}
		else if (strPrice.equals("")) {
			String result = "価格を入力してください。";
			String backPages = "product_manager.jsp";
			request.setAttribute("RESULT", result);
			request.setAttribute("BACKPAGES", backPages);
			
			RequestDispatcher rd = request.getRequestDispatcher("/error.jsp"); 
			
			rd.forward(request, response);
			
			System.out.println(result);
			return;
		}
		
		//数値チェック
		try {
			no = Integer.parseInt(strNo);
		} catch(NumberFormatException e) {
			String result = "商品番号には数値を入力してください。";
			String backPages = "product_manager.jsp";
			request.setAttribute("RESULT", result);
			request.setAttribute("BACKPAGES", backPages);
			
			RequestDispatcher rd = request.getRequestDispatcher("/error.jsp"); 
			
			rd.forward(request, response);
			
			System.out.println(result);
			return;
		}
		try {
			price = Integer.parseInt(strPrice);
		} catch(NumberFormatException e) {
			String result = "価格には数値を入力してください。";
			String backPages = "product_manager.jsp";
			request.setAttribute("RESULT", result);
			request.setAttribute("BACKPAGES", backPages);
			
			RequestDispatcher rd = request.getRequestDispatcher("/error.jsp"); 
			
			rd.forward(request, response);
			
			System.out.println(result);
			return;
		}
		
		/**
		 * 桁数チェック
		 */
		if (noLength > 3) {
			String result = "商品番号の桁数は3桁迄です。";
			String backPages = "product_manager.jsp";
			request.setAttribute("RESULT", result);
			request.setAttribute("BACKPAGES", backPages);
			
			RequestDispatcher rd = request.getRequestDispatcher("/error.jsp"); 
			
			rd.forward(request, response);
			
			System.out.println(result);
			return;
		}
		else if (nameLength > 20) {
			String result = "商品名の桁数は20桁迄です。";
			String backPages = "product_manager.jsp";
			request.setAttribute("RESULT", result);
			request.setAttribute("BACKPAGES", backPages);
			
			RequestDispatcher rd = request.getRequestDispatcher("/error.jsp"); 
			
			rd.forward(request, response);
			
			System.out.println(result);
			return;
		}
		
		// 入力チェック通過後の処理
		// ProductModelをインスタンス化
		ProductModel productModel = new ProductModel();
		
		// 商品番号の重複チェック-----------------------------------------------------------------------------
		// データを取得
		ProductDTO dto = productModel.getProductList(no);
		if (dto != null) {
			String result = "商品番号：" + strNo + "　は既に存在します。";
			String backPages = "product_manager.jsp";
			request.setAttribute("RESULT", result);
			request.setAttribute("BACKPAGES", backPages);
			
			RequestDispatcher rd = request.getRequestDispatcher("/error.jsp"); 
			
			rd.forward(request, response);
			return;
		}
		// -----------------------------------------------------------------------------------------------
		
		// 重複チェックを通過後
		// 入力データをModelに引き渡す。
		productModel.addProduct(no, strName, categoryId, price);
		
		// 商品登録完了ページへ推移
		response.sendRedirect("/jv3204/product.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
