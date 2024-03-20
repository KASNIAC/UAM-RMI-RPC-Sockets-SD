package Servidores;

import Comun.OperacionesRMI_Interface;

public class OperacionesRMI implements OperacionesRMI_Interface{
    private BaseDatos bd;
    private int noCliente = 1;
    
    public OperacionesRMI(BaseDatos bd) {
       this.bd = bd;
    }
    
    @Override
    public String buscaPalabra(String palabra){
        return this.bd.buscaPalabra(palabra);
    }
    
    @Override
    public int noCliente(){
        return noCliente++;
    }
}