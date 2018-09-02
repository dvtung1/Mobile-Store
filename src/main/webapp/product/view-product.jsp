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
<link rel="stylesheet" href="style/styleView.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
	<div class="header">
		<h1>Products</h1>
		<p>All the available products in our store</p>

	</div>

	<div class="nav">
		<form action="viewCartPost.do">
			<input type="submit" value="View cart" />
		</form>
	</div>

	<div class="mainView">
		<div class="row">
			<logic:empty name="product-list">
			NO ITEMS TO SHOW
			</logic:empty>

			<logic:notEmpty name="product-list">
				<logic:iterate id="product" name="product-list">
					<div class="column">
						<h2>
							<b><bean:write name="product" property="productName" /></b>
						</h2>
						<img src='<bean:write name="product" property="imageSrc"/>' />
						<p>
							<bean:write name="product" property="description" />
						</p>
						<p>
							<bean:write name="product" property="unitPrice" />
							USD
						</p>
						<p>
							<bean:write name="product" property="unitInStock" />
							units in stock
						</p>
						<html:link page="/viewCart.do" paramId="productId"
							paramName="product" paramProperty="id">
					Order Now
					</html:link>
					</div>
				</logic:iterate>
			</logic:notEmpty>

		</div>
	</div>
</body>
</html>