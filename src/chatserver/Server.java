package chatserver;

import DB.Conectar;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import chatclient.ClientI;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Server  extends UnicastRemoteObject implements ServerI  {
    int clientesConectados = 0;
    private ArrayList<ClientI> clients;
    private final String SQL_INSERT_SINTOMAS = "INSERT INTO sintomas (sintoma) VALUES(?)";
    private final String SQL_INSERT_SIGNOS = "INSERT INTO signos (signos) VALUES(?)"; 
    private PreparedStatement PS; //Variables SQL
    Conectar con;
    
    public Server() throws RemoteException, SQLException {
       PS = null;
       con = new Conectar();
       clients = new ArrayList<>();
    }

    @Override
    public boolean login(ClientI client) throws RemoteException {
        clientesConectados++;
        System.out.println("Cliente"+clientesConectados+"conectado");
        clients.add(client);
        return true;
    }

    @Override
    public boolean logout(ClientI client) throws RemoteException {
        clientesConectados--;
        System.out.println("Cliente desconectado Clientes totales:"+clientesConectados+"");
        clients.remove(client);
        return true;
        
    }

    @Override
    public void busqueda_principal(ClientI c, List<String> sintomas, List<String> signos) throws RemoteException {
        try {
            //hago la busqueda
            con = new Conectar();
            Connection reg = con.GetConnection();
            //termino la busqueda y la guardo en una lista
            List<String> enfermedades = new ArrayList<>();
            enfermedades.add("toda la caca que encontro el la db");
            //ahora presento los datos al cliente solisitante
            c.mostrar_deduccion(enfermedades, sintomas, signos, 1);
        } catch (SQLException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void busqueda_clientes(ClientI c, List<String> sintomas, List<String> signos) throws RemoteException {
        //hago la busqueda en os clientes o internet o lo que sea
        
        //termino la busqueda y la guardo en una lista
        List<String> enfermedades = new ArrayList<>();
        enfermedades.add("toda la caca que encontro en los clientes o internet");
        //ahora presento los datos al cliente solisitante
        c.mostrar_deduccion(enfermedades, sintomas, signos, 0);
    }

    @Override
    public void add_sintoma(String sintoma) throws RemoteException {
        try {
            //Establecer coneccion e insertar en la tabla correspondiente
            PS = con.GetConnection().prepareStatement(SQL_INSERT_SINTOMAS);
            PS.setString(1, sintoma);
            int res = PS.executeUpdate();
            //Confirmar el update a la DB
            if(res > 0){
                System.out.println("Registro exitoso");
            }
        } catch (SQLException e){
            System.err.println("Error al guardar en la DB: " + e.getMessage());
        }
    }
    
    @Override
    public void add_signo(String signo) throws RemoteException {
        try {
            //Establecer coneccion e insertar en la tabla correspondiente
            PS = con.GetConnection().prepareStatement(SQL_INSERT_SIGNOS);
            PS.setString(1, signo);
            int res = PS.executeUpdate();
            //Confirmar el update a la DB
            if(res > 0){
                System.out.println("Registro exitoso");
            }
        } catch (SQLException e){
            System.err.println("Error al guardar en la DB: " + e.getMessage());
        }
    }

    @Override
    public void add_enfermedad(String enfermedad, String sintomas, String signos) throws RemoteException {
        
    }

    @Override
    public List<String> get_sintomas() throws RemoteException {
        
        return new ArrayList<>();
    }
}
