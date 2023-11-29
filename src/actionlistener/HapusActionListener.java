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
public class HapusActionListener implements ActionListener{
    
    private final BiodataFrame biodataFrame; // Variable biodataFrame untuk menyimpan nilai biodataFrame
  private final BiodataDao biodataDao;       // Variable biodataDao untuk menyimpan nilai biodataDao

  // Constructor HapusActionListener
  public HapusActionListener(BiodataFrame biodataFrame, BiodataDao biodataDao) {
    // Inisialisasi nilai dari biodataFrame dan biodataDao
    this.biodataFrame = biodataFrame;
    this.biodataDao = biodataDao;
  }

  public void actionPerformed(ActionEvent e) {    
    int row = this.biodataFrame.getTable().getSelectedRow();       // Variable row untuk menyimpan nilai baris yang dipilih
    int column = this.biodataFrame.getTable().getSelectedColumn(); // Variable column untuk menyimpan nilai kolom yang dipilih

    if (row == -1 || column == -1) {
      this.biodataFrame.showAlertFailed("dihapus");
      return;
    } else {
      // Variable newValue untuk menyimpan nilai dari table yang diedit
      String newValue = (String) this.biodataFrame.getTable().getModel().getValueAt(row, column);     
      Biodata id = new Biodata(); // Variable id untuk menyimpan nilai id dari biodata yang akan dihapus
     
      String col = ""; // Variable col untuk menyimpan nama kolom yang dipilih

      // Switch case untuk menentukan nama kolom yang dipilih
      switch (column) {
          
          case 0:           // Jika kolom bernilai 0              
              col = "nama"; // Set col dengan nama
              break;         
          case 1:                 // Jika kolom bernilai 1            
              col = "no_telepon"; // Set col dengan no_telepon
              break;          
          case 2:                    // Jika kolom bernilai 2              
              col = "jenis_kelamin"; // Set col dengan jenis_kelamin
              break;         
          case 3:             // Jika kolom bernilai 3             
              col = "alamat"; // Set col dengan alamat
              break;          
          default:  // Jika kolom bernilai selain 0, 1, 2, dan 3           
              System.out.println("Kolom tidak ditemukan"); // Tampilkan pesan kolom tidak ditemukan
              break;
      }
	  // Set id dengan nilai id dari biodata yang akan dihapus
	  id = this.biodataDao.select(col, newValue);

      // Variable confirmation untuk menyimpan nilai dari message dialog konfirmasi
      int confirmation = this.biodataFrame.showConfirmation("hapus");

      // Jika confirmation bernilai 1
      if (confirmation == 1) {
        // Panggil method showAlertFailed pada biodataFrame dengan parameter "tidak dihapus"
        this.biodataFrame.showAlertFailed("tidak dihapus");        
        return; // Batalkan proses
      } 
      // Jiak confirmation bernilai 0
      else {       
        this.biodataFrame.deleteBiodata(id); // Hapus tabel biodata berdasarkan id        
        this.biodataDao.delete(id); // Hapus data biodata berdasarkan id        
        this.biodataFrame.showAlertSuccess("dihapus"); // Panggil method showAlertSuccess pada biodataFrame dengan parameter "dihapus"
      }
    }
  }
}
