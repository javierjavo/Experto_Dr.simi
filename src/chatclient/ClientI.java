package chatclient;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.*;

public interface ClientI extends Remote {
// Método para obtener el nombre del cliente
        public List<String> busqueda_local(List<String> sintomas,List<String> signos, String tratamiento) throws RemoteException;
        public void mostrar_deduccion(List<String>Enfermedades,List<String> sintomas,List<String> signos, String tratatmiento, int busqueda) throws RemoteException;
}
