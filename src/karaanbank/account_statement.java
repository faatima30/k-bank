/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package karaanbank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hp
 */
public class account_statement extends javax.swing.JInternalFrame {

    Connection con = null;
    PreparedStatement st = null;
    public void db_con() {
        String url = "jdbc:mysql://localhost:3306/kaaraanbank";
        String user = "root";
        String pass = "";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
//            JOptionPane.showMessageDialog(null,"connected successfully");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public account_statement() {
        initComponents();
        db_con();
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
        account_txt = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        show_tbl = new javax.swing.JTable();
        search = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 102, 51));
        setClosable(true);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Enter AccountNO:");

        account_txt.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        account_txt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                account_txtKeyReleased(evt);
            }
        });

        show_tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Transaction ID", "AccountNO", "Holder", "Credit", "Debit", "balance", "Transactedby", "describtion", "Date"
            }
        ));
        jScrollPane1.setViewportView(show_tbl);

        search.setBackground(new java.awt.Color(204, 204, 204));
        search.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        search.setForeground(new java.awt.Color(51, 51, 51));
        search.setText("Find Report");
        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addGap(44, 44, 44)
                .addComponent(account_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(search)
                .addGap(127, 127, 127))
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(account_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(search))
                .addGap(37, 37, 37)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(182, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void account_txtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_account_txtKeyReleased
        
                
    }//GEN-LAST:event_account_txtKeyReleased

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed

        DefaultTableModel tbl=(DefaultTableModel) show_tbl.getModel();
        try {
            String name="";
            st = con.prepareStatement("select holder from open_account where account='" + account_txt.getText() + "'");
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                name=rs.getString("holder");
            }
            st = con.prepareStatement("select * from transaction where account='" + account_txt.getText() + "'");
         ResultSet r = st.executeQuery();
         double credit=0;        
         double debit=0;     
         double balance=0;

         while (r.next()) {
         credit=r.getDouble("credit"); 
         debit=r.getDouble("debit");
         balance=credit-debit;
         tbl.addRow(new Object[]{
         r.getString("tid"), 
                     r.getString(1),
                     name,
                     r.getString(3),
                     r.getString(4),
                     balance,
                     r.getString(5),
                     r.getString(6),      
                     r.getString(7),
                     r.getString(8) 


         });
         }
        
        
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error);
        }
    }//GEN-LAST:event_searchActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField account_txt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton search;
    private javax.swing.JTable show_tbl;
    // End of variables declaration//GEN-END:variables
}