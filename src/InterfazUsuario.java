//Hecho por 
//MANUEL FUERIS FRUTOS
//VELISLAVOVA TSEKOVA CRISTIANA

import java.io.IOException;
import java.util.Scanner;

public class InterfazUsuario {
    private LibroDeRecetas libroDeRecetas;
    private PlanificadorSemanal planificador;
    private int maxIngredientes;
    private int maxInstrucciones;

    /**
     * Constructor principal que inicializa el libro de recetas y el planificador semanal.
     *
     * @param maxIngredientes    Número máximo de ingredientes por receta.
     * @param maxInstrucciones   Número máximo de instrucciones por receta.
     * @param maxRecetasEnLibro  Número máximo de recetas en el libro.
     */
    public InterfazUsuario(int maxIngredientes, int maxInstrucciones, int maxRecetasEnLibro) {
        this.maxIngredientes = maxIngredientes;
        this.maxInstrucciones = maxInstrucciones;
        this.libroDeRecetas = new LibroDeRecetas(maxRecetasEnLibro);
        this.planificador = new PlanificadorSemanal();
    }

    /**
     * Constructor que también carga recetas desde un archivo.
     *
     * @param maxIngredientes    Número máximo de ingredientes por receta.
     * @param maxInstrucciones   Número máximo de instrucciones por receta.
     * @param maxRecetasEnLibro  Número máximo de recetas en el libro.
     * @param archivoRecetas     Nombre del archivo desde el que se cargarán las recetas.
     */
    public InterfazUsuario(int maxIngredientes, int maxInstrucciones, int maxRecetasEnLibro, String archivoRecetas) {
        this(maxIngredientes, maxInstrucciones, maxRecetasEnLibro);
        try {
            libroDeRecetas.cargarRecetasDeArchivo(archivoRecetas, maxIngredientes, maxInstrucciones);
            System.out.println("Archivo cargado correctamente.");
        } catch (IOException e) {
            System.out.println("ERROR: No se pudo cargar el archivo.");
        }
    }

    /**
     * Método para iniciar la aplicación mostrando el menú principal.
     */
    public void iniciar() {
        Scanner scanner = new Scanner(System.in);
        menuPrincipal(scanner);
        scanner.close();
    }

    /**
     * Muestra el menú principal y gestiona las opciones del usuario.
     */
    private void menuPrincipal(Scanner scanner) {
        while (true) {
            System.out.println("\nMENÚ PRINCIPAL:");
            System.out.println("1. Nueva receta");
            System.out.println("2. Consultar receta");
            System.out.println("3. Planificar comidas");
            System.out.println("4. Guardar recetas");
            System.out.println("5. Cargar recetas");
            System.out.println("6. Guardar plan semanal");
            System.out.println("7. Salir");

            int opcion = Utilidades.leerNumero(scanner, "Introduce una opción", 1, 7);

            if (opcion == 7) {
                System.out.println("Saliendo del programa.");
                break;
            }

            switch (opcion) {
                case 1 -> agregarReceta(scanner);
                case 2 -> consultarReceta(scanner);
                case 3 -> planificarComidas(scanner);
                case 4 -> guardarRecetas(scanner);
                case 5 -> cargarRecetas(scanner);
                case 6 -> guardarPlanSemanal(scanner);
            }
        }
    }

    /**
     * Solicita al usuario los datos de la receta y la añade al libro de recetas.
     */
    private void agregarReceta(Scanner scanner) {
        System.out.println("\nCREAR UNA NUEVA RECETA");
        String nombre = Utilidades.leerCadena(scanner, "Introduce el nombre de la receta");

        Receta nuevaReceta = new Receta(nombre, maxIngredientes, maxInstrucciones);

        System.out.println("Introduce los ingredientes. Pulsa ENTER para finalizar:");
        while (!nuevaReceta.ingredientesCompletos()) {
            String ingrediente = Utilidades.leerCadena(scanner, "- ");
            if (ingrediente.isEmpty()) break;
            nuevaReceta.agregarIngrediente(ingrediente);
        }

        System.out.println("Introduce las instrucciones. Pulsa ENTER para finalizar:");
        while (!nuevaReceta.instruccionesCompletas()) {
            String instruccion = Utilidades.leerCadena(scanner, "- ");
            if (instruccion.isEmpty()) break;
            nuevaReceta.agregarInstruccion(instruccion);
        }

        if (libroDeRecetas.agregarReceta(nuevaReceta)) {
            System.out.println("Receta añadida correctamente.");
        } else {
            System.out.println("ERROR: No se pudo añadir la receta. El libro está lleno.");
        }
    }

    /**
     * Permite buscar una receta y gestionarla.
     */
    private void consultarReceta(Scanner scanner) {
        System.out.println("\nCONSULTAR RECETA");
        Receta seleccionada = buscarRecetaPorNombre(scanner);
        if (seleccionada != null) {
            System.out.println(seleccionada);
            editarReceta(scanner, seleccionada);
        } else {
            System.out.println("No se encontraron recetas con ese nombre.");
        }
    }

