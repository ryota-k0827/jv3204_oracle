package dto;

public class CategoryDTO {
	// フィールド変数
	private int id;
	private String name;
	
	// アクセサメソッド
	/**
	 * カテゴリーIDのセッター
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * カテゴリーIDのゲッター
	 * @return id
	 */
	public int getId() {
		return id;
	}
	/**
	 * カテゴリー名をセッター
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * カテゴリー名のゲッター
	 * @return name
	 */
	public String getName() {
		return name;
	}

}