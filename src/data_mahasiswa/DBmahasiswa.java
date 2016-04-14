/*
 * created by firman (1302100093)
 * berikan kritik dan saran di 085299911170
 * 
 * /
 * 
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

public class DBmahasiswa extends javax.swing.JFrame {
  
    
    private DefaultTableModel model;
   
    /** Creates new form DBmahasiswa */
    public DBmahasiswa() {
        
        initComponents();
         folder = System.getProperty("user.dir") + File.separator + "images";
        //folder = getClass().getResource("").getFile().toString() + "images";
        cetak(folder);
        new File(folder).mkdir();
      
        model=new DefaultTableModel();
        tblmaha.setModel(model);
        model.addColumn("Stambuk");
        model.addColumn("Nama Mahasiswa");
        model.addColumn("Jenis Kelamin");        
        model.addColumn("Tgl Lahir");        
        model.addColumn("Alamat");
        model.addColumn("No. Hp");
        model.addColumn("E-mail");
        model.addColumn("Asal Daerah");        
        model.addColumn("Asal Sekolah");        
        model.addColumn("Fakultas");
        model.addColumn("Jurusan");
        model.addColumn("Angkatan");
        
        bupdate.setEnabled(false);
        bpilihgambar.setEnabled(false);
        tasaldaerah.setEnabled(false);        
        temail.setEnabled(false);
        thp.setEnabled(false);
        tnama.setEnabled(false);
        tasalsekolah.setEnabled(false);
        jcangkatan.setEnabled(false);
        jcfakultas.setEnabled(false);
         bSimpan.setEnabled(false);
         bedit.setEnabled(true);
         bhapus.setEnabled(true);
         bkeluar.setEnabled(true);
         bSimpan.setEnabled(false);
         talamat.setEditable(false);
           jjurusan.setEnabled(false);
         jjeniskelamin.setEnabled(false);
         txttgl.setVisible(false);
         txttanggal.setEnabled(false);

         eGambar.setVisible(false);
         loadData();
    }

public void loadData() {
    
     String id = tstambuk.getText();
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        try {            
            Connection c=ClassDB.getkoneksi();
            Statement s= c.createStatement();
            String sql="Select * from dmahasiswa";
            ResultSet r=s.executeQuery(sql);
            
            while (r.next()) {
                Object[] o=new Object[12];
                o[0]=r.getString("stambuk");
                o[1]=r.getString("nama");
                o[2]=r.getString("jenis_kelamin");                
                o[3]=r.getString("tgl_lahir");
                o[4]=r.getString("alamat");
                o[5]=r.getString("hp");
                o[6]=r.getString("email");
                o[7]=r.getString("asal_daerah");                
                o[8]=r.getString("asal_sekolah");
                o[9]=r.getString("fakultas");
                o[10]=r.getString("jurusan");
                o[11]=r.getString("angkatan");
                
                model.addRow(o);
            }
            r.close();
            s.close();
            
        ((Painter) cGambar).setImage(gambar(id));
            ShowData();
        }catch(SQLException e) {
            System.out.println("Terjadi kesalahan");
        }
    }

public void TambahData() {
  String stambuk= this.tstambuk.getText();
  String nama=this.tnama.getText();
  java.util.Date tgl_lahir=(java.util.Date) this.txttgl.getDate();
  String alamat=this.talamat.getText();
  String hp=this.thp.getText();
  String email=this.temail.getText();
  String asal_daerah=this.tasaldaerah.getText();
  String asal_sekolah=this.tasalsekolah.getText();
  String fakultas=(String) this.jcfakultas.getSelectedItem();
  String jenis_kelamin=(String) this.jjeniskelamin.getSelectedItem();
  String jurusan=(String) this.jjurusan.getSelectedItem();              
  String angkatan=(String) this.jcangkatan.getSelectedItem();
  
       try {
           
            Connection c=ClassDB.getkoneksi();
            String sql = "Insert into dmahasiswa values (?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement p=(PreparedStatement) c.prepareStatement(sql);
            p.setString(1, stambuk);
            p.setString(2, nama);
            p.setString(3, jenis_kelamin);
            p.setDate(4,  new java.sql.Date(tgl_lahir.getTime()));
                     
            p.setString(5, alamat);
            p.setString(6, hp);
            p.setString(7, email);
            p.setString(8, asal_daerah);
            p.setString(9, asal_sekolah);
            p.setString(10, fakultas);
            p.setString(11, jurusan);
            p.setString(12, angkatan);
            
            p.executeUpdate();
            p.close();
       
             new NIOCopier(eGambar.getText(), gambar(tstambuk.getText()));
     
       } catch (IOException ex) {
          cetak(ex.toString());
       }catch(SQLException e){
            System.out.println(e);
        }finally{
            loadData();
        }
}

