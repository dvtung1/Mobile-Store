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

public class CartDao {

	private static final String tableName = "CART";

	/**
	 * Upload product to cart table.
	 * 
	 * @param product
	 *            Product
	 */
	public void uploadCartDb(Product product) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String sqlCode = "INSERT INTO " + tableName + " VALUES (?, ?, ?)";

		try {
			connection = MyDbUtil.getConnection();
			preparedStatement = connection.prepareStatement(sqlCode);
			preparedStatement.setInt(1, product.getId());
			preparedStatement.setString(2, product.getProductName());
			preparedStatement.setInt(3, product.getUnitPrice());
			preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeQuietly(preparedStatement);
			DbUtils.closeQuietly(connection);
		}

	}

	/**
	* Get cart list from db.
	*
	* @return cart list
	*/
	public List<Product> getProducts() {
		List<Product> productList = new ArrayList<Product>();

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		String sql = "SELECT DISTINCT * FROM " + tableName;

		try {

			connection = MyDbUtil.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				Product product = new Product();
				int uniqueId = resultSet.getInt("ID");
				product.setId(uniqueId);
				product.setProductName(resultSet.getString("NAME"));
				int price = resultSet.getInt("PRICE");
				product.setUnitPrice(price);
				int quantity = getDuplicate(uniqueId);
				product.setDuplication(quantity); // set quantity
				int totalPrice = price * quantity;
				product.setTotalPrice(totalPrice);
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
	* Remove an item from the cart.
	*
	* @param id
	*			id of item
	* @return cart list
	*/
	public void removeCart(int id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String sql = "DELETE FROM " + tableName + " WHERE ID=?";

		try {

			connection = MyDbUtil.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeQuietly(preparedStatement);
			DbUtils.closeQuietly(connection);
		}

	}

	/**
	* Remove all items from the cart.
	*/
	public void removeCartAll() {
		Connection connection = null;
		Statement statement = null;

		String sql = "DELETE FROM " + tableName;

		try {
			connection = MyDbUtil.getConnection();
			statement = connection.createStatement();
			statement.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeQuietly(statement);
			DbUtils.closeQuietly(connection);
		}
	}

	/**
	 * Get quantity of product based on id.
	 * 
	 * @param id
	 *            id
	 * @return quantity
	 */
	private int getDuplicate(int id) {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		int count = 0;

		String sql = "SELECT COUNT(*) FROM " + tableName + " WHERE ID=" + id + " GROUP BY ID";
		try {
			connection = MyDbUtil.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			if (resultSet.next()) {
				count = resultSet.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeQuietly(resultSet);
			DbUtils.closeQuietly(statement);
			DbUtils.closeQuietly(connection);
		}

		return count;

	}

	/**
	* Calculate total price of all items in the cart.
	*
	* @return price of all items in cart
	*/
	public int calculateBill() {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		String sql = "SELECT SUM(PRICE) FROM " + tableName;

		int total = 0;

		try {
			connection = MyDbUtil.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			if (resultSet.next()) {
				total = resultSet.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeQuietly(resultSet);
			DbUtils.closeQuietly(statement);
			DbUtils.closeQuietly(connection);
		}

		return total;
	}

}
