/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpack;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author kunal
 */
public class ServerUI extends javax.swing.JFrame {

    private ArrayList<String> activeusr = new ArrayList();
    private ArrayList<String> allusr = new ArrayList();
    private  Map<String, Socket> allusrmap = new ConcurrentHashMap<>();
    private ServerSocket ss;
    private  Set<String> activeusrset = new HashSet<>();
    private  int port = 8818;

    /**
     * Creates new form ServerUI
     */
    public ServerUI() {
        initComponents();
        try {
            ss = new ServerSocket(port);
            jTextArea1.append("Server started on port: " + port + "\n");
            jTextArea1.append("Waiting for the clients...\n");
            new acceptclient().start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class acceptclient extends Thread {

        @Override
        public void run() {
            while (true) {
                try {
                    Socket clientSocket = ss.accept();
                    String uname = new DataInputStream(clientSocket.getInputStream()).readUTF();
                    DataOutputStream cOutStream = new DataOutputStream(clientSocket.getOutputStream());
                    if (activeusrset != null && activeusrset.contains(uname)) {
                        cOutStream.writeUTF("Username already taken");
                    } else {
                        allusrmap.put(uname, clientSocket);
                        activeusrset.add(uname);
                        cOutStream.writeUTF("");
                        activeusr.add(uname);
                        if (!allusr.contains(uname)) {
                            allusr.add(uname);
                        }

                        jTextArea2.setText("");
                        jTextArea3.setText("");
                        for (String a : activeusr) {
                            jTextArea2.append(a + " \n");
                        }
                        for (String b : allusr) {
                            jTextArea3.append(b + " \n");
                        }
                        jTextArea1.append(uname + " Connected...\n");
                        new readmsg(clientSocket, uname).start();
                        new makelist().start();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } 
            }
        }
    }

    class readmsg extends Thread {

        Socket s;
        String Id;

        private readmsg(Socket s, String uname) {
            this.s = s;
            this.Id = uname;
        }

        @Override
        public void run() {
            while (jTextArea3 != null && !allusrmap.isEmpty()) {
                try {
                    String message = new DataInputStream(s.getInputStream()).readUTF();    
                    String[] msgList = message.split(":");
                    if (msgList[0].equalsIgnoreCase("multicast")) {
                        String[] receivers = msgList[1].split(",");
                        for (String usr : receivers) {
                            try {
                                if (activeusrset.contains(usr)) {
                                    new DataOutputStream(((Socket) allusrmap.get(usr)).getOutputStream()).writeUTF(Id + " to you : " + msgList[2]);

                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    } else if (msgList[0].equalsIgnoreCase("broadcast")) {
                        Iterator<String> itr1 = allusrmap.keySet().iterator();
                        while (itr1.hasNext()) {
                            String usrnames = (String) itr1.next();
                            if (!usrnames.equalsIgnoreCase(Id)) {
                                try {
                                    if (activeusrset.contains(usrnames)) {
                                        new DataOutputStream(((Socket) allusrmap.get(usrnames)).getOutputStream()).writeUTF(Id + " to everyone :" + msgList[1]);

                                    } else {

                                        new DataOutputStream(s.getOutputStream()).writeUTF("Error ! " + usrnames + " is disconnected.\n");

                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    } else if (msgList[0].equalsIgnoreCase("exit")) {
                        activeusrset.remove(Id);
                        jTextArea1.append(Id + " disconnected\n");

                        new makelist().start();

                        Iterator<String> itr = activeusrset.iterator();
                        while (itr.hasNext()) {
                            String usernames2 = (String) itr.next();
                            if (!usernames2.equalsIgnoreCase(Id)) {
                                try {
                                    new DataOutputStream(((Socket) allusrmap.get(usernames2)).getOutputStream()).writeUTF(Id + " disconnected...");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                
                            }
                        }
                        activeusr.remove(Id);

                        jTextArea2.setText("");
                        for (String a : activeusr) {
                            jTextArea2.append(a + " \n");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class makelist extends Thread {

        @Override
        public void run() {
            try {
                String ids = "";
                Iterator itr = activeusrset.iterator();
                while (itr.hasNext()) {
                    String key = (String) itr.next();
                    ids += key + ",";
                }
                if (ids.length() != 0) {
                    ids = ids.substring(0, ids.length() - 1);
                }
                itr = activeusrset.iterator();
                while (itr.hasNext()) {
                    String key = (String) itr.next();
                    try {
                        new DataOutputStream(((Socket) allusrmap.get(key)).getOutputStream()).writeUTF(":;.,/=" + ids);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jTextArea2.setEditable(false);
        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        jTextArea3.setEditable(false);
        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        jScrollPane3.setViewportView(jTextArea3);

        jLabel1.setText("Active Users");

        jLabel2.setText("All users");

        jLabel3.setText("Logs");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 9, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addGap(7, 7, 7)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(ServerUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ServerUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ServerUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ServerUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ServerUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    // End of variables declaration//GEN-END:variables
}
