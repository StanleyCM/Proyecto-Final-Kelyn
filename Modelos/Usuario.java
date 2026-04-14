package Modelos;

// Encapsulamiento
// Abstraccion
// Builder
public class Usuario {

    // Atributos privados - Encapsulamiento
    private int    id;
    private String username;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private String password;

    // Constructor privado - solo el Builder construye instancias
    private Usuario(Builder b) {
        this.id       = b.id;
        this.username = b.username;
        this.nombre   = b.nombre;
        this.apellido = b.apellido;
        this.telefono = b.telefono;
        this.email    = b.email;
        this.password = b.password;
    }

    // Constructor protegido para subclases - Herencia
    protected Usuario(String username, String nombre, String apellido,
                      String telefono, String email, String password) {
        this.username = username;
        this.nombre   = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email    = email;
        this.password = password;
    }

    // Getters - Encapsulamiento
    public int    getId()       { return id; }
    public String getUsername() { return username; }
    public String getNombre()   { return nombre; }
    public String getApellido() { return apellido; }
    public String getTelefono() { return telefono; }
    public String getEmail()    { return email; }
    public String getPassword() { return password; }

    public static Builder builder() { return new Builder(); }

    // PATRON: Builder
    public static class Builder {
        private int    id;
        private String username;
        private String nombre;
        private String apellido;
        private String telefono;
        private String email;
        private String password;

        public Builder id(int id)           { this.id = id;           return this; }
        public Builder username(String u)   { this.username = u;      return this; }
        public Builder nombre(String n)     { this.nombre = n;        return this; }
        public Builder apellido(String a)   { this.apellido = a;      return this; }
        public Builder telefono(String t)   { this.telefono = t;      return this; }
        public Builder email(String e)      { this.email = e;         return this; }
        public Builder password(String p)   { this.password = p;      return this; }

        public Usuario build() {
            if (username == null || username.isBlank())
                throw new IllegalStateException("El username es obligatorio");
            return new Usuario(this);
        }
    }

    @Override
    public String toString() {
        return nombre + " " + apellido + " (@" + username + ")";
    }
}