/******************* ProgramaServidor *******************
    * Este es el programa principal que se ejecuta para iniciar los Servidores
    
    * Se instancia un objeto de la clase "BaseDatos" llamado bd, el cual se pasará por
      referencia a cada uno de los servidores (Socket, RPC, RMI)
    
    * Posteriormente se instancian cada uno de los 3 tipos de servidores y se encienden
      haciendo uso de la función start()
    
    * Cada uno de los 3 servidores atiende a múltiples clientes de manera concurrente.
********************************************************/

package Servidores;

import Servidores.ServidorRMI;
import Servidores.ServidorRPC;
import Servidores.ServidorSocket;

public class ProgramaServidor {
    public static void main(String[] args) {
        try{
            System.out.println("Iniciando carga de archivos...");
            BaseDatos bd = new BaseDatos();
            System.out.println("Carga terminada \n");

            System.out.println("Iniciando servidor socket...");
            ServidorSocket ss = new ServidorSocket(bd);
            ss.start( );
            System.out.println("Servidor socket iniciado correctamente... \n");
            
            System.out.println("Iniciando servidor RPC...");
            ServidorRPC sr = new ServidorRPC(bd);
            sr.start( );
            System.out.println("Servidor RPC iniciado correctamente... \n");
            
            System.out.println("Iniciando servidor RMI...");
            ServidorRMI srmi = new ServidorRMI(bd);
            srmi.start( );
            System.out.println("Servidor RMI iniciado correctamente... \n");
        } catch (Exception exception){
            System.err.println("Server: " + exception);
        }
    }   
}