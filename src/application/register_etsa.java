package application;

import java.awt.Color;
import java.awt.Font;
import static java.lang.Thread.sleep;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.JTableHeader;
import net.proteanit.sql.DbUtils;
import java.util.concurrent.TimeUnit;
import javax.swing.JTable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ToccoTedd
 */
public class register_etsa extends javax.swing.JFrame {

    /**
     * Creates new form register_cond
     */
 Connection conn=null;
 ResultSet rs=null;
 PreparedStatement pst=null;
 
 static int selected;
static List<Integer> customers=new ArrayList<Integer>();
static List<Integer> customers_one=new ArrayList<Integer>();
static List<Integer> customers_two=new ArrayList<Integer>();
static List<Integer> customers_three=new ArrayList<Integer>();
static List<Integer> studio=new ArrayList<Integer>();
static List<Integer> one=new ArrayList<Integer>();
static List<Integer> two=new ArrayList<Integer>();
static List<Integer> three=new ArrayList<Integer>();
 
 
 
    public register_etsa() {
        initComponents();
        this.getContentPane().setBackground(Color.white);
        conn=javaconnect.ConnecrDb();
        fetch_data();
        
         //for JOptionpane Ge'ez
       javax.swing.UIManager.put("OptionPane.font", new FontUIResource(new Font("Nyala", Font.PLAIN, 20)));
       javax.swing.UIManager.put("OptionPane.messageFont", new Font("Nyala", Font.PLAIN, 20));
       javax.swing.UIManager.put("OptionPane.buttonFont", new Font("Nyala", Font.PLAIN, 20));
      // -> end
    }
static String selected_house;
static String selected_geza_table;
static int total_house;
static int total_people;
 String c7;
 String c8;
 String c9;
 String c10;
void fetch_data(){
        String sql="select sum(n_studio) as studio from cond";
        try{
           pst=pst=conn.prepareStatement(sql);
           rs=pst.executeQuery(sql);
            if(rs.next()){
                String add1=rs.getString("studio");
                c7=add1; 
                System.out.print(c7);
            }
        }catch(Exception e){
        JOptionPane.showMessageDialog(null, e);
    }
        String sql1="select sum(n_one) as one from cond";
        try{
           pst=pst=conn.prepareStatement(sql1);
           rs=pst.executeQuery(sql1);
            if(rs.next()){
                String add1=rs.getString("one");
                c8=add1;               
            }
        }catch(Exception e){
        JOptionPane.showMessageDialog(null, e);
    }
            String sql2="select sum(n_two) as two from cond";
        try{
           pst=pst=conn.prepareStatement(sql2);
           rs=pst.executeQuery(sql2);
            if(rs.next()){
                String add1=rs.getString("two");
                c9=add1;               
            }
        }catch(Exception e){
        JOptionPane.showMessageDialog(null, e);
    }
        String sql3="select sum(n_three) as three from cond";
        try{
           pst=pst=conn.prepareStatement(sql3);
           rs=pst.executeQuery(sql3);
            if(rs.next()){
                String add1=rs.getString("three");
                c10=add1;               
            }
        }catch(Exception e){
        JOptionPane.showMessageDialog(null, e);
    }
           
    }
public static void generate_random_customers(int num1){
        Random stud=new Random();
        boolean generate_studio=true;
        int counting=0;
        while(generate_studio)
                {

                    int testnum= stud.nextInt(num1)+1;
                    if (!customers.contains(testnum))
                    {
                        customers.add(testnum);
                        counting++;
                        System.out.println(" c- :"+ counting+":"+testnum);
                    }
                    else{
                        
                    }
                    if(counting>=num1)
                    {
                        generate_studio=false;
                    }
                }
       } 
    public static void generate_random_customers_one(int num1){
        Random stud=new Random();
        boolean generate_studio=true;
        int counting=0;
        while(generate_studio)
                {

                    int testnum= stud.nextInt(num1)+1;
                    if (!customers_one.contains(testnum))
                    {
                        customers_one.add(testnum);
                        counting++;
                        System.out.println(" c- :"+ counting+":"+testnum);
                    }
                    else{
                        
                    }
                    if(counting>=num1)
                    {
                        generate_studio=false;
                    }
                }
       } 
     public static void generate_random_customers_two(int num1){
        Random stud=new Random();
        boolean generate_studio=true;
        int counting=0;
        while(generate_studio)
                {

                    int testnum= stud.nextInt(num1)+1;
                    if (!customers_two.contains(testnum))
                    {
                        customers_two.add(testnum);
                        counting++;
                        System.out.println(" c- :"+ counting+":"+testnum);
                    }
                    else{
                        
                    }
                    if(counting>=num1)
                    {
                        generate_studio=false;
                    }
                }
       } 
 public static void generate_random_customers_three(int num1){
        Random stud=new Random();
        boolean generate_studio=true;
        int counting=0;
        while(generate_studio)
                {

                    int testnum= stud.nextInt(num1)+1;
                    if (!customers_three.contains(testnum))
                    {
                        customers_three.add(testnum);
                        counting++;
                        System.out.println(" c- :"+ counting+":"+testnum);
                    }
                    else{
                        
                    }
                    if(counting>=num1)
                    {
                        generate_studio=false;
                    }
                }
       } 


