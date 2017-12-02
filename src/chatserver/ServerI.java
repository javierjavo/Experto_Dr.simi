package chatserver;

import java.rmi.Remote;
import java.rmi.RemoteException;
import chatclient.ClientI;
import java.util.*;

public interface ServerI extends Remote {
    public boolean login (ClientI client) throws RemoteException;
    public boolean logout (ClientI client) throws RemoteException;
    
    public void busqueda_clientes (ClientI c,List<String> sintomas,List<String> signos, String tratamiento) throws RemoteException;
    public void busqueda_principal (ClientI c, List<String> sintomas,List<String> signos, String tratamiento) throws RemoteException;
    public void add_sintoma(String sintoma)throws RemoteException;  
    public void add_signo(String signo)throws RemoteException;  
    public void add_tratamiento(String tratamiento)throws RemoteException;  
    public void add_enfermedad(String enfermedad,String sintomas, String signos, String tratamiento)throws RemoteException;   
    public List<String> get_sintomas()throws RemoteException; 
    public List<String> get_signos()throws RemoteException; 
    
}
