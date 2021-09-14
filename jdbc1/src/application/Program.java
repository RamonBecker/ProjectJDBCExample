package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import db.DB;
import db.DBException;
import db.DBIntegrityException;

public class Program {

	private static Connection conn = DB.getConnection();;
	private static Statement st = null;
	private static ResultSet rs = null;
	private static PreparedStatement ps = null;

	public static void main(String[] args) {

		transacao();
	}

	private static void transacao() {
		try {

			conn.setAutoCommit(false);

			st = conn.createStatement();

			int rows1 = st.executeUpdate("UPDATE seller SET BaseSalary = 2090 WHERE DepartmentId = 1");

			int x = 1;

			if (x < 2) {
				throw new SQLException("Fake error");
			}

			int rows2 = st.executeUpdate("UPDATE seller SET BaseSalary = 3090 WHERE DepartmentId = 2");

			conn.commit();
			System.out.println("rows1 : " + rows1);
			System.out.println("rows2 : " + rows2);

		} catch (SQLException e) {
			try {
				conn.rollback();
				throw new DBException("Transaction rolled back! Caused by: " + e.getMessage());
			} catch (SQLException e1) {
				throw new DBException("Error trying to rollback! Caused by: " + e1.getMessage());
			}
		} finally {
			DB.closeStatement(ps);
			DB.closeConnection();
		}
	}

	private static void deletar() {
		try {
			ps = conn.prepareStatement("DELETE FROM department " + "WHERE " + "Id = ?");

			ps.setInt(1, 2);

			int rowsAffected = ps.executeUpdate();

			System.out.println("Done! Rows affected: " + rowsAffected);

		} catch (SQLException e) {
			throw new DBIntegrityException(e.getMessage());
		} finally {
			DB.closeStatement(ps);
			DB.closeConnection();
		}
	}

	private static void atualizar() {

		try {
			ps = conn.prepareStatement(
					"UPDATE seller " + "SET BaseSalary = BaseSalary + ? " + "WHERE " + "(DepartmentId = ?)");
			// st.
			ps.setDouble(1, 200.0);
			ps.setInt(2, 2);

			int rowsAffected = ps.executeUpdate();

			System.out.println("Done! Rows affected: " + rowsAffected);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(ps);
			DB.closeConnection();
		}
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
		} catch (SQLException e) {
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
