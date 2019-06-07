/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

import javafx.application.Platform;

/**
 *
 * @author HADUSH
 */
public class next extends javax.swing.JFrame {
	static DatabaseManager databaseManager=new DatabaseManager();

    /**
     * Creates new form next
     */
    Connection conn=null;
    ResultSet rs=null;
    PreparedStatement pst=null;
    public next() {
        initComponents();
        this.setTitle("Login page");
        conn=databaseManager.connection;
        }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        inter_username = new javax.swing.JTextField();
        inter_password = new javax.swing.JPasswordField();
        login = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        inter_progress_bar = new javax.swing.JProgressBar();
        inter_display = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setLayout(null);

        jLabel2.setFont(new java.awt.Font("Nyala", 0, 18)); // NOI18N
        jLabel2.setText("መለለይ ተጠቃሚ(Username)");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(200, 300, 180, 50);

        jLabel3.setFont(new java.awt.Font("Nyala", 0, 18)); // NOI18N
        jLabel3.setText("መሕለፈ ቃል(Password)");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(200, 350, 190, 40);

        inter_username.setFont(new java.awt.Font("Nyala", 0, 18)); // NOI18N
        inter_username.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inter_usernameKeyPressed(evt);
            }
        });
        jPanel1.add(inter_username);
        inter_username.setBounds(430, 300, 280, 40);

        inter_password.setFont(new java.awt.Font("Nyala", 0, 18)); // NOI18N
        inter_password.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inter_passwordKeyPressed(evt);
            }
        });
        jPanel1.add(inter_password);
        inter_password.setBounds(430, 350, 280, 40);

        login.setFont(new java.awt.Font("Nyala", 0, 24)); // NOI18N
        login.setText("ይእተዉ");
        login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginActionPerformed(evt);
            }
        });
        jPanel1.add(login);
        login.setBounds(430, 400, 280, 50);

        jLabel7.setFont(new java.awt.Font("Nyala", 1, 18)); // NOI18N
        jLabel7.setText("Developers:");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(60, 510, 140, 20);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/call-icon-24.png"))); // NOI18N
        jPanel1.add(jLabel9);
        jLabel9.setBounds(80, 550, 40, 24);

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("+251919054098 / +251945069098 / +25142648779");
        jPanel1.add(jLabel11);
        jLabel11.setBounds(130, 550, 430, 20);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Email-icon-24.png"))); // NOI18N
        jPanel1.add(jLabel10);
        jLabel10.setBounds(80, 590, 30, 24);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("hailu1221tedros@gmail.com / kidane10g@gmail.com / bekuretsyonhintsa@gmail.com");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(130, 590, 540, 17);

        inter_progress_bar.setFont(new java.awt.Font("Nyala", 0, 24)); // NOI18N
        inter_progress_bar.setStringPainted(true);
        jPanel1.add(inter_progress_bar);
        inter_progress_bar.setBounds(190, 460, 520, 40);

        inter_display.setFont(new java.awt.Font("Nyala", 0, 18)); // NOI18N
        inter_display.setForeground(new java.awt.Color(255, 102, 102));
        jPanel1.add(inter_display);
        inter_display.setBounds(370, 260, 340, 30);

        jLabel4.setFont(new java.awt.Font("Nyala", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 51));
        jLabel4.setText("             መሰል ኮፒ ብሕጊ ዝተሓለወ እዩ  ምምሕዳር ከተማ መቐለ ");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(0, 650, 900, 30);

        jLabel1.setFont(new java.awt.Font("Nyala", 0, 24)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/last.jpg"))); // NOI18N
        jPanel1.add(jLabel1);
        jLabel1.setBounds(0, 0, 900, 770);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 681, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(916, 720));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cancell(){
        inter_display.setText("");
        inter_progress_bar.setValue(0);
        
    }
    public static String getRole(){
        return role;
    }
    private void inter_usernameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inter_usernameKeyPressed
        // TODO add your handling code here:
        cancell();
    }//GEN-LAST:event_inter_usernameKeyPressed

    private void inter_passwordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inter_passwordKeyPressed
        // TODO add your handling code here:
        cancell();
    }//GEN-LAST:event_inter_passwordKeyPressed

    private void loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginActionPerformed
    Platform.runLater(new Runnable(){
    	public void run(){
    		Main.primaryStage.show();
    	}
    });
    	// TODO add your handling code here:
        String var_username=inter_username.getText();
        String var_password=inter_password.getText();
        if(var_username.isEmpty() || var_password.isEmpty()){
            inter_display.setText("በይዘኦም ኣብ ክልቲኡ ቀፅሪ ይምልኡ።");
        }else{
            //1.2 max rows
            String tmp_role="";
            int max_row=0;
            try{
                String sql="select count(counter) from account";
                pst=conn.prepareStatement(sql);
                rs=pst.executeQuery();
                while(rs.next()){
                    max_row=Integer.parseInt(rs.getString("count(counter)"));
                }
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null,e);
            }
            //1.2
            //1.1count the specific row   
            int count=0;
            try{
                String sql="select * from account where username='"+var_username+"' and password='"+var_password+"'";
                pst=conn.prepareStatement(sql);
                rs=pst.executeQuery();
                while(rs.next()){
                    count=Integer.parseInt(rs.getString("counter"));
                    tmp_role=rs.getString("type");
                }
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null,e);
            }
            //1.1
            if(count > 0){
                role=tmp_role; 
               int max_num=0,min_num=0;
                if( count > max_row){
                    max_num=count;
                    min_num=max_row;
                }else{
                    max_num=max_row;
                    min_num=count;
                }
                final int ratio=(min_num*100)/max_num;
                new Thread(){
                    @Override
                    public void run(){

                        try{
                            for(int i=0;i<=100;i++){
                                int time=(20*ratio)/100;
                                sleep(time);
                                inter_progress_bar.setValue(i);
                            }
                            dispose();
                            //main_interface m=new main_interface();
                            //m.setVisible(true);
                        }catch(Exception e){
                            JOptionPane.showMessageDialog(null,e);
                        }
                    }
                }.start();

            }else{
                inter_display.setText("ዝተሰሓሓተ መለለይ ተጠቃሚ ወይ መሕለፈ ቃል ተጠቂሞም።");
            }
        }
    }//GEN-LAST:event_loginActionPerformed

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
            java.util.logging.Logger.getLogger(next.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(next.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(next.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(next.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new next().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel inter_display;
    private javax.swing.JPasswordField inter_password;
    private javax.swing.JProgressBar inter_progress_bar;
    private javax.swing.JTextField inter_username;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton login;
    // End of variables declaration//GEN-END:variables
private static  String role="User";
}
