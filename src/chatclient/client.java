package chatclient;

import DB.Conectar;
import chatserver.Server;
import chatserver.ServerI;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class client extends UnicastRemoteObject implements ClientI 
{
    private ClientUI ui;
    private ServerI server;
    private PreparedStatement PS; //Variables SQL
    Conectar con;
    
    public client () throws RemoteException{
    }
    
    public void setGUI(ClientUI ui,ServerI server) throws SQLException{ //super importante no moder esto, al crear una instancia
        this.ui=ui; // local se asigna a si mismo como una y se manda como parametro en el login al seridor
        this.server = server;
        PS = null;
        con = new Conectar();
    }

    @Override
    public List<String> busqueda_local(List<String> sintomas, List<String> signos, String tratamiento) throws RemoteException {
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
                System.out.println(peso);
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
              enfermedades.add(treeMap.get(key));
            }
            Collections.reverse(enfermedades);
            //ahora presento los datos al cliente solisitante
        } catch (SQLException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        return enfermedades;
    }

    @Override
    public void mostrar_deduccion(List<String> Enfermedades,List<String> sintomas,List<String> signos, String tratamiento, int busqueda) throws RemoteException {
       new msgbox(this,Enfermedades,sintomas,signos,tratamiento, this.server, busqueda);
    }

}
