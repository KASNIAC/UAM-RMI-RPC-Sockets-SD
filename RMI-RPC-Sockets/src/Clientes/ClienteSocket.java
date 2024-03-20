package Clientes;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteSocket implements Cliente{
    public Socket conexion;
    public PrintWriter salida;
    private Scanner entrada;
    
    @Override
    public int inicia() {
        try {
            conexion = new Socket("localhost", 1235);
            salida = new PrintWriter(conexion.getOutputStream(), true);
            entrada = new Scanner(conexion.getInputStream());       
            // Scanner teclado = new Scanner(System.in);
        } catch (IOException ex) {
            Logger.getLogger(ClienteSocket.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
        int contador = entrada.nextInt();
        entrada.nextLine(); // Me salto el salto de linea
        return contador; // Devuelvo el número de usuario 
    }
    
    @Override
    public String buscaPalabra(String palabra) {
        salida.println("Activo"); // Le informo al Manejador que sigo activo
        salida.println(palabra);
        return entrada.nextLine();
    }

    @Override
    public void termina() {
        salida.println("Inactivo"); // Le informo al Manejador antes de morirme
        try {
             // Cierre de flujos y conexión al finalizar
            entrada.close();
            salida.close();
            conexion.close();
        } catch (IOException ex) {
            Logger.getLogger(ClienteSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
