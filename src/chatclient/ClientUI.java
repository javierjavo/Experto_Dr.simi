package chatclient;

import chatserver.Server;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import chatserver.ServerI;
import java.util.*;

public class ClientUI extends javax.swing.JFrame {
private client client;
private ServerI server;
private boolean isConnected;

    public ClientUI(String svr,int port ) throws UnknownHostException {
        initComponents();
       
        jBtnSend.setVisible(false);
        
        //corre el servidor desde homme y aprobecho para inicializar al cliente
        try{
            java.rmi.registry.LocateRegistry.createRegistry(port);
            // Enlaza un objeto Server al nombre ChatService en el registro.
            ServerI cs = new Server();
            Naming.rebind("Service", cs);
            System.out.println("El servidor del chat está listo.");
        } catch (MalformedURLException | RemoteException e){
            System.err.println("Server caca failed: " + e);
        }
        
        if (!isConnected) {
            try {
                client = new client();
                server = (ServerI) Naming.lookup("rmi://" + svr+":"+port+"/Service");
                client.setGUI(this,server);
                server.login(client);
                jBtnSend.setVisible(true);
                isConnected = true;
            } catch (MalformedURLException | NotBoundException | RemoteException e) {
                JOptionPane.showMessageDialog(this, "Error, no se pudo conectar.");
            }
        } else {
            try {
                server.logout(client);
            } catch (RemoteException ex) {
                System.err.println("Error al desconectar.");
            }
                jBtnSend.setVisible(false);
                isConnected = false;
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jBtnSend = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jBtnSend.setText("Buscar");
        jBtnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnSendActionPerformed(evt);
            }
        });

        jTextArea2.setColumns(20);
        jTextArea2.setRows(2);
        jTextArea2.setTabSize(4);
        jTextArea2.setText("llanto,mucha caca,penejes");
        jTextArea2.setWrapStyleWord(true);
        jTextArea2.setName("InSintomas"); // NOI18N
        jScrollPane4.setViewportView(jTextArea2);

        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        jTextArea3.setText("llanto,mucha caca,penejes");
        jTextArea3.setName("inSignos"); // NOI18N
        jScrollPane5.setViewportView(jTextArea3);

        jLabel3.setText("Sintomas");

        jLabel4.setText("Signos");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBtnSend, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnSend, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSendActionPerformed
    try {
        List<String> sintomas;
        List<String> signos;

        sintomas = Arrays.asList(jTextArea2.getText().split(","));
        signos = Arrays.asList(jTextArea3.getText().split(","));
        
        server.busqueda_principal(client, sintomas, signos);
    } catch (RemoteException ex) {
        Logger.getLogger(ClientUI.class.getName()).log(Level.SEVERE, null, ex);
    }
        
    }//GEN-LAST:event_jBtnSendActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        System.out.println("Adios");
        try {
            // TODO add your handling code here:
            if (isConnected)
            server.logout(client);
        } catch (RemoteException ex) {
            System.err.println("Error al desconectar");
       }
 
    }//GEN-LAST:event_formWindowClosing


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnSend;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    // End of variables declaration//GEN-END:variables

}
