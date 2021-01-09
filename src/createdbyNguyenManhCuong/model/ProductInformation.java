package createdbyNguyenManhCuong.model;


public class ProductInformation implements Comparable<ProductInformation>{
	
	public static enum TypeCompare {
		name,
		amount, 
		price,
		macDinh
	}
	
	public static TypeCompare typeCompare;
	
	private int id;
	private String productName;
	private String productTrademark;
	private String productType;
	private long productPrice;
	private long productAmount; // số lượng sản phẩm.
	private String productDescribe; // mô tả sản phẩm.
	private byte[] productImage;
	
	public ProductInformation() {
		super();
	}
	public ProductInformation(int id, String productName, String productTrademark, String productType, long productPrice, 
			long productAmount, String productDescribe, byte[] image) {
		super();
		this.id = id;
		this.productName = productName;
		this.productTrademark = productTrademark;
		this.productType = productType;
		this.productPrice = productPrice;
		this.productAmount = productAmount;
		this.productDescribe = productDescribe;
		this.productImage = image;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public byte[] getProductImage() {
		return productImage;
	}
	public void setProductImage(byte[] productImage) {
		this.productImage = productImage;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductTrademark() {
		return productTrademark;
	}
	public void setProductTrademark(String productTrademark) {
		this.productTrademark = productTrademark;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public long getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(long prodtctPrice) {
		this.productPrice = prodtctPrice;
	}
	public long getProductAmount() {
		return productAmount;
	}
	public void setProductAmount(long productAmount) {
		this.productAmount = productAmount;
	}
	public String getProductDescribe() {
		return productDescribe;
	}
	public void setProductDescribe(String productDescribe) {
		this.productDescribe = productDescribe;
	}
    @Override
    public String toString() {
    	return	"ID: " + id +
    			"\nName: " +  productName + 
    		   "\nType: " + productType +
    		   "\nPrice: " + productPrice +
    		   "\nAmount: " + productAmount + 
    		   "\nDescride: " + productDescribe;
    }
	
	@Override
	public int compareTo(ProductInformation o) {
		if(typeCompare == TypeCompare.name) { // So Sánh theo Tên Sản Phẩm
			return this.getProductName().compareToIgnoreCase(o.getProductName());
		}
		else if(typeCompare == TypeCompare.amount) { // So Sánh Theo Số Lượng Sản Phẩm
			if(this.getProductAmount() > o.getProductAmount()) return 1;
			else if(this.getProductAmount() < o.getProductAmount()) return -1;
			else return 0;
		}
		else if(typeCompare == TypeCompare.price) { // So Sánh Theo Price Của Sản Phảm
			if(this.getProductPrice() > o.getProductPrice()) return 1;
			else if(this.getProductPrice() < o.getProductPrice()) return -1;
			else return 0;
		}
		return 0;
	}
}
