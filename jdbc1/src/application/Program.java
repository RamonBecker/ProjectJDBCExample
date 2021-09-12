package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;

public class Program {

	private static Connection conn = DB.getConnection();;
	private static Statement st = null;
	private static ResultSet rs = null;
	private static PreparedStatement ps = null;

	public static void main(String[] args) {

		inserir();
	}

	private static void inserir() {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			/*
			 * ps = conn.prepareStatement("INSERT INTO seller " +
			 * "(Name, Email, BirthDate, BaseSalary, DepartmentId) " + "VALUES " +
			 * "(?, ? , ? , ?, ?)", Statement.RETURN_GENERATED_KEYS);
			 * 
			 * ps.setString(1, "Carl Purple"); ps.setString(2, "carl@gmail.com");
			 * ps.setDate(3, new java.sql.Date(sdf.parse("22/04/1985").getTime()));
			 * ps.setDouble(4, 3000.0); ps.setInt(5, 4);
			 */

			ps = conn.prepareStatement("INSERT INTO department (Name) VALUES ('D1'), ('D2')", st.RETURN_GENERATED_KEYS);
			int rowsAffected = ps.executeUpdate();

			if (rowsAffected > 0) {
				ResultSet rs = ps.getGeneratedKeys();

				while (rs.next()) {
					int id = rs.getInt(1);
					System.out.println("Done! Id = " + id);
				}
			} else {
				System.out.println("No row affected!");
			}
		} catch (SQLException  e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(ps);
			DB.closeConnection();
		}
	}

	private static void selecionarDepartamentos() {

		try {
			st = conn.createStatement();
			rs = st.executeQuery("select * from department");
			while (rs.next()) {
				System.out.println(rs.getInt("Id") + ", " + rs.getString("Name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}

}
