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
import java.sql.SQLException;
import java.util.*;

public class ClientUI extends javax.swing.JFrame {
private client client;
private ServerI server;
private boolean isConnected;

    public ClientUI(String svr,int port ) throws UnknownHostException, SQLException {
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
                List<String> sint = server.get_sintomas();
                List<String> sign = server.get_signos();
                for(String a : sint){
                    jComboBoxSintomas.addItem(a);
                    jcb_NE_Sin.addItem(a);
                }
                for(String a : sign){
                    jComboBoxSignos.addItem(a);
                    jcb_NE_Sig.addItem(a);
                }
                
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
        jTextArea_DE_Sin = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea_DE_Sig = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jComboBoxSintomas = new javax.swing.JComboBox<>();
        jComboBoxSignos = new javax.swing.JComboBox<>();
        jButtonSintomas = new javax.swing.JButton();
        jButtonSignos = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButtonANSint = new javax.swing.JButton();
        jButtonANSig = new javax.swing.JButton();
        jTextFieldSintoma = new javax.swing.JTextField();
        jTextFieldSigno = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jcb_NE_Sin = new javax.swing.JComboBox<>();
        jcb_NE_Sig = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jtf_NuevaEnfermedad = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jButton_NE_Sin = new javax.swing.JButton();
        jButton_NE_Sig = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea_NE_Sin = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea_NE_Sig = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

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

        jTextArea_DE_Sin.setColumns(20);
        jTextArea_DE_Sin.setRows(2);
        jTextArea_DE_Sin.setTabSize(4);
        jTextArea_DE_Sin.setWrapStyleWord(true);
        jTextArea_DE_Sin.setFocusable(false);
        jTextArea_DE_Sin.setName("InSintomas"); // NOI18N
        jScrollPane4.setViewportView(jTextArea_DE_Sin);

        jTextArea_DE_Sig.setColumns(20);
        jTextArea_DE_Sig.setRows(5);
        jTextArea_DE_Sig.setFocusable(false);
        jTextArea_DE_Sig.setName("inSignos"); // NOI18N
        jScrollPane5.setViewportView(jTextArea_DE_Sig);

        jLabel3.setText("Sintomas");

        jLabel4.setText("Signos");

        jButtonSintomas.setText("Agregar");
        jButtonSintomas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSintomasActionPerformed(evt);
            }
        });

        jButtonSignos.setText("Agregar");
        jButtonSignos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSignosActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Diagnostico de Enfermedades");

        jButtonANSint.setText("Agregar Nuevo Sintoma");
        jButtonANSint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonANSintActionPerformed(evt);
            }
        });

        jButtonANSig.setText("Agregar Nuevo Signo");
        jButtonANSig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonANSigActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Agregar Nuevas Enfermedades");

        jButton1.setText("Agregar Nueva Enfermedad");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel5.setText("Sintomas");

        jLabel6.setText("Signos");

        jLabel7.setText("Nombre");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Agregar Nuevos Sintomas y Signos");

        jButton_NE_Sin.setText("Agregar");
        jButton_NE_Sin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_NE_SinActionPerformed(evt);
            }
        });

        jButton_NE_Sig.setText("Agregar");
        jButton_NE_Sig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_NE_SigActionPerformed(evt);
            }
        });

        jTextArea_NE_Sin.setColumns(20);
        jTextArea_NE_Sin.setRows(5);
        jTextArea_NE_Sin.setFocusable(false);
        jScrollPane1.setViewportView(jTextArea_NE_Sin);

        jTextArea_NE_Sig.setColumns(20);
        jTextArea_NE_Sig.setRows(5);
        jTextArea_NE_Sig.setFocusable(false);
        jScrollPane2.setViewportView(jTextArea_NE_Sig);

        jLabel9.setText("Sintomas");

        jLabel10.setText("Signos");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jComboBoxSintomas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel3)
                                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jButtonSintomas))
                                .addGap(44, 44, 44)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButtonSignos)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel4)
                                        .addComponent(jScrollPane5)
                                        .addComponent(jComboBoxSignos, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jBtnSend, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(141, 141, 141)))
                        .addGap(47, 47, 47))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(8, 8, 8)
                                            .addComponent(jLabel6)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jcb_NE_Sig, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel5)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jcb_NE_Sin, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButton1)
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jtf_NuevaEnfermedad, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGap(33, 33, 33)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton_NE_Sin)
                                    .addComponent(jButton_NE_Sig)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButtonANSint, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButtonANSig, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jTextFieldSintoma, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                                        .addComponent(jTextFieldSigno, javax.swing.GroupLayout.Alignment.LEADING)))))
                        .addContainerGap(12, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(141, 141, 141))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxSintomas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxSignos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSintomas)
                    .addComponent(jButtonSignos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBtnSend, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonANSint)
                    .addComponent(jTextFieldSintoma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonANSig)
                    .addComponent(jTextFieldSigno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jLabel7)
                    .addComponent(jtf_NuevaEnfermedad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jcb_NE_Sin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_NE_Sin))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jcb_NE_Sig, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_NE_Sig))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSendActionPerformed
    try {
        List<String> sintomas;
        List<String> signos;

        sintomas = Arrays.asList(jTextArea_DE_Sin.getText().split(":"));
        signos = Arrays.asList(jTextArea_DE_Sig.getText().split(":"));
        
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

    private void jButtonANSintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonANSintActionPerformed
        // TODO add your handling code here:
        //Evento para ejecutar el metodo de Agregar Sintomas
        //Obtener el texto y mandarlo como parametro al metodo
        String sintoma = jTextFieldSintoma.getText();
        try {
            server.add_sintoma(sintoma);
        } catch (RemoteException ex) {
            Logger.getLogger(ClientUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        jTextFieldSintoma.setText("");
    }//GEN-LAST:event_jButtonANSintActionPerformed

    private void jButtonANSigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonANSigActionPerformed
        // TODO add your handling code here:
        //Evento para ejecutar el metodo de Agregar Signos
        //Obtener el texto y mandarlo como parametro al metodo
        String signo = jTextFieldSigno.getText();
        try {
            server.add_signo(signo);
        } catch (RemoteException ex) {
            Logger.getLogger(ClientUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        jTextFieldSigno.setText("");
    }//GEN-LAST:event_jButtonANSigActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            String e = jtf_NuevaEnfermedad.getText();
            String sin = jTextArea_NE_Sin.getText();
            String sig = jTextArea_NE_Sig.getText(); 
            if( e.length() > 0 )
                server.add_enfermedad(e, sin, sig);
        } catch (RemoteException ex) {
            Logger.getLogger(ClientUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        jTextArea_NE_Sin.setText("");
        jTextArea_NE_Sig.setText("");
        jtf_NuevaEnfermedad.setText("");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButtonSintomasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSintomasActionPerformed
        // TODO add your handling code here:
        String str = jTextArea_DE_Sin.getText();
        String sin = jComboBoxSintomas.getSelectedItem().toString();
        if(str.length() <= 0)
            jTextArea_DE_Sin.setText(sin);
        else
            jTextArea_DE_Sin.setText(str + ":" + sin);
    }//GEN-LAST:event_jButtonSintomasActionPerformed

    private void jButtonSignosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSignosActionPerformed
        // TODO add your handling code here:
        String str = jTextArea_DE_Sig.getText();
        String sin = jComboBoxSignos.getSelectedItem().toString();
        if(str.length() <= 0)
            jTextArea_DE_Sig.setText(sin);
        else
            jTextArea_DE_Sig.setText(str + ":" + sin);
    }//GEN-LAST:event_jButtonSignosActionPerformed

    private void jButton_NE_SinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_NE_SinActionPerformed
        // TODO add your handling code here:
        String str = jTextArea_NE_Sin.getText();
        String sin = jcb_NE_Sin.getSelectedItem().toString();
        if(str.length() <= 0)
            jTextArea_NE_Sin.setText(sin);
        else
            jTextArea_NE_Sin.setText(str + ":" + sin);
    }//GEN-LAST:event_jButton_NE_SinActionPerformed

    private void jButton_NE_SigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_NE_SigActionPerformed
        // TODO add your handling code here:
        String str = jTextArea_NE_Sig.getText();
        String sin = jcb_NE_Sig.getSelectedItem().toString();
        if(str.length() <= 0)
            jTextArea_NE_Sig.setText(sin);
        else
            jTextArea_NE_Sig.setText(str + ":" + sin);
    }//GEN-LAST:event_jButton_NE_SigActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnSend;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonANSig;
    private javax.swing.JButton jButtonANSint;
    private javax.swing.JButton jButtonSignos;
    private javax.swing.JButton jButtonSintomas;
    private javax.swing.JButton jButton_NE_Sig;
    private javax.swing.JButton jButton_NE_Sin;
    private javax.swing.JComboBox<String> jComboBoxSignos;
    private javax.swing.JComboBox<String> jComboBoxSintomas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextArea jTextArea_DE_Sig;
    private javax.swing.JTextArea jTextArea_DE_Sin;
    private javax.swing.JTextArea jTextArea_NE_Sig;
    private javax.swing.JTextArea jTextArea_NE_Sin;
    private javax.swing.JTextField jTextFieldSigno;
    private javax.swing.JTextField jTextFieldSintoma;
    private javax.swing.JComboBox<String> jcb_NE_Sig;
    private javax.swing.JComboBox<String> jcb_NE_Sin;
    private javax.swing.JTextField jtf_NuevaEnfermedad;
    // End of variables declaration//GEN-END:variables

}
