//Hecho por
//MANUEL FUERIS FRUTOS
//VELISLAVOVA TSEKOVA CRISTIANA



public class Main {
    public static void main(String[] args) {
        // Comprueba los argumentos de la línea de comandos y lanza la interfaz de usuario
        if (args.length < 3 || args.length > 4) {
            System.out.println("Uso: java Main <maxIngredientes> <maxInstrucciones> <maxRecetasEnLibro> [<archivoRecetas>]");
            return;
        }

        try {
            // Convierte los primeros tres argumentos en enteros
            // Estos argumentos especifican el máximo de ingredientes, instrucciones y recetas
            int maxIngredientes = Integer.parseInt(args[0]);
            int maxInstrucciones = Integer.parseInt(args[1]);
            int maxRecetasEnLibro = Integer.parseInt(args[2]);

            InterfazUsuario interfazUsuario;

            // Si se proporciona un cuarto argumento, se utiliza para cargar recetas desde un archivo
            if (args.length == 4) {
                String archivoRecetas = args[3];
                // Inicializa la interfaz de usuario cargando recetas desde el archivo especificado
                interfazUsuario = new InterfazUsuario(maxIngredientes, maxInstrucciones, maxRecetasEnLibro, archivoRecetas);
            } else {
                // Inicializa la interfaz de usuario sin cargar recetas de archivo
                interfazUsuario = new InterfazUsuario(maxIngredientes, maxInstrucciones, maxRecetasEnLibro);
            }

            // Llama al método iniciar para mostrar el menú principal y comenzar la interacción
            interfazUsuario.iniciar();

        } catch (NumberFormatException e) {

            System.out.println("ERROR: Los argumentos maxIngredientes, maxInstrucciones y maxRecetasEnLibro deben ser números enteros.");
        } catch (Exception e) {

            System.out.println("ERROR inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
