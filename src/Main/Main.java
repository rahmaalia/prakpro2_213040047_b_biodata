/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;
import javax.swing.*;
import dao.BiodataDao;
import biodata.BiodataFrame;
/**
 *
 * @author LENOVO
 */
public class Main extends JFrame{
    private final BiodataDao biodataDao; // untuk menyimpan nilai biodataDao
    private final BiodataFrame biodataFrame; // untuk menyimpan nilai biodataFrame

    // Constructor Main
    public Main() {
        // mengatur judul dari frame
        this.setTitle("Biodata");

        // mengatur ukuran dari frame
        this.setSize(400, 500);
        
        // Inisialisasi nilai dari biodataDao dan biodataFrame
        this.biodataDao = new BiodataDao();
        this.biodataFrame = new BiodataFrame(biodataDao);

        // untuk menampilkan frame
        this.biodataFrame.setVisible(true);
    }

    public static void main(String[] args) {
        // Method invokeLater dari SwingUtilities untuk menjalankan objek Runnable yang berguna menangani event dan update UI
        SwingUtilities.invokeLater(new Runnable() {
            // Override method run dari Runnable
            public void run() {
                new Main();
            }
        });
    }
}
 
