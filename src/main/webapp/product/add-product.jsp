<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add products</title>
<link rel="stylesheet" href="style/styleAdd.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
	<div class="header">
		<h1>Products</h1>
		<p>Add products</p>
	</div>

	<form action="login.do">
		<input type="submit" value="Log out" />
	</form>

	<div class="mainAdd">
		<p>Add new product</p>
		<hr />

		<html:form action="/addProductResult.do" method="post"
			enctype="multipart/form-data">

			<p>
				<b>Product name</b>
			</p>
			<html:messages id="name_error" property="product.name.required">
				<p style="color: red">
					<bean:write name="name_error" />
				</p>
			</html:messages>
			<html:text property="productName" name="product" />

			<br />

			<p>
				<b>Unit price</b>
			</p>
			<html:messages id="price_error" property="product.price.required">
				<p style="color: red">
					<bean:write name="price_error" />
				</p>
			</html:messages>
			<html:text property="unitPrice" name="product" />

			<br />

			<p>
				<b>Units In Stock</b>
			</p>
			<html:messages id="unit_error" property="product.unit.required">
				<p style="color: red">
					<bean:write name="unit_error" />
				</p>
			</html:messages>
			<html:text property="unitInStock" name="product" />

			<br />

			<p>
				<b>Description</b>
			</p>
			<html:messages id="description_error"
				property="product.description.required">
				<p style="color: red">
					<bean:write name="description_error" />
				</p>
			</html:messages>
			<html:textarea styleClass="description" property="description"
				name="product" />

			<br />

			<p>
				<b>Manufacturer</b>
			</p>
			<html:messages id="manufacturer_error"
				property="product.manufacturer.required">
				<p style="color: red">
					<bean:write name="manufacturer_error" />
				</p>
			</html:messages>
			<html:text property="manufacturer" name="product" />

			<br />

			<p>
				<b>Category</b>
			</p>
			<html:messages id="category_error"
				property="product.category.required">
				<p style="color: red">
					<bean:write name="category_error" />
				</p>
			</html:messages>
			<html:text property="category" name="product" />

			<br />

			<p>
				<b>Condition</b>
			</p>
			<html:messages id="condition_error"
				property="product.condition.required">
				<p style="color: red">
					<bean:write name="condition_error" />
				</p>
			</html:messages>
			<html:text property="condition" name="product" />

			<br />

			<p>
				<b>File</b>
			</p>
			<html:messages id="image_error" property="product.image.required">
				<p style="color: red">
					<bean:write name="image_error" />
				</p>
			</html:messages>
			<html:file property="file" name="product" accept="image/*"></html:file>

			<br />
			<br />

			<html:submit>Add Product</html:submit>

		</html:form>
	</div>
</body>
</html>