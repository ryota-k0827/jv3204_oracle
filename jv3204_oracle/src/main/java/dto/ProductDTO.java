package dto;

public class ProductDTO {
	// フィールド変数
	private int id;
	private String name;
	private int price;
	private int categoryId;
	
	// アクセサメソッド
	/**
	 * 商品IDのゲッター
	 * @return id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * 商品IDのセッター
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 商品名のゲッター
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 商品名のセッター
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 価格のゲッター
	 * @return price
	 */
	public int getPrice() {
		return price;
	}
	
	/**
	 * 価格のセッター
	 * @param price
	 */
	public void setPrice(int price) {
		this.price = price;
	}
	
	/**
	 * カテゴリーIDのゲッター
	 * @return categoryId
	 */
	public int getCategoryId() {
		return categoryId;
	}
	
	/**
	 * カテゴリーIDのセッター
	 * @param categoryId
	 */
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

}