public void UpdateData() {
    
    int i=tblmaha.getSelectedRow();
        if(i==-1)
        {
            return;
        }
  String id=(String) model.getValueAt(i, 0);
  String nama=this.tnama.getText();
  String tgl_lahir=this.txttanggal.getText();
  String alamat=this.talamat.getText();
  String hp=this.thp.getText();
  String email=this.temail.getText();
  String asal_daerah=this.tasaldaerah.getText();
  String asal_sekolah=this.tasalsekolah.getText();
  String fakultas=(String) this.jcfakultas.getSelectedItem();
  String jenis_kelamin=(String) this.jjeniskelamin.getSelectedItem();
  String jurusan=(String) this.jjurusan.getSelectedItem();              
  String angkatan=(String) this.jcangkatan.getSelectedItem();
        try {
            Connection c=ClassDB.getkoneksi();
            String sql = "Update dmahasiswa Set nama=?"
                    + ",jenis_kelamin=?,tgl_lahir=?,alamat=?,hp=?"
                    + ",email=?,asal_daerah=?,asal_sekolah=?"
                    + ",fakultas=?,jurusan=?,angkatan=? WHERE stambuk=?";
            PreparedStatement p=(PreparedStatement) c.prepareStatement(sql);
            p.setString(1, nama);
            p.setString(2, jenis_kelamin);
            p.setString(3, tgl_lahir);                     
            p.setString(4, alamat);
            p.setString(5, hp);
            p.setString(6, email);
            p.setString(7, asal_daerah);
            p.setString(8, asal_sekolah);
            p.setString(9, fakultas);
            p.setString(10, jurusan);
            p.setString(11, angkatan);
            p.setString(12, id);
            p.executeUpdate();
            p.close();
              new NIOCopier(eGambar.getText(), gambar(tstambuk.getText()));
           } catch (IOException ex) {
          cetak(ex.toString());
        }catch(SQLException e){

            System.out.println("Terjadi kesalahan pada pengupdetan data");
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
            String sql = "DELETE From dmahasiswa  WHERE stambuk=?";
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

public void mouseClicked(MouseEvent e) {
      ShowData();
   }
           

    private void cetak(String str) {
        System.out.println(str);
    }
    

    public void kosong (){
        txttanggal.setText("");
        tstambuk.setText("");
        tnama.setText("");
        jjeniskelamin.setSelectedIndex(0);
        talamat.setText("");
        thp.setText("");
        temail.setText("");
        tasaldaerah.setText("");
        tasalsekolah.setText("");
        jjurusan.setSelectedIndex(0);
        jcfakultas.setSelectedIndex(0);
        jcangkatan.setSelectedIndex(0);
     }
            
            
public void ShowData() {
     String id = tstambuk.getText();
       
    int i=tblmaha.getSelectedRow();
        
        if(i==-1)
        {
            return;
        }
        String stambuk= (String) model.getValueAt(i, 0);
        tstambuk.setText(stambuk);
        String nama=(String) model.getValueAt(i, 1);
        tnama.setText(nama);        
        String jenis_kelamin=(String) model.getValueAt(i, 2);
        jjeniskelamin.setSelectedItem(jenis_kelamin);
         String tgl_lahir=(String) model.getValueAt(i, 3);
        txttanggal.setText(tgl_lahir);
        String alamat=(String) model.getValueAt(i, 4);
        talamat.setText(alamat);
        String hp=(String) model.getValueAt(i, 5);
        thp.setText(hp);
        String email=(String) model.getValueAt(i, 6);
        temail.setText(email);
        String asal_daerah=(String) model.getValueAt(i, 7);
        tasaldaerah.setText(asal_daerah);
        String asal_sekolah=(String) model.getValueAt(i, 8);
        tasalsekolah.setText(asal_sekolah);
        String fakultas=(String) model.getValueAt(i, 9);
        jcfakultas.setSelectedItem(fakultas);
        String jurusan=(String) model.getValueAt(i, 10);
        jjurusan.setSelectedItem(jurusan);
        String angkatan=(String) model.getValueAt(i, 11);
        jcangkatan.setSelectedItem(angkatan);
        ((Painter) cGambar).setImage(gambar(id));
     }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jcangkatan = new javax.swing.JComboBox();
        jcfakultas = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jjurusan = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        tasalsekolah = new javax.swing.JTextField();
        tasaldaerah = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        temail = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        thp = new javax.swing.JTextField();
        talamat = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tstambuk = new javax.swing.JTextField();
        tnama = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txttgl = new com.toedter.calendar.JDateChooser();
        jjeniskelamin = new javax.swing.JComboBox();
        txttanggal = new javax.swing.JTextField();
        btambah = new javax.swing.JButton();
        bedit = new javax.swing.JButton();
        bhapus = new javax.swing.JButton();
        bSimpan = new javax.swing.JButton();
        bkeluar = new javax.swing.JButton();
        Cbatal = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblmaha = new javax.swing.JTable();
        brefresh = new javax.swing.JButton();
        bupdate = new javax.swing.JButton();
        eGambar = new javax.swing.JTextField();
        cGambar = new Painter();
        bpilihgambar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Input Data Mahasiswa");
        setAlwaysOnTop(true);
        setFocusCycleRoot(false);
        setResizable(false);

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jcangkatan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "pilih", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020" }));

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, tblmaha, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.angkatan}"), jcangkatan, org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        jcangkatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcangkatanActionPerformed(evt);
            }
        });

        jcfakultas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "pilih", "Ilmu Komputer" }));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, tblmaha, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.fakultas}"), jcfakultas, org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        jcfakultas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcfakultasActionPerformed(evt);
            }
        });

        jLabel10.setText("Jurusan");

        jLabel9.setText("Fakultas");

        jLabel11.setText("Angkatan");

        jjurusan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "pilih", "Teknik Informatika", "Sistem Informasi" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addGap(61, 61, 61)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jcfakultas, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcangkatan, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jjurusan, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jcfakultas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jjurusan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jcangkatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel8.setText("Asal Sekolah");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, tblmaha, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.asalSekolah}"), tasalsekolah, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, tblmaha, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.asalDaerah}"), tasaldaerah, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jLabel7.setText("Asal Daerah");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tasalsekolah)
                    .addComponent(tasaldaerah, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(tasaldaerah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tasalsekolah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, tblmaha, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.email}"), temail, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jLabel6.setText("E-mail");

        jLabel5.setText("No. Handphone");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, tblmaha, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.hp}"), thp, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        thp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                thpActionPerformed(evt);
            }
        });

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, tblmaha, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.alamat}"), talamat, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jLabel4.setText("Alamat");

        jLabel3.setText("Tgl Lahir");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, tblmaha, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.stambuk}"), tstambuk, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        tstambuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tstambukActionPerformed(evt);
            }
        });
        tstambuk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tstambukKeyReleased(evt);
            }
        });

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, tblmaha, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.nama}"), tnama, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jLabel2.setText("Nama ");

        jLabel1.setText("Stambuk");

        jLabel12.setText("Jenis Kelamin");

        txttgl.setDateFormatString("dd-mm-yyyy");
        txttgl.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                txttglAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        jjeniskelamin.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "pilih", "Laki-laki", "Perempuan" }));

        txttanggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttanggalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(66, 66, 66)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tstambuk, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tnama, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel3)
                            .addComponent(jLabel12))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(temail, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(thp, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(talamat, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txttanggal)
                                    .addComponent(jjeniskelamin, 0, 164, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txttgl, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(32, 32, 32))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tstambuk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tnama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel12)
                    .addComponent(jjeniskelamin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txttgl, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(txttanggal)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(talamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(thp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(temail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        btambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Add-32.png"))); // NOI18N
        btambah.setText("Tambah");
        btambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btambahActionPerformed(evt);
            }
        });

        bedit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Edit-32.png"))); // NOI18N
        bedit.setText("Edit");
        bedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                beditActionPerformed(evt);
            }
        });

        bhapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Delete-32.png"))); // NOI18N
        bhapus.setText("Hapus");
        bhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bhapusActionPerformed(evt);
            }
        });

        bSimpan.setText("Simpan");
        bSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSimpanActionPerformed(evt);
            }
        });

        bkeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/door_out.png"))); // NOI18N
        bkeluar.setText("Keluar");
        bkeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bkeluarActionPerformed(evt);
            }
        });

        Cbatal.setText("Batal");
        Cbatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CbatalActionPerformed(evt);
            }
        });

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
        tblmaha.setAutoscrolls(false);
        tblmaha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblmahaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblmaha);

        brefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Refresh-32.png"))); // NOI18N
        brefresh.setText("refresh");
        brefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brefreshActionPerformed(evt);
            }
        });

        bupdate.setText("Update");
        bupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bupdateActionPerformed(evt);
            }
        });

        eGambar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eGambarActionPerformed(evt);
            }
        });

        cGambar.setBackground(new java.awt.Color(255, 255, 255));
        cGambar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        bpilihgambar.setText("Pilih Gambar");
        bpilihgambar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bpilihgambarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cGambar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bpilihgambar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btambah, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bedit, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bhapus, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(brefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Cbatal, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bkeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(50, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(546, 546, 546)
                    .addComponent(eGambar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(588, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(cGambar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bpilihgambar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btambah)
                        .addComponent(bkeluar)
                        .addComponent(Cbatal, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(brefresh)
                        .addComponent(bSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bhapus)
                        .addComponent(bedit)))
                .addGap(13, 13, 13)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(268, 268, 268)
                    .addComponent(eGambar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(256, Short.MAX_VALUE)))
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents


private void thpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_thpActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_thpActionPerformed

private void btambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btambahActionPerformed

    bupdate.setEnabled(false);
    kosong();
tblmaha.setVisible(false);
         txttgl.setVisible(true);
         txttanggal.setVisible(false);
        bpilihgambar.setEnabled(true);
    jjeniskelamin.setEnabled(true);
    txttgl.setEnabled(true);
    jjurusan.setEnabled(true);
    tasaldaerah.setEnabled(true);        
        temail.setEnabled(true);
        thp.setEnabled(true);
        tnama.setEnabled(true);
        tasalsekolah.setEnabled(true);
        jcangkatan.setEnabled(true);
        jcfakultas.setEnabled(true);
       bSimpan.setEnabled(true);
         btambah.setEnabled(false);
         bedit.setEnabled(false);
         bhapus.setEnabled(false);
         talamat.setEditable(true);
         
}//GEN-LAST:event_btambahActionPerformed

private void beditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_beditActionPerformed

    bupdate.setEnabled(true);
    txttgl.setVisible(true);
txttanggal.setEnabled(true);
    bpilihgambar.setEnabled(true);
        // TODO add your handling code here:
 jjeniskelamin.setEnabled(true);
    txttgl.setEnabled(true);
    jjurusan.setEnabled(true);
  
   
         tasaldaerah.setEnabled(true);        
        temail.setEnabled(true);
        thp.setEnabled(true);
        tnama.setEnabled(true);
        tasalsekolah.setEnabled(true);
        jcangkatan.setEnabled(true);
        jcfakultas.setEnabled(true);
           bSimpan.setEnabled(false);
         btambah.setEnabled(false);
         bedit.setEnabled(false);
         bhapus.setEnabled(false);
        
   
// TODO add your handling code here:
    
}//GEN-LAST:event_beditActionPerformed

