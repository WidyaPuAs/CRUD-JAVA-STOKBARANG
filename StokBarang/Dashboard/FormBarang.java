package Dashboard; // mendeklarasikan package dashboard

import java.sql.SQLException; // import class pengecualian operasi sql seperti kesalahan sintaks SQL, masalah koneksi, atau akses ke database.
import javax.swing.JOptionPane; // menyediakan berbagai dialog yang dapat digunakan untuk menampilkan pesan, mengambil masukan dari pengguna dll
import java.sql.*; // mengimport semua yang digunakan untuk mengakses dan mengelola database
import javax.swing.*; // membuat antarmuka pengguna grafis (GUI) dalam Java (tombol, kotak teks, tabel)
import javax.swing.table.DefaultTableModel; // import implementasi default dari model tabel untuk komponen tabel dalam GUI
import Koneksi.config; // import class untuk koneksi ke database

public class FormBarang extends javax.swing.JFrame {
    
    Connection con; 
    ResultSet ors; 
    Statement stm;

    private Object[][] databarang = null; 
    private String[] judulKolomTabel = {"kode Barang","nama Barang"}; 
    private DefaultTableModel oTbl;
    
    private void openConn(){ 
       try {
          config.main(null);
          con = config.con;
          stm = config.stm;
       } catch (Exception e) {
          JOptionPane.showMessageDialog(rootPane, e);
      }
    }
    
    private void isiTabelSql(String sql) {
        try{
            stm = con.createStatement();
            ors = stm.executeQuery(sql);
            ResultSetMetaData myTable = ors.getMetaData();
            int ikolom = myTable.getColumnCount();
            int ibaris = 0;
            while (ors.next()){
                ibaris = ors.getRow();
            }

            databarang = new Object[ibaris][ikolom];
            int brs = 0;
            ors.beforeFirst();
            while(ors.next()){
                databarang[brs][0] = ors.getString("kd_barang");
                databarang[brs][1] = ors.getString("nama_barang");
                brs++;
            }
            
            oTbl = new DefaultTableModel(databarang,judulKolomTabel);
            tabelbarang.setModel(oTbl);

        }catch(SQLException e){
             JOptionPane.showMessageDialog(rootPane,e);
        }
}


    
    private void cari(){ 
        String sql = "select * from tb_barang where nama_barang = '";
        sql += txtsearch.getText()+ "'";
        isiTabelSql(sql);
    }
    
    private void kosongForm(){ 
     txtkodebarang.setText("");
     txtnamabarang.setText("");
     txtsearch.setText("");
    }
    
