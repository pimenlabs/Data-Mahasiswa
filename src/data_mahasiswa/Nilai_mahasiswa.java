    /*
 * created by firman (1302100093)
 * berikan kritik dan saran di 085299911170
 * 
 * /
/*
 * Nilai_mahasiswa.java
 *
 * Created on May 17, 2012, 12:20:32 PM
 */
package data_mahasiswa;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import koneksi.ClassDB;
import com.mysql.jdbc.PreparedStatement;
import com.sun.org.apache.bcel.internal.util.JavaWrapper;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author firnan
 */
public class Nilai_mahasiswa extends javax.swing.JFrame {
private DefaultTableModel model;
    /** Creates new form Nilai_mahasiswa */
    public Nilai_mahasiswa() {
        initComponents();
        model=new DefaultTableModel();
        tblmaha.setModel(model);
        model.addColumn("Kode");
        model.addColumn("Mata Kuliah");
        model.addColumn("SKS");
        model.addColumn("NIlai");
        model.addColumn("NIlai X SKS");
        tnama.setEnabled(false);
        jtahunajaran.setEnabled(false);
        jButton1.setEnabled(false);
        jsismester.setEnabled(false);
        jindeks.setText("");
        jnilai.setText("");
        jnilai_x_sks.setText("");
        jjumsks.setText("");
        jprestasi.setText("");
        loadstambuk();
    }
    public void loadstambuk() {
    
        
        try {            
            Connection c=ClassDB.getkoneksi();
            Statement s= c.createStatement();
            String sql="Select stambuk from dmahasiswa";
            ResultSet r=s.executeQuery(sql);
            
            while (r.next()) {
                 jstambuk.addItem(r.getString("stambuk"));
            }
           
            r.close();
            s.close();
        }catch(SQLException e) {
            System.out.println("Terjadi kesalahan");
        }
    }
    

    //proses
    
    

    public void loadData() {
    
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        try {            
            Connection c=ClassDB.getkoneksi();
            Statement s= c.createStatement();
            String sql="Select * from dkhs";
            ResultSet r=s.executeQuery(sql);
            
            while (r.next()) {
                Object[] o=new Object[12];
                o[0]=r.getString("Stambuk");
                o[1]=r.getString("nama");
                o[2]=r.getString("tahun_ajaran");                
                o[3]=r.getString("semester");
                o[4]=r.getString("kode");
                o[5]=r.getString("mata_kuliah");
                o[6]=r.getString("sks");
                o[7]=r.getString("nilai");                
                o[8]=r.getString("nilai_x_sks");
                
                model.addRow(o);
            }
            r.close();
            s.close();
            
        }catch(SQLException e) {
            System.out.println("Terjadi kesalahan");
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblmaha = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jsismester = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jprestasi = new javax.swing.JLabel();
        jindeks = new javax.swing.JLabel();
        jjumsks = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        tnama = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jnilai = new javax.swing.JLabel();
        jnilai_x_sks = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        bcek = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jstambuk = new javax.swing.JComboBox();
        jtahunajaran = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Daftar nilai KHS");
        setResizable(false);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 3, 48)); // NOI18N
        jLabel3.setText("Kartu Hasil Studi");

        tblmaha.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblmaha);

        jButton1.setText("Tampilkan");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jsismester.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--pilih--", "Semester 1", "Semester 2", "Semester 3", "Semester 4", "Semester 5", "Semester 6", "Semester 7", "Semester 8" }));
        jsismester.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jsismesterActionPerformed(evt);
            }
        });

        jLabel2.setText("Semester");

        jLabel1.setText("Tahun ajaran");

        jLabel4.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel4.setText("Indeks Prestasi :");

        jLabel5.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel5.setText("SKS yang di belanjakan :");

        jprestasi.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jprestasi.setText("jprestasi");

        jindeks.setText("jindeks");

        jjumsks.setText("jjumlahsks");

        jLabel9.setText("Data Mahasiswa");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Total SKS =");

        jnilai.setText(" jnilai");

        jnilai_x_sks.setText(" jnilai_x_sks");

        jLabel13.setText("Stambuk");

        jLabel14.setText("Nama");

        jLabel15.setText("Pilih data yang akan di tampilkan");

        bcek.setText("Cek Ketersediaan");
        bcek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bcekActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Total Nilai x SKS =");

        jstambuk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--pilih--" }));

        jtahunajaran.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--pilih--", "2008/2009", "2009/2010", "2010/2011", "2011/2012", "2012/2013", "2013/2014", "2014/2015", " " }));
        jtahunajaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtahunajaranActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(263, 263, 263)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel14))
                                .addGap(40, 40, 40)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tnama, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                                    .addComponent(bcek)
                                    .addComponent(jstambuk, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1))
                                .addGap(45, 45, 45)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jsismester, 0, 123, Short.MAX_VALUE)
                                    .addComponent(jButton1)
                                    .addComponent(jtahunajaran, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jLabel15)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 826, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jindeks))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jjumsks)))))
                .addContainerGap(104, Short.MAX_VALUE))
            .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 999, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(297, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(273, 273, 273))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jnilai, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(141, 141, 141)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jnilai_x_sks)
                        .addGap(181, 181, 181))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jprestasi, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(231, 231, 231))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addGap(1, 1, 1)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel15))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tnama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(jstambuk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jtahunajaran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jsismester, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bcek)
                    .addComponent(jButton1))
                .addGap(13, 13, 13)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jnilai, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jnilai_x_sks))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jindeks))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jjumsks))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jprestasi)
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
 public void loadkode(){
     
        try {            
            Connection c=ClassDB.getkoneksi();
            Statement s= c.createStatement();
             String sql="Select nama from dkhs where stambuk='" + jstambuk.getSelectedItem() +"'";
           
            ResultSet r=s.executeQuery(sql);
            
            while (r.next()) {
               tnama.setText(r.getString("nama")); 
            }
                 
            
          
            r.close();
            s.close();
        }catch(SQLException e) {
            System.out.println("terjadi kesalahan");
        }
 }
