/*
 * created by firman (1302100093)
 * berikan kritik dan saran di 085299911170
 * 
 * /

/*
 * Input_nilai_KHS.java
 *
 * Created on May 17, 2012, 12:34:17 PM
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
import javax.swing.*;
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
public class Input_nilai_KHS extends javax.swing.JFrame {

    private DefaultTableModel model;
    /** Creates new form Input_nilai_KHS */
    public Input_nilai_KHS() {
        initComponents();
    

        model=new DefaultTableModel();
        tblmaha.setModel(model);
        //data mahasiswa
        model.addColumn("Stambuk");
        model.addColumn("Nama");
        model.addColumn("Tahun ajaran");        
        model.addColumn("Semester");  
        //data krs
        model.addColumn("Kode");
        model.addColumn("Mata Kuliah");
        model.addColumn("SKS");        
        model.addColumn("Nilai");        
        model.addColumn("Nilai X SKS");
        
        jstambuk.setEnabled(false);
        tnama.setEnabled(false);
        jtahun.setEnabled(false);
        jsemester.setEnabled(false);
        jkode.setEnabled(false);
        tmatakul.setEnabled(false);
        tsks.setEnabled(false);
        jComboBox1.setEnabled(false);
        tnk.setEnabled(false);
          loadData();       
        btambah.setEnabled(true);
        bhapus.setEnabled(true);
        bbatal.setEnabled(true);
        bsimpan.setEnabled(false);
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

public void TambahData() {
  String Stambuk=(String) this.jstambuk.getSelectedItem();
  String nama=this.tnama.getText();
  String tahun_ajaran=(String) this.jtahun.getSelectedItem();
  String semester=(String) this.jsemester.getSelectedItem();
  String kode=(String) this.jkode.getSelectedItem();
  String mata_kuliah=this.tmatakul.getText();
  String sks=this.tsks.getText();
  String nilai=(String) this.jComboBox1.getSelectedItem();
  String nilai_x_sks=this.tnk.getText();
       try {
           
            Connection c=ClassDB.getkoneksi();
            String sql = "Insert into dkhs values (?,?,?,?,?,?,?,?,?)";
            PreparedStatement p=(PreparedStatement) c.prepareStatement(sql);
            p.setString(1, Stambuk);
            p.setString(2, nama);
            p.setString(3, tahun_ajaran);
            p.setString(4,  semester);
            p.setString(5, kode);
            p.setString(6, mata_kuliah);
            p.setString(7, sks);
            p.setString(8, nilai);
            p.setString(9, nilai_x_sks);
            p.executeUpdate();
            p.close();
       
       }catch(SQLException e){
            System.out.println(e);
        }finally{
            loadData();
        }
}

public void DeleteData() {
    
    int i=tblmaha.getSelectedRow();
        if(i==-1)
        {
            return;
        }
        String id=(String) model.getValueAt(i, 0);
       
       
        try {
            Connection c=ClassDB.getkoneksi();
            String sql = "DELETE From dkhs  WHERE stambuk=?";
            PreparedStatement p=(PreparedStatement) c.prepareStatement(sql);
            p.setString(1, id);            
            p.executeUpdate();
            p.close();
         }catch(SQLException e){
            System.out.println("Terjadi kesalahan");
        }finally{
            loadData();
        }
}
    public void loadkode(){
     
        try {            
            Connection c=ClassDB.getkoneksi();
            Statement s= c.createStatement();
             String sql="Select kode_mata_kuliah from dnilai where semester='" + jsemester.getSelectedItem() +"'";
           
            ResultSet r=s.executeQuery(sql);
            
            while (r.next()) {
                jkode.addItem(r.getString("kode_mata_kuliah"));
                 
            }
           
            r.close();
            s.close();
        }catch(SQLException e) {
            System.out.println("erjadi kesalahan");
        }
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        tnama = new javax.swing.JTextField();
        jsemester = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jstambuk = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jtahun = new javax.swing.JComboBox();
        tsks = new javax.swing.JTextField();
        tnk = new javax.swing.JTextField();
        tmatakul = new javax.swing.JTextField();
        jkode = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblmaha = new javax.swing.JTable();
        btambah = new javax.swing.JButton();
        bsimpan = new javax.swing.JButton();
        bhapus = new javax.swing.JButton();
        bkeluar = new javax.swing.JButton();
        bbatal = new javax.swing.JButton();
        brefresh = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel11.setText("Nilai X Sks");

        jLabel10.setText("Nilai");

        jLabel9.setText("Sks");

        jLabel8.setText("Mata kuliah");

        jLabel7.setText("Kode");

        jsemester.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--pilih--", "Semester 1", "Semester 2", "Semester 3", "Semester 4", "Semester 5", "Semester 6", "Semester 7", "Semester 8" }));
        jsemester.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jsemesterActionPerformed(evt);
            }
        });

        jLabel2.setText("Nama");

        jstambuk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--pilih--" }));
        jstambuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jstambukActionPerformed(evt);
            }
        });

        jLabel3.setText("Tahun ajaran");

        jLabel1.setText("Stambuk");

        jLabel4.setText("Semester");

        jtahun.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--pilih--", "2010/2011", "2011/2012", "2012/2013", "2013/2014", "2014/2015" }));

        tmatakul.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tmatakulActionPerformed(evt);
            }
        });

        jkode.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--pilih--" }));
        jkode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jkodeActionPerformed(evt);
            }
        });

        jLabel5.setText("A=4,B=3,C=1,E=0");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "---pilih--", "A", "B", "C", "E" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel7)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tnama, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                    .addComponent(jsemester, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tnk, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jstambuk, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jkode, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtahun, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tsks, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5))
                    .addComponent(tmatakul, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jstambuk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tnama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jtahun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jsemester, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jkode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(tmatakul, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(tsks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(tnk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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
        tblmaha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblmahaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblmaha);

        btambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Add-32.png"))); // NOI18N
        btambah.setText("Baru");
        btambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btambahActionPerformed(evt);
            }
        });

        bsimpan.setText("Simpan");
        bsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bsimpanActionPerformed(evt);
            }
        });

        bhapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Delete-32.png"))); // NOI18N
        bhapus.setText("Hapus");
        bhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bhapusActionPerformed(evt);
            }
        });

        bkeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/door_out.png"))); // NOI18N
        bkeluar.setText("Keluar");
        bkeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bkeluarActionPerformed(evt);
            }
        });

        bbatal.setText("Batal");
        bbatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bbatalActionPerformed(evt);
            }
        });

        brefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Refresh-32.png"))); // NOI18N
        brefresh.setText("Refresh");
        brefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btambah, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bsimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bhapus, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bbatal, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(brefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bkeluar, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 639, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, 0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btambah, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bsimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bhapus, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bbatal, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(brefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bkeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void jstambukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jstambukActionPerformed
    if (tnama.getText()==""){
           JOptionPane.showConfirmDialog(this, "error");
       }else{
            try {            
            Connection c=ClassDB.getkoneksi();
            Statement s= c.createStatement();
            String sql="Select nama from dmahasiswa where stambuk='" + jstambuk.getSelectedItem() +"'";
            ResultSet r=s.executeQuery(sql);
            
            while (r.next()) {
                tnama.setText(r.getString("nama"));
                        
            }
           
            r.close();
            s.close();
        }catch(SQLException e) {
            System.out.println("Terjadi error di jstambuk");
        }
       }
}//GEN-LAST:event_jstambukActionPerformed

private void jsemesterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jsemesterActionPerformed
if (tmatakul.getText()==""){
    JOptionPane.showMessageDialog(null, "masih kosong");
}else {
    jkode.removeAllItems();
   
    loadkode();
}
        
    // TODO add your handling code here:
}//GEN-LAST:event_jsemesterActionPerformed

