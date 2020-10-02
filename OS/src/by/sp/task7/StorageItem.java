package by.sp.task7;

class StorageItem {
	String model;
	int quantity;
	int price;

	StorageItem(String model, int quantity, int price) {
		this.model = model;
		this.quantity = quantity;
		this.price = price;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getModel() {
		return model;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getPrice() {
		return price;
	}
}
