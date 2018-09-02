package vn.dvtung1.service;

import java.util.List;

import vn.dvtung1.dao.ProductDao;
import vn.dvtung1.model.Product;

public class ProductService {

   private ProductDao productDao;
   
   public ProductService() {
     productDao = new ProductDao(); 
   }
   
   public void uploadProductService(Product product) {
     productDao.uploadProductDb(product);
   }
   
   public List<Product> getProductService(){
     return productDao.getProducts();
   }
   
   public Product getProductIdService(int id) {
	   return productDao.getProductId(id);
   }
}
