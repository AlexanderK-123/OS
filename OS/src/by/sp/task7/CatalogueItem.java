package by.sp.task7;

class CatalogueItem {
	String manufacturer;
	String model;
	int year;
	String country;
	String type;

	CatalogueItem(String manufacturer, String model, int year, String country, String type) {
		this.manufacturer = manufacturer;
		this.model = model;
		this.year = year;
		this.country = country;
		this.type = type;
	}

	public void setManufacturer(String name) {
		this.manufacturer = name;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getModel() {
		return model;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getYear() {
		return year;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCountry() {
		return country;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}
