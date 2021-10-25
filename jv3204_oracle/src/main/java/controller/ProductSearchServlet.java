package controller;


import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.CategoryDTO;
import dto.ProductDTO;
import model.CategoryModel;
import model.ProductModel;

@WebServlet("/ProductSearchServlet")
public class ProductSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductSearchServlet() {
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
		String strLowPrice = request.getParameter("low_price");
		String strHighPrice = request.getParameter("high_price");
		String strName = request.getParameter("name");
		int categoryId = Integer.parseInt(request.getParameter("category"));
		int lowPrice = 0;
		int highPrice = 0;
		
		//数値チェック
		if (!(strLowPrice.equals("")))
			try {
				lowPrice = Integer.parseInt(strLowPrice);
			}
			catch (NumberFormatException e) {
				String result = "価格には数値を入力してください。";
				String backPages = "product_search.jsp";
				request.setAttribute("RESULT", result);
				request.setAttribute("BACKPAGES", backPages);
				
				RequestDispatcher rd = request.getRequestDispatcher("/error.jsp"); 
				
				rd.forward(request,  response);
				
				System.out.println(result);
				return;
		}
		if (!(strHighPrice.equals("")))
			try {
				highPrice = Integer.parseInt(strHighPrice);
			}
			catch (NumberFormatException e) {
				String result = "価格には数値を入力してください。";
				String backPages = "product_search.jsp";
				request.setAttribute("RESULT", result);
				request.setAttribute("BACKPAGES", backPages);
				
				RequestDispatcher rd = request.getRequestDispatcher("/error.jsp"); 
				
				rd.forward(request,  response);
				
				System.out.println(result);
				return;
			}
		
		//価格の入力チェック
		if (lowPrice > highPrice && !(strLowPrice.equals("")) && !(strHighPrice.equals(""))) {
			String result = "価格 < 価格で入力してください。";
			String backPages = "product_search.jsp";
			request.setAttribute("RESULT", result);
			request.setAttribute("BACKPAGES", backPages);
			
			RequestDispatcher rd = request.getRequestDispatcher("/error.jsp"); 
			
			rd.forward(request,  response);
			
			System.out.println(result);
			return;
		}

		// 入力チェック通過後の処理
		// ProductModelをインスタンス化
		ProductModel productModel = new ProductModel();
		// beanListを用意
		ArrayList<ProductDTO> productDtoList;
		
		
		// Productの処理--------------------------------------------------------------------------------------
		// 全項目空白時（全件検索）
		if (strName.equals("") && categoryId == 0 && lowPrice == 0 && highPrice == 0) {
			// データをbeanListとして取得
			productDtoList = productModel.getProductList();
		}
		// 項目に入力値が存在する時（条件検索）
		else {
			// データをbeanListとして取得
			productDtoList = productModel.getProductList(strName, categoryId, lowPrice, highPrice);
		}
		// --------------------------------------------------------------------------------------------------
		
		// 該当商品が存在した時
		if (!productDtoList.isEmpty()) {
			
			// Categoryの処理-------------------------------------------------------------------------------------
			// CategoryModelをインスタンス化
			CategoryModel categoryModel = new CategoryModel();
			// データをbeanListとして取得
			ArrayList<CategoryDTO> categoryDtoList = categoryModel.getCategoryList();
			// --------------------------------------------------------------------------------------------------
			
			// フォワードの準備
			request.setAttribute("productDtoList", productDtoList);
			request.setAttribute("categoryDtoList", categoryDtoList);
			// アイテム一覧画面に推移
			RequestDispatcher rd = request.getRequestDispatcher("/product_search_result.jsp");
			rd.forward(request, response);
		}
		
		// 該当商品が存在しない時
		else {
			// フォワードの準備
			String result = "該当する商品が見つかりませんでした。";
			String backPages = "product_search.jsp";
			request.setAttribute("RESULT", result);
			request.setAttribute("BACKPAGES", backPages);
			// エラーページに推移
			RequestDispatcher rd = request.getRequestDispatcher("/error.jsp"); 
			rd.forward(request,  response);
		}
		
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