private void bcekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bcekActionPerformed
        
  if (jstambuk.getSelectedIndex()==0) {
    JOptionPane.showMessageDialog(null, "Silahkan mengisi stambuk terlebih dahulu");
}else {      
      
        tnama.setEnabled(true);
        jtahunajaran.setEnabled(true);
        jButton1.setEnabled(true);
        jsismester.setEnabled(true);  
          loadkode();
}
    // TODO add your handling code here:
}//GEN-LAST:event_bcekActionPerformed
public void jsks(){
    try {  
          Connection c=ClassDB.getkoneksi();
            Statement s= c.createStatement();
            String sql="Select sum(sks) from dkhs where stambuk='" + jstambuk.getSelectedItem()+"' and tahun_ajaran='" + jtahunajaran.getSelectedItem() + "' and semester='" + jsismester.getSelectedItem() + "'";
      
            ResultSet r=s.executeQuery(sql);
            while (r.next()) {
            jnilai.setText((r.getString("sum(sks)")));    
            jjumsks.setText(r.getString("sum(sks)"));
            }
            r.close();
            s.close();
            
       
        }catch(SQLException e) {
            System.out.println("Terjadi kesalahan >> sum sks");
        }
}

                           
public void jnilai_x_sks(){
    try {  
          Connection c=ClassDB.getkoneksi();
            Statement s= c.createStatement();
            String sql="Select sum(nilai_x_sks) from dkhs where stambuk='" + jstambuk.getSelectedItem()+"' and tahun_ajaran='" + jtahunajaran.getSelectedItem() + "' and semester='" + jsismester.getSelectedItem() + "'";
      
            ResultSet r=s.executeQuery(sql);
            while (r.next()) {
            jnilai_x_sks.setText((r.getString("sum(nilai_x_sks)")));    
            }
            r.close();
            s.close();
        }catch(SQLException e) {
            System.out.println("Terjadi kesalahan >> sum nilai_x_sks");
        }
}
private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
String tahun=(String) jtahunajaran.getSelectedItem();
 model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
if (tahun.trim().equals("")){
    JOptionPane.showMessageDialog(null, "Tahun ajaran masih kosong !!!");
} else {
      try {  
            Connection c=ClassDB.getkoneksi();
            Statement s= c.createStatement();
            String sql="Select kode,mata_kuliah,sks,nilai,nilai_x_sks from dkhs where stambuk='" + jstambuk.getSelectedItem()+"' and tahun_ajaran='" + jtahunajaran.getSelectedItem() + "' and semester='" + jsismester.getSelectedItem() + "'";
     
            ResultSet r=s.executeQuery(sql);
            while (r.next()) {
                Object[] o=new Object[12];
                o[0]=r.getString("kode");
                o[1]=r.getString("mata_kuliah");
                o[2]=r.getString("sks");                
                o[3]=r.getString("nilai");
                o[4]=r.getString("nilai_x_sks");
                model.addRow(o);
                jsks();
                jnilai_x_sks();
                
                double a,b,hasil;
                
                a = Integer.parseInt(jnilai_x_sks.getText());
                b = Integer.parseInt(jnilai.getText());
                hasil=a/b;
                jindeks.setText(String.valueOf(hasil));
                if (jindeks.getText()==""){
                    JOptionPane.showMessageDialog(null, "jumlah indeks masih kosong");
                } else {
                    if ((hasil >= 3.0) && (hasil <=4.0)){
                        jprestasi.setText("Selamat, Anda dalam posisi sangat aman !!!");
                    }else if ((hasil >= 1.5) && (hasil <=2.9)){
                        jprestasi.setText("Selamat, prestasi anda kurang baik !!!");
                    }else if ((hasil >= 0.1) && (hasil <=1.4)){
                        jprestasi.setText("Maaf,anda harus lebih giat belajar lagi !!!");
                    }
                }
            }
            r.close();
            s.close();
            
       
        }catch(SQLException e) {
            System.out.println("Terjadi kesalahan");
        }   
}
    

    // TODO add your handling code here:
}//GEN-LAST:event_jButton1ActionPerformed

    private void jtahunajaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtahunajaranActionPerformed
       // TODO add your handling code here:
    }//GEN-LAST:event_jtahunajaranActionPerformed

    private void jsismesterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jsismesterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jsismesterActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Nilai_mahasiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Nilai_mahasiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Nilai_mahasiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Nilai_mahasiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Nilai_mahasiswa().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bcek;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel jindeks;
    private javax.swing.JLabel jjumsks;
    private javax.swing.JLabel jnilai;
    private javax.swing.JLabel jnilai_x_sks;
    private javax.swing.JLabel jprestasi;
    private javax.swing.JComboBox jsismester;
    private javax.swing.JComboBox jstambuk;
    private javax.swing.JComboBox jtahunajaran;
    private javax.swing.JTable tblmaha;
    private javax.swing.JTextField tnama;
    // End of variables declaration//GEN-END:variables
}
