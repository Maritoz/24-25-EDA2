public class GestorCSV {
    private String[][] datos;
    private String[] cabeceras;
    private int filas;
    private int columnas;
    private Indice[] indices;
    private boolean[] columnaIndexada;

    public GestorCSV(int capacidadMaxima, int numColumnas) {
        datos = new String[capacidadMaxima][numColumnas];
        cabeceras = new String[numColumnas];
        indices = new Indice[numColumnas];
        columnaIndexada = new boolean[numColumnas];
        filas = 0;
        columnas = numColumnas;
    }

    public void cargarDatos(String[] cabeceras, String[][] datosEntrada) {
        this.cabeceras = cabeceras;
        filas = datosEntrada.length;

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                datos[i][j] = datosEntrada[i][j];
            }
        }

        System.out.println("> Datos cargados correctamente");
    }

    public void crearIndice(String nombreColumna) {
        int indiceColumna = obtenerIndiceColumna(nombreColumna);
        if (indiceColumna == -1) {
            System.out.println("Columna no encontrada: " + nombreColumna);
            return;
        }

        indices[indiceColumna] = new IndiceOrdenado(filas);
        columnaIndexada[indiceColumna] = true;

        for (int i = 0; i < filas; i++) {
            indices[indiceColumna].agregar(datos[i][indiceColumna], i);
        }

        System.out.println("> Índice creado para la columna: " + nombreColumna);
    }

    public String[][] buscarPorIndice(String nombreColumna, String valor) {
        int indiceColumna = obtenerIndiceColumna(nombreColumna);
        if (indiceColumna == -1 || !columnaIndexada[indiceColumna]) {
            System.out.println("Columna no indexada: " + nombreColumna);
            return new String[0][0];
        }

        int[] posiciones = indices[indiceColumna].buscar(valor);
        String[][] resultado = new String[posiciones.length][columnas];

        for (int i = 0; i < posiciones.length; i++) {
            System.arraycopy(datos[posiciones[i]], 0, resultado[i], 0, columnas);
        }

        return resultado;
    }

    private int obtenerIndiceColumna(String nombreColumna) {
        for (int i = 0; i < cabeceras.length; i++) {
            if (cabeceras[i].equals(nombreColumna)) {
                return i;
            }
        }
        return -1;
    }

    public boolean estaIndexada(String nombreColumna) {
        int indice = obtenerIndiceColumna(nombreColumna);
        return indice != -1 && columnaIndexada[indice];
    }

    public String[] obtenerValoresUnicos(String nombreColumna) {
        int indice = obtenerIndiceColumna(nombreColumna);
        if (indice == -1 || !columnaIndexada[indice]) {
            return new String[0];
        }
        return indices[indice].obtenerTodos();
    }

    public void imprimirDatos() {
        for (String cabecera : cabeceras) {
            System.out.printf("%-20s", cabecera);
        }
        System.out.println("\n" + "=".repeat(80));

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.printf("%-20s", datos[i][j]);
            }
            System.out.println();
        }
    }
}
