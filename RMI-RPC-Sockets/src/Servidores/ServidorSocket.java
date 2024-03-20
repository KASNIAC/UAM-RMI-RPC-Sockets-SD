package Servidores;

import java.net.*;
import java.io.*;
import java.util.*;
import java.util.logging.*;

public class ServidorSocket {
    private BaseDatos bd;
    
    public ServidorSocket(BaseDatos bd){
        this.bd = bd;
    }
    
    // Se hizo uso de una lambda, para que el servidor de Socket tenga su propio hilo
    // Con esto lo que se consigue es que se pueda instanciar en cualquier momento en ProgramaServidor
    public void start() {
       (new Thread(() -> {
           try {
              int numeroHilo = 1;
              ServerSocket servidor = new ServerSocket(1235);
              while(true){
                  Socket entrante = servidor.accept();
                  // System.out.println("Generando Hilo " + numeroHilo + ".");
                  
                  Runnable r = new Manejador(entrante, numeroHilo, this.bd);
                  Thread t = new Thread(r);
                  t.start();
                  ++numeroHilo;
              }
          } catch (Exception ex) {
              Logger.getLogger(ServidorSocket.class.getName()).log(Level.SEVERE, null, ex);
          }
       })).start( );
    }
}

class Manejador implements Runnable {
    private BaseDatos bd;
    private Socket cliente;
    private int contador;
    
    public Manejador(Socket cliente, int contador, BaseDatos bd){
        this.cliente = cliente;
        this.contador = contador;
        this.bd = bd;
    }

    @Override
    public void run(){
        try{
            try{
                Scanner entrada = new Scanner(cliente.getInputStream());
                PrintWriter salida = new PrintWriter(cliente.getOutputStream(), true);
                salida.println(contador); // Le informo que numero de cliente es

                while(true){
                    String verifica = entrada.nextLine();
                    if(verifica.equals("Activo")){
                        String palabra = entrada.nextLine();
                        salida.println(this.bd.buscaPalabra(palabra));
                    } else if(verifica.equals("Inactivo")){
                        break;
                    }
                }
            } finally {
                cliente.close();
                // System.out.println("Cliente " + contador + " finalizado.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}