package vn.dvtung1.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;
import org.apache.struts.upload.FormFile;

import vn.dvtung1.model.Admin;
import vn.dvtung1.model.Product;
import vn.dvtung1.service.CartService;
import vn.dvtung1.service.ProductService;

public class MainAction extends MappingDispatchAction {
	private ProductService productService;
	private CartService cartService;

	public MainAction() {
		productService = new ProductService();
		cartService = new CartService();
	}

	/**
	 * Upload file to project folder.
	 * 
	 * @param file
	 *            Formfile
	 * @throws IOException
	 *             exception
	 */
	private void uploadFile(FormFile file) throws IOException {
		String rootPath = getServlet().getServletContext().getRealPath("/") + "images";

		File folder = new File(rootPath);
		if (!folder.exists()) {
			folder.mkdir(); // create folder for image
		}
		String path = getServlet().getServletContext().getInitParameter("URLAddress");
		// write file to folder
		File newFile = new File(folder.getPath() + "/" + file.getFileName());

		FileOutputStream fos = new FileOutputStream(newFile);
		fos.write(file.getFileData()); 
		fos.close();
	}

	/*
	 * Check if admin has log in or not.
	 */
	public ActionForward addProduct(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(3000); //50m
		String userId = (String) session.getAttribute("userId");
		String userPass = (String) session.getAttribute("userPass");
		if (userId == null || userPass == null) {
			System.out.println("FAILED");
			return mapping.findForward("failed");
		}

		return mapping.findForward("success");
	}

	/*
	 * Add Product to Db.
	 */
	public ActionForward addProductResult(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Product product = (Product) form;

		FormFile file = product.getFile();

		uploadFile(file);

		product.setImageSrc(request.getContextPath() + "/images/" + file.getFileName());

		if (product != null) {
			productService.uploadProductService(product);
		}

		//reset session after form is submitted
		product = new Product();
		form = null;
		
		return mapping.findForward("success");
	}

	/*
	 * View all products available.
	 */
	public ActionForward viewProduct(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		List<Product> productList = productService.getProductService();
		request.setAttribute("product-list", productList);

		return mapping.findForward("view-product");
	}

	/*
	 * Move an item to cart db when users press order now.
	 */
	public ActionForward viewCart(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		int id = Integer.valueOf(request.getParameter("productId"));
		Product product = productService.getProductIdService(id);
		if (product != null) {
			cartService.uploadCartService(product);
		}
		return mapping.findForward("success");
	}

	/*
	 * View current cart list.
	 */
	public ActionForward viewCartPost(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		List<Product> cart = cartService.getCartService();
		request.setAttribute("cart-list", cart);

		request.setAttribute("totalPrice", cartService.totalBillService());
		return mapping.findForward("view-cart");
	}

	/*
	 * When a user delete a single item from cart.
	 */
	public ActionForward viewCartDeleted(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		int id = Integer.valueOf(request.getParameter("productId"));
		cartService.removeCartService(id);

		return mapping.findForward("success");
	}

	/*
	 * User clear all carts.
	 */
	public ActionForward viewCartDeletedAll(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		cartService.removeCartAllService();

		return mapping.findForward("success");
	}

	/*
	 * Verify log-in info.
	 */
	public ActionForward loginVerified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Admin admin = (Admin) form;
		if (admin != null) {
			HttpSession session = request.getSession();
			session.setMaxInactiveInterval(3000);
			session.setAttribute("userId", admin.getUserName());
			session.setAttribute("userPass", admin.getPassWord());
		}
		return mapping.findForward("success");
	}
	
	/*
	 * Reset username and password when refresh the page.
	 */
	public ActionForward login(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		if (session != null) {
			session.invalidate();
		}

		return mapping.findForward("success");
	}

}
