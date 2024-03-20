package Clientes;

import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.xmlrpc.XmlRpcClient;
import org.apache.xmlrpc.XmlRpcException;

public class ClienteRPC implements Cliente{
    public XmlRpcClient cliente;
    public Scanner entrada;
    
    public Vector<Integer> params;
    public Object respuestaNoCliente;
    
    public Vector<String> palabras;
    public Object respuestaPalabra;
    
    @Override
    public int inicia() {
        try{
            cliente = new XmlRpcClient("http://localhost:1232");
            entrada = new Scanner(System.in);
            
            params = new Vector<Integer>();
            
            respuestaNoCliente = cliente.execute("miServidor.noCliente", params);
            int noCliente = ((Integer) respuestaNoCliente).intValue();
            return noCliente;
            
        } catch(Exception exception){
            System.err.println("JavaClient: " + exception);
            return -1;
        }
    }

    @Override
    public String buscaPalabra(String palabra) {
        try {
            Vector<String> palabras = new Vector<String>();
            palabras.add(palabra);
            respuestaPalabra = cliente.execute("miServidor.buscaPalabra", palabras);
            String resultado = ((String) respuestaPalabra);
            return resultado;
        } catch (XmlRpcException ex) {
            Logger.getLogger(ClienteRPC.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClienteRPC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Fallo inesperado";
    }

    @Override
    public void termina() {
        // No definido
    }
}