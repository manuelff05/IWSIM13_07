//Hecho por 
//MANUEL FUERIS FRUTOS
//VELISLAVOVA TSEKOVA CRISTIANA


public class Receta {
    private String nombre; // Nombre de la receta
    private int maxIngredientes;
    private int maxInstrucciones;
    private String[] ingredientes;
    private String[] instrucciones;
    private int numIngredientes;
    private int numInstrucciones;

    /**
     * Constructor de la clase Receta.
     * Inicializa el nombre, el número máximo de ingredientes e instrucciones.
     * Crea los arrays fijos para ingredientes e instrucciones.
     *
     * @param nombre Nombre de la receta.
     * @param maxIngredientes Número máximo de ingredientes.
     * @param maxInstrucciones Número máximo de instrucciones.
     */
    public Receta(String nombre, int maxIngredientes, int maxInstrucciones) {
        this.nombre = nombre;
        this.maxIngredientes = maxIngredientes;
        this.maxInstrucciones = maxInstrucciones;
        this.ingredientes = new String[maxIngredientes];
        this.instrucciones = new String[maxInstrucciones];
        this.numIngredientes = 0;
        this.numInstrucciones = 0;
    }

    /**
     * Devuelve el nombre de la receta.
     *
     * @return El nombre de la receta.
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Devuelve una copia del array de ingredientes actuales.
     * Solo se devuelven los ingredientes añadidos, no el array completo.
     *
     * @return Un array con los ingredientes actuales.
     */
    public String[] getIngredientes() {
        String[] cpIngredientes = new String[numIngredientes];
        for (int i = 0; i < numIngredientes; i++) {
            cpIngredientes[i] = ingredientes[i];
        }
        return cpIngredientes;
    }

    /**
     * Devuelve una copia del array de instrucciones actuales.
     * Solo se devuelven las instrucciones añadidas, no el array completo.
     *
     * @return Un array con las instrucciones actuales.
     */
    public String[] getInstrucciones() {
        String[] cpInstrucciones = new String[numInstrucciones];
        for (int i = 0; i < numInstrucciones; i++) {
            cpInstrucciones[i] = instrucciones[i];
        }
        return cpInstrucciones;
    }

    /**
     * Añade un ingrediente a la receta si no se ha alcanzado el límite.
     * Muestra un mensaje de error si se supera el número máximo de ingredientes.
     *
     * @param ingrediente Nombre del ingrediente a añadir.
     * @return true si el ingrediente se añadió correctamente, false en caso contrario.
     */
    public boolean agregarIngrediente(String ingrediente) {
        if (numIngredientes < maxIngredientes) {
            ingredientes[numIngredientes++] = ingrediente;
            return true;
        }
        System.out.println("No se pueden añadir más ingredientes.");
        return false;
    }

    /**
     * Añade una instrucción a la receta si no se ha alcanzado el límite.
     * Muestra un mensaje de error si se supera el número máximo de instrucciones.
     *
     * @param instruccion Texto de la instrucción a añadir.
     * @return true si la instrucción se añadió correctamente, false en caso contrario.
     */
    public boolean agregarInstruccion(String instruccion) {
        if (numInstrucciones < maxInstrucciones) {
            instrucciones[numInstrucciones++] = instruccion;
            return true;
        }
        System.out.println("No se pueden añadir más instrucciones.");
        return false;
    }

    /**
     * Comprueba si se ha alcanzado el máximo número de ingredientes.
     *
     * @return true si los ingredientes están completos, false en caso contrario.
     */
    public boolean ingredientesCompletos() {
        return numIngredientes == maxIngredientes;
    }

    /**
     * Comprueba si se ha alcanzado el máximo número de instrucciones.
     *
     * @return true si las instrucciones están completas, false en caso contrario.
     */
    public boolean instruccionesCompletas() {
        return numInstrucciones == maxInstrucciones;
    }

    /**
     * Devuelve el número actual de ingredientes añadidos a la receta.
     *
     * @return Número de ingredientes actuales.
     */
    public int numIngredientes() {
        return numIngredientes;
    }

    /**
     * Devuelve el número actual de instrucciones añadidas a la receta.
     *
     * @return Número de instrucciones actuales.
     */
    public int numInstrucciones() {
        return numInstrucciones;
    }

    /**
     * Genera una representación formateada de la receta en forma de cadena.
     * Incluye el nombre de la receta, los ingredientes y las instrucciones numeradas.
     *
     * @return Una cadena con la información completa de la receta.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Receta: ").append(nombre).append("\n");
        sb.append("Ingredientes:\n");
        for (int i = 0; i < numIngredientes; i++) {
            sb.append("- ").append(ingredientes[i]).append("\n");
        }
        sb.append("Instrucciones:\n");
        for (int i = 0; i < numInstrucciones; i++) {
            sb.append((i + 1)).append(". ").append(instrucciones[i]).append("\n");
        }
        return sb.toString();
    }

    /**
     * Genera una representación simplificada de la receta en formato "raw".
     * Incluye el nombre, los ingredientes y las instrucciones sin formato adicional.
     *
     * @return Una cadena simplificada con la información de la receta.
     */
    public String toRawString() {
        StringBuilder sb = new StringBuilder();
        sb.append(nombre).append("\n");
        for (int i = 0; i < numIngredientes; i++) {
            sb.append(ingredientes[i]).append("\n");
        }
        sb.append("INSTRUCCIONES\n");
        for (int i = 0; i < numInstrucciones; i++) {
            sb.append(instrucciones[i]).append("\n");
        }
        sb.append("-----\n");
        return sb.toString();
    }

    /**
     * Devuelve el número máximo de ingredientes permitidos en la receta.
     *
     * @return El máximo número de ingredientes.
     */
    public int getMaxIngredientes() {
        return maxIngredientes;
    }

    /**
     * Devuelve el número máximo de instrucciones permitidas en la receta.
     *
     * @return El máximo número de instrucciones.
     */
    public int getMaxInstrucciones() {
        return maxInstrucciones;
    }
}