/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Crud;

import java.sql.SQLException;

/**
 *
 * @author HP
 */
public class Crud {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        koneksi k = new koneksi();
        k.connect();
        
        Mahasiswa mhs = new Mahasiswa();
        mhs.setNim("001");
        mhs.setNama("Andar lutfianto");
        mhs.setJurusan("Teknik Mesin");
        mhs.setAlamat("Kayuringin Jaya");
        mhs.setNo_tlp("081297391775");
        mhs.setNim("002");
        mhs.setNama("Arya wijaya kusuma");
        mhs.setJurusan("Teknik Informatika");
        mhs.setAlamat("Kayuringin Jaya");
        mhs.setNo_tlp("08129739100");
        
        Mahasiswa_ctrl ctrl = new Mahasiswa_ctrl(mhs);
        
        ctrl.saveData();
//          ctrl.deleteData();
    }
    
}
