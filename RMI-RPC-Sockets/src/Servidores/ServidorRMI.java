package Servidores;

import Comun.OperacionesRMI_Interface;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServidorRMI {
    private BaseDatos bd;
    
    public ServidorRMI(BaseDatos bd) {
        this.bd = bd;
    }

    public void start() throws Exception {
        //System.setProperty("java.rmi.server.hostname","172.17.20.56");
        OperacionesRMI_Interface op = new OperacionesRMI(bd);

        Registry reg = LocateRegistry.createRegistry(1237);
        
        Remote stub = UnicastRemoteObject.exportObject(op, 0);
        reg.bind("rmi://localhost:1237/ServicioBuscaPalabra", stub);
    }
}