    private void simpan(){ 
        try{
            String sql = "insert into tb_barang values ('";
            sql+= txtkodebarang.getText() + "','" ;
            sql+= txtnamabarang.getText() + "')";

            stm.executeUpdate(sql);
            stm.close();
            JOptionPane.showMessageDialog(rootPane,"berhasil Menambahkan data");

            String s = "select * from tb_barang";
            isiTabelSql(s);
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }
    
    private void edit(){ 
        try{
            String sql = "update tb_barang set ";
            sql+= "nama_barang = '" + txtnamabarang.getText() + "'";
            sql+= " where kd_barang = '" + txtkodebarang.getText() + "'";

            stm.executeUpdate(sql);
            stm.close();
            JOptionPane.showMessageDialog(rootPane,"berhasil edit data");
            kosongForm();
            
            String s = "select * from tb_barang";
            isiTabelSql(s);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }

    private void hapus(){ 
        try{
            String sql = "delete from tb_barang where kd_barang = '" + txtkodebarang.getText() + "'";
            stm.executeUpdate(sql);
            stm.close();

            JOptionPane.showMessageDialog(rootPane, "berhasil hapus data");
            kosongForm();
            String s = "select * from tb_barang";
            isiTabelSql(s);

        }catch(SQLException e){
            JOptionPane.showMessageDialog(rootPane, e);
        } 
    }
    
    private void bacaBaris(){ 
        int brs = tabelbarang.getSelectedRow(); // dideklarasikan dan diinisialisasi dgn nilai indeks baris yang dipilih lalu mengembalikan dlm bntuk int
        txtkodebarang.setText(tabelbarang.getValueAt(brs, 0).toString());
        txtnamabarang.setText(tabelbarang.getValueAt(brs, 1).toString()); // mengeset text mengambil dari value variable brs lalu diubah jdi string
    }
    /**
     * Creates new form FormBarang
     */
    public FormBarang() { // merupakan konstruktor class formbarang dipanggil saat objek formbarang dibuat
        initComponents(); // method default berfungsi menginisialisasi komponen GUI yang ada dalam formbarang
        openConn(); // mengkoneksikan ke database lalu melakukan operasi database seperti mengambil data dari tabel database

        String sql = "select * from tb_barang"; // berisi variable yg didalamnya query SQL mengambil semua data dari tabel tb_barang
        isiTabelSql(sql); // mengisi data diambil dri database tadi ke dalam table yang sudah ada
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelJudul = new javax.swing.JLabel();
        labelSearch = new javax.swing.JLabel();
        txtsearch = new javax.swing.JTextField();
        btnadd = new javax.swing.JButton();
        btnupdate = new javax.swing.JButton();
        btndelete = new javax.swing.JButton();
        btnexit = new javax.swing.JButton();
        labelKodebarang = new javax.swing.JLabel();
        txtkodebarang = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelbarang = new javax.swing.JTable();
        labelNamabarang = new javax.swing.JLabel();
        txtnamabarang = new javax.swing.JTextField();
        btnrefresh = new javax.swing.JButton();
        labelPeringatan = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        labelJudul.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 36)); // NOI18N
        labelJudul.setText("FORM BARANG");

        labelSearch.setText("Search");

        txtsearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtsearchMouseClicked(evt);
            }
        });
        txtsearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsearchActionPerformed(evt);
            }
        });
        txtsearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtsearchKeyPressed(evt);
            }
        });

        btnadd.setText("ADD");
        btnadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaddActionPerformed(evt);
            }
        });

        btnupdate.setText("UPDATE");
        btnupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupdateActionPerformed(evt);
            }
        });

        btndelete.setText("DELETE");
        btndelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeleteActionPerformed(evt);
            }
        });

        btnexit.setText("EXIT");
        btnexit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnexitActionPerformed(evt);
            }
        });

        labelKodebarang.setText("Kode Barang");

        tabelbarang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Title 1", "Title 2"
            }
        ));
        tabelbarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelbarangMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabelbarang);

        labelNamabarang.setText("Nama Barang");

        txtnamabarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnamabarangActionPerformed(evt);
            }
        });

        btnrefresh.setText("REFRESH");
        btnrefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrefreshActionPerformed(evt);
            }
        });

        labelPeringatan.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        labelPeringatan.setForeground(new java.awt.Color(255, 51, 51));
        labelPeringatan.setText("*masukkan nama barang");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnadd, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btndelete, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(labelJudul)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelNamabarang)
                            .addComponent(labelKodebarang, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtkodebarang)
                            .addComponent(txtnamabarang)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(btnexit, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnrefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(86, 86, 86)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelSearch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelPeringatan)
                            .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelJudul)
                    .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSearch))
                .addGap(5, 5, 5)
                .addComponent(labelPeringatan)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelKodebarang)
                            .addComponent(txtkodebarang, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelNamabarang)
                            .addComponent(txtnamabarang, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnadd, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btndelete, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnexit, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnrefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(112, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsearchActionPerformed
        // TODO add your handling code here:
        cari();
    }//GEN-LAST:event_txtsearchActionPerformed

    private void btnaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddActionPerformed
        // TODO add your handling code here:
//        simpan();
        try {
            String sql = "INSERT INTO tb_barang (kd_barang, nama_barang) VALUES (?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, txtkodebarang.getText());
            pst.setString(2, txtnamabarang.getText());
            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "Penyimpanan Data Berhasil");

            String s = "select * from tb_barang";
            isiTabelSql(s);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }//GEN-LAST:event_btnaddActionPerformed

    private void btnupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdateActionPerformed
        // TODO add your handling code here:
        edit();
    }//GEN-LAST:event_btnupdateActionPerformed

    private void btnexitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnexitActionPerformed
        // TODO add your handling code here:
        dispose(); //keluar dari operasi saat diklik button keluar
    }//GEN-LAST:event_btnexitActionPerformed

    private void btndeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteActionPerformed
        // TODO add your handling code here:
        
        // berisi method yang akan dijalankan jika tombol delete di klik
        String sPesan = "Yakin mo hapus '" + txtnamabarang.getText() + "'?"; // berisi variable pesan konfirmasi dan mengambil output dari txtnamabarang
        int iConfirm = JOptionPane.showConfirmDialog(rootPane,sPesan , null, JOptionPane.YES_NO_OPTION); 
        // yes_no_option menampilkan dua pilihan yaitu yes dan no
        // didalam method joptionpane terdapat rootpane yaitu menjadi komponen utama dari frame atau panel tempat dialog ini ditampilkan.
        // sPesan memanggil variable dan null berfungsi agar tidak ada ikon yang ditampilkan di dialog konfirmasi
        if(iConfirm == 0){ // 0=yes : 1=no  jika pengguna memilih "Yes" (nilai 0), maka blok kode di dalam kondisi if akan dieksekusi.
            hapus(); 
        }
    }//GEN-LAST:event_btndeleteActionPerformed

    private void txtsearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsearchKeyPressed

    }//GEN-LAST:event_txtsearchKeyPressed

    private void txtsearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtsearchMouseClicked

    }//GEN-LAST:event_txtsearchMouseClicked

    private void txtnamabarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnamabarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnamabarangActionPerformed

    private void btnrefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrefreshActionPerformed
        // TODO add your handling code here:
        kosongForm();
        String sql = "select * from tb_barang";
        isiTabelSql(sql);
    }//GEN-LAST:event_btnrefreshActionPerformed

    private void tabelbarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelbarangMouseClicked
        // TODO add your handling code here:
        bacaBaris();
    }//GEN-LAST:event_tabelbarangMouseClicked

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
            java.util.logging.Logger.getLogger(FormBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        // menampilkan tampilan formbarang
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormBarang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnadd;
    private javax.swing.JButton btndelete;
    private javax.swing.JButton btnexit;
    private javax.swing.JButton btnrefresh;
    private javax.swing.JButton btnupdate;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelJudul;
    private javax.swing.JLabel labelKodebarang;
    private javax.swing.JLabel labelNamabarang;
    private javax.swing.JLabel labelPeringatan;
    private javax.swing.JLabel labelSearch;
    private javax.swing.JTable tabelbarang;
    private javax.swing.JTextField txtkodebarang;
    private javax.swing.JTextField txtnamabarang;
    private javax.swing.JTextField txtsearch;
    // End of variables declaration//GEN-END:variables
}
