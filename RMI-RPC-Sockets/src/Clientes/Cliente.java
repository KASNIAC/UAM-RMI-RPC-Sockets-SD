/******************* Interfaz Cliente *******************
    * Todos los tipos de Cliente (Socket, RPC, RMI) implementan esta clase.
    * En inicia instancian los elementos necesarios para operar y devuelven el número
      de cliente que son.
    * En buscaPalabra tomar la palabra a buscar y establecen la comunicación con su tipo de
      Servidor correspondiente para hacer la búsqueda, para posteriormente devolver las
      coincidencias.
    * En termina en realidad la única que lo implementa es ClienteSocket para cerrar la
      conexión, así como el buffer de entrada y salida de datos.
************************************************************/

package Clientes;

public interface Cliente {
    int inicia();   // Devolverá el número de cliente en caso de éxito, o -1 en caso de fracaso
    String buscaPalabra(String palabra);
    void termina();   // Se implementa para aquellos clientes que definan como cerrar el buffer de datos
}