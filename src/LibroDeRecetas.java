//Hecho por 
//MANUEL FUERIS FRUTOS
//VELISLAVOVA TSEKOVA CRISTIANA

import java.io.*;

public class LibroDeRecetas {
    private Receta[] recetas;
    private int maxReecetasEnLibro;
    private int numRecetas;

    /**
     * Constructor
     * @param maxRecetasEnLibro nº máximo de recetas que puede contener el libro
     */

    public LibroDeRecetas(int maxRecetasEnLibro) {
        // Inicialización del libro de recetas
        this.maxReecetasEnLibro=maxRecetasEnLibro;
        this.recetas=new Receta[maxRecetasEnLibro];
        this.numRecetas=0;
    }

    /**
     *
     * @param receta Receta a añadir
     * @return true si se ha añadido correctamente, false en caso contrario
     */

    public boolean agregarReceta(Receta receta) {
        // Añade una receta al libro de recetas
        if (recetasCompletas()){
            System.out.println("No se pueden añadir más recetas. ");
            return false;
        }
        recetas[numRecetas++]=receta;
        return true; // @todo MODIFICAR PARA DEVOLVER SI SE HA AÑADIDO LA RECETA
    }

    /**
     * Busca recetas por su nombre
     * @param texto El texto a buscar en el nombre de las recetas
     * @return Array de recetas que contienen el texto en su nombre. Si no se encuentran, devuelve un array vacío
     */
    public Receta[] buscarRecetaPorNombre(String texto) {
        // Busca recetas por su nombre y devuelve todas las encontradas
        Receta[] resultados= new Receta[numRecetas];
        int contador =0;
        for (int i =0; i<numRecetas; i++){
            if (recetas[i].getNombre().toLowerCase().contains(texto.toLowerCase())){
                resultados[contador++]=recetas[i];
            }
        }
        //Crear array
        Receta[] encontrados = new Receta[contador];
        for (int i = 0; i< contador; i++){
            encontrados[i]=resultados[i];
        }
        return encontrados; // @todo MODIFICAR PARA DEVOLVER LAS RECETAS ENCONTRADAS
    }

    /**
     * Guarda todas las recetas en un archivo de texto
     * @param nombreArchivo donde se guardarán las recetas
     * @throws IOException si ocurre algun error al escribir el archivo
     */

    public void guardarRecetasEnArchivo(String nombreArchivo) throws IOException {
        // Guarda las recetas en un archivo de texto
        try (BufferedWriter writer=new BufferedWriter(new FileWriter(nombreArchivo))){
            for (int i = 0; i <numRecetas; i++){
                writer.write(recetas[i].toRawString());
                writer.newLine(); //añade linea en blanco entre recetas si fuese necesario
            }
        }
    }

    /**
     * Carga recetas desde un archivo de texto
     * @param nombreArchivo nombre del archivo desde el que se cargarán las recetas
     * @param maxIngredientes nº máximo de ingredientes por receta
     * @param maxInstrucciones nº máximo de instrucciones por receta
     * @throws IOException si ocurre error al leer el archivo
     */

    public void cargarRecetasDeArchivo(String nombreArchivo, int maxIngredientes, int maxInstrucciones) throws IOException {
        // Carga las recetas desde un archivo de texto
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))){
            String linea;
            String nombreReceta =null;
            Receta receta = null;
            boolean lecturaIngredientes = true;

            while ((linea = reader.readLine()) != null){
                linea =linea.trim();
                if (linea.equals("INSTRUCCIONES")){
                    lecturaIngredientes=false;
                    continue;
                }
                if (linea.equals("-----")){
                    if (receta != null){
                        agregarReceta(receta);
                    }
                    receta =null;
                    lecturaIngredientes=true;
                    continue;
                }
                if (receta == null) {
                    receta = new Receta(linea, maxIngredientes,maxInstrucciones);
                }else {
                    if (lecturaIngredientes){
                        receta.agregarIngrediente(linea);
                    }else {
                        receta.agregarInstruccion(linea);
                    }
                }
            }
            //añadir la última receta si no está seguida de "-----"
            if (receta!=null && receta.getNombre()!=null){
                agregarReceta(receta);
            }
        }
    }

    /**
     * Comprueba si el libro de recetas está completo
     * @return true si lo está, false en otro caso
     */

    public boolean recetasCompletas() {
        // Comprueba si el libro de recetas está completo
        return numRecetas>=maxReecetasEnLibro; // @todo MODIFICAR PARA DEVOLVER SI ESTÁ COMPLETO
    }

    /**
     * Devuelve el nº actual de recetas en el libro
     * @return nº de recetas
     */
    public int numRecetas() {
        // Devuelve el número de recetas en el libro
        return numRecetas; // @todo MODIFICAR PARA DEVOLVER EL NÚMERO DE RECETAS
    }

    /**
     * Elimina receta del libro de recetas
     * @param seleccionada receta a eliminar
     */
    public void eliminarReceta(Receta seleccionada) {
        // Elimina una receta del libro de recetas
        if (seleccionada==null){
            return;
        }
        for (int i=0; i< numRecetas; i++){
            if (recetas[i] == seleccionada) { // Comparación por ref
                for (int j = i; j < numRecetas - 1; j++) {
                    recetas[j] = recetas[j + 1];
                }
                recetas[--numRecetas]=null; //eliminar última ref 
                System.out.println("Receta eliminada. ");
                return;
            }
        }
        //si no se encuentra la receta, no hacer nada

    }
}