private void bhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bhapusActionPerformed
 String kode=this.tstambuk.getText();
        if ("".equals(kode.trim()) || kode.trim()==null)
          {
          return;
          }else{
        DeleteData();
        kosong();
        }
        // TODO add your handling code here:
}//GEN-LAST:event_bhapusActionPerformed

private void bSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSimpanActionPerformed
        
          TambahData();
        
        kosong();
        tblmaha.setVisible(true);
        txttgl.setVisible(false);
         txttanggal.setEnabled(false);
        bpilihgambar.setEnabled(false);
        tasaldaerah.setEnabled(false);        
        temail.setEnabled(false);
        thp.setEnabled(false);
        tnama.setEnabled(false);
        tasalsekolah.setEnabled(false);
        jcangkatan.setEnabled(false);
        jcfakultas.setEnabled(false);
         bSimpan.setEnabled(false);
         btambah.setEnabled(true);
         bedit.setEnabled(true);
         bhapus.setEnabled(true);
                 
                 
                // TODO add your handling code here:
}//GEN-LAST:event_bSimpanActionPerformed

private void bkeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bkeluarActionPerformed
 this.dispose();
// TODO add your handling code here:
}//GEN-LAST:event_bkeluarActionPerformed

private void jcangkatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcangkatanActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_jcangkatanActionPerformed

