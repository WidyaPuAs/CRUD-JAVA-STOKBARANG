package Koneksi; // deklarasi package koneksi

import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.JOptionPane; // mengimpor class yang dibutuhkan

// disini terdapat class config dan method main
public class config {
    public static Connection con; // mendeklarasikan variable statis untuk menyimpan objek koneksi database
    public static Statement stm; // untuk menyimpan objek statement
    
    public static void main(String[] args){
        try { // try catch digunakan untuk mengatasi pengecualian 
            String url ="jdbc:mysql://localhost/db_br"; //membuat variable untuk menampung link yang menghubungkan database
            String user="root"; // untuk autentikasi ke database
            String pass="";
            
            Class.forName("com.mysql.jdbc.Driver"); // memuat driver JDBC MySQL 
            con = DriverManager.getConnection(url,user,pass); // mendapatkan koneksi database dengan url, user, pass yg telah ditentukan
            stm = con.createStatement(); // membuat objek statement untuk menjalankan query sql database
            
            System.out.println("koneksi berhasil;"); // jika berhasil maka akan menjalankan console output koneksi berhasil
        } catch (Exception e) { // exception adalah tipe pengecualian dan e adalah variable untuk mengakses informasi pengecualian
            JOptionPane.showMessageDialog(null,"koneksi gagal\n" + e.getMessage()); 
            // jika terjadi pengecualian maka akan menjalankan JOptionPane.showMessageDialog() diambil dari objek exception e
            System.exit(0); // system akan otomatis memberhentikan program jika gagal koneksi
        }
    }   
}