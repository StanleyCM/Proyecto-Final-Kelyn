package Modelos;

// Herencia - extiende Usuario
// Polimorfismo - sobreescribe toString()
public class UsuarioRegistrado extends Usuario {

    private String fechaRegistro;

    // Herencia - llama al constructor de la clase padre
    public UsuarioRegistrado(String username, String nombre, String apellido,
                             String telefono, String email,
                             String password, String fechaRegistro) {
        super(username, nombre, apellido, telefono, email, password);
        this.fechaRegistro = fechaRegistro;
    }

    public String getFechaRegistro()             { return fechaRegistro; }
    public void   setFechaRegistro(String fecha) { this.fechaRegistro = fecha; }

    // Polimorfismo - sobreescribe toString() de Usuario
    @Override
    public String toString() {
        return super.toString() + " (Registrado el: " + fechaRegistro + ")";
    }
}