private void CbatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbatalActionPerformed
    kosong();
    tblmaha.setVisible(true);
txttgl.setVisible(false);
txttanggal.setEnabled(false);
txttgl.setVisible(false);
txttanggal.setVisible(true);
bpilihgambar.setEnabled(false);
jjurusan.setEnabled(false);
         jjeniskelamin.setEnabled(false);
         txttgl.setEnabled(false);
         talamat.setEditable(false);
    tasaldaerah.setEnabled(false);        
        temail.setEnabled(false);
        thp.setEnabled(false);
        tnama.setEnabled(false);
        tasalsekolah.setEnabled(false);
        jcangkatan.setEnabled(false);
        jcfakultas.setEnabled(false);
         bSimpan.setEnabled(false);
         bedit.setEnabled(true);
         bhapus.setEnabled(true);
         bkeluar.setEnabled(true);
         bSimpan.setEnabled(false);
         btambah.setEnabled(true);
         kosong();
                 // TODO add your handling code here:
}//GEN-LAST:event_CbatalActionPerformed

private void jcfakultasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcfakultasActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_jcfakultasActionPerformed

private void tstambukKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tstambukKeyReleased
 
    // TODO add your handling code here:
}//GEN-LAST:event_tstambukKeyReleased

private void brefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brefreshActionPerformed
    tblmaha.setVisible(true);
    loadData();
}//GEN-LAST:event_brefreshActionPerformed

