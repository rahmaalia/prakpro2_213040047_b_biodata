/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package actionlistener;

import java.awt.event.*;

import biodata.Biodata;
import biodata.BiodataFrame;
import dao.BiodataDao;
/**
 *
 * @author LENOVO
 */
public class UpdateActionListener implements ActionListener {
    private final BiodataFrame biodataFrame;// Variable biodataFrame untuk menyimpan nilai biodataFrame
    private final BiodataDao biodataDao;    // Variable biodataDao untuk menyimpan nilai biodataDao
    private String id;                      // Variable id untuk menyimpan nilai id dari biodata yang akan diubah

    // Constructor SimpanActionListener
    public UpdateActionListener(BiodataFrame biodataFrame, BiodataDao biodataDao, String id) {
        // Inisialisasi nilai dari biodataFrame, biodataDao, dan id
        this.biodataFrame = biodataFrame;
        this.biodataDao = biodataDao;
        this.id = id;
    }

    public void actionPerformed(ActionEvent e) {
        // Variable jenisKelamin untuk menyimpan nilai dari objek jenisLaki atau jenisPerempuan
        String jenisKelamin = "";

        // Jika jenisLaki diklik
        if (biodataFrame.getJenisLaki().isSelected()) {
            
            jenisKelamin = biodataFrame.getJenisLaki().getText(); // Maka jenisKelamin akan bernilai Laki-Laki
            biodataFrame.getJenisLaki().setSelected(false);     // Kembalikan radio button ke kondisi semula
        }
        // Jika jenisPerempuan diklik
        else if (biodataFrame.getJenisPerempuan().isSelected()) {
            jenisKelamin = biodataFrame.getJenisPerempuan().getText(); // Maka jenisKelamin akan bernilai Perempuan
            biodataFrame.getJenisPerempuan().setSelected(false); // Kembalikan radio button ke kondisi semula
        }

        // Variable nama untuk menyimpan nilai dari objek textFieldNama (nama)
        String nama = this.biodataFrame.getNamaTextField().getText();
        // Variable telepon untuk menyimpan nilai dari objek textFieldTelepon (telepon)
        String telepon = this.biodataFrame.getNoTeleponTextField().getText();
        // Variable alamat untuk menyimpan nilai dari objek txtOutput (alamat)
        String alamat = this.biodataFrame.getAlamatTextField().getText();

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
        int confirmation = this.biodataFrame.showConfirmation("ubah");

        // Jika confirmation berinilai opsi yes
        if (confirmation == 0) {           
            Biodata biodata = new Biodata(); // Buat objek biodata untuk menyimpan nilai biodata yang akan diubah
            
            biodata.setId(this.id); // Set id biodata dengan nilai this.id            
            biodata.setNama(nama); // Set nama biodata dengan nilai nama           
            biodata.setNoTelepon(telepon);// Set telepon biodata dengan nilai telepon            
            biodata.setJenisKelamin(jenisKelamin); // Set jenisKelamin biodata dengan nilai jenisKelamin           
            biodata.setAlamat(alamat); // Set alamat biodata dengan nilai alamat

            // Panggil method updateBiodata pada biodataFrame dengan parameter biodata
            this.biodataFrame.updateBiodata(biodata);
            // Panggil method update pada biodataDao dengan parameter biodata
            this.biodataDao.update(biodata);
            // Panggil method showAlertSuccess pada biodataFrame dengan parameter "diubah"
            this.biodataFrame.showAlertSuccess("diubah");           
            this.id = null; // Kembalikan nilai id ke kondisi kosong
        }
        // Jika confirmation nilai opsi no
        else {
            // Panggil method showAlertFailed pada biodataFrame dengan parameter "diubah"
            this.biodataFrame.showAlertFailed("diubah");
        }

        
        this.biodataFrame.getNamaTextField().setText(""); // Kembalikan isi textFieldNama ke kondisi kosong        
        this.biodataFrame.getNoTeleponTextField().setText(""); // Kembalikan isi textFieldTelepon ke kondisi kosong       
        this.biodataFrame.getAlamatTextField().setText(""); // Kembalikan isi textare ke kondisi kosong     
        this.biodataFrame.getButtonUpdate().removeActionListener(this); // Hapus action listener dari button simpan ubah
    }
}
