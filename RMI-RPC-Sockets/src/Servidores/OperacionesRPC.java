package Servidores;

public class OperacionesRPC {
    private BaseDatos bd;
    private int noCliente = 1;
    
    public OperacionesRPC(BaseDatos bd) {
       this.bd = bd;
    }
    
    public String buscaPalabra(String palabra){
        return this.bd.buscaPalabra(palabra);
    }
    
    public int noCliente(){
        return noCliente++;
    }
}
