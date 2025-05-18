public class Alumno {
    String nombre;
    String apellido;
    double nota;

    Alumno(String nombre, String apellido, double nota) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.nota = nota;
    }

    @Override
    public String toString() {
        return nombre + " " + apellido + " " + nota;
    }
}
