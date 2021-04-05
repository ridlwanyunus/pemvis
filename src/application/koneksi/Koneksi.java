package application.koneksi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Koneksi {

	private Connection koneksi;
	
	public Connection connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Koneksi berhasil");
		} catch (Exception e) {
			System.out.println("Koneksi gagal: " + e);
		}
		String url = "jdbc:mysql://localhost/penjualan";
		try {
			koneksi = DriverManager.getConnection(url, "root", "");
			System.out.println("Berhasil koneksi ke database.");
		} catch (SQLException ex) {
			System.out.println("Gagal koneksi database " + ex);
		}
		return koneksi;
	}
	
}