 public static   void generate_random_studio(int num1){
        Random stud=new Random();
        boolean generate_studio=true;
        int counting=0;
        while(generate_studio)
                {

                    int testnum= stud.nextInt(num1)+1;
                    if (!studio.contains(testnum))
                    {
                        studio.add(testnum);
                        counting++;
                        System.out.println("g- :"+ counting+":"+testnum);
                    }
                    else{
                        
                    }
                    if(counting>=num1)
                    {
                        generate_studio=false;
                    }
                }
    }
  public static  void generate_random_one(int num1){
        Random stud=new Random();
        boolean generate_studio=true;
        int counting=0;
        while(generate_studio)
                {

                    int testnum= stud.nextInt(num1)+1;
                    if (!one.contains(testnum))
                    {
                        one.add(testnum);
                        counting++;
                        System.out.println("g-:"+ counting+":"+testnum);
                    }
                    else{
                        
                    }
                    if(counting>=num1)
                    {
                        generate_studio=false;
                    }
                }
    }
  public static  void generate_random_two(int num1){
        Random stud=new Random();
        boolean generate_studio=true;
        int counting=0;
        while(generate_studio)
                {

                    int testnum= stud.nextInt(num1)+1;
                    if (!two.contains(testnum))
                    {
                        two.add(testnum);
                        counting++;
                        System.out.println("g-:"+ counting+":"+testnum);
                    }
                    else{
                        
                    }
                    if(counting>=num1)
                    {
                        generate_studio=false;
                    }
                }
    }
  public static  void generate_random_three(int num1){ 
       Random stud=new Random();
        boolean generate_studio=true;
        int counting=0;
        while(generate_studio)
                {

                    int testnum= stud.nextInt(num1)+1;
                    if (!three.contains(testnum))
                    {
                        three.add(testnum);
                        counting++;
                        System.out.println("g-:"+ counting+":"+testnum);
                    }
                    else{
                        
                    }
                    if(counting>=num1)
                    {
                        generate_studio=false;
                    }
                }

     
  }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        geza_mret = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        aynet = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        bezhi_seb = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        bezhi_geza = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        sm = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        block = new javax.swing.JTextField();
        teref = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setIcon(new javax.swing.ImageIcon("src/images/condominuemmk.jpg")); // NOI18N

       // jLabel1.setIcon(new javax.swing.ImageIcon("F:\\T#(Tedd-busi)\\HouseLottery_Module_1\\src\\images\\footerFL_notAccepted.jpg")); // NOI18N

