/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * koneksi kon;
 *
 * public m_sapi() throws SQLException {
 *
 * @author Muhammad Fahmy
 */
public class m_sapi extends modelInheritance {

    koneksi kon;
    public static String coba;

    public m_sapi() throws SQLException {
        super();
        kon = new koneksi("root", "", "pplagro");
    }

    @Override
    public boolean delete(String query) throws SQLException {
        String queries = "DELETE FROM sapi WHERE id_sapi=" + query;
        return super.delete(queries); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean save(String query) throws SQLException {
        String queries = "INSERT INTO sapi VALUES (" + query + ")";
        return super.save(queries); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean save2(String query) throws SQLException {
        String queries = "INSERT INTO validasi VALUES (" + query + ")";
        return super.save(queries); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean update(String query, String id) throws SQLException {
        String queries = "UPDATE sapi SET berat_sapi=" + query + " WHERE id_sapi=" + id;
        return super.update(queries); //To change body of generated methods, choose Tools | Templates.
    }

    public DefaultTableModel getTable() throws SQLException {
        String header[] = {"Nomor Sapi", "Berat Sapi", "Umur Sapi", "Nomor Kandang"};
        DefaultTableModel tabelModel = new DefaultTableModel(null, header);
        ResultSet rs = kon.getResult("SELECT s.id_sapi, s.berat_sapi, s.umur_sapi, k.nomor_kandang FROM sapi s join kandang k on s.id_kandang=k.id_kandang");
        for (int i = tabelModel.getRowCount() - 1; i >= 0; i--) {
            tabelModel.removeRow(i);
        }
        while (rs.next()) {
            String kolom[] = new String[4];
            for (int i = 0; i < kolom.length; i++) {
                kolom[i] = rs.getString(i + 1);
            }

            tabelModel.addRow(kolom);
        }
        return tabelModel;
    }

    public String[] nomorSapi() throws SQLException {
        String query = "SELECT * FROM sapi";
        ResultSet rs = kon.getResult(query);
        rs.last();
        String tahun[] = new String[rs.getRow()];
        rs.beforeFirst();
        int a = 0;
        while (rs.next()) {
            tahun[a] = rs.getString("id_sapi");
            a++;
        }
        a = 0;
        return tahun;
    }

    public String coba(String id) throws SQLException {
        String query = "SELECT * FROM sapi WHERE id_sapi=" + id;
        ResultSet rs = kon.getResult(query);
        while (rs.next()) {
            coba = rs.getString("id_sapi");
        }
        return coba;
    }
    
    public String[] kandang() throws SQLException {
        String query = "SELECT * FROM kandang";
        ResultSet rs = kon.getResult(query);
        rs.last();
        String tahun[] = new String[rs.getRow()];
        rs.beforeFirst();
        int a = 0;
        while (rs.next()) {
            tahun[a] = rs.getString("id_kandang");
            a++;
        }
        a = 0;
        return tahun;
    }
}
