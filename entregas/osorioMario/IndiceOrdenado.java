public class IndiceOrdenado extends Indice {

    public IndiceOrdenado(int capacidadMaxima) {
        super(capacidadMaxima);
    }

    @Override
    public void agregar(String valor, int posicion) {
        int indiceValor = -1;
        for (int i = 0; i < cantidadValores; i++) {
            if (valores[i].equals(valor)) {
                indiceValor = i;
                break;
            }
        }

        if (indiceValor == -1) {
            valores[cantidadValores] = valor;
            indiceValor = cantidadValores;
            cantidadValores++;
        }

        posiciones[indiceValor][contadores[indiceValor]] = posicion;
        contadores[indiceValor]++;
        ordenar();
    }

    private void ordenar() {
        for (int i = 1; i < cantidadValores; i++) {
            String key = valores[i];
            int[] posKey = posiciones[i];
            int countKey = contadores[i];
            int j = i - 1;

            while (j >= 0 && valores[j].compareTo(key) > 0) {
                valores[j + 1] = valores[j];
                posiciones[j + 1] = posiciones[j];
                contadores[j + 1] = contadores[j];
                j--;
            }
            valores[j + 1] = key;
            posiciones[j + 1] = posKey;
            contadores[j + 1] = countKey;
        }
    }

    @Override
    public int[] buscar(String valor) {
        int inicio = 0, fin = cantidadValores - 1;

        while (inicio <= fin) {
            int medio = (inicio + fin) / 2;
            int cmp = valores[medio].compareTo(valor);
            if (cmp == 0) {
                int[] resultado = new int[contadores[medio]];
                System.arraycopy(posiciones[medio], 0, resultado, 0, contadores[medio]);
                return resultado;
            } else if (cmp < 0) {
                inicio = medio + 1;
            } else {
                fin = medio - 1;
            }
        }
        return new int[0];
    }

    @Override
    public boolean contiene(String valor) {
        return buscar(valor).length > 0;
    }

    @Override
    public String[] obtenerTodos() {
        String[] resultado = new String[cantidadValores];
        System.arraycopy(valores, 0, resultado, 0, cantidadValores);
        return resultado;
    }
}
