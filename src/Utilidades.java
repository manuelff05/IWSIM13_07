//Hecho por 
//MANUEL FUERIS FRUTOS
//VELISLAVOVA TSEKOVA CRISTIANA


import java.util.Scanner;

/**
 * Clase con métodos de utilidad para la entrada de datos por teclado.
 */
public class Utilidades {
    private static final String[] DIAS_ABREVIADOS = {"L", "M", "X", "J", "V", "S", "D"};
    private static final String[] DIAS_COMPLETOS = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo" };

    /**
     * Muestra un mensaje y lee una cadena por teclado
     * @param teclado Objeto Scanner para leer por teclado
     * @param s Mensaje a mostrar al usuario
     * @return Cadena de texto introducida por el usuario
     */
    public static String leerCadena(Scanner teclado, String s) {
        System.out.print(s + ": ");
        return teclado.nextLine().trim(); // @todo MODIFICAR PARA DEVOLVER LA CADENA LEÍDA
    }

    /**
     * Muuesta un mensaje y lee un nº por teclado dentro de un rango específico.
     * Si el nº no está en el rango o no es válido, vuelve a solicitar uno.
     * @param teclado
     * @param mensaje
     * @param minimo
     * @param maximo
     * @return El nº leído dentro del rango
     */

    public static int leerNumero(Scanner teclado, String mensaje, int minimo, int maximo) {
        int num;
        while (true){
            System.out.print(mensaje + " [" + minimo + "-" + maximo + "]: ");
            if (teclado.hasNextInt()){
                num= teclado.nextInt();
                teclado.nextLine();
                if(num >= minimo && num <= maximo){
                    return num;
                }else{
                    System.out.println("ERROR: Número fuera del rango permitido. Inténtalo de nuevo.");
                }
            }else{
                System.out.println("ERROR: Entrada inválida. Por favor, introduce un número. ");
                teclado.nextLine();
            }
        }
        // @todo MODIFICAR PARA DEVOLVER EL NÚMERO LEÍDO
    }

    /**
     * Muestra un mensaje y lee un día de la semana por teclado (L,M,X,J,V,S,D)
     * Devuelve su posición dentro de la semana(0-6)
     * @param teclado para leer datos
     * @param mensaje mensaje a mostrar
     * @return posición del día de la semana
     */
    public static int leerDiaDeLaSemana(Scanner teclado, String mensaje) {
        while (true){
            System.out.print(mensaje + "(L, M, X, J, V, S, D: ");
            String dia = teclado.nextLine().trim().toUpperCase();
            int posicion= diaSemanaAPosicion(dia);
            if (posicion !=-1){
                return posicion;
            }else{
                System.out.println("ERROR: Día no válido. Introduce un valor válido (L, M, X, J, V, S, D).");
            }
        }// @todo MODIFICAR PARA DEVOLVER EL DÍA DE LA SEMANA LEÍDO
    }

    /**
     * Devuelve la posición de un día de la semana (L, M, X, J, V, S, D) dentro de la semana (0-6)
     * @param dia Cadena que representa el día de la semana
     * @return La posición del día (0= Lunes, 6= Domingo) o -1 si el día no es válido
     */

    public static int diaSemanaAPosicion(String dia) {
        for (int i = 0; i< DIAS_ABREVIADOS.length; i++){
            if (DIAS_ABREVIADOS[i].equals(dia)){
                return i;
            }
        }
        return -1; // @todo MODIFICAR PARA DEVOLVER LA POSICIÓN DEL DÍA DE LA SEMANA
    }

    /**
     * Devuelve el día de la semana (Lunes, Martes, Miércoles, Jueves, Viernes, Sábado, Domingo)
     * correspondiente a una posición dentro de la semana (0-6)
     * @param pos Posición del día de la semana
     * @return Nombre completo del día o "Desconocido" si la posición no es válida
     */

    public static String posicionADiaSemana(int pos) {
        if (pos >= 0 && pos < DIAS_COMPLETOS.length){
            return DIAS_COMPLETOS[pos];
        }
        return "Desconocido"; // @todo MODIFICAR PARA DEVOLVER EL DÍA DE LA SEMANA
    }


}