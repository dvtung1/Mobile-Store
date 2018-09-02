package vn.dvtung1.model;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.upload.FormFile;

public class Product extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String productName;
	private int unitPrice;
	private int unitInStock;
	private String description;
	private String manufacturer;
	private String category;
	private String condition;
	private String imageSrc; // file path of image

	private FormFile file; // save file input form into bean

	private int duplication;
	private int totalPrice;

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getDuplication() {
		return duplication;
	}

	public void setDuplication(int duplication) {
		this.duplication = duplication;
	}

	public FormFile getFile() {
		return file;
	}

	public void setFile(FormFile file) {
		this.file = file;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getUnitInStock() {
		return unitInStock;
	}

	public void setUnitInStock(int unitInStock) {
		this.unitInStock = unitInStock;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getImageSrc() {
		return imageSrc;
	}

	public void setImageSrc(String imageSrc) {
		this.imageSrc = imageSrc;
	}

	@Override
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors actionErrors = new ActionErrors();

		if (productName == null || productName.length() == 0) {
			actionErrors.add("product.name.required", new ActionMessage("error.product.name.required"));
		}

		if (unitPrice < 0) {
			actionErrors.add("product.price.required", new ActionMessage("error.product.price.required"));
		}

		if (unitInStock < 0) {
			actionErrors.add("product.unit.required", new ActionMessage("error.product.unit.required"));
		}

		if (description == null || description.length() == 0) {
			actionErrors.add("product.description.required", new ActionMessage("error.product.description.required"));
		}

		if (manufacturer == null || manufacturer.length() == 0) {
			actionErrors.add("product.manufacturer.required", new ActionMessage("error.product.manufacturer.required"));
		}

		if (category == null || category.length() == 0) {
			actionErrors.add("product.category.required", new ActionMessage("error.product.category.required"));
		}

		if (condition == null || condition.length() == 0) {
			actionErrors.add("product.condition.required", new ActionMessage("error.product.condition.required"));
		}

		if (file == null || file.getFileSize() == 0) {
			actionErrors.add("product.image.required", new ActionMessage("error.product.image.required"));
		}

		return actionErrors;
	}

}
