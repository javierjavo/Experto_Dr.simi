package chatserver;

import DB.Conectar;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import chatclient.ClientI;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server  extends UnicastRemoteObject implements ServerI  {
    int clientesConectados = 0;
    private ArrayList<ClientI> clients;
    private final String SQL_INSERT_SINTOMAS = "INSERT INTO sintomas (sintoma) VALUES(?)";
    private final String SQL_INSERT_SIGNOS = "INSERT INTO signos (signos) VALUES(?)";
    private final String SQL_INSERT_ENFERMEDAD = "INSERT INTO enfermedades (enfermedad,sintomas,signos,tratamiento) VALUES(?,?,?,?)";
    private final String SQL_INSERT_TRATAMIENTO = "INSERT INTO tratamientos (tratamientos) VALUES(?)";
    
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
    public void busqueda_principal(ClientI c, List<String> sintomas, List<String> signos, String tratamiento) throws RemoteException {
        List<String> enfermedades = new ArrayList<>();
        Map<Integer, String> treeMap = new TreeMap<>();
        try {
            //hago la busqueda
            String sql = "SELECT * FROM enfermedades";
            ResultSet rs = con.GetConnection().prepareStatement(sql).executeQuery();
            while(rs.next()){
                int peso=0;
                String sin[] = rs.getString(3).split(":");
                String sig[] = rs.getString(4).split(":");
                String trat = rs.getString(5);
                
                for(String si : sin){
                    if(sintomas.contains(si)){
                        peso++;
                    }
                }
                for(String sg : sig){
                    if(signos.contains(sg)){
                        peso++;
                    }
                }
                if(peso>0){
                    peso = (int)(100*peso)/(signos.size()+sintomas.size());
                    while(treeMap.containsKey(peso))peso++;
                    treeMap.put(peso, rs.getString(2) + ";" + trat);
                }
                else
                    treeMap.put(peso, "no encontrado");
            }
            Iterator<Integer> it = treeMap.keySet().iterator();
            while(it.hasNext()){
              Integer key = it.next();
              System.out.println("Clave: " + key + " -> Valor: " + treeMap.get(key));
              enfermedades.add(treeMap.get(key));
            }
            Collections.reverse(enfermedades);
            //ahora presento los datos al cliente solisitante
            c.mostrar_deduccion(enfermedades, sintomas, signos, tratamiento, 1);
        } catch (SQLException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void busqueda_clientes(ClientI c, List<String> sintomas, List<String> signos, String tratamiento) throws RemoteException {
        List<String> enfermedades = new ArrayList<>();
        //hago la busqueda en os clientes o internet o lo que sea
        for(ClientI client : clients){
            List<String> aux = client.busqueda_local(sintomas, signos, tratamiento);
            enfermedades.addAll(aux);
        }
        
        c.mostrar_deduccion(enfermedades, sintomas, signos, tratamiento, 0);
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
        }finally{
            PS = null;
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
        }finally{
            PS = null;
        }
    }
    
    @Override
    public void add_tratamiento(String tratamiento) throws RemoteException {
        try {
            //Establecer coneccion e insertar en la tabla correspondiente
            PS = con.GetConnection().prepareStatement(SQL_INSERT_TRATAMIENTO);
            PS.setString(1, tratamiento);
            int res = PS.executeUpdate();
            //Confirmar el update a la DB
            if(res > 0){
                System.out.println("Registro exitoso");
            }
        } catch (SQLException e){
            System.err.println("Error al guardar en la DB: " + e.getMessage());
        }finally{
            PS = null;
        }
    }

    @Override
    public void add_enfermedad(String enfermedad, String sintomas, String signos, String tratamiento) throws RemoteException {
        try {
            PS = con.GetConnection().prepareStatement(SQL_INSERT_ENFERMEDAD);
            PS.setString(1, enfermedad);
            PS.setString(2, sintomas);
            PS.setString(3, signos);
            PS.setString(4, tratamiento);
            int rs = PS.executeUpdate();
            if(rs > 0){
                System.out.println("Registro exitoso");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            PS = null;
        }
    }

    @Override
    public List<String> get_sintomas() throws RemoteException {
        List<String> enfermedad = new ArrayList<>();
        try {
            String sql = "SELECT * FROM sintomas";
            ResultSet rs = con.GetConnection().prepareStatement(sql).executeQuery();
            while(rs.next()){
                enfermedad.add(rs.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        return enfermedad;
    }

    @Override
    public List<String> get_signos() throws RemoteException {
        List<String> enfermedad = new ArrayList<>();
        try {
            String sql = "SELECT * FROM signos";
            ResultSet rs = con.GetConnection().prepareStatement(sql).executeQuery();
            while(rs.next()){
                enfermedad.add(rs.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return enfermedad;
    }
}
