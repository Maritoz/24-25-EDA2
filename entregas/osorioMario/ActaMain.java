public class ActaMain {
    public static void main(String[] args) {
        ActaCierre acta = new ActaCierre();
        java.util.Scanner sc = new java.util.Scanner(System.in);
        System.out.println("Agregar alumnos (nombre apellido nota). Escriba 'cerrar' para terminar:");
        while (true) {
            String entrada = sc.nextLine();
            if (entrada.trim().equalsIgnoreCase("cerrar")) break;
            String[] partes = entrada.split(" ");
            if (partes.length != 3) {
                System.out.println("Formato incorrecto. Intente de nuevo.");
                continue;
            }
            try {
                double nota = Double.parseDouble(partes[2]);
                acta.agregarAlumno(partes[0], partes[1], nota);
            } catch (NumberFormatException e) {
                System.out.println("Nota inválida. Intente de nuevo.");
            }
        }
        acta.cerrarActa();
        acta.imprimirActa();
        acta.verificarIntegridad();
    }
}
