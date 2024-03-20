package Comun;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface OperacionesRMI_Interface extends Remote{
    public String buscaPalabra(String palabra) throws RemoteException;
    public int noCliente() throws RemoteException;
}