private void tblmahaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblmahaMouseClicked
ShowData();
txttanggal.setEnabled(true);
bpilihgambar.setEnabled(false);
        tasaldaerah.setEnabled(true);        
        temail.setEnabled(true);
        thp.setEnabled(true);
        tnama.setEnabled(true);
        tasalsekolah.setEnabled(true);
        jcangkatan.setEnabled(true);
        jcfakultas.setEnabled(true);
         bSimpan.setEnabled(true);
         bkeluar.setEnabled(true);
         bSimpan.setEnabled(false);
         talamat.setEditable(true);
           jjurusan.setEnabled(true);
         jjeniskelamin.setEnabled(true);
         txttgl.setEnabled(true);
    // TODO add your handling code here:
}//GEN-LAST:event_tblmahaMouseClicked

private void tstambukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tstambukActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_tstambukActionPerformed
 private String gambar(String id) {
        return folder + File.separator + id.trim() + ".jpg";
    }

   
private void bpilihgambarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bpilihgambarActionPerformed
        javax.swing.JFileChooser jfc = new JFileChooser();
        FileFilter jpgFilter, gifFilter, bothFilter;
        jpgFilter = new FileNameExtensionFilter("Gambar JPEG", "jpg");
        gifFilter = new FileNameExtensionFilter("Gambar GIF", "gif");
        bothFilter = new FileNameExtensionFilter("Gambar JPEG dan GIF", "jpg", "gif");
        jfc.setAcceptAllFileFilterUsed(false);
        jfc.addChoosableFileFilter(jpgFilter);
        jfc.addChoosableFileFilter(gifFilter);
        jfc.addChoosableFileFilter(bothFilter);
        if (jfc.showOpenDialog(this) == jfc.APPROVE_OPTION) {
            String f = jfc.getSelectedFile().toString();
            eGambar.setText(f);
            //lGambar.setIcon(new ImageIcon(f));
            ((Painter) cGambar).setImage(f);

        }// TODO add your handling code here:
}//GEN-LAST:event_bpilihgambarActionPerformed

