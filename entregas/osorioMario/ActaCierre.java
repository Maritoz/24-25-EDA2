class ActaCierre {
    private Alumno[] alumnos = new Alumno[10];
    private int contador = 0;
    private boolean actaCerrada = false;
    private int hashOriginal = 0;

    void agregarAlumno(String nombre, String apellido, double nota) {
        if (actaCerrada) {
            System.out.println("El acta ya está cerrada. No se pueden agregar más alumnos.");
            return;
        }
        if (contador == alumnos.length) ampliar();
        alumnos[contador++] = new Alumno(nombre, apellido, nota);
    }

    private void ampliar() {
        Alumno[] nuevo = new Alumno[alumnos.length * 2];
        for (int i = 0; i < alumnos.length; i++) nuevo[i] = alumnos[i];
        alumnos = nuevo;
    }

    void cerrarActa() {
        if (actaCerrada) {
            System.out.println("El acta ya había sido cerrada.");
            return;
        }
        actaCerrada = true;
        hashOriginal = calcularHash();
        System.out.println("Acta cerrada. Hash generado: " + hashOriginal);
    }

    void verificarIntegridad() {
        if (!actaCerrada) {
            System.out.println("El acta aún no se ha cerrado.");
            return;
        }
        int hashActual = calcularHash();
        if (hashActual == hashOriginal) {
            System.out.println("Integridad comprobada. El acta no ha sido modificada.");
        } else {
            System.out.println("Atención: El acta ha sido modificada. Hash actual: " + hashActual + " | Hash original: " + hashOriginal);
        }
    }

    int calcularHash() {
        long hash = 7;
        for (int i = 0; i < contador; i++) {
            Alumno a = alumnos[i];
            String data = a.toString();
            for (int j = 0; j < data.length(); j++) {
                hash = hash * 31 + data.charAt(j);
            }
            hash = hash * 31 + (int) (a.nota * 100);
        }
        return (int) hash;
    }

    void imprimirActa() {
        System.out.println("--- Acta de Calificaciones ---");
        for (int i = 0; i < contador; i++) {
            System.out.println((i + 1) + ". " + alumnos[i]);
        }
    }
}
