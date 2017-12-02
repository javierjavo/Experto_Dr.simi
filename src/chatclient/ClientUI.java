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
        jb_Borrar_Signos = new javax.swing.JButton();
        jb_Borrar_Sintomas = new javax.swing.JButton();
        jb_NE_Bsig = new javax.swing.JButton();
        jb_NE_BSin = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jta_Trat = new javax.swing.JTextArea();

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
        jTextArea_DE_Sin.setLineWrap(true);
        jTextArea_DE_Sin.setRows(2);
        jTextArea_DE_Sin.setTabSize(4);
        jTextArea_DE_Sin.setWrapStyleWord(true);
        jTextArea_DE_Sin.setFocusable(false);
        jTextArea_DE_Sin.setName("InSintomas"); // NOI18N
        jScrollPane4.setViewportView(jTextArea_DE_Sin);

        jTextArea_DE_Sig.setColumns(20);
        jTextArea_DE_Sig.setLineWrap(true);
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
        jTextArea_NE_Sin.setLineWrap(true);
        jTextArea_NE_Sin.setRows(5);
        jTextArea_NE_Sin.setFocusable(false);
        jScrollPane1.setViewportView(jTextArea_NE_Sin);

        jTextArea_NE_Sig.setColumns(20);
        jTextArea_NE_Sig.setLineWrap(true);
        jTextArea_NE_Sig.setRows(5);
        jTextArea_NE_Sig.setFocusable(false);
        jScrollPane2.setViewportView(jTextArea_NE_Sig);

        jb_Borrar_Signos.setText("Borrar");
        jb_Borrar_Signos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_Borrar_SignosActionPerformed(evt);
            }
        });

        jb_Borrar_Sintomas.setText("Borrar");
        jb_Borrar_Sintomas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_Borrar_SintomasActionPerformed(evt);
            }
        });

        jb_NE_Bsig.setText("Borrar");
        jb_NE_Bsig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_NE_BsigActionPerformed(evt);
            }
        });

        jb_NE_BSin.setText("Borrar");
        jb_NE_BSin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_NE_BSinActionPerformed(evt);
            }
        });

        jLabel9.setText("Tratamiento");

        jta_Trat.setColumns(20);
        jta_Trat.setRows(5);
        jScrollPane3.setViewportView(jta_Trat);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 239, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButtonANSig, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldSigno, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButtonANSint)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldSintoma, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addGap(123, 123, 123))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jcb_NE_Sin, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton_NE_Sin)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jb_NE_BSin)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6)
                                .addGap(132, 132, 132))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButton_NE_Sig)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jb_NE_Bsig))
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jcb_NE_Sig, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBoxSintomas, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButtonSintomas)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jb_Borrar_Sintomas)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButtonSignos)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jb_Borrar_Signos))
                                    .addComponent(jComboBoxSignos, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jBtnSend, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(34, 34, 34)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtf_NuevaEnfermedad, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(jBtnSend, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxSintomas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxSignos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSintomas)
                    .addComponent(jButtonSignos)
                    .addComponent(jb_Borrar_Signos)
                    .addComponent(jb_Borrar_Sintomas))
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonANSint)
                    .addComponent(jTextFieldSintoma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldSigno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonANSig))
                .addGap(19, 19, 19)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jLabel7)
                            .addComponent(jtf_NuevaEnfermedad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(jLabel6)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2)
                            .addComponent(jScrollPane1)))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcb_NE_Sin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcb_NE_Sig, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_NE_Sin)
                    .addComponent(jButton_NE_Sig)
                    .addComponent(jb_NE_Bsig)
                    .addComponent(jb_NE_BSin))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSendActionPerformed
        try {
            List<String> sintomas;
            List<String> signos;
            String trat;

            sintomas = Arrays.asList(jTextArea_DE_Sin.getText().split(":"));
            signos = Arrays.asList(jTextArea_DE_Sig.getText().split(":"));
            trat = jta_Trat.getText();

            server.busqueda_principal(client, sintomas, signos, trat);
        } catch (RemoteException ex) {
            Logger.getLogger(ClientUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jBtnSendActionPerformed

    private void updateContent() throws RemoteException{
        jComboBoxSintomas.removeAllItems();
        jComboBoxSignos.removeAllItems();
        jcb_NE_Sin.removeAllItems();
        jcb_NE_Sig.removeAllItems();
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
        
    }
    
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
            updateContent();
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
            updateContent();
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
            String trat = jta_Trat.getText();
            if( e.length() > 0 )
                server.add_tratamiento(trat);
                server.add_enfermedad(e, sin, sig, trat);
        } catch (RemoteException ex) {
            Logger.getLogger(ClientUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        jTextArea_NE_Sin.setText("");
        jTextArea_NE_Sig.setText("");
        jtf_NuevaEnfermedad.setText("");
        jta_Trat.setText("");
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

    private void jb_Borrar_SintomasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_Borrar_SintomasActionPerformed
        // TODO add your handling code here:
        jTextArea_DE_Sin.setText("");
    }//GEN-LAST:event_jb_Borrar_SintomasActionPerformed

    private void jb_Borrar_SignosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_Borrar_SignosActionPerformed
        // TODO add your handling code here:
        jTextArea_DE_Sig.setText("");
    }//GEN-LAST:event_jb_Borrar_SignosActionPerformed

    private void jb_NE_BSinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_NE_BSinActionPerformed
        // TODO add your handling code here:
        jTextArea_NE_Sin.setText("");
    }//GEN-LAST:event_jb_NE_BSinActionPerformed

    private void jb_NE_BsigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_NE_BsigActionPerformed
        // TODO add your handling code here:
        jTextArea_NE_Sig.setText("");
    }//GEN-LAST:event_jb_NE_BsigActionPerformed


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
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextArea jTextArea_DE_Sig;
    private javax.swing.JTextArea jTextArea_DE_Sin;
    private javax.swing.JTextArea jTextArea_NE_Sig;
    private javax.swing.JTextArea jTextArea_NE_Sin;
    private javax.swing.JTextField jTextFieldSigno;
    private javax.swing.JTextField jTextFieldSintoma;
    private javax.swing.JButton jb_Borrar_Signos;
    private javax.swing.JButton jb_Borrar_Sintomas;
    private javax.swing.JButton jb_NE_BSin;
    private javax.swing.JButton jb_NE_Bsig;
    private javax.swing.JComboBox<String> jcb_NE_Sig;
    private javax.swing.JComboBox<String> jcb_NE_Sin;
    private javax.swing.JTextArea jta_Trat;
    private javax.swing.JTextField jtf_NuevaEnfermedad;
    // End of variables declaration//GEN-END:variables

}
