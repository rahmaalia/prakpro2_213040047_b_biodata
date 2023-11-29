/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package actionlistener;

import java.awt.event.*;

import biodata.BiodataFrame;
import dao.BiodataDao;
/**
 *
 * @author LENOVO
 */
public class UbahActionListener implements ActionListener{
    private final BiodataFrame biodataFrame; // Variable biodataFrame untuk menyimpan nilai biodataFrame
    private final BiodataDao biodataDao;    // Variable biodataDao untuk menyimpan nilai biodataDao

    public UbahActionListener(BiodataFrame biodataFrame, BiodataDao biodataDao) {
        // Inisialisasi nilai dari biodataFrame dan biodataDao
        this.biodataFrame = biodataFrame;
        this.biodataDao = biodataDao;
    }

    public void actionPerformed(ActionEvent e) {
        int row = this.biodataFrame.getTable().getSelectedRow(); // Variable row untuk menyimpan nilai baris yang dipilih

        int column = this.biodataFrame.getTable().getSelectedColumn(); // Variable column untuk menyimpan nilai kolom yang dipilih

        // Variable biodataUbah untuk menyimpan nilai dari table yang diedit
        String biodataUbah = (String) this.biodataFrame.getTable().getModel().getValueAt(row, column);
        
        String id = ""; // Variable id untuk menyimpan nilai id dari biodata yang akan diubah
       
        String col = ""; // Variable col untuk menyimpan nama kolom yang dipilih

        // Switch case untuk menentukan nama kolom yang dipilih
        switch (column) {            
            case 0: // Jika kolom bernilai 0               
                col = "nama"; // Set col dengan nama
                break;           
            case 1: // Jika kolom bernilai 1               
                col = "no_telepon"; // Set col dengan no_telepon
                break;            
            case 2: // Jika kolom bernilai 2               
                col = "jenis_kelamin"; // Set col dengan jenis_kelamin
                break;           
            case 3: // Jika kolom bernilai 3               
                col = "alamat"; // Set col dengan alamat
                break;           
            default: // Jika kolom bernilai selain 0, 1, 2, dan 3
                // Tampilkan pesan kolom tidak ditemukan
                System.out.println("Kolom tidak ditemukan");
                break;
        }

        // Dapatkan id dari biodata yang akan diubah
        id = this.biodataDao.select(col, biodataUbah).getId();
        
        // Set nilai dari textFieldNama dengan biodata yang akan diubah
        this.biodataFrame.getNamaTextField().setText(this.biodataDao.select(col, biodataUbah).getNama());
        // Set nilai dari textFieldTelepon dengan biodata yang akan diubah
        this.biodataFrame.getNoTeleponTextField().setText(this.biodataDao.select(col, biodataUbah).getNoTelepon());

        // Jika jenis kelamin dari biodata yang akan diubah adalah Laki-Laki
        if (this.biodataDao.select(col, biodataUbah).getJenisKelamin().equals("Laki-Laki")) {
            // Set nilai dari jenisLaki menjadi true
            this.biodataFrame.getJenisLaki().setSelected(true);
        } else {
            // Set nilai dari jenisPerempuan menjadi true
            this.biodataFrame.getJenisPerempuan().setSelected(true);
        }

        // Set nilai dari textFieldAlamat dengan biodata yang akan diubah
        this.biodataFrame.getAlamatTextField().setText(this.biodataDao.select(col, biodataUbah).getAlamat());

        // Instansiasi objek SimpanUbahActionListener dengan nama simpanUbahListener
        UpdateActionListener simpanUbahListener = new UpdateActionListener(
                this.biodataFrame,
                this.biodataDao,
                id);

        // Tambahkan action listener pada buttonSimpanUbah
        this.biodataFrame.getButtonUpdate().addActionListener(simpanUbahListener);
    }
}