private void bupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bupdateActionPerformed
        UpdateData();
        kosong();
        bpilihgambar.setEnabled(false);
        tasaldaerah.setEnabled(false);        
        temail.setEnabled(false);
        thp.setEnabled(false);
        tnama.setEnabled(false);
        tasalsekolah.setEnabled(false);
        jcangkatan.setEnabled(false);
        jcfakultas.setEnabled(false);
         bSimpan.setEnabled(false);
         btambah.setEnabled(true);
         bedit.setEnabled(true);
         bhapus.setEnabled(true);
         bupdate.setEnabled(false);
 JOptionPane.showMessageDialog(null, "Data telah terupdate !!!");
       
         // TODO add your handling code here:
}//GEN-LAST:event_bupdateActionPerformed


private void txttanggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttanggalActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_txttanggalActionPerformed

private void txttglAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_txttglAncestorAdded

    // TODO add your handling code here:
}//GEN-LAST:event_txttglAncestorAdded

private void eGambarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eGambarActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_eGambarActionPerformed

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
            java.util.logging.Logger.getLogger(DBmahasiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DBmahasiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DBmahasiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DBmahasiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new DBmahasiswa().setVisible(true);
            }
        });
    }
    private String folder;
    private java.sql.Connection con;
    private java.sql.Statement stmt;
    private String mySqlDriver = "com.mysql.jdbc.Driver";
    private String mySqlUrl = "jdbc:mysql://localhost:3306/dbmahasiswa";
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cbatal;
    private javax.swing.JButton bSimpan;
    private javax.swing.JButton bedit;
    private javax.swing.JButton bhapus;
    private javax.swing.JButton bkeluar;
    private javax.swing.JButton bpilihgambar;
    private javax.swing.JButton brefresh;
    private javax.swing.JButton btambah;
    private javax.swing.JButton bupdate;
    private java.awt.Canvas cGambar;
    private javax.swing.JTextField eGambar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox jcangkatan;
    private javax.swing.JComboBox jcfakultas;
    private javax.swing.JComboBox jjeniskelamin;
    private javax.swing.JComboBox jjurusan;
    private javax.swing.JTextField talamat;
    private javax.swing.JTextField tasaldaerah;
    private javax.swing.JTextField tasalsekolah;
    private javax.swing.JTable tblmaha;
    private javax.swing.JTextField temail;
    private javax.swing.JTextField thp;
    private javax.swing.JTextField tnama;
    private javax.swing.JTextField tstambuk;
    private javax.swing.JTextField txttanggal;
    private com.toedter.calendar.JDateChooser txttgl;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    public class NIOCopier {

        public NIOCopier(String asal, String tujuan) throws IOException {
            FileInputStream inFile = new FileInputStream(asal);
            FileOutputStream outFile = new FileOutputStream(tujuan);
            FileChannel inChannel = inFile.getChannel();
            FileChannel outChannel = outFile.getChannel();
            for (ByteBuffer buffer = ByteBuffer.allocate(1024 * 1024);
                    inChannel.read(buffer) != -1;
                    buffer.clear()) {
                buffer.flip();
                while (buffer.hasRemaining()) {
                    outChannel.write(buffer);
                }
            }
            inChannel.close();
            outChannel.close();
        }
    }
//Class untuk menampilkan gambar

    public class Painter extends Canvas {

        Image image;

        public void setImage(String file) {
            URL url = null;
            try {
                url = new File(file).toURI().toURL();
            } catch (Exception ex) {
                cetak(ex.toString());
            }
            image = getToolkit().getImage(url);
            repaint();
        }

        public void paint(Graphics g) {
         try {
            long  d = image.getHeight(this) / this.getHeight();
            long w = image.getWidth(this) / d;
            long x = this.getWidth() / 2 - w / 2;
            g.drawImage(image, (int) x, 0, (int) (w), this.getHeight(), this);
             } catch (Exception ex) {
                  System.out.println("");
        }
        }
    }

}
