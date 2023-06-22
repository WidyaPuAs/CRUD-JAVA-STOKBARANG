package Login;

import java.sql.SQLException; // import class pengecualian operasi sql seperti kesalahan sintaks SQL, masalah koneksi, atau akses ke database.
import javax.swing.JOptionPane; // menyediakan berbagai dialog yang dapat digunakan untuk menampilkan pesan, mengambil masukan dari pengguna dll
import java.sql.*; // mengimport semua yang digunakan untuk mengakses dan mengelola database
import javax.swing.*; // membuat antarmuka pengguna grafis (GUI) dalam Java (tombol, kotak teks, tabel)
import javax.swing.table.DefaultTableModel;
import Koneksi.config; // buat menghubungkan database

public class Login extends javax.swing.JFrame {
    Connection con; // deklarasi
    ResultSet ors; 
    Statement stm;

    private Object[][] dataLogin = null; // deklarasi objek
    
    private void openConn(){ 
       try {
          config.main(null);
          con = config.con;
          stm = config.stm;
       } catch (Exception e) {
          JOptionPane.showMessageDialog(rootPane, e);
      }
    }
    private void signUpButtonClicked() {
        String username = JOptionPane.showInputDialog(this, "Enter your username:");
        if (username != null && !username.isEmpty()) {
            String password = JOptionPane.showInputDialog(this, "Enter your password:");
            if (password != null && !password.isEmpty()) {
                // Lakukan proses registrasi dengan username dan password yang diinputkan
                boolean registrationSuccess = registerUser(username, password);
                if (registrationSuccess) {
                    JOptionPane.showMessageDialog(this, "Registration berhasil!");
                } else {
                    JOptionPane.showMessageDialog(this, "Registrasi gagal! Akun sudah ada", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Invalid password!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private boolean registerUser(String username, String password) {
    // Konfigurasi koneksi database

        try {
            // Membuat koneksi ke database
            config.main(null);
            con = config.con;
            stm = config.stm;

            // Membuat statement SQL untuk INSERT data ke tabel tb_user
            String sql = "INSERT INTO tb_user (username, password) VALUES (?, ?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);

            // Menjalankan perintah INSERT
            int rowsInserted = statement.executeUpdate();

            // Menutup koneksi dan statement
            statement.close();
            con.close();

            // Mengembalikan nilai true jika data berhasil dimasukkan ke dalam tabel
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            // Mengembalikan nilai false jika terjadi kesalahan dalam operasi INSERT
            return false;
        }

    }
    
    public Login() {
        initComponents();
        openConn();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPasswordField1 = new javax.swing.JPasswordField();
        lblJudul = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtusername = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtpassword = new javax.swing.JTextField();
        btnsignup = new javax.swing.JButton();
        btnsignin = new javax.swing.JButton();

        jPasswordField1.setText("jPasswordField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblJudul.setFont(new java.awt.Font("Nirmala UI", 1, 48)); // NOI18N
        lblJudul.setText("Sign In");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Username");

        txtusername.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Password");

        txtpassword.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        btnsignup.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnsignup.setText("Sign Up");
        btnsignup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsignupActionPerformed(evt);
            }
        });

        btnsignin.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnsignin.setText("Login");
        btnsignin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsigninActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(245, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtpassword, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtusername, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(btnsignin, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnsignup, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(241, 241, 241))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblJudul)
                        .addGap(351, 351, 351))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lblJudul)
                .addGap(27, 27, 27)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtusername, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addGap(11, 11, 11)
                .addComponent(txtpassword, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnsignup, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsignin, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(100, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnsignupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsignupActionPerformed
        signUpButtonClicked();
    }//GEN-LAST:event_btnsignupActionPerformed

    private void btnsigninActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsigninActionPerformed
        // TODO add your handling code here:
        String user = txtusername.getText();
        String password = jPasswordField2.getText();
        String cekuser = null;
        String cekpassword = null;
        
        try {
            config.main(null);
            con = config.con;
            stm = config.stm;
            String query = "SELECT * FROM tb_user WHERE username = '"+ user +"' AND password = '" + password +"'";
            ResultSet ors = stm.executeQuery(query);
            while(ors.next()){
                cekuser = ors.getString("username");
                cekpassword = ors.getString("password");
            }
            ors.close();
            stm.close();
        } catch (SQLException e){
            System.err.println("eror kode");
        }
        if(cekuser == null && cekpassword== null){
            String pesan = "Username Atau Password Salah";
            JOptionPane.showMessageDialog(null,pesan,"Message",JOptionPane.INFORMATION_MESSAGE);
        } else {
            String pesan1 = "login berhasil";
            JOptionPane.showMessageDialog(null,pesan1,"message",
            JOptionPane.INFORMATION_MESSAGE);
            new Login().setVisible(true);
            dispose();
        }
    }//GEN-LAST:event_btnsigninActionPerformed

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnsignin;
    private javax.swing.JButton btnsignup;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JLabel lblJudul;
    private javax.swing.JTextField txtpassword;
    private javax.swing.JTextField txtusername;
    // End of variables declaration//GEN-END:variables
}
