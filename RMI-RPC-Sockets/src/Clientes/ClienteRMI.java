package Clientes;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import Comun.OperacionesRMI_Interface;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteRMI implements Cliente{
    Registry registry;
    OperacionesRMI_Interface op;
    
    @Override
    public int inicia() {
        try {
            registry = LocateRegistry.getRegistry("localhost", 1237);
            op = (OperacionesRMI_Interface) registry.lookup("rmi://localhost:1237/ServicioBuscaPalabra");

            return op.noCliente();
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(ClienteRMI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    @Override
    public String buscaPalabra(String palabra) {
        try {
            return op.buscaPalabra(palabra);
        } catch (RemoteException ex) {
            Logger.getLogger(ClienteRMI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Fallo inesperado";
    }

    @Override
    public void termina() {
        // NO definido
    }
}