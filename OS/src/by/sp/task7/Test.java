package by.sp.task7;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class Test {
	static Connection conn = null;
	public static DerbyDBManager db = new DerbyDBManager("ExternalDB");
	static GUI gui = new GUI();

	public static void createTable(String tableName) {
		if (tableName.equals("Types")) {
			try {
				db.executeUpdate("CREATE TABLE Types " + "(Type VARCHAR(32) PRIMARY KEY, Description VARCHAR(128) )");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (tableName.equals("Catalogue")) {
			try {
				db.executeUpdate(
						"CREATE TABLE Catalogue " + "(Manufacturer VARCHAR(32), Model VARCHAR(32) PRIMARY KEY, "
								+ "MyYear INT, Country VARCHAR(32), Type VARCHAR(32) REFERENCES Types (Type))");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (tableName.equals("Storage")) {
			try {
				db.executeUpdate(
						"CREATE TABLE Storage " + "(Model VARCHAR(32) PRIMARY KEY REFERENCES Catalogue (Model), "
								+ "Quantity INT, " + "Price INT )");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void fillDBTypes(String tableName, String type, String description) throws SQLException {
		db.executeUpdate("INSERT INTO " + tableName + " VALUES (" + "'" + type + "','" + description + "')");
	}

	public static void fillDBCatalogue(String tableName, String manufacturer, String model, Integer year,
			String country, String type) throws SQLException {
		String catalogueIns = "INSERT INTO Catalogue(Manufacturer, Model, MyYear, Country, Type) VALUES(?,?,?,?,?)";
		conn = db.con;
		PreparedStatement st = conn.prepareStatement(catalogueIns);
		try {
			st.setString(1, manufacturer);
			st.setString(2, model);
			st.setInt(3, year);
			st.setString(4, country);
			st.setString(5, type);
			st.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Insert error: " + e);
		}
	}

	public static void fillDBStorage(String tableName, String model, Integer quantity, Integer price)
			throws SQLException {
		String storageIns = "INSERT INTO Storage(Model, Quantity, Price) VALUES(?,?,?)";
		conn = db.con;
		PreparedStatement st = conn.prepareStatement(storageIns);
		try {
			st.setString(1, model);
			st.setInt(2, quantity);
			st.setInt(3, price);
			st.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Insert error: " + e);
		}
	}

	public static void showDB(String tableName) {
		ResultSet rs = null;
		if (tableName.equals("Types")) {
			try {
				rs = db.executeQuery("SELECT * FROM " + tableName);
				while (rs.next()) {
					new TypeTable().addAtTable(rs.getString(1), rs.getString(2));
				}
				new TypeTable().myTable();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (tableName.equals("Catalogue")) {
			try {
				rs = db.executeQuery("SELECT * FROM " + tableName);
				while (rs.next()) {
					new StoreTable().addAtTable(rs.getString(1), rs.getString(2), Integer.parseInt(rs.getString(3)),
							rs.getString(4), rs.getString(5));
				}
				new StoreTable().myTable();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (tableName.equals("Storage")) {
			try {
				rs = db.executeQuery("SELECT * FROM " + tableName);
				while (rs.next()) {
					new StorageTable().addAtTable(rs.getString(1), Integer.parseInt(rs.getString(2)),
							Integer.parseInt(rs.getString(3)));
				}
				new StorageTable().myTable();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		gui.createGUI();

	}
}