    /**
     * Solicita al usuario un texto para buscar y seleccionar una receta por su nombre.
     */
    private Receta buscarRecetaPorNombre(Scanner scanner) {
        String textoBusqueda = Utilidades.leerCadena(scanner, "Introduce el nombre o parte del nombre de la receta");
        Receta[] resultados = libroDeRecetas.buscarRecetaPorNombre(textoBusqueda);

        return seleccionarReceta(scanner, resultados);
    }

    /**
     * Permite al usuario seleccionar una receta de una lista.
     */
    private Receta seleccionarReceta(Scanner scanner, Receta[] recetas) {
        if (recetas.length == 0) {
            System.out.println("No se encontraron recetas.");
            return null;
        }

        System.out.println("SELECCIONA UNA RECETA:");
        for (int i = 0; i < recetas.length; i++) {
            System.out.println((i + 1) + ". " + recetas[i].getNombre());
        }

        int seleccion = Utilidades.leerNumero(scanner, "Selecciona una receta (0 para cancelar)", 0, recetas.length);
        return seleccion == 0 ? null : recetas[seleccion - 1];
    }

    /**
     * Permite editar una receta seleccionada.
     */
    private void editarReceta(Scanner scanner, Receta seleccionada) {
        System.out.println("\nEDITAR RECETA:");
        System.out.println("1. Cambiar nombre");
        System.out.println("2. Añadir ingrediente");
        System.out.println("3. Añadir instrucción");
        System.out.println("4. Volver al menú principal");

        int opcion = Utilidades.leerNumero(scanner, "Selecciona una opción", 1, 4);
        switch (opcion) {
            case 1 -> {
                String nuevoNombre = Utilidades.leerCadena(scanner, "Introduce el nuevo nombre");
                seleccionada = new Receta(nuevoNombre, maxIngredientes, maxInstrucciones);
                System.out.println("Nombre cambiado correctamente.");
            }
            case 2 -> {
                String ingrediente = Utilidades.leerCadena(scanner, "Introduce un nuevo ingrediente");
                seleccionada.agregarIngrediente(ingrediente);
            }
            case 3 -> {
                String instruccion = Utilidades.leerCadena(scanner, "Introduce una nueva instrucción");
                seleccionada.agregarInstruccion(instruccion);
            }
            default -> System.out.println("Volviendo al menú principal.");
        }
    }

    /**
     * Inicia el proceso de planificación de comidas.
     */
    private void planificarComidas(Scanner scanner) {
        System.out.println("\nPLANIFICAR COMIDAS:");
        Receta seleccionada = buscarRecetaPorNombre(scanner);

        if (seleccionada != null) {
            int dia = Utilidades.leerDiaDeLaSemana(scanner, "Selecciona un día para esta receta");
            planificador.agregarComida(dia, seleccionada);
            System.out.println("Receta añadida al día: " + Utilidades.posicionADiaSemana(dia));
        } else {
            System.out.println("No se seleccionó ninguna receta.");
        }
    }

    /**
     * Solicita un nombre de archivo y guarda las recetas en ese archivo.
     */
    private void guardarRecetas(Scanner scanner) {
        String archivo = Utilidades.leerCadena(scanner, "Introduce el nombre del archivo donde guardar las recetas");
        try {
            libroDeRecetas.guardarRecetasEnArchivo(archivo);
            System.out.println("Recetas guardadas correctamente en el archivo.");
        } catch (IOException e) {
            System.out.println("ERROR: No se pudieron guardar las recetas.");
        }
    }

    /**
     * Solicita un nombre de archivo y carga las recetas desde ese archivo.
     */
    private void cargarRecetas(Scanner scanner) {
        String archivo = Utilidades.leerCadena(scanner, "Introduce el nombre del archivo desde el que cargar recetas");
        try {
            libroDeRecetas.cargarRecetasDeArchivo(archivo, maxIngredientes, maxInstrucciones);
            System.out.println("Recetas cargadas correctamente desde el archivo.");
        } catch (IOException e) {
            System.out.println("ERROR: No se pudieron cargar las recetas.");
        }
    }

    /**
     * Solicita un nombre de archivo y guarda el plan semanal en ese archivo.
     */
    private void guardarPlanSemanal(Scanner scanner) {
        String archivo = Utilidades.leerCadena(scanner, "Introduce el nombre del archivo donde guardar el plan semanal");
        try {
            planificador.guardarPlanEnArchivo(archivo);
            System.out.println("Plan semanal guardado correctamente en el archivo.");
        } catch (IOException e) {
            System.out.println("ERROR: No se pudo guardar el plan semanal.");
        }
    }
}