package vn.dvtung1.service;

import java.util.List;

import vn.dvtung1.dao.CartDao;
import vn.dvtung1.model.Product;

public class CartService {
	private CartDao cartDao;
	
	public CartService() {
		cartDao = new CartDao();
	}
	
	public void uploadCartService(Product product) {
		cartDao.uploadCartDb(product);
	}
	
	public List<Product> getCartService(){
		return cartDao.getProducts();
	}
	
	public void removeCartService(int id) {
		cartDao.removeCart(id);
	}
	
	public void removeCartAllService() {
		cartDao.removeCartAll();
	}
	
	public int totalBillService() {
		return cartDao.calculateBill();
	}
}
