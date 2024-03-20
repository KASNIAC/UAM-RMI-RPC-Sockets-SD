/******************* ProgramaCliente*******************
    * Este es el programa principal que se ejecuta de parte del cliente.
    
    * El primer menú da la opción al cliente de elegir el servidor al cual
      desea conectarse. El objeto "c" se instancia en este punto haciendo
      uso del Polimorfismo para saber si se trata de un cliente de tipo
      Socket, RPC o RMI.
    
    * Si se eligió una opción válida, se manda a llamar a c.inicia(). Cada clase
      inicia de una forma diferente de acuerdo con el tipo de Cliente, pero en todos los
      casos retorna el número de cliente en caso de éxito.

    * El segundo menú da la opción al cliente de buscar una palabra en los archivos.
      Una vez más haciendo uso del Polimorfismo se manda a llamar a c.buscaPalabra(palabra).
      Cada clase pasa la palabra al servidor para realizar la búsqueda de una forma diferente
      de acuerdo con el tipo de Cliente.
    
    * Al salir del segundo menú se manda a llamar a c.termina(), por lo que si un mismo
      cliente desea volver a conectarse con un servidor al que ya se había conectado se le 
      asignará un número de Cliente diferente.
    
    * Al salir del primer menú se termina con la sesión del cliente.
***********************************************************/

package Clientes;

import Clientes.ClienteSocket;
import Clientes.Cliente;
import Clientes.ClienteRMI;
import Clientes.ClienteRPC;
import java.util.Scanner;

public class ProgramaCliente {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        while(true){
            // Primer Menu
            System.out.println("\t----- ¿Qué deseas activar? -----");
            System.out.println("1) Socket");
            System.out.println("2) RPC");
            System.out.println("3) RMI");
            System.out.println("4) Salir");
            System.out.print("\tElige: ");
            int opcion = entrada.nextInt(); entrada.nextLine();  // -> Quito el salto de línea
            int opcion2 = 1;
            Cliente c = null;   // Se instanciará hasta que elija el tipo de Servidor
            
            String tipoConexion = "";
            if(opcion == 1){
                System.out.println("Estas dentro de Sockets\n");
                c = new ClienteSocket();
                tipoConexion = "Sockets";
            } else if(opcion == 2){
                System.out.println("Estas dentro de RPC\n");
                c = new ClienteRPC();
                tipoConexion = "RPC";
            } else if(opcion == 3){
                System.out.println("Estas dentro de RMI\n");
                c = new ClienteRMI();
                tipoConexion = "RMI";
            } else if(opcion == 4){
                break;
            } else{
                System.out.println("Elige una opción válida, vuelve a intentarlo\n");
                continue;
            }
            
            int cliente = c.inicia(); // retorno el numero de cliente
            while(true){
                System.out.println("Usted es el cliente #" + cliente + " dentro de " + tipoConexion);
                // Segundo menu
                System.out.println("\t -----|MENU " + tipoConexion + "|-----");
                System.out.println("1) Buscar coincidencias");
                System.out.println("2) Salir");
                System.out.print("\tElige: ");

                opcion2 = entrada.nextInt(); entrada.nextLine(); // quito el salto de línea

                if(opcion2 == 1){
                    System.out.print("Digite la palabra a buscar: ");
                    String palabra = entrada.nextLine().toUpperCase();
                    
                    // c buscará de acuerdo al tipo de Cliente que sea
                    System.out.println(c.buscaPalabra(palabra) + "\n");
                } else if(opcion2 == 2){
                    c.termina();
                    break;
                } else{
                    System.out.println("Elige una opción válida, vuelve a intentarlo\n");
                }
            }
        }
    }
}
