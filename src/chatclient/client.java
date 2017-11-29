package chatclient;

import chatserver.ServerI;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class client extends UnicastRemoteObject implements ClientI 
{
    private ClientUI ui;
    private ServerI server;
    
    public client () throws RemoteException{
    }
    
    public void setGUI(ClientUI ui,ServerI server){ //super importante no moder esto, al crear una instancia
        this.ui=ui; // local se asigna a si mismo como una y se manda como parametro en el login al seridor
        this.server = server;
    }

    @Override
    public List<String> busqueda_local(List<String> sintomas, List<String> signos) throws RemoteException {
        return new ArrayList<>();
    }

    @Override
    public void mostrar_deduccion(List<String> Enfermedades,List<String> sintomas,List<String> signos, int busqueda) throws RemoteException {
       new msgbox(this,Enfermedades,sintomas,signos, this.server, busqueda);
    }

}
