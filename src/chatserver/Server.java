package chatserver;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import chatclient.ClientI;
import java.util.*;

public class Server  extends UnicastRemoteObject implements ServerI  {
    int clientesConectados = 0;
    private ArrayList<ClientI> clients;
    public Server() throws RemoteException {
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
    public void busqueda_principal(List<String> sintomas, List<String> signos) throws RemoteException {
        
    }
    
    @Override
    public void busqueda_clientes(List<String> sintomas, List<String> signos) throws RemoteException {
     
    }

    @Override
    public void add_sintoma(String sintoma) throws RemoteException {
        
    }

    @Override
    public void add_enfermedad(String enfermedad, String sintomas, String signos) throws RemoteException {
        
    }

    @Override
    public List<String> get_sintomas() throws RemoteException {
        
        return new ArrayList<>();
    }
}
