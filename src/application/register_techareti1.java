package application;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.plaf.FontUIResource;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ToccoTedd
 */
public class register_techareti1 extends javax.swing.JFrame {

    /**
     * Creates new form register_techareti
     */
 Connection conn=null;
 ResultSet rs=null;
 PreparedStatement pst=null;
    public register_techareti1() {
        initComponents();
        this.getContentPane().setBackground(Color.white);

        conn=javaconnect.ConnecrDb();
         //for JOptionpane Ge'ez
       javax.swing.UIManager.put("OptionPane.font", new FontUIResource(new Font("Nyala", Font.PLAIN, 20)));
       javax.swing.UIManager.put("OptionPane.messageFont", new Font("Nyala", Font.PLAIN, 20));
       javax.swing.UIManager.put("OptionPane.buttonFont", new Font("Nyala", Font.PLAIN, 20));
      // -> end
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        s1 = new javax.swing.JTextField();
        s2 = new javax.swing.JTextField();
        s3 = new javax.swing.JTextField();
        s4 = new javax.swing.JTextField();
        s5 = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

       // jLabel1.setIcon(new javax.swing.ImageIcon("F:\\T#(Tedd-busi)\\HouseLottery_Module_1\\src\\images\\footerFL_notAccepted.jpg")); // NOI18N

        jLabel2.setIcon(new javax.swing.ImageIcon("src/images/condominuemmk.jpg")); // NOI18N

        jLabel3.setFont(new java.awt.Font("Nyala", 0, 24)); // NOI18N
        jLabel3.setText("                ደለይቲ ገዛ መመዝገቢ ቅጥዒ");

        jLabel4.setFont(new java.awt.Font("Nyala", 0, 18)); // NOI18N
        jLabel4.setText("ሽም");

        jLabel5.setFont(new java.awt.Font("Nyala", 0, 18)); // NOI18N
        jLabel5.setText("ሽም ኣቦ");

        jLabel6.setFont(new java.awt.Font("Nyala", 0, 18)); // NOI18N
        jLabel6.setText("ሽም ኣቦሓጎ");

        jLabel7.setFont(new java.awt.Font("Nyala", 0, 18)); // NOI18N
        jLabel7.setText("ኣድራሻ");

        jLabel8.setFont(new java.awt.Font("Nyala", 0, 18)); // NOI18N
        jLabel8.setText("መደቀሲ ዓይነት");

        s1.setFont(new java.awt.Font("Nyala", 0, 18)); // NOI18N
        s1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                s1ActionPerformed(evt);
            }
        });

        s2.setFont(new java.awt.Font("Nyala", 0, 18)); // NOI18N

        s3.setFont(new java.awt.Font("Nyala", 0, 18)); // NOI18N

        s4.setFont(new java.awt.Font("Nyala", 0, 18)); // NOI18N

        s5.setBackground(new java.awt.Color(248, 248, 248));
        s5.setFont(new java.awt.Font("Nyala", 0, 18)); // NOI18N
        s5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ስትድዮ", "በዓል 1-መደቀሲ", "በዓል 2-መደቀሲ", "በዓል 3-መደቀሲ" }));
        s5.setBorder(null);
        s5.setFocusable(false);

        jButton1.setBackground(new java.awt.Color(243, 248, 243));
        jButton1.setFont(new java.awt.Font("Nyala", 0, 24)); // NOI18N
        jButton1.setText("መዝግብ");
        jButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 255, 0)));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(243, 248, 243));
        jButton2.setFont(new java.awt.Font("Nyala", 0, 24)); // NOI18N
        jButton2.setIcon(new ImageIcon("src/images/app_back_button_sml.jpg")); // NOI18N
        jButton2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 255, 0)));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jButton2)
                .addGap(338, 338, 338)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(s1)
                            .addComponent(s2)
                            .addComponent(s3)
                            .addComponent(s4)
                            .addComponent(s5, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(124, 124, 124)
                        .addComponent(jButton2)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(s1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(s2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(s3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(s4, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(s5, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 133, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void s1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_s1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_s1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        cond_home n=new cond_home();
        n.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String add1=s1.getText();
        String add2=s2.getText();
        String add3=s3.getText();
        String add4=s4.getText();
        String add5=s5.getSelectedItem().toString();
        if(add1.isEmpty()||add2.isEmpty()||add3.isEmpty()||add4.isEmpty()){
            //jLabel5.setText("ክፍቲ ቦታ ኣይተመልኣን");
            JOptionPane.showMessageDialog(null, "ክፍቲ ቦታ ኣይተመልኣን");
        }else{
        try{
            String sql1="select * from customer where Fname='"+add1+"' and Mname='"+add2+"' and Lname='"+add3+"' and Address='"+add4+"'and Interest='"+add5+"'";
            pst=conn.prepareCall(sql1);
            rs=pst.executeQuery(sql1);
            if(rs.next()){
               JOptionPane.showMessageDialog(null, "ተመዝጊቡ ፀኒሑ"); 
            }else{
            String sql="INSERT INTO customer VALUES(NULL,'"+add1+"','"+add2+"','"+add3+"','"+add4+"','"+add5+"')";
            pst=conn.prepareStatement(sql);
            pst.execute();
            if(add5.equals("ስትድዮ")){
                 String s111="INSERT INTO stu_alga VALUES(NULL,'"+add1+"','"+add2+"','"+add3+"','"+add4+"','"+add5+"')";
            pst=conn.prepareStatement(s111);
            pst.execute();
            }else if(add5.equals("በዓል 1-መደቀሲ")){
              String s111="INSERT INTO one_alga VALUES(NULL,'"+add1+"','"+add2+"','"+add3+"','"+add4+"','"+add5+"')";
            pst=conn.prepareStatement(s111);
            pst.execute();  
            }else if(add5.equals("በዓል 2-መደቀሲ")){
                String s111="INSERT INTO two_alga VALUES(NULL,'"+add1+"','"+add2+"','"+add3+"','"+add4+"','"+add5+"')";
            pst=conn.prepareStatement(s111);
            pst.execute(); 
            }else{
                String s111="INSERT INTO three_alga VALUES(NULL,'"+add1+"','"+add2+"','"+add3+"','"+add4+"','"+add5+"')";
            pst=conn.prepareStatement(s111);
            pst.execute(); 
            }
            
            JOptionPane.showMessageDialog(null, "ብ ትክክል ተመዝቢቡ");
            s1.setText("");
            s2.setText("");
            s3.setText("");
            s4.setText("");
            s5.setSelectedItem("");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(register_techareti1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(register_techareti1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(register_techareti1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(register_techareti1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new register_techareti1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField s1;
    private javax.swing.JTextField s2;
    private javax.swing.JTextField s3;
    private javax.swing.JTextField s4;
    private javax.swing.JComboBox s5;
    // End of variables declaration//GEN-END:variables
}