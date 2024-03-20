/******************* BaseDatos *******************
    * Esta clase es la encargada de leer el contenido de los archivos y guardar la
      información que nos interesa.
    
    * Su constructor es el encargado de ir a la carpeta especificada, verificar el contenido
      de los archivos e ir guardando la relación de las palabras, con los nombres de los
      archivos en donde aparece, así como el numero de veces que aparece dicha palabra en
      el mismo archivo.
    
    * Se tiene un HashMap<String, HashMap<String, Integer>> mapa. El primer String corresponde
      con las palabras contenidas en el archivo, el segundo HashMap<String, Integer> tiene
      como String el nombre del archivo y como Integer la cantidad de veces que aparece esa
      palabra en el archivo.
    
    * Se hace uso de una regex para que palabras dentro del archivo del tipo "¡Hola!" sea igual
      a "Hola", es decir, despreciando los signos de puntuación.
    
    * La regex solo se contruye una vez haciendo uso de:
      Pattern regex = Pattern.compile("[.,¡!\\?\\(\\)\\{\\}\\[\\]¿]");
    
    * La clase implementa el método buscaPalabra(), que toma como parámetro la palabra a buscar
      y devuelve una cadena con todas las coincidencias, dicha cadena se podria formatear para
      ser entregada al cliente en formato XML o JSON. Este método será usado por todos los
      Servidores.
**************************************************/

package Servidores;

import java.io.*;
import java.util.*;
import java.util.regex.*;

public class BaseDatos {
    private HashMap<String, HashMap<String, Integer>> mapa = new HashMap<>();
    
    public BaseDatos() throws IOException{
        // Especifica la ruta de la carpeta que deseas leer
        String carpeta = "archivos";

        // Crea un objeto File para representar la carpeta
        File directorio = new File(carpeta);

        // Verifica si la ruta especificada es una carpeta
        if (directorio.isDirectory()) {
            // Obtiene una lista de todos los archivos en la carpeta
            File[] archivos = directorio.listFiles();
            Pattern regex = Pattern.compile("[.,¡!\\?\\(\\)\\{\\}\\[\\]¿]");
            // Itera a través de la lista de archivos e imprime sus nombres
            for (File archivo : archivos) {
                if (archivo.isFile()) {
                    String nombreArchivo = archivo.getName();
                    // System.out.println(nombreArchivo);
                    String ruta = carpeta + "\\" + nombreArchivo;
                    try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
                        String linea;
                        while ((linea = br.readLine()) != null) {
                            linea = linea.toUpperCase();
                            linea = regex.matcher(linea).replaceAll(""); // Quito los que no me interesan
                            
                            String[] palabras = linea.split(" ");
                            for (String palabra : palabras) {
                                if(mapa.containsKey(palabra)){
                                    if(mapa.get(palabra).containsKey(nombreArchivo)){
                                        int valor = 1 + mapa.get(palabra).get(nombreArchivo);
                                        mapa.get(palabra).put(nombreArchivo, valor);
                                    } else{
                                        mapa.get(palabra).put(nombreArchivo, 1);
                                    }
                                    
                                } else{
                                    HashMap<String, Integer> nuevo = new HashMap<>();
                                    nuevo.put(nombreArchivo, 1);
                                    
                                    mapa.put(palabra, nuevo);
                                }
                            }
                            // System.out.println(linea); // Imprimir cada línea del archivo
                        }
                    } catch (IOException e) {
                        System.err.println("Error al leer el archivo: " + e.getMessage());
                    }

                }
            }
        } else {
            System.out.println("La ruta especificada no es una carpeta.");
        }
    }
    
    public String buscaPalabra(String palabra){
        String coincidencias = "";
        if(this.mapa.containsKey(palabra)){
            coincidencias = "\tArchivos en donde se encontró la palabra: ";
            Iterator<HashMap.Entry<String, Integer>> iterator = mapa.get(palabra).entrySet().iterator();
            while(iterator.hasNext()){
                HashMap.Entry<String, Integer> actual = iterator.next();
                coincidencias += "(" + actual.getKey() + " -> " + actual.getValue() + ")\t";
            }
        } else{
            coincidencias = "Palabra no encontrada en ningun archivo";
        }
        return coincidencias;
    }    
}
