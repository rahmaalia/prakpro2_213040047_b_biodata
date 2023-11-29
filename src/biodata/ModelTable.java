/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biodata;

import javax.swing.table.*;
import java.util.List;
/**
 *
 * @author LENOVO
 */
public class ModelTable extends AbstractTableModel{
    // Array columnNames untuk menyimpan nama-nama kolom
    private String[] columnNames = { "Nama", "Nomor HP", "Jenis Kelamin", "Alamat" };
    // Membuat List dengan nama data yang berisi Biodata untuk menyimpan data dari input
    private List<Biodata> data;

    // Constructor ModelTable dengan parameter List<Biodata> data
    public ModelTable(List<Biodata> data) {
        this.data = data; // Set this.data dengan parameter data
    }

    // Fungsi untuk menambah jumlah kolom
    public int getColumnCount() {
        return columnNames.length;  // Kembalikan panjang/ukuran dari array columnNames
    }

    // Fungsi untuk menambah jumlah baris
    public int getRowCount() {
        return data.size(); // Kembalikan jumlah data yang diterima/panjang dari ArrayList data
    }

    // Fungsi untuk mendapatkan nama dari kolom yang dipilih dari parameter
    public String getColumnName(int col) {       
        return columnNames[col]; // Kembalikan elemen columnNames dengan index col
    }

    // Fungsi untuk mendapatkan nilai dari baris dan kolom tertentu
    public Object getValueAt(int row, int col) {
        // Menyimpan elemen dari List data dari baris yang dipilih ke Biodata rowItem
        Biodata rowItem = data.get(row);
        // Membuat variable value untuk menyimpan nilai dari kolom yang dipilih
        String value = "";

        // Switch case untuk menentukan nilai dari variable value
        switch (col) {
            // Jika col bernilai 0
            case 0:               
                value = rowItem.getNama(); // Set value dengan nama dari rowItem
                break;
            // Jika col bernilai 1
            case 1:                
                value = rowItem.getNoTelepon(); // Set value dengan noTelepon dari rowItem
                break;
            // Jika col bernilai 2
            case 2:                
                value = rowItem.getJenisKelamin(); // Set value dengan jenisKelamin dari rowItem
                break;
            // Jika col bernilai 3
            case 3:               
                value = rowItem.getAlamat(); // Set value dengan alamat dari rowItem
                break;
        }

        // Kembalikan nilai dari variable value
        return value;
    }

    // Fungsi untuk mengatur apakah cell bisa diedit atau tidak
    public boolean isCellEditable(int row, int col) {       
        return false; // Kembalikan nilai false
    }

    // Method untuk menambah nilai ke table
    public void add(Biodata value) {       
        data.add(value); // Menambahkan input user ke List data      
        fireTableRowsInserted(data.size() - 1, data.size() - 1); // Menambahkan elemen-elemen List data ke table
    }

    // Method untuk mengubah nilai di table
    public void update(Biodata value) {
        // Membuat variable i untuk menyimpan index dari data
        int i = 0;

        // Looping untuk mencari index dari data yang ingin diubah
        for (Biodata b : data) {
            // Jika id dari data sama dengan id dari value
            if (b.getId().equals(value.getId())) {               
                b = value;                            // Biodata tersebut menjadi value               
                data.set(i, value);        // Set List data ke-i dengan value               
                fireTableCellUpdated(data.size() - 1, // Mengubah nilai di table
                        data.size() - 1);
            }            
            i++; // Increment i
        }
    }

    // Method untuk menghapus nilai di table
    public void delete(Biodata value) {
        // Membuat variable i untuk menyimpan index dari data
        int i = 0;

        // Looping untuk mencari index dari data yang ingin dihapus
        for (Biodata b : data) {
            // Jika id dari data sama dengan id dari value
            if (b.getId().equals(value.getId())) {
                // Hapus data dari List data
                data.remove(i);
                // Hentikan looping
                break;
            }
            // Increment i
            i++;
        }
        
        // Menghapus nilai di table
        fireTableRowsDeleted(data.size() - 1,
                data.size() - 1);
    }
}
