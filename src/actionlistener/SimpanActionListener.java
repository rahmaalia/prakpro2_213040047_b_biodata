/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package actionlistener;

import java.awt.event.*;
import java.util.UUID;

import biodata.Biodata;
import biodata.BiodataFrame;
import dao.BiodataDao;
/**
 *
 * @author LENOVO
 */
public class SimpanActionListener implements ActionListener {
    private final BiodataFrame biodataFrame; // Variable biodataFrame untuk menyimpan nilai biodataFrame
    private final BiodataDao biodataDao;     // Variable biodataDao untuk menyimpan nilai biodataDao

    // Constructor SimpanActionListener
    public SimpanActionListener(BiodataFrame biodataFrame, BiodataDao biodataDao) {
        // Inisialisasi nilai dari biodataFrame dan biodataDao
        this.biodataFrame = biodataFrame;
        this.biodataDao = biodataDao;
    }

    public void actionPerformed(ActionEvent e) {        
        String jenisKelamin = ""; // Variable jenisKelamin untuk menyimpan nilai dari objek jenisLaki atau jenisPerempuan

        // Jika jenis Laki diklik
        if (biodataFrame.getJenisLaki().isSelected()) {           
            jenisKelamin = biodataFrame.getJenisLaki().getText(); // Maka jenisKelamin akan bernilai Laki-Laki            
            biodataFrame.getJenisLaki().setSelected(false);     // Kembalikan radio button ke kondisi semula
        }
        // Jika jenisPerempuan diklik
        if (biodataFrame.getJenisPerempuan().isSelected()) {
            
            jenisKelamin = biodataFrame.getJenisPerempuan().getText(); // Maka jenisKelamin akan bernilai Perempuan            
            biodataFrame.getJenisPerempuan().setSelected(false);     // Kembalikan radio button ke kondisi semula
        }
       
        String nama = biodataFrame.getNama();         // Variable nama untuk menyimpan nilai dari objek textFieldNama (nama)        
        String telepon = biodataFrame.getNoTelepon(); // Variable telepon untuk menyimpan nilai dari objek textFieldTelepon (telepon)       
        String alamat = biodataFrame.getAlamat();     // Variable alamat untuk menyimpan nilai dari objek txtOutput (alamat)

        // Jika nama, telepon dan alamat bernilai kosong
        if (nama.equalsIgnoreCase("") && telepon.equalsIgnoreCase("") && alamat.equalsIgnoreCase("")) {           
            this.biodataFrame.showAlertAllEmpty(); // Panggil method showAlertAllEmpty pada biodataFrame
            return;
        } else {
            // Jika nama bernilai kosong
            if (nama.equalsIgnoreCase("")) {               
                this.biodataFrame.showAlertNameEmpty(); // Panggil method showAlertNameEmpty pada biodataFrame
                return;
            }
            // Jika telepon bernilai kosong
            if (telepon.equalsIgnoreCase("")) {               
                this.biodataFrame.showAlertTelephoneEmpty(); // Panggil method showAlertTelephoneEmpty pada biodataFrame
                return;
            }
            // Jika alamat bernilai kosong
            if (alamat.equalsIgnoreCase("")) {               
                this.biodataFrame.showAlertAddressEmpty(); // Panggil method showAlertAddressEmpty pada biodataFrame
                return;
            }
        }

        // Variable confirmation untuk menyimpan nilai dari message dialog konfirmasi
        int confirmation = this.biodataFrame.showConfirmation("tambah");

        // Jika confirmation berinilai opsi yes
        if (confirmation == 0) {           
            Biodata biodata = new Biodata(); // Instansiasi objek biodata dengan nama biodata

            
            biodata.setId(UUID.randomUUID().toString()); // Set id biodata dengan nilai random            
            biodata.setNama(nama);                          // Set nama biodata dengan nilai dari variable nama            
            biodata.setNoTelepon(telepon);          // Set telepon biodata dengan nilai dari variable telepon            
            biodata.setJenisKelamin(jenisKelamin);          // Set jenisKelamin biodata dengan nilai dari variable jenisKelamin           
            biodata.setAlamat(alamat);                      // Set alamat biodata dengan nilai dari variable alamat

            // Tambahkan biodata ke objek biodataFrame dan biodataDao
            this.biodataFrame.addBiodata(biodata);
            this.biodataDao.insert(biodata);

            // Panggil method showAlertSuccess pada biodataFrame dengan parameter "ditambahkan"
            this.biodataFrame.showAlertSuccess("ditambahkan");
        }
        // Jika confirmation nilai opsi no
        else {
            // Panggil method showAlertFailed pada biodataFrame dengan parameter "ditambahkan"
            this.biodataFrame.showAlertFailed("ditambahkan");
        }
        
        this.biodataFrame.getNamaTextField().setText("");      // Kembalikan isi textFieldNama ke kondisi kosong       
        this.biodataFrame.getNoTeleponTextField().setText(""); // Kembalikan isi textFieldTelepon ke kondisi kosong
        this.biodataFrame.getAlamatTextField().setText("");    // Kembalikan isi textare ke kondisi kosong

    }
}
