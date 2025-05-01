public class IndiceNormal extends Indice {

    public IndiceNormal(int capacidadMaxima) {
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
    }

    @Override
    public int[] buscar(String valor) {
        for (int i = 0; i < cantidadValores; i++) {
            if (valores[i].equals(valor)) {
                int[] resultado = new int[contadores[i]];
                System.arraycopy(posiciones[i], 0, resultado, 0, contadores[i]);
                return resultado;
            }
        }
        return new int[0];
    }

    @Override
    public boolean contiene(String valor) {
        for (int i = 0; i < cantidadValores; i++) {
            if (valores[i].equals(valor)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String[] obtenerTodos() {
        String[] resultado = new String[cantidadValores];
        System.arraycopy(valores, 0, resultado, 0, cantidadValores);
        return resultado;
    }
}