        jButton2.setBackground(new java.awt.Color(243, 248, 243));
        jButton2.setFont(new java.awt.Font("Nyala", 0, 24)); // NOI18N
        jButton2.setIcon(new ImageIcon("src/images/app_back_button_sml.jpg")); // NOI18N
        jButton2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 255, 0)));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Nyala", 0, 18)); // NOI18N
        jLabel3.setText(" መደቀሲ ዓይነት ይምረፁ");

        geza_mret.setBackground(new java.awt.Color(248, 248, 248));
        geza_mret.setFont(new java.awt.Font("Nyala", 0, 18)); // NOI18N
        geza_mret.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ስትድዮ", "በዓል 1-መደቀሲ", "በዓል 2-መደቀሲ", "በዓል 3-መደቀሲ" }));
        geza_mret.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                geza_mretActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(243, 248, 243));
        jButton1.setFont(new java.awt.Font("Nyala", 0, 18)); // NOI18N
        jButton1.setText("ምረፅ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Nyala", 0, 18)); // NOI18N
        jLabel4.setText("ናይዚ ዕፃ ዝርዝር ሓበሬታ");

        jLabel5.setFont(new java.awt.Font("Nyala", 0, 18)); // NOI18N
        jLabel5.setText("ዓይነት መደቀሲ");

        aynet.setFont(new java.awt.Font("Nyala", 0, 18)); // NOI18N
        aynet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aynetActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Nyala", 0, 18)); // NOI18N
        jLabel6.setText("በዝሒ ሰብ");

        bezhi_seb.setFont(new java.awt.Font("Nyala", 0, 18)); // NOI18N
        bezhi_seb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bezhi_sebActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Nyala", 0, 18)); // NOI18N
        jLabel7.setText("በዝሒ ገዛ");

        bezhi_geza.setFont(new java.awt.Font("Nyala", 0, 18)); // NOI18N

        jButton3.setBackground(new java.awt.Color(243, 248, 243));
        jButton3.setFont(new java.awt.Font("Nyala", 0, 36)); // NOI18N
        jButton3.setText("ዕፃ ምውፃኣ ጀምር");
        jButton3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 255, 102)));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Nyala", 0, 48)); // NOI18N
        jLabel8.setText("   ሽም");

        sm.setFont(new java.awt.Font("Nyala", 0, 36)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Nyala", 0, 48)); // NOI18N
        jLabel9.setText("ገዛ ቁፅሪ");

        block.setFont(new java.awt.Font("Nyala", 0, 36)); // NOI18N
        block.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blockActionPerformed(evt);
            }
        });

        teref.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        teref.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sm, javax.swing.GroupLayout.PREFERRED_SIZE, 586, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addGap(8, 8, 8)
                .addComponent(block, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1387, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addGap(37, 37, 37)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(geza_mret, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(38, 38, 38)
                                        .addComponent(teref, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(44, 44, 44)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(aynet, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(bezhi_seb, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(bezhi_geza, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(bezhi_geza)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(geza_mret, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(aynet)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(bezhi_seb, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(48, 48, 48)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                            .addComponent(teref, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(65, 65, 65)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sm, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(block, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(144, 144, 144)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        cond_home n=new cond_home();
        n.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void aynetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aynetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_aynetActionPerformed
  static int n_click=0;
  static int n_click_studio=0;
  static int n_click_one=0;
  static int n_click_two=0;
  static int n_click_three=0;
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        n_click++;
            String house=geza_mret.getSelectedItem().toString();
            selected_house=house;
            
                 String add1=c7;
                 String add2=c8;
                 String add3=c9;
                 String add4=c10;
                    if(house.equals("ስትድዮ")){
                        if(n_click_studio>1){
                        
                        JOptionPane.showMessageDialog(null, "ሓደ ግዜ ጥራሕ ይምረፁ-ካልኣይ ኣይፍቀድን");
                        }else{
                        aynet.setText(house);
                        bezhi_geza.setText(add1);   
                        int num1=Integer.parseInt(add1);
                        generate_random_studio(num1);
                        total_house=num1;
                        n_click_studio++; 
                        }
                  }else if(house.equals("በዓል 1-መደቀሲ")){
                      if(n_click_one>1){
                        
                        JOptionPane.showMessageDialog(null, "ሓደ ግዜ ጥራሕ ይምረፁ-ካልኣይ ኣይፍቀድን");
                        }else{
                        aynet.setText(house);
                        bezhi_geza.setText(add2);
                        int num1=Integer.parseInt(add2); 
                        generate_random_one(num1);
                        total_house=num1;
                        n_click_one++; 
                      }
                  }else if(house.equals("በዓል 2-መደቀሲ")){
                      if(n_click_two>1){
                        
                        JOptionPane.showMessageDialog(null, "ሓደ ግዜ ጥራሕ ይምረፁ-ካልኣይ ኣይፍቀድን");
                        }else{
                        aynet.setText(house);
                        bezhi_geza.setText(add3);
                        int num1=Integer.parseInt(add3);  
                        generate_random_two(num1);
                        total_house=num1;
                        n_click_two++; 
                      }
                    }
                    else if(house.equals("በዓል 3-መደቀሲ")){
                        if(n_click_three>1){
                        
                        JOptionPane.showMessageDialog(null, "ሓደ ግዜ ጥራሕ ይምረፁ-ካልኣይ ኣይፍቀድን");
                        }else{
                        bezhi_geza.setText(add4);
                        int num1=Integer.parseInt(add4); 
                        generate_random_three(num1);
                        total_house=num1;
                        n_click_three++; 
                    }
                    }
                 ///////////////////////////////get house
                 
        
            if(house.equals("ስትድዮ")){
            String s="select COUNT(*) as num from stu_alga";
            try{       if(n_click_studio>1){                        
                        //JOptionPane.showMessageDialog(null, "ክልተ ግዜ ዕፃ ምውፃእ ኣይከኣልን");
                         }else{
                           pst=conn.prepareStatement(s);
                        rs=pst.executeQuery(s);
                        if(rs.next()){
                           String number=rs.getString("num");
                           bezhi_seb.setText(number);
                           int num1=Integer.parseInt(number);
                           total_people=num1;
                           generate_random_customers(num1);
                           n_click_studio++;
                        } 
                        
                        }
                        }catch(Exception e){
        JOptionPane.showMessageDialog(null, e);
                        }
    }  else if(house.equals("በዓል 1-መደቀሲ")){
            String s="select COUNT(*) as num from one_alga";
            try{     if(n_click_one>1){                        
                        //JOptionPane.showMessageDialog(null, "ክልተ ግዜ ዕፃ ምውፃእ ኣይከኣልን");
                         }else{
                        pst=conn.prepareStatement(s);
                        rs=pst.executeQuery(s);
                        if(rs.next()){
                           String number=rs.getString("num");
                           bezhi_seb.setText(number);
                             int num1=Integer.parseInt(number);
                             generate_random_customers_one(num1);
                             total_people=num1;
                             n_click_one++; 
                        }
            }
                        }catch(Exception e){
                       JOptionPane.showMessageDialog(null, e);
            
                       }
            
            }  
            else if(house.equals("በዓል 2-መደቀሲ")){
            String s="select COUNT(*) as num from two_alga";
            try{
                       if(n_click_two>1){                        
                        //JOptionPane.showMessageDialog(null, "ክልተ ግዜ ዕፃ ምውፃእ ኣይከኣልን");
                         }else{
                        pst=conn.prepareStatement(s);
                        rs=pst.executeQuery(s);
                        if(rs.next()){
                           String number=rs.getString("num");
                           bezhi_seb.setText(number);
                             int num1=Integer.parseInt(number);
                             generate_random_customers_two(num1);
                             total_people=num1;
                             n_click_two++; 
                        }
                       }
                        }catch(Exception e){
                       JOptionPane.showMessageDialog(null, e);
            
                       }
            } 
            else if(house.equals("በዓል 3-መደቀሲ")){
            String s="select COUNT(*) as num from three_alga";
            try{
                       if(n_click_three>1){                        
                        //JOptionPane.showMessageDialog(null, "ክልተ ግዜ ዕፃ ምውፃእ ኣይከኣልን");
                         }else{
                        pst=conn.prepareStatement(s);
                        rs=pst.executeQuery(s);
                        if(rs.next()){
                           String number=rs.getString("num");
                           bezhi_seb.setText(number);
                           int num1=Integer.parseInt(number);
                           generate_random_customers_three(num1);
                           total_people=num1;
                           n_click_three++; 
                        }
                       }
                        }catch(Exception e){
                       JOptionPane.showMessageDialog(null, e);
            
                       }
            }  
           
    }//GEN-LAST:event_jButton1ActionPerformed
static int n_c_s=0;
  static int n_c_o=0;
  static int n_c_t=0;
  static int n_c_th=0;
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        if(n_click!=0){
        int count1=0;
        String seb=bezhi_seb.getText();
        String geza=bezhi_geza.getText();
        String house=selected_house;
        if(house.equals("ስትድዮ")){
            if(n_c_s>0){
                JOptionPane.showMessageDialog(null, "ዕፃ ካልኣይ ምድጋም ኣይከኣልን");
            }else{
            count1++;
            int num0=Integer.parseInt(geza);  
            int num2=Integer.parseInt(seb);
            int num1;
            if(num0<num2){
                num1=num0;
            }else{
                num1=num2;
            }
            new Thread(){
                                   @Override
                                       public void run(){
                                          for(int i=0;i<num1;i++){
                                              int sakt=num1-i-1;
                                            
                int gezzza=studio.get(i);
                int sebbb=customers.get(i);
                System.out.println("the gezza number "+ gezzza+ " sebbb number "+sebbb);
                String Sql1="select * from stu_alga where cus_id='"+sebbb+"'";
                try{
                       pst=conn.prepareStatement(Sql1);
                        rs=pst.executeQuery(Sql1);
                        if(rs.next()){
                           String fname=rs.getString("Fname");
                           String mname=rs.getString("Mname");
                           String lname=rs.getString("Lname");
                           String full=fname +"     "+mname+"    "+lname;                       
                           String b=Integer.toString(gezzza);                         
                           System.out.println("the gezza number ");
                               sm.setText(full);
                               block.setText("   "+b);
                               String sakt1=Integer.toString(sakt);
                               teref.setText(sakt1);
                               String sql2="INSERT INTO assigned VALUES(NULL,'"+fname+"','"+mname+"','"+lname+"','"+house+"','"+b+"')";
                               try{
                                   pst=conn.prepareStatement(sql2);
                                   pst.execute();
                               }catch(Exception e){
                       JOptionPane.showMessageDialog(null, e);
            
                       }
                                 }
                }catch(Exception e){
                       JOptionPane.showMessageDialog(null, e);
            
                       }
                                       int time=5000;
                                               try { 
                                                     sleep(time);
                                                } catch (InterruptedException ex) {
                                                   Logger.getLogger(register_etsa.class.getName()).log(Level.SEVERE, null, ex);
                                                }
            }
                                          
                                             }
                                       }.start();
            n_c_s++;
            }
            
        }else if(house.equals("በዓል 1-መደቀሲ")){
            if(n_c_o>0){
                JOptionPane.showMessageDialog(null, "ዕፃ ካልኣይ ምድጋም ኣይከኣልን");
            }else{
            int num0=Integer.parseInt(geza);  
            int num2=Integer.parseInt(seb);
            int num1;
            if(num0<num2){
                num1=num0;
            }else{
                num1=num2;
            }
               new Thread(){
                                   @Override
                                       public void run(){
                                          for(int i=0;i<num1;i++){
                                              int sakt=num1-i-1;
                int gezzza=one.get(i);
                int sebbb=customers_one.get(i);
                System.out.println("the gezza number "+ gezzza+ " sebbb number "+sebbb);
                String Sql1="select * from one_alga where cus_id='"+sebbb+"'";
                try{
                       pst=conn.prepareStatement(Sql1);
                        rs=pst.executeQuery(Sql1);
                        if(rs.next()){
                           String fname=rs.getString("Fname");
                           String mname=rs.getString("Mname");
                           String lname=rs.getString("Lname");
                           String full=fname +"     "+mname+"    "+lname;                       
                           String b=Integer.toString(gezzza);                         
                           System.out.println("the gezza number ");
                               sm.setText(full);
                               block.setText("   "+b);
                               String sakt1=Integer.toString(sakt);
                               teref.setText(sakt1);
                               String sql2="INSERT INTO assigned VALUES(NULL,'"+fname+"','"+mname+"','"+lname+"','"+house+"','"+b+"')";
                               try{
                                   pst=conn.prepareStatement(sql2);
                                   pst.execute();
                                   //update_list_table();
                               }catch(Exception e){
                       JOptionPane.showMessageDialog(null, e);
            
                       }
                                 }
                }catch(Exception e){
                       JOptionPane.showMessageDialog(null, e);
            
                       }
                                       int time=5000;
                                               try { 
                                                     sleep(time);
                                                } catch (InterruptedException ex) {
                                                   Logger.getLogger(register_etsa.class.getName()).log(Level.SEVERE, null, ex);
                                                }
            }
                                          
                                             }
                                       }.start();
               n_c_o++;
            }
        }else if(house.equals("በዓል 2-መደቀሲ")){
            if(n_c_t>0){
                JOptionPane.showMessageDialog(null, "ዕፃ ካልኣይ ምድጋም ኣይከኣልን");
            }else{
            int num0=Integer.parseInt(geza);  
            int num2=Integer.parseInt(seb);
            int num1;
            if(num0<num2){
                num1=num0;
            }else{
                num1=num2;
            }
               new Thread(){
                                   @Override
                                       public void run(){
                                          for(int i=0;i<num1;i++){
                                              int sakt=num1-i-1;
                int gezzza=two.get(i);
                int sebbb=customers_two.get(i);
                System.out.println("the gezza number "+ gezzza+ " sebbb number "+sebbb);
                String Sql1="select * from two_alga where cus_id='"+sebbb+"'";
                try{
                       pst=conn.prepareStatement(Sql1);
                        rs=pst.executeQuery(Sql1);
                        if(rs.next()){
                           String fname=rs.getString("Fname");
                           String mname=rs.getString("Mname");
                           String lname=rs.getString("Lname");
                           String full=fname +"     "+mname+"    "+lname;                       
                           String b=Integer.toString(gezzza);                         
                           System.out.println("the gezza number ");
                               sm.setText(full);
                               block.setText("  "+b);
                                String sakt1=Integer.toString(sakt);
                               teref.setText(sakt1);
                               String sql2="INSERT INTO assigned VALUES(NULL,'"+fname+"','"+mname+"','"+lname+"','"+house+"','"+b+"')";
                               try{
                                   pst=conn.prepareStatement(sql2);
                                   pst.execute();
                                   //update_list_table();
                               }catch(Exception e){
                       JOptionPane.showMessageDialog(null, e);
            
                       }
                                 }
                }catch(Exception e){
                       JOptionPane.showMessageDialog(null, e);
            
                       }
                                       int time=5000;
                                               try { 
                                                     sleep(time);
                                                } catch (InterruptedException ex) {
                                                   Logger.getLogger(register_etsa.class.getName()).log(Level.SEVERE, null, ex);
                                                }
            }
                                          
                                             }
                                       }.start();
               n_c_t++;
            }
        }else if(house.equals("በዓል 3-መደቀሲ")){
            if(n_c_th>0){
                JOptionPane.showMessageDialog(null, "ዕፃ ካልኣይ ምድጋም ኣይከኣልን");
            }else{
            int num0=Integer.parseInt(geza);  
            int num2=Integer.parseInt(seb);
            int num1;
            if(num0<num2){
                num1=num0;
            }else{
                num1=num2;
            }
               new Thread(){
                                   @Override
                                       public void run(){
                                          for(int i=0;i<num1;i++){
                                              int sakt=num1-i-1;
                int gezzza=three.get(i);
                int sebbb=customers_three.get(i);
                System.out.println("the gezza number "+ gezzza+ " sebbb number "+sebbb);
                String Sql1="select * from three_alga where cus_id='"+sebbb+"'";
                try{
                       pst=conn.prepareStatement(Sql1);
                        rs=pst.executeQuery(Sql1);
                        if(rs.next()){
                           String fname=rs.getString("Fname");
                           String mname=rs.getString("Mname");
                           String lname=rs.getString("Lname");
                           String full=fname +"     "+mname+"    "+lname;                       
                           String b=Integer.toString(gezzza);                         
                           System.out.println("the gezza number ");
                               sm.setText(full);
                               block.setText("   "+b);
                                 String sakt1=Integer.toString(sakt);
                               teref.setText(sakt1);
                               String sql2="INSERT INTO assigned VALUES(NULL,'"+fname+"','"+mname+"','"+lname+"','"+house+"','"+b+"')";
                               try{
                                   pst=conn.prepareStatement(sql2);
                                   pst.execute();
                                   //update_list_table();
                               }catch(Exception e){
                       JOptionPane.showMessageDialog(null, e);
            
                       }
                                 }
                }catch(Exception e){
                       JOptionPane.showMessageDialog(null, e);
            
                       }
                                       int time=5000;
                                               try { 
                                                     sleep(time);
                                                } catch (InterruptedException ex) {
                                                   Logger.getLogger(register_etsa.class.getName()).log(Level.SEVERE, null, ex);
                                                }
            }
                                          
                                             }
                                       }.start();
               n_c_th++;
            }
        }
        }else{
            JOptionPane.showMessageDialog(null, "ገዛ ዓይነት ይምረፁ");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void geza_mretActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_geza_mretActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_geza_mretActionPerformed

    private void bezhi_sebActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bezhi_sebActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bezhi_sebActionPerformed

    private void blockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_blockActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_blockActionPerformed

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
            java.util.logging.Logger.getLogger(register_cond.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(register_cond.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(register_cond.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(register_cond.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new register_cond().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField aynet;
    private javax.swing.JTextField bezhi_geza;
    private javax.swing.JTextField bezhi_seb;
    private javax.swing.JTextField block;
    private javax.swing.JComboBox geza_mret;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField sm;
    private javax.swing.JLabel teref;
    // End of variables declaration//GEN-END:variables
}
