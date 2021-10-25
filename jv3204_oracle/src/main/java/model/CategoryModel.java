package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.CategoryDAO;
import dto.CategoryDTO;


public class CategoryModel {

	/**
	 * カテゴリー一覧を取得して返却する
	 * @return categoryList
	 */
	public ArrayList<CategoryDTO> getCategoryList() {
		// ResultSetを初期化
		ResultSet rs = null;
		
		ArrayList<CategoryDTO> categoryList = new ArrayList<CategoryDTO>();
		CategoryDAO dao = new CategoryDAO();
		
		try {
			rs = dao.searchCategory();
			while (rs.next()) {
				CategoryDTO dto = new CategoryDTO();
				dto.setId(rs.getInt("id"));
				dto.setName(rs.getString("name"));

				categoryList.add(dto);
			}

		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {

			dao.close();
		}


		return categoryList;
	}
	
	/**
	 * カテゴリーIDでデータを取得し返却する
	 * @param categoryId
	 * @return categoryData
	 */
	public CategoryDTO getCategoryList(int categoryId) {
		// ResultSetを初期化
		ResultSet rs = null;
		CategoryDTO categoryData = new CategoryDTO();
		CategoryDAO dao = new CategoryDAO();
		
		try {
			// データを取得
			rs = dao.searchCategory(categoryId);
			categoryData.setId(rs.getInt("id"));
			categoryData.setName(rs.getString("name"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dao.close();
		}
		return categoryData;
	}
}
