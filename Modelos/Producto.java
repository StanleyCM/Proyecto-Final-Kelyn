package Modelos;

// Encapsulamiento
// Abstraccion
// Builder
public class Producto {

    // Atributos privados - Encapsulamiento
    private int    id;
    private String nombre;
    private String marca;
    private String categoria;
    private double precio;
    private int    cantidadDisponible;

    // Constructor privado - solo el Builder construye instancias
    private Producto(Builder b) {
        this.id        = b.id;
        this.nombre    = b.nombre;
        this.marca     = b.marca;
        this.categoria = b.categoria;
        this.precio    = b.precio;
        this.cantidadDisponible = b.cantidadDisponible;
    }

    // Getters - Encapsulamiento
    public int    getId()        { return id; }
    public String getNombre()    { return nombre; }
    public String getMarca()     { return marca; }
    public String getCategoria() { return categoria; }
    public double getPrecio()    { return precio; }
    public int    getCantidadDisponible() { return cantidadDisponible; }
    public int    getStock()     { return cantidadDisponible; }

    public static Builder builder() { return new Builder(); }

    // PATRON: Builder
    public static class Builder {
        private int    id;
        private String nombre;
        private String marca;
        private String categoria;
        private double precio;
        private int    cantidadDisponible;

        public Builder id(int id)          { this.id = id;        return this; }
        public Builder nombre(String n)    { this.nombre = n;     return this; }
        public Builder marca(String m)     { this.marca = m;      return this; }
        public Builder categoria(String c) { this.categoria = c;  return this; }
        public Builder precio(double p)    { this.precio = p;     return this; }
        public Builder cantidadDisponible(int c) { this.cantidadDisponible = c; return this; }
        public Builder stock(int s)        { this.cantidadDisponible = s; return this; }

        public Producto build() {
            if (nombre == null || nombre.isBlank())
                throw new IllegalStateException("El nombre es obligatorio");
            return new Producto(this);
        }
    }

    @Override
    public String toString() { return nombre + " (" + marca + ")"; }
}
