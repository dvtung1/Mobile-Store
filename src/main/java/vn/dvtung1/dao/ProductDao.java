package vn.dvtung1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;

import vn.dvtung1.model.Product;
import vn.dvtung1.utils.MyDbUtil;

public class ProductDao {

	private static final String tableName = "PRODUCTINFO";

	private String insertValues(Product product) {

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("INSERT INTO ");
		stringBuilder.append(tableName + " ");
		stringBuilder.append("VALUES (");
		stringBuilder.append("sequence_uniqueID.NEXTVAL" + ", ");		//Oracle sequence
		stringBuilder.append("'" + product.getProductName() + "', ");
		stringBuilder.append(product.getUnitPrice() + ", ");
		stringBuilder.append(product.getUnitInStock() + ", ");
		stringBuilder.append("'" + product.getDescription() + "', ");
		stringBuilder.append("'" + product.getManufacturer() + "', ");
		stringBuilder.append("'" + product.getCategory() + "', ");
		stringBuilder.append("'" + product.getCondition() + "', ");
		stringBuilder.append("'" + product.getImageSrc() + "'");
		stringBuilder.append(")");

		return stringBuilder.toString();
	}

	/**
	 * Upload product to database.
	 * 
	 * @param product
	 *            Product
	 */
	public void uploadProductDb(Product product) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String sqlCode = insertValues(product);

		try {
			connection = MyDbUtil.getConnection();
			preparedStatement = connection.prepareStatement(sqlCode);
			preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeQuietly(preparedStatement);
			DbUtils.closeQuietly(connection);
		}

	}

	/**
	* Get list of all products from db.
	*
	* @return product list
	*/
	public List<Product> getProducts() {
		List<Product> productList = new ArrayList<Product>();

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		String sql = "SELECT * FROM " + tableName;

		try {

			connection = MyDbUtil.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				Product product = new Product();
				product.setId(resultSet.getInt("ID"));
				product.setProductName(resultSet.getString("NAME"));
				product.setUnitPrice(resultSet.getInt("PRICE"));
				product.setUnitInStock(resultSet.getInt("UNITS"));
				product.setDescription(resultSet.getString("DESCRIPTION"));
				product.setManufacturer(resultSet.getString("MANUFACTURER"));
				product.setCategory(resultSet.getString("CATEGORY"));
				product.setCondition(resultSet.getString("CONDITION"));
				product.setImageSrc(resultSet.getString("IMAGEPATH"));

				productList.add(product);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeQuietly(resultSet);
			DbUtils.closeQuietly(statement);
			DbUtils.closeQuietly(connection);
		}

		return productList;
	}

	/**
	* Get specific product info through id.
	*
	* @param id
	*			product id
	* @return product info
	*/
	public Product getProductId(int id) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String sql = "SELECT * FROM " + tableName + " WHERE ID=?";
		
		Product product = new Product();

		try {
		
			connection = MyDbUtil.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				product.setId(resultSet.getInt("ID"));
				product.setProductName(resultSet.getString("NAME"));
				product.setUnitPrice(resultSet.getInt("PRICE"));
				product.setUnitInStock(resultSet.getInt("UNITS"));
				product.setDescription(resultSet.getString("DESCRIPTION"));
				product.setManufacturer(resultSet.getString("MANUFACTURER"));
				product.setCategory(resultSet.getString("CATEGORY"));
				product.setCondition(resultSet.getString("CONDITION"));
				product.setImageSrc(resultSet.getString("IMAGEPATH"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeQuietly(resultSet);
			DbUtils.closeQuietly(preparedStatement);
			DbUtils.closeQuietly(connection);
		}
		
		return product;
	}

}
