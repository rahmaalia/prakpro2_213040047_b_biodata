/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

/**
 *
 * @author lenovo
 */
import java.sql.Connection;    // Mengimpor kelas Connection dari paket java.sql untuk mengelola koneksi JDBC
import java.sql.DriverManager; // Mengimpor kelas DriverManager dari paket java.sql untuk manajemen pemandu dalam JDBC

public class MySqlConnection {
    private final String DB_URL = "jdbc:mysql://localhost:3306/biodata"; // Mendefinisikan URL Database
    private final String DB_USER = "root";                                      // Mendefinisikan Username Database
    private final String DB_PASS = "";                                          // Mendefinisikan Password Database

    // Deklarasi variabel instance untuk instance MySqlConnection
    private static MySqlConnection instance;

    // Metode untuk mendapatkan instance MySqlConnection
    public static MySqlConnection getInstance() {
        // Memeriksa apakah instance bernilai null
        if (instance == null) {
            instance = new MySqlConnection();
        }
        return instance; // Mengembalikan instance MySqlConnection
    }

    // Metode untuk mendapatkan koneksi ke database
    public Connection getConnection() {
        Connection connection = null; // Inisialisasi variabel koneksi
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Memuat driver JDBC MySQL
            // Mendapatkan koneksi ke database dengan URL, username, dan password yang ditentukan
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        } catch (Exception e) {
            e.printStackTrace(); // Menangkap dan mencetak error jika ada
        }
        return connection; // Mengembalikan koneksi yang berhasil atau null jika gagal
    }
}