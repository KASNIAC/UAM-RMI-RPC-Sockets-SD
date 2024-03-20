package Servidores;

import org.apache.xmlrpc.WebServer;

public class ServidorRPC {
    private BaseDatos bd;
    
    public ServidorRPC(BaseDatos bd){
        this.bd = bd;
    }

    public void start() throws Exception {
        WebServer server = new WebServer(1232);
        OperacionesRPC op = new OperacionesRPC(this.bd);// OperacionesRPC op = new OperacionesRPC(this.bd);
        server.addHandler("miServidor", op);
        server.start();
    }
}