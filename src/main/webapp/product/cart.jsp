<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="style/styleCart.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
	<div class="header">
		<h1>Cart</h1>
		<p>All the selected products in your cart</p>
	</div>

	<form action="viewCartDeletedAll.do">
		<input type="submit" value="Clear cart" />
	</form>

	<div class="mainCart">
		<table>
			<tr>
				<th>Product</th>
				<th>Quantity</th>
				<th>Unit price</th>
				<th>Price</th>
				<th>Action</th>
			</tr>

			<logic:notEmpty name="cart-list">
				<logic:iterate id="product" name="cart-list">
					<tr>
						<td><bean:write name="product" property="productName" /></td>
						<td><bean:write name="product" property="duplication" /></td>
						<td><bean:write name="product" property="unitPrice" /></td>
						<td><bean:write name="product" property="totalPrice" /></td>
						<td><html:link page="/viewCartDeleted.do" paramId="productId"
								paramName="product" paramProperty="id">Remove</html:link></td>
					</tr>
				</logic:iterate>
			</logic:notEmpty>
		</table>
		<p id="grandTotal"><b>Grand Total: <%= request.getAttribute("totalPrice")%></b></p>
	</div>
	<form action="viewProduct.do">
		<input type="submit" value="Continue shopping" />
	</form>

</body>
</html>