private void bkeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bkeluarActionPerformed
this.dispose();// TODO add your handling code here:
}//GEN-LAST:event_bkeluarActionPerformed

private void bbatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bbatalActionPerformed
stabil();
jstambuk.setEnabled(false);
        tnama.setEnabled(false);
        jtahun.setEnabled(false);
        jsemester.setEnabled(false);
        jkode.setEnabled(false);
        tmatakul.setEnabled(false);
        tsks.setEnabled(false);
        jComboBox1.setEnabled(false);
        tnk.setEnabled(false);
                
        btambah.setEnabled(true);
        bhapus.setEnabled(true);
        bbatal.setEnabled(true);
        bsimpan.setEnabled(false);
        // TODO add your handling code here:
}//GEN-LAST:event_bbatalActionPerformed

private void brefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brefreshActionPerformed
   
    loadData();// TODO add your handling code here:
}//GEN-LAST:event_brefreshActionPerformed

private void jkodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jkodeActionPerformed
if (tmatakul.getText()==""){
           JOptionPane.showConfirmDialog(this, "mata kul masih kosong");
       }else{
            try {            
            Connection c=ClassDB.getkoneksi();
            Statement s= c.createStatement();
            String sql="Select nama_mata_kuliah,kredit from dnilai where kode_mata_kuliah='" + jkode.getSelectedItem() +"'";
            ResultSet r=s.executeQuery(sql);
            
            while (r.next()) {
                tmatakul.setText(r.getString("nama_mata_kuliah"));
                tsks.setText(r.getString("kredit"));        
            }
           
            r.close();
            s.close();
        }catch(SQLException e) {
            System.out.println("Terja");
        }
       }
    
    // TODO add your handling code here:
}//GEN-LAST:event_jkodeActionPerformed

private void tmatakulActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tmatakulActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_tmatakulActionPerformed

