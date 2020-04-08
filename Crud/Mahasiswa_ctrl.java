/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Crud;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class Mahasiswa_ctrl {
    Mahasiswa mhs = new Mahasiswa();
    koneksi k = new koneksi();
    ResultSet rs;
    
    private final String primary_key ="nim";
    private final String table_name = "mahasiswa";
    //select * from mahasiswa
    private final String q_select = " select * from "+ table_name;

    public Mahasiswa_ctrl() {
    }
    
    public Mahasiswa_ctrl(Mahasiswa _mhs){
        this.mhs = _mhs;
    }
    
    private void replaceData() throws SQLException{
        rs.updateString("nim", mhs.getNim());
        rs.updateString("nama", mhs.getNama());
        rs.updateString("jurusan", mhs.getJurusan());
        rs.updateString("alamat", mhs.getAlamat());
        rs.updateString("no_hp", mhs.getNo_tlp());
    }
    
    
    public void saveData(){
        try {
            k = new koneksi();
            if (k.connect()) {
                String q = q_select + " where " + primary_key + " = '"+ mhs.getNim() +"'";
                rs = k.showQuery(q);
                
                if (rs.next()) {
                    this.replaceData();
                    rs.updateRow();
                    System.out.println("Data berhasil diupdate");
                }else{
                    rs.moveToInsertRow();
                    this.replaceData();
                    rs.insertRow();
                    System.out.println("Data berhasil disimpan");
                    rs.moveToCurrentRow();
                }
            }
                
            
        } catch (Exception e) {
            System.err.println("Pesan error : "+ e.getMessage());
        }finally {
            k.close();
        }
    }
    
 public void deleteData() throws SQLException{
     try {
         k = new koneksi();
         if (k.connect()) {
         String q = q_select + " where " + primary_key + " = '"+ mhs.getNim() +"'";
         rs = k.showQuery(q);
         
         if (rs.next()) {
             rs.deleteRow();
         }
         
        }
     } catch (Exception e) {
         e.printStackTrace();
     }finally{
         k.close();
     } 
    }
 
    public void retrieveData(ResultSet _rs, Mahasiswa _mhs) throws SQLException{
        _mhs.setNim(_rs.getString("nim"));
        _mhs.setNama(_rs.getString("nama"));
        _mhs.setJurusan(_rs.getString("jurusan"));
        _mhs.setAlamat(_rs.getString("alamat"));
        _mhs.setNo_tlp(_rs.getString("no_hp"));
    }
    
    private List<Mahasiswa> getList(String _q){
        List<Mahasiswa> list = new ArrayList<Mahasiswa>();
        try {
            k = new koneksi();
            if (k.connect()) {
                rs = k.showQuery(_q);
                while (rs.next()) {                    
                    Mahasiswa mhsList = new Mahasiswa();
                    this.retrieveData(rs, mhsList);
                    list.add(mhsList);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
           k.close();
        }
        return list;
    }
    
    public List<Mahasiswa> getAllList(){
          return this.getList(q_select); 
    }
}
