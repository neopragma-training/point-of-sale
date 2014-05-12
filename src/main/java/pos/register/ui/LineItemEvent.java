package pos.register.ui;

public class LineItemEvent {
	
	private Object source;
	private LineItemEventType type;
	private String productId;
	private Integer quantity;
	
	public LineItemEvent(Object source, LineItemEventType type, String productId, Integer quantity) {
		this.source = source;
		this.type = type;
		this.productId = productId;
		this.quantity = quantity;
	}

	public Object getSource() {
		return source;
	}
	
	public LineItemEventType getType() {
		return type;
	}
	public String getProductId() {
		return productId;
	}
	public Integer getQuantity() {
		return quantity;
	}

}