private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
int a,b;
    if (jComboBox1.getSelectedIndex()==1){
    a = Integer.parseInt(tsks.getText());
    b = a*4;
    tnk.setText(String.valueOf(b));
    }else  if (jComboBox1.getSelectedIndex()==2){
    a = Integer.parseInt(tsks.getText());
    b = a*3;
    tnk.setText(String.valueOf(b)); 
    }else  if (jComboBox1.getSelectedIndex()==3){
    a = Integer.parseInt(tsks.getText());
    b = a*1;
    tnk.setText(String.valueOf(b));
    }else  if (jComboBox1.getSelectedIndex()==4){
    a = Integer.parseInt(tsks.getText());
    b = a*0;
    tnk.setText(String.valueOf(b));
    }
    // TODO add your handling code here:
}//GEN-LAST:event_jComboBox1ActionPerformed

private void bhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bhapusActionPerformed
 String kode=(String) this.jstambuk.getSelectedItem();
        if ("".equals(kode.trim()) || kode.trim()==null)
          {
          return;
          }else{
        DeleteData();
        stabil();
        
        }// TODO add your handling code here:
}//GEN-LAST:event_bhapusActionPerformed

private void btambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btambahActionPerformed
 jstambuk.setEnabled(true);
        tnama.setEnabled(true);
        jtahun.setEnabled(true);
        jsemester.setEnabled(true);
        jkode.setEnabled(true);
        tmatakul.setEnabled(true);
        tsks.setEnabled(true);
        jComboBox1.setEnabled(true);
        tnk.setEnabled(true);
                
        btambah.setEnabled(false);
        bhapus.setEnabled(false);
        bbatal.setEnabled(true);
        bsimpan.setEnabled(true);
        jstambuk.removeAllItems();
        loadstambuk();
        // TODO add your handling code here:
}//GEN-LAST:event_btambahActionPerformed

private void bsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bsimpanActionPerformed

    TambahData();
    
jstambuk.setEnabled(false);
        tnama.setEnabled(false);
        jtahun.setEnabled(false);
        jsemester.setEnabled(false);
        jkode.setEnabled(false);
        tmatakul.setEnabled(false);
        tsks.setEnabled(false);
        jComboBox1.setEnabled(false);
        tnk.setEnabled(false);
          loadData();       
        btambah.setEnabled(true);
        bhapus.setEnabled(true);
        bbatal.setEnabled(true);
        bsimpan.setEnabled(false);
        JOptionPane.showMessageDialog(null, "Data berhasil tersimpan");
        stabil();
    jstambuk.removeAllItems();
    jComboBox1.setSelectedItem(0);
// TODO add your handling code here:
}//GEN-LAST:event_bsimpanActionPerformed

private void tblmahaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblmahaMouseClicked
ShowData();// TODO add your handling code here:
}//GEN-LAST:event_tblmahaMouseClicked
public void stabil (){
   
   jstambuk.setSelectedItem(0);
        tnama.setText("");
        jtahun.setSelectedItem(0);
        jsemester.setSelectedItem(0);
        jkode.setSelectedItem(0);
        tmatakul.setText("");
        tsks.setText("");
        jComboBox1.setSelectedItem(0);
        tnk.setText("");
        }
public void ShowData() {
    int i=tblmaha.getSelectedRow();
        
        if(i==-1)
        {
            return;
        }
  String Stambuk=(String) model.getValueAt(i, 0);
  jstambuk.setSelectedItem(Stambuk);
  String nama= (String) model.getValueAt(i, 1);
  tnama.setText(nama);
  String tahun_ajaran= (String) model.getValueAt(i, 2);
  jtahun.setSelectedItem(tahun_ajaran);
  String semester= (String) model.getValueAt(i, 3);
  jsemester.setSelectedItem(semester);
  String kode= (String) model.getValueAt(i, 4);
  jkode.setSelectedItem(kode);
  String mata_kuliah= (String) model.getValueAt(i, 5);
  tmatakul.setText(mata_kuliah);
  String sks= (String) model.getValueAt(i, 6);
  tsks.setText(sks);
  String nilai= (String) model.getValueAt(i, 7);
  jComboBox1.setSelectedItem(nilai);
  String nilai_x_sks=(String) model.getValueAt(i, 8);
  tnk.setText(nilai_x_sks);
  jstambuk.removeAllItems();
  loadstambuk();
  }
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
            java.util.logging.Logger.getLogger(Input_nilai_KHS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Input_nilai_KHS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Input_nilai_KHS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Input_nilai_KHS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Input_nilai_KHS().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bbatal;
    private javax.swing.JButton bhapus;
    private javax.swing.JButton bkeluar;
    private javax.swing.JButton brefresh;
    private javax.swing.JButton bsimpan;
    private javax.swing.JButton btambah;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox jkode;
    private javax.swing.JComboBox jsemester;
    private javax.swing.JComboBox jstambuk;
    private javax.swing.JComboBox jtahun;
    private javax.swing.JTable tblmaha;
    private javax.swing.JTextField tmatakul;
    private javax.swing.JTextField tnama;
    private javax.swing.JTextField tnk;
    private javax.swing.JTextField tsks;
    // End of variables declaration//GEN-END:variables
}
