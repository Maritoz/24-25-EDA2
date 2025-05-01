public class Cliente {
    public static void main(String[] args) {
        String[] cabeceras = {"ID", "Nombre", "Ciudad"};
        String[][] datos = {
            {"1", "Ana", "Lima"},
            {"2", "Luis", "Quito"},
            {"3", "Carlos", "Lima"},
            {"4", "Sofía", "Bogotá"},
            {"5", "Luis", "Lima"}
        };

        GestorCSV gestor = new GestorCSV(10, 3);
        gestor.cargarDatos(cabeceras, datos);
        gestor.imprimirDatos();

        gestor.crearIndice("Nombre");

        System.out.println("\n> Resultados para búsqueda por índice 'Nombre' = Luis:");
        String[][] resultados = gestor.buscarPorIndice("Nombre", "Luis");

        for (String[] fila : resultados) {
            for (String campo : fila) {
                System.out.printf("%-20s", campo);
            }
            System.out.println();
        }

        System.out.println("\n> Valores únicos en columna 'Nombre':");
        for (String valor : gestor.obtenerValoresUnicos("Nombre")) {
            System.out.println(" - " + valor);
        }
    }
}
