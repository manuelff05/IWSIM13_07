//Hecho por 
//MANUEL FUERIS FRUTOS
//VELISLAVOVA TSEKOVA CRISTIANA



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class PlanificadorSemanal {
    private Receta[] comidaSemana; // para almacenar las recetas planificadas de cada semana
    private static final String[] DIAS = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo" };

    /**
     * Constructor
     * Inicializa el array para almacenar las comidas de cada día de la semana
     */

    public PlanificadorSemanal() {
        // Inicialización del planificador semanal
        comidaSemana= new Receta[7]; // siete días de la semana
    }

    /**
     * Añade una receta a un día de la semana en el planificador
     * @param dia de la semana (0= lunes , 6= domingo)
     * @param receta a añadir
     */

    public void agregarComida(int dia, Receta receta) {
        // Añade una receta a un día de la semana en el planificador semanal
        if (dia >= 0 && dia < 7 ) { // Verifica que el día esté en el rango
            comidaSemana[dia] = receta;
            System.out.println("Receta planificada para " + DIAS[dia]);
        }else{
            System.out.println("Día no válido");
        }
    }

    /**
     * Devuelve una representación en forma de cadena del planificador semanal.
     * @return el planificador como String
     */

    @Override
    public String toString() {
        // Devuelve una representación en forma de cadena del planificador semanal
        StringBuilder sb = new StringBuilder();
        sb.append("----------------------------------------------------------------------------------------------------\n");
        for (String dia : DIAS) {
            sb.append(String.format("%-15s", dia));
        }
        sb.append("\n----------------------------------------------------------------------------------------------------\n");

        for (int i = 0; i < 7; i++) {
            if (comidaSemana[i] != null) {
                sb.append(String.format("%-15s", comidaSemana[i].getNombre()));
            } else {
                sb.append(String.format("%-15s", ""));
            }
        }
        sb.append("\n----------------------------------------------------------------------------------------------------\n");
        return sb.toString();
        // @todo MODIFICAR PARA DEVOLVER LA CADENA CORRECTA
    }

    /**
     * Guarda el planificador semanal en archivo de texto
     * @param nombreArchivo donde se guarda el planificador
     * @throws IOException si ocurre un error al escribir en el archivo
     */

    public void guardarPlanEnArchivo(String nombreArchivo) throws IOException {
        // Guarda el planificador semanal en un archivo de texto
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))){
            for (int i = 0; i < 7; i++) {
                writer.write(DIAS[i] + ": ");
                if (comidaSemana[i] != null) {
                    writer.write(comidaSemana[i].getNombre() + "\n");
                } else {
                    writer.write("---\n");
                }
            }
            System.out.println("Plan semanal guardado en " + nombreArchivo);
        }
    }
}