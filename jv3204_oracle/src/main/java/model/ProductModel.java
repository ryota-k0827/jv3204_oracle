package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.ProductDAO;
import dto.ProductDTO;

public class ProductModel {

	/**
	 * 商品一覧を取得して返却する
	 * ProductSearchServletで使用
	 * @return beanList
	 * @throws SQLException
	 */
	public ArrayList<ProductDTO> getProductList() {

		// ResultSet を初期化
		ResultSet rs = null;

		ArrayList<ProductDTO> productList = new ArrayList<ProductDTO>();

		// データベース接続をするために DAO をインスタンス化
		ProductDAO dao = new ProductDAO();

		try {
			rs = dao.searchProducts();

			while (rs.next()) {
				ProductDTO dto = new ProductDTO();
				dto.setId(rs.getInt("no"));
				dto.setName(rs.getString("name"));
				dto.setCategoryId(rs.getInt("category_id"));
				dto.setPrice(rs.getInt("price"));

				productList.add(dto);
			}

		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
		
			dao.close();
		}


		return productList;
	}

	/**
	 * 検索した結果を商品一覧として取得し返却する
	 * ProductSearchServletで使用
	 * @param name
	 * @param categoryId
	 * @param lowPrice
	 * @param highPrice
	 * @return productList
	 * @throws SQLException
	 */
	public ArrayList<ProductDTO> getProductList(String name, int categoryId, int lowPrice, int highPrice) {

		// ResultSet を初期化
		ResultSet rs = null;
		ArrayList<ProductDTO> productList = new ArrayList<ProductDTO>();
		ProductDAO dao = new ProductDAO();

		try {
			// データを取得
			rs = dao.searchProducts(name, categoryId, lowPrice, highPrice);
			while (rs.next()) {
				ProductDTO dto = new ProductDTO();
	
				dto.setId(rs.getInt("no"));
				dto.setName(rs.getString("name"));
				dto.setCategoryId(rs.getInt("category_id"));
				dto.setPrice(rs.getInt("price"));

				productList.add(dto);
			}

		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
	
			dao.close();
		}

		return productList;
	}
	
	/**
	 * 商品IDでデータを取得し返却する（主に重複チェックで使用）
	 * @param categoryId
	 * 
	 * @return productData
	 * @throws SQLException
	 */
	public ProductDTO getProductList(int no) {
		// ResultSetを初期化
		ResultSet rs = null;
		ProductDTO dto = new ProductDTO();
		ProductDAO dao = new ProductDAO();
		
		try {
			// データを取得
			rs = dao.searchProducts(no);
			if (rs.next()) {
				dto.setId(rs.getInt("no"));
				dto.setName(rs.getString("name"));
				dto.setCategoryId(rs.getInt("category_id"));
				dto.setPrice(rs.getInt("price"));
			} else {
				dto = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dao.close();
		}
		return dto;
	}

	/**
	 *テーブルに商品のデータを追加する
	 * @param id
	 * @param name
	 * @param categoryId
	 * @param price
	 * @throws SQLException
	 */
	public void addProduct(int id, String name, int categoryId, int price) {
		
		ProductDAO dao = new ProductDAO();

	
		ProductDTO dto = new ProductDTO();

		dto.setId(id);
		dto.setName(name);
		dto.setCategoryId(categoryId);
		dto.setPrice(price);

		try {
			
			dao.insert(dto);
		} catch (SQLException e) {
	
			e.printStackTrace();
		} finally {
		
			dao.close();
		}

	}

}
