/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpack;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.StringTokenizer;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author kunal
 */
public class ClientUI extends javax.swing.JFrame {

    String id, clientIds = "";
    DefaultListModel<String> dm1;
    DataInputStream din;
    DataOutputStream dout;

    /**
     * Creates new form ClientUI
     */
    public ClientUI() {
        initComponents();
    }

    public ClientUI(String id, Socket s) {
        initComponents();
        this.id = id;

        try {

            this.setTitle(id);
            dm1 = new DefaultListModel<String>();
            jList1.setModel(dm1);
            din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());
            new msgread().start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    class msgread extends Thread {

        @Override
        public void run() {
            while (true) {
                try {
                    String m = din.readUTF();
                    
                    if (m.contains(":;.,/=")) {
                        m = m.substring(6);
                        dm1.clear();
                        StringTokenizer st = new StringTokenizer(m, ",");
                        while (st.hasMoreTokens()) {
                            String u = st.nextToken();
                            if (!id.equals(u)) {
                                dm1.addElement(u);

                            }
                        }
                    } else {

                        msg.append("" + m + "\n");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    break;
                }
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        msg = new javax.swing.JTextArea();
        send = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        sendpriv = new javax.swing.JRadioButton();
        sendall = new javax.swing.JRadioButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        msg.setEditable(false);
        msg.setColumns(20);
        msg.setRows(5);
        jScrollPane1.setViewportView(msg);

        send.setToolTipText("Enter your message here");
        send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendActionPerformed(evt);
            }
        });

        jButton1.setText("Send");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(jList1);

        buttonGroup1.add(sendpriv);
        sendpriv.setSelected(true);
        sendpriv.setText("Send privately");
        sendpriv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendprivActionPerformed(evt);
            }
        });

        buttonGroup1.add(sendall);
        sendall.setText("Broadcast");
        sendall.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendallActionPerformed(evt);
            }
        });

        jButton2.setText("Exit");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setText("Messages");

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(0, 255, 255));
        jTextField1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setText("CHAT APPLICATION");

        jLabel2.setText("Active Users");

        jLabel3.setText("Enter Message");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(send, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(sendpriv)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sendall))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(0, 245, Short.MAX_VALUE))
            .addComponent(jTextField1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sendpriv)
                    .addComponent(sendall))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(send, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addContainerGap(61, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sendActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String clientmsg = send.getText();
        if (clientmsg != null && !clientmsg.isEmpty()) {
            try {
                String sendtoserver = "";
                String cast = "broadcast";
                int flag = 0;

                if (sendpriv.isSelected()) {
                    cast = "multicast";
                    List<String> clientList = jList1.getSelectedValuesList();
                    if (clientList.isEmpty()) {
                        flag = 1;
                    }
                    for (String selectedUsr : clientList) {
                        if (clientIds.isEmpty()) {
                            clientIds += selectedUsr;
                        } else {
                            clientIds += "," + selectedUsr;
                        }
                    }
                    sendtoserver = cast + ":" + clientIds + ":" + clientmsg;
                } else {
                    sendtoserver = cast + ":" + clientmsg;
                }
                if (cast.equalsIgnoreCase("multicast")) {
                    if (flag == 1) {
                        JOptionPane.showMessageDialog(this, "Please select users to send the message to");
                    } else {
                        dout.writeUTF(sendtoserver);
                        send.setText("");
                        msg.append("< You said to " + clientIds + ">" + clientmsg + "\n");
                    }
                } else {
                    dout.writeUTF(sendtoserver);
                    send.setText("");
                    msg.append("< You said to everyone >" + clientmsg + "\n");
                }
                clientIds = "";
            } catch (Exception ex) {

            }
    }//GEN-LAST:event_jButton1ActionPerformed
        else{JOptionPane.showMessageDialog(this, "Please type a message first");}
    }
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try {
            dout.writeUTF("exit");
            this.dispose();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void sendallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendallActionPerformed
        // TODO add your handling code here:
        jList1.setEnabled(false);
    }//GEN-LAST:event_sendallActionPerformed

    private void sendprivActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendprivActionPerformed
        // TODO add your handling code here:
        jList1.setEnabled(true);
    }//GEN-LAST:event_sendprivActionPerformed

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
            java.util.logging.Logger.getLogger(ClientUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClientUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClientUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClientUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextArea msg;
    private javax.swing.JTextField send;
    private javax.swing.JRadioButton sendall;
    private javax.swing.JRadioButton sendpriv;
    // End of variables declaration//GEN-END:variables
}