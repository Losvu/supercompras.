package Modelo;

public class Proveedores {
    private int id_proveedor;
    private String nombre;
    private String direccion;
    private String numero_telefono;
    private String correo_electronico;
    private String marca_proveedor;

    public Proveedores() {
    }

    public Proveedores(int id_proveedor, String nombre, String direccion, String numero_telefono, String correo_electronico, String marca_proveedor) {
        this.id_proveedor = id_proveedor;
        this.nombre = nombre;
        this.direccion = direccion;
        this.numero_telefono = numero_telefono;
        this.correo_electronico = correo_electronico;
        this.marca_proveedor = marca_proveedor;
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNumero_telefono() {
        return numero_telefono;
    }

    public void setNumero_telefono(String numero_telefono) {
        this.numero_telefono = numero_telefono;
    }

    public String getCorreo_electronico() {
        return correo_electronico;
    }

    public void setCorreo_electronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
    }

    public String getMarca_proveedor() {
        return marca_proveedor;
    }

    public void setMarca_proveedor(String marca_proveedor) {
        this.marca_proveedor = marca_proveedor;
    